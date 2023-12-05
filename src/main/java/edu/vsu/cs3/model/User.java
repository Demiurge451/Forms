package edu.vsu.cs3.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String login;
    private String password;
    private String phone;
    private String email;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Form> forms;
}
