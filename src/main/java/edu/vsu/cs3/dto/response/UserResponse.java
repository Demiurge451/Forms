package edu.vsu.cs3.dto.response;

import edu.vsu.cs3.model.Form;
import lombok.Data;

import java.util.List;

@Data
public class UserResponse {
    private Integer id;
    private String login;
    private String password;
    private String phone;
    private String email;
    private List<FormResponse> forms;
}
