package edu.vsu.cs3.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int order;
    private String txt;
}
