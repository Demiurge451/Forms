package edu.vsu.cs3.dto.request;

import lombok.Data;

@Data
public class AnswerRequest {
    private Integer ord;
    private String txt;
    private Integer question_id;
}
