package edu.vsu.forms.dto.response;

import lombok.Data;

@Data
public class UserResponse {
    private long id;
    private String login;
    private String password;
    private long number;
    private String email;
}
