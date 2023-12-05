package edu.vsu.cs3.service;

import edu.vsu.cs3.model.Answer;
import edu.vsu.cs3.repository.AnswerRepository;
import edu.vsu.cs3.specification.AnswerSpecification;
import edu.vsu.cs3.specification.UserSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
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

    public List<Answer> getListOfAnswers(PageRequest pageRequest) {
        return answerRepository.findAll(pageRequest).getContent();
    }

    public List<Answer> getListOfAnswers(String txt, PageRequest pageRequest) {
        return answerRepository.findAll(Specification.where(AnswerSpecification.hasTxt(txt)), pageRequest).getContent();
    }

    public Answer findById(int id) {
        return answerRepository.getReferenceById(id);
    }

    @Transactional
    public Answer save(Answer answer) {
        return answerRepository.save(answer);
    }

    @Transactional
    public void delete(int id) {
        answerRepository.delete(findById(id));
    }

    @Transactional
    public Answer update(int id, Answer updatedAnswer) {
        Answer answer = answerRepository.getReferenceById(id);
        answer.setQuestion(updatedAnswer.getQuestion());
        answer.setTxt(updatedAnswer.getTxt());
        return answerRepository.save(answer);
    }
}
