package com.example.clientsecurity.model;

import lombok.Data;

@Data
public class ApprovalTeam {
    private String id;

    private String name;

    private String code;

    private String email;

    private String phone;

    private String description;

    private String address;

    private String organizationCode;

    private String branchCode;

    private String submittedApproval;
}
