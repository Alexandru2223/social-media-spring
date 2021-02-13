package com.pibd.project1.mapper;

import com.pibd.project1.domain.entity.RegisterEntity;
import com.pibd.project1.domain.model.RegisterDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RegisterEntityToRegisterDTOMapper implements Converter<RegisterEntity, RegisterDTO> {

    @Override
    public RegisterDTO convert(RegisterEntity source) {

        return RegisterDTO.builder()
                .registerId(source.getRegisterId())
                .email(source.getEmail())
                .fullName(source.getFullName())
                .password(source.getPassword())
                .enabled(source.getEnabled())
                .token(source.getToken())
                .build();
    }
}
