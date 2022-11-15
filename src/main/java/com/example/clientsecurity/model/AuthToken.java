package com.example.clientsecurity.model;

import lombok.Data;

import java.util.List;

@Data
public class AuthToken {
    private String access_token;
    private String token_type;
    private int expires_in;
    private String scope;
    private List<String> authorities;
    private Integer employeeId;
    private String userId;
    private Integer teamId;
    private Integer leadId;
}
