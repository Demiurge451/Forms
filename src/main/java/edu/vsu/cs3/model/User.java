package edu.vsu.cs3.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="account")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String login;
    private String password;
    private String number;
    private String email;
}
