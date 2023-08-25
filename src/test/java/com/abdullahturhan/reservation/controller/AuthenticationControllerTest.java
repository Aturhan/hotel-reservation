package com.abdullahturhan.reservation.controller;

import com.abdullahturhan.reservation.dto.JwtAuthenticationResponse;
import com.abdullahturhan.reservation.dto.SignInRequest;
import com.abdullahturhan.reservation.dto.SignUpRequest;
import com.abdullahturhan.reservation.enumTypes.Gender;
import com.abdullahturhan.reservation.service.AuthenticationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
//@AutoConfigureMockMvc
@ActiveProfiles(value = "integration")
class AuthenticationControllerTest {
    @MockBean
    private AuthenticationService authenticationService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void itShouldSignUp_WhenValidSignUpRequest() throws Exception {
        String mockJwtToken = "mocked-jwt-token";
        //given
        SignUpRequest request = SignUpRequest.builder()
                .firstName("John")
                .lastName("Doe")
                .password("password")
                .username("username")
                .email("email@gmail.com")
                .age(30)
                .gender(Gender.MALE)
                .build();
        JwtAuthenticationResponse response = JwtAuthenticationResponse.builder()
                .token(mockJwtToken)
                        .build();

        //when
        when(authenticationService.signUp(any(SignUpRequest.class))).thenReturn(response);

        String url ="/api/v1/auth/signup";

        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(serializeJson(request)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"token\":\"" + mockJwtToken + "\"}"));


    }

    @Test
    void itShouldSignIn_WhenValidSignInRequest() throws Exception {
        String mockJwtToken = "mocked-jwt-token";

        //given
        SignInRequest request = SignInRequest.builder()
                .username("johnDoe")
                .password("password")
                .build();

        JwtAuthenticationResponse response = JwtAuthenticationResponse.builder()
                .token(mockJwtToken)
                .build();

        //when
        when(authenticationService.signIn(any(SignInRequest.class))).thenReturn(response);

        String url ="/api/v1/auth/signin";

        mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializeJson(request)))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(content().json("{\"token\":\"" + mockJwtToken + "\"}"));



    }


    private String serializeJson(Object o) throws JsonProcessingException {
        return objectMapper.writeValueAsString(o);
    }
}