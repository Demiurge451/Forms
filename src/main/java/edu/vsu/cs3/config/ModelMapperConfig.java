package edu.vsu.cs3.config;

import edu.vsu.cs3.dto.request.AnswerRequest;
import edu.vsu.cs3.dto.request.FormRequest;
import edu.vsu.cs3.dto.request.QuestionRequest;
import edu.vsu.cs3.dto.request.ResultRequest;
import edu.vsu.cs3.dto.response.*;
import edu.vsu.cs3.model.*;
import edu.vsu.cs3.repository.AnswerRepository;
import edu.vsu.cs3.repository.FormRepository;
import edu.vsu.cs3.repository.QuestionRepository;
import edu.vsu.cs3.repository.UserRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    private final FormRepository formRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;

    @Autowired
    public ModelMapperConfig(FormRepository formRepository, QuestionRepository questionRepository,
                             AnswerRepository answerRepository, UserRepository userRepository) {
        this.formRepository = formRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.userRepository = userRepository;
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);

        configureFormMappings(modelMapper);
        configureQuestionMappings(modelMapper);
        configureAnswerMappings(modelMapper);
        configureUserMappings(modelMapper);

        return modelMapper;
    }

    private void configureFormMappings(ModelMapper modelMapper) {
        Converter<Integer, Form> formConverter = context -> {
            Integer formId = context.getSource();
            return formId != null ? formRepository.getReferenceById(formId) : null;
        };

        Converter<Form, Integer> formIdConverter = context -> {
            Form form = context.getSource();
            return form != null ? form.getId() : null;
        };

        modelMapper.createTypeMap(Question.class, QuestionResponse.class)
                .addMappings(mapper -> mapper.using(formIdConverter).map(Question::getForm, QuestionResponse::setForm_id));

        modelMapper.createTypeMap(QuestionRequest.class, Question.class)
                .addMappings(mapper -> mapper.using(formConverter).map(QuestionRequest::getForm_id, Question::setForm));
    }

    private void configureQuestionMappings(ModelMapper modelMapper) {
        Converter<Integer, Question> questionConverter = context -> {
            Integer questionId = context.getSource();
            return questionId != null ? questionRepository.getReferenceById(questionId) : null;
        };

        Converter<Question, Integer> questionIdConverter = context -> {
            Question question = context.getSource();
            return question != null ? question.getId() : null;
        };

        modelMapper.createTypeMap(Answer.class, AnswerResponse.class)
                .addMappings(mapper -> mapper.using(questionIdConverter).map(Answer::getQuestion, AnswerResponse::setQuestion_id));

        modelMapper.createTypeMap(AnswerRequest.class, Answer.class)
                .addMappings(mapper -> mapper.using(questionConverter).map(AnswerRequest::getQuestion_id, Answer::setQuestion));
    }

    private void configureAnswerMappings(ModelMapper modelMapper) {
        Converter<Integer, Answer> answerConverter = context -> {
            Integer answerId = context.getSource();
            return answerId != null ? answerRepository.getReferenceById(answerId) : null;
        };
        Converter<Answer, Integer> answerIdConverter = context -> {
            Answer answer = context.getSource();
            return answer != null ? answer.getId() : null;
        };

        modelMapper.createTypeMap(ResultRequest.class, Result.class)
                .addMappings(mapper -> mapper.using(answerConverter).map(ResultRequest::getAnswer_id, Result::setAnswer));

        modelMapper.createTypeMap(Result.class, ResultResponse.class)
                .addMappings(mapper -> mapper.using(answerIdConverter).map(Result::getAnswer, ResultResponse::setAnswer_id));
    }

    private void configureUserMappings(ModelMapper modelMapper) {
        Converter<Integer, User> userConverter = context -> {
            Integer userId = context.getSource();
            return userId != null ? userRepository.getReferenceById(userId) : null;
        };

        Converter<User, Integer> userIdConverter = context -> {
            User user = context.getSource();
            return user != null ? user.getId() : null;
        };

        modelMapper.createTypeMap(FormRequest.class, Form.class)
                .addMappings(mapper -> mapper.using(userConverter).map(FormRequest::getUser_id, Form::setUser));

        modelMapper.createTypeMap(Form.class, FormResponse.class)
                .addMappings(mapper -> mapper.using(userIdConverter).map(Form::getUser, FormResponse::setUser_id));
    }
}
