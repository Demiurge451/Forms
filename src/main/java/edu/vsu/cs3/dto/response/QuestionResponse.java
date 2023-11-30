package edu.vsu.cs3.dto.response;

import edu.vsu.cs3.model.Form;
import lombok.Builder;
import lombok.Data;

@Data
public class QuestionResponse {
    private int id;
    private int ord;
    private String txt;
    private int form_id;
}
