package edu.vsu.cs3.dto.response;

import edu.vsu.cs3.model.Question;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FormResponse {
    private Integer id;
    private String title;
    private String foreword;
    private List<QuestionResponse> questions;
    private Integer user_id;
}
