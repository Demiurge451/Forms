package edu.vsu.cs3.dto.request;

import lombok.Data;

@Data
public class QuestionRequest {
    private int ord;
    private String txt;
    private int form_id;
}
