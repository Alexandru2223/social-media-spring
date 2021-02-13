package com.pibd.project1.service;

import com.pibd.project1.domain.entity.RegisterEntity;
import com.pibd.project1.domain.entity.RoleEntity;
import com.pibd.project1.domain.model.RegisterDTO;
import com.pibd.project1.mapper.RegisterDTOToRegisterEntityMapper;
import com.pibd.project1.mapper.RegisterEntityToRegisterDTOMapper;
import com.pibd.project1.repository.RegisterRepository;
import com.pibd.project1.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.SecureRandom;
import java.util.Base64;

@Service
@AllArgsConstructor
public class RegisterService {

    private final RegisterDTOToRegisterEntityMapper registerDTOToRegisterEntityMapper;

    private final RegisterEntityToRegisterDTOMapper registerEntityToRegisterDTOMapper;

    private final RegisterRepository repository;

    private final BCryptPasswordEncoder encoder;

    private final EmailService emailService;

    private final RoleRepository roleRepository;

    private final SecureRandom secureRandom = new SecureRandom();
    private final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

    public RegisterDTO create(RegisterDTO registerDTO) {

        RegisterEntity registerEntity = registerDTOToRegisterEntityMapper.convert(registerDTO);
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        String token = base64Encoder.encodeToString(randomBytes);
        registerEntity.setEnabled(0);
        registerEntity.setToken(token);
        registerEntity.setPassword(encoder.encode(registerDTO.getPassword()));
        RegisterEntity save = repository.save(registerEntity);

        String subject = save.getFullName() + " welcome to PIBD Project X";
        String body = "Please enter this token: " + token + " to validate your account";
        emailService.sendSimpleMessage(save.getEmail(), "pibd@etti.upb", subject, body);

        return registerEntityToRegisterDTOMapper.convert(save);
    }

    @Transactional
    public void validateAccount(String token) throws Exception{

        RegisterEntity registerEntityByToken = repository.findRegisterEntityByToken(token);
        if(registerEntityByToken == null){
            throw new Exception("Invalid token");
        }
        registerEntityByToken.setEnabled(1);
        RoleEntity roleEntity = RoleEntity.builder()
                .email(registerEntityByToken.getEmail())
                .authority("ROLE_USER")
                .build();
        repository.save(registerEntityByToken);
        roleRepository.save(roleEntity);
    }
}
