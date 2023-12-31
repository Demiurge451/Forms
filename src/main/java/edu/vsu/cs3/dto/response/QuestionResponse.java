package edu.vsu.cs3.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class QuestionResponse {
    private Integer id;
    private Integer ord;
    private boolean multiplySelection;
    private String txt;
    private Integer form_id;
    private List<AnswerResponse> answers;
}
