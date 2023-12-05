package edu.vsu.cs3.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class AnswerResponse {
    private int id;
    private int ord;
    private String txt;
    private int question_id;
}
