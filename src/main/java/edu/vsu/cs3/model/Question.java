package edu.vsu.cs3.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Boolean multiplySelection;
    private int ord;
    private String txt;

    @ManyToOne
    @JoinColumn(name="form_id")
    private Form form;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Answer> answers;
}
