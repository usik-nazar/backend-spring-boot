package com.nazar.backendspringboot.service;

import com.nazar.backendspringboot.dao.user.UserEntity;
import com.nazar.backendspringboot.dao.user.UserRepo;
import com.nazar.backendspringboot.dto.AuthRequest;
import com.nazar.backendspringboot.exception.IncorrectLoginOrPasswordException;
import com.nazar.backendspringboot.security.TokenGenerator;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthWebService {

    private final UserRepo userRepo;
    private final BCryptPasswordEncoder encoder;

    public void signUp(AuthRequest request) {
        Optional<UserEntity> byId = userRepo.findById(request.getLogin());

        if (byId.isPresent())
            throw new RuntimeException("User already exists");

        userRepo.save(new UserEntity(request.getLogin(), encoder.encode(request.getPassword())));
    }

    public String signIn(AuthRequest request) {
        Optional<UserEntity> byId = userRepo.findById(request.getLogin());

        if (byId.isEmpty())
            throw new IncorrectLoginOrPasswordException();

        if (!encoder.matches(request.getPassword(), byId.get().getPassword()))
            throw new IncorrectLoginOrPasswordException();

        return TokenGenerator.generate(byId.get());
    }
}
