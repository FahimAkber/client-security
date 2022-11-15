package com.example.clientsecurity.model;

import lombok.Data;

import java.util.List;

@Data
public class UserSession {
    private String jsessionId;

    private String csrfToken;

    private int saveRequestCount;

    private String oauthToken;

    private String refreshToken;

    private Long employeeId;

    private String userId;

    private Long leadId;

    private Long teamId;

    private List<String> authorities;

    //for requisition permission if team lead then set the team ids
    private List<String> whichTeamLeadIds;

    //for requisition permission if project director then set the project ids
    private List<String> whichProjectDirectorIds;

    private List<ApprovalTeam> permissionList;

    private String userType;

    private String userName;

    private String profilePic;

    private boolean onlineCheckIn;

    private String lat;

    private String lon;

    private String orgCode;

    private String branchCode;

    private String designation;

    private String teamName;

    private String initiator;
}
