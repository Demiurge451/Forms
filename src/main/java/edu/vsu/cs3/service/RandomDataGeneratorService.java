package edu.vsu.cs3.service;

import com.github.javafaker.Faker;
import edu.vsu.cs3.dto.response.AnswerResponse;
import edu.vsu.cs3.dto.response.QuestionResponse;
import edu.vsu.cs3.model.*;
import edu.vsu.cs3.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import java.util.*;

@Service
@Transactional
public class RandomDataGeneratorService {

    private UserService userService;

    private FormService formService;

    private QuestionService questionService;

    private final AnswerService answerService;

    private final ResultService resultService;
    private final ModelMapper modelMapper;

    private final Random random = new Random();

    @Autowired
    public RandomDataGeneratorService(UserService userService, FormService formService,
                                      QuestionService questionService, AnswerService answerService,
                                      ResultService resultService, ModelMapper modelMapper) {
        this.userService = userService;
        this.formService = formService;
        this.questionService = questionService;
        this.answerService = answerService;
        this.resultService = resultService;
        this.modelMapper = modelMapper;
    }

    private final Faker faker = new Faker();

    public void generateTestData(int count) {
        List<Integer> users = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            User user = new User();
            user.setLogin(faker.name().username());
            user.setPassword(faker.internet().password());
            user.setPhone(faker.phoneNumber().cellPhone());
            user.setEmail(faker.internet().emailAddress());

            user = userService.save(user);
            users.add(user.getId());
        }
        generateForm(users, count);
    }

    public void generateForm(List<Integer> users, int count) {
        List<Integer> forms = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Form form = new Form();
            form.setTitle(faker.lorem().word());
            form.setForeword(faker.lorem().sentence());
            form.setUser(userService.findById(users.get(random.nextInt(count))));
            form = formService.save(form);
            forms.add(form.getId());
        }
        generateQuestions(forms, count);
    }

    public void generateQuestions(List<Integer> forms, int count) {
        List<Integer> questions = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Question question = new Question();
            question.setMultiplySelection(random.nextBoolean());
            question.setTxt(faker.lorem().sentence());
            question.setForm(formService.findById(forms.get(random.nextInt(count))));

            question = questionService.save(question);
            questions.add(question.getId());
        }
        generateAnswers(questions, count);
    }
    public void generateAnswers(List<Integer> questions, int count) {
        List<Integer> answers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Answer answer = new Answer();
            answer.setTxt(faker.lorem().word());
            answer.setQuestion(questionService.findById(questions.get(random.nextInt(count))));

            answer = answerService.save(answer);
            answers.add(answer.getId());
        }
        generateResults(questions, answers, count);
    }

    public void generateResults(List<Integer> questions, List<Integer> answers, int count) {
        for (int i = 0; i < count; i++) {
            Result result = new Result();
            result.setAnswer(answerService.findById(answers.get(random.nextInt(count))));
            resultService.save(result);
        }
    }
}
