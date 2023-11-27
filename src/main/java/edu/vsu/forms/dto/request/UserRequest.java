package edu.vsu.forms.dto.request;

import lombok.Data;

@Data
public class UserRequest {
    private long id;
    private String login;
    private String password;
    private String number;
    private String email;
}
