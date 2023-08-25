package com.abdullahturhan.reservation.controller;

import com.abdullahturhan.reservation.dto.JwtAuthenticationResponse;
import com.abdullahturhan.reservation.dto.SignInRequest;
import com.abdullahturhan.reservation.dto.SignUpRequest;
import com.abdullahturhan.reservation.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signUp( @RequestBody SignUpRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(authenticationService.signUp(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signIn(@RequestBody SignInRequest request){
        return ResponseEntity.status(HttpStatus.OK)
                .body(authenticationService.signIn(request));

    }
}
