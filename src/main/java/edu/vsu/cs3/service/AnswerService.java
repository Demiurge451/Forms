package edu.vsu.cs3.service;

import edu.vsu.cs3.model.Answer;
import edu.vsu.cs3.model.Form;
import edu.vsu.cs3.repository.AnswerRepository;
import edu.vsu.cs3.repository.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class AnswerService {
    private final AnswerRepository answerRepository;

    @Autowired
    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public List<Answer> getListOfAnswers() {
        return answerRepository.findAll();
    }

    public Answer findById(int id) {
        return answerRepository.getReferenceById(id);
    }

    @Transactional
    public void save(Answer answer) {
        answerRepository.save(answer);
    }

    @Transactional
    public void delete(int id) {
        answerRepository.delete(findById(id));
    }
}
