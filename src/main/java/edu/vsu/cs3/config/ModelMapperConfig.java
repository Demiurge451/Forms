package edu.vsu.cs3.config;

import edu.vsu.cs3.dto.request.FormRequest;
import edu.vsu.cs3.dto.request.QuestionRequest;
import edu.vsu.cs3.dto.response.FormResponse;
import edu.vsu.cs3.dto.response.QuestionResponse;
import edu.vsu.cs3.model.Form;
import edu.vsu.cs3.model.Question;
import edu.vsu.cs3.repository.FormRepository;
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

    @Autowired
    public ModelMapperConfig(FormRepository formRepository) {
        this.formRepository = formRepository;
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        Converter<Form, Integer> formIdConverter = context -> context.getSource().getId();
        Converter<Integer, Form> formConverter = context -> formRepository.getReferenceById(context.getSource());


        modelMapper.createTypeMap(Question.class, QuestionResponse.class).addMappings(
                mapper -> mapper.using(formIdConverter).map(Question::getForm, QuestionResponse::setForm_id)
        );

        modelMapper.createTypeMap(QuestionRequest.class, Question.class).addMappings(
                mapper -> mapper.using(formConverter).map(QuestionRequest::getForm_id, Question::setForm)
        );

        return modelMapper;
    }
}
