package edu.vsu.cs3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "forms")
public class Form {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String foreword;

    @ToString.Exclude
    @OneToMany(mappedBy="form", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Question> questions = new ArrayList<>();
}
