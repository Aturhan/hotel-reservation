package com.abdullahturhan.reservation.service;

import com.abdullahturhan.reservation.dto.JwtAuthenticationResponse;
import com.abdullahturhan.reservation.dto.SignInRequest;
import com.abdullahturhan.reservation.dto.SignUpRequest;
import com.abdullahturhan.reservation.entity.Customer;
import com.abdullahturhan.reservation.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final CustomerService customerService;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public JwtAuthenticationResponse signUp(SignUpRequest request){
        var customer = Customer.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .age(request.getAge())
                .email(request.getEmail())
                .gender(request.getGender())
                .password(passwordEncoder.encode(request.getPassword()))
                .username(request.getUsername())
                .role(request.getRole())
                .build();
        customer = customerService.save(customer);
        var jwt = jwtService.generateToken(customer);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        var customer = customerRepository.findCustomerByUsername(request.getUsername())
                .orElseThrow(()->new IllegalArgumentException("Invalid username or password"));
        var jwt = jwtService.generateToken(customer);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }


}
