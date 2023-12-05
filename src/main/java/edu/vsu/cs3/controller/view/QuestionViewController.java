package edu.vsu.cs3.controller.view;

import edu.vsu.cs3.dto.request.QuestionRequest;
import edu.vsu.cs3.model.Question;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/questionView")
public class QuestionViewController {
    @GetMapping("/new")
    public String createQuestion(Model model) {
        model.addAttribute("question",new Question());
        return "newQuestion";
    }
}
