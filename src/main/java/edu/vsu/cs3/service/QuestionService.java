package edu.vsu.cs3.service;

import edu.vsu.cs3.dto.request.QuestionRequest;
import edu.vsu.cs3.dto.response.QuestionResponse;
import edu.vsu.cs3.model.Question;
import edu.vsu.cs3.repository.FormRepository;
import edu.vsu.cs3.repository.QuestionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class QuestionService {
    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> getListOfQuestions() {
        return questionRepository.findAll();
    }

    public Question findById(int id) {
        return questionRepository.getReferenceById(id);
    }

    @Transactional
    public void save(Question question) {
        questionRepository.save(question);
    }


    @Transactional
    public void delete(int id) {
        questionRepository.delete(findById(id));
    }
}
