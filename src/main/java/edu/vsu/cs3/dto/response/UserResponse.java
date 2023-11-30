package edu.vsu.cs3.dto.response;

import lombok.Data;

@Data
public class UserResponse {
    private int id;
    private String login;
    private String password;
    private long number;
    private String email;
}
