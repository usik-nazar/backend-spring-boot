package com.nazar.backendspringboot.service;

import com.nazar.backendspringboot.dto.AuthRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthWebService {

    public void signUp(AuthRequest request) {

    }

    public String signIn(AuthRequest request) {
        return null;
    }
}
