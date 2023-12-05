package edu.vsu.cs3.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "answers")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int ord;
    private String txt;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
}
