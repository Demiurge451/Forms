package edu.vsu.cs3.dto.request;

import edu.vsu.cs3.model.Answer;
import edu.vsu.cs3.model.Question;
import lombok.Data;

import java.util.List;

@Data
public class FormRequest {
    private String foreword;
    private Integer user_id;
}
