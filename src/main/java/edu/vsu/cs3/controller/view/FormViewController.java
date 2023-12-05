package edu.vsu.cs3.controller.view;

import edu.vsu.cs3.dto.request.FormRequest;
import edu.vsu.cs3.model.Form;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/formView")
public class FormViewController {
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("form", new Form());
        return "newForm";
    }
}
