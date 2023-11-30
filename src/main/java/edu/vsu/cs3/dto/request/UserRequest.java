package edu.vsu.cs3.dto.request;

import lombok.Data;

@Data
public class UserRequest {
    private String login;
    private String password;
    private String number;
    private String email;
}
