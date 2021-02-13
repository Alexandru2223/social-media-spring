package com.pibd.project1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.JdbcUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@Order(SecurityProperties.BASIC_AUTH_ORDER)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {

        JdbcUserDetailsManagerConfigurer<AuthenticationManagerBuilder> user =
                auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select email,password, enabled "
                        + "from register "
                        + "where email = ?")
                        .authoritiesByUsernameQuery("select user_email,authority "
                                + "from authorities "
                                + "where user_email = ?")
                .passwordEncoder(bCryptPasswordEncoder);

    }


    @Override
    protected void configure(HttpSecurity httpSecurity)
            throws Exception {
        httpSecurity.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .permitAll()
                .antMatchers("/artist/create")
                .permitAll()
                .antMatchers("/register/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }


}
