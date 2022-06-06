package com.nazar.backendspringboot.controller;

import com.nazar.backendspringboot.dto.AuthRequest;
import com.nazar.backendspringboot.service.AuthWebService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static java.util.Collections.singletonMap;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("auth")
public class AuthController {

    private final AuthWebService service;

    @RequestMapping("sign-up")
    public ResponseEntity<?> signUp(@RequestBody AuthRequest request) {
        service.signUp(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping("sign-in")
    public Map<String, String> signIn(@RequestBody AuthRequest request) {
        return singletonMap("token", service.signIn(request));
    }
}

