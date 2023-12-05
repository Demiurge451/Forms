package edu.vsu.cs3.dto.request;

import lombok.Data;

@Data
public class AnswerRequest {
    private int ord;
    private String txt;
    private int question_id;
}
