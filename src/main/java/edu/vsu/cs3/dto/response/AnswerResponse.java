package edu.vsu.cs3.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class AnswerResponse {
    private Integer id;
    private Integer ord;
    private String txt;
    private Integer question_id;
}
