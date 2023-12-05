package edu.vsu.cs3.dto.request;

import edu.vsu.cs3.dto.response.AnswerResponse;
import edu.vsu.cs3.dto.response.QuestionResponse;
import lombok.Data;

import java.util.List;

@Data
public class QuestionRequest {
    private int ord;
    private String txt;
    private String selection;
    private int form_id;
}
