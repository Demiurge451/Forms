package edu.vsu.cs3.config;

import edu.vsu.cs3.dto.request.AnswerRequest;
import edu.vsu.cs3.dto.request.FormRequest;
import edu.vsu.cs3.dto.request.QuestionRequest;
import edu.vsu.cs3.dto.response.AnswerResponse;
import edu.vsu.cs3.dto.response.FormResponse;
import edu.vsu.cs3.dto.response.QuestionResponse;
import edu.vsu.cs3.model.Answer;
import edu.vsu.cs3.model.Form;
import edu.vsu.cs3.model.Question;
import edu.vsu.cs3.repository.FormRepository;
import edu.vsu.cs3.repository.QuestionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Collectors;

@Configuration
public class ModelMapperConfig {
    private final FormRepository formRepository;
    private final QuestionRepository questionRepository;

    @Autowired
    public ModelMapperConfig(FormRepository formRepository, QuestionRepository questionRepository) {
        this.formRepository = formRepository;
        this.questionRepository = questionRepository;
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        Converter<Form, Integer> formIdConverter = context -> context.getSource().getId();
        Converter<Integer, Form> formConverter = context -> formRepository.getReferenceById(context.getSource());

        Converter<Question, Integer> questionIdConverter = context -> context.getSource().getId();
        Converter<Integer, Question> questionConverter = context -> questionRepository.getReferenceById(context.getSource());

        modelMapper.createTypeMap(Question.class, QuestionResponse.class).addMappings(
                mapper -> mapper.using(formIdConverter).map(Question::getForm, QuestionResponse::setForm_id)
        );

        modelMapper.createTypeMap(QuestionRequest.class, Question.class).addMappings(
                mapper -> mapper.using(formConverter).map(QuestionRequest::getForm_id, Question::setForm)
        );

        modelMapper.createTypeMap(Answer.class, AnswerResponse.class).addMappings(
                mapper -> mapper.using(questionIdConverter).map(Answer::getQuestion, AnswerResponse::setQuestion_id)
        );

        modelMapper.createTypeMap(AnswerRequest.class, Answer.class).addMappings(
                mapper -> mapper.using(questionConverter).map(AnswerRequest::getQuestion_id, Answer::setQuestion)
        );

        return modelMapper;
    }
}
