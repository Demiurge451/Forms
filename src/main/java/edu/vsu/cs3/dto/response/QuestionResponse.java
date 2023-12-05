package edu.vsu.cs3.dto.response;

import edu.vsu.cs3.model.Form;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class QuestionResponse {
    private int id;
    private int ord;
    private String selection;
    private String txt;
    private int form_id;
    private List<AnswerResponse> answers;
}
