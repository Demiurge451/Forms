package edu.vsu.cs3.service;

import edu.vsu.cs3.model.Question;
import edu.vsu.cs3.model.Result;
import edu.vsu.cs3.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ResultService {
    private final ResultRepository resultRepository;

    @Autowired
    public ResultService(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    public List<Result> getListOfResults() {
        return resultRepository.findAll();
    }

    public Result findById(int id) {
        return resultRepository.getReferenceById(id);
    }

    @Transactional
    public void save(Result result) {
        resultRepository.save(result);
    }


    @Transactional
    public void delete(int id) {
        resultRepository.delete(findById(id));
    }

    @Transactional
    public Result update(int id, Result updatedResult) {
        Result result = resultRepository.getReferenceById(id);
        result.setAnswer(updatedResult.getAnswer());
        return resultRepository.save(result);
    }
}
