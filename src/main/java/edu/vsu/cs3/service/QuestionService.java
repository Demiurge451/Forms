package edu.vsu.cs3.service;

import edu.vsu.cs3.model.Question;
import edu.vsu.cs3.model.Result;
import edu.vsu.cs3.repository.QuestionRepository;
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

    @Transactional
    public Question update(int id, Question updatedQuestion) {
        Question question = questionRepository.getReferenceById(id);
        question.setOrd(updatedQuestion.getOrd());
        question.setTxt(updatedQuestion.getTxt());
        question.setForm(updatedQuestion.getForm());
        question.setMultiplySelection(updatedQuestion.getMultiplySelection());
        return questionRepository.save(question);
    }
}
