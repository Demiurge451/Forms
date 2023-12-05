package edu.vsu.cs3.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String selection;
    private int ord;
    private String txt;

    @ManyToOne
    @JoinColumn(name="form_id")
    private Form form;

    @ToString.Exclude
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Answer> answers;
}
