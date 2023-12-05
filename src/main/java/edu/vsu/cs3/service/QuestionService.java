package edu.vsu.cs3.service;

import edu.vsu.cs3.model.Question;
import edu.vsu.cs3.repository.QuestionRepository;
import edu.vsu.cs3.specification.AnswerSpecification;
import edu.vsu.cs3.specification.QuestionSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
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

    public List<Question> getListOfQuestions(PageRequest pageRequest) {
        return questionRepository.findAll(pageRequest).getContent();
    }

    public List<Question> getListOfQuestions(String txt, PageRequest pageRequest) {
        return questionRepository.findAll(Specification.where(QuestionSpecification.hasTxt(txt)), pageRequest).getContent();
    }
    public Question findById(int id) {
        return questionRepository.getReferenceById(id);
    }

    @Transactional
    public Question save(Question question) {
        return questionRepository.save(question);
    }


    @Transactional
    public void delete(int id) {
        questionRepository.delete(findById(id));
    }

    @Transactional
    public Question update(int id, Question updatedQuestion) {
        Question question = questionRepository.getReferenceById(id);
        question.setTxt(updatedQuestion.getTxt());
        question.setForm(updatedQuestion.getForm());
        question.setMultiplySelection(updatedQuestion.getMultiplySelection());
        return questionRepository.save(question);
    }
}
