package com.pibd.project1.mapper;

import com.pibd.project1.domain.entity.RegisterEntity;
import com.pibd.project1.domain.model.RegisterDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RegisterDTOToRegisterEntityMapper implements Converter<RegisterDTO, RegisterEntity> {

    @Override
    public RegisterEntity convert(RegisterDTO source) {

        return RegisterEntity.builder()
                .registerId(source.getRegisterId())
                .email(source.getEmail())
                .fullName(source.getFullName())
                .password(source.getPassword())
                .enabled(source.getEnabled())
                .token(source.getToken())
                .build();
    }
}
