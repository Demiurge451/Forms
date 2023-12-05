package edu.vsu.cs3.dto.request;

import lombok.Data;

@Data
public class QuestionRequest {
    private String txt;
    private boolean multiplySelection;
    private Integer form_id;
}
