package edu.vsu.cs3.dto.response;

import edu.vsu.cs3.model.Question;
import lombok.Data;

import java.util.List;

@Data
public class ResultResponse {
    private Integer id;
    private Integer answer_id;
}
