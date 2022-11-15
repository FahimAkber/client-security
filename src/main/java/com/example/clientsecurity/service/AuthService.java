package com.example.clientsecurity.service;

import com.example.clientsecurity.model.AuthToken;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<AuthToken> getAuthentication(String userName, String password);
    HttpHeaders getHeadersWithClientCredentials();
}
