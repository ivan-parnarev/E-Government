package com.egovernment.egovbackend.service;

import com.egovernment.egovbackend.domain.entity.Answer;
import com.egovernment.egovbackend.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;

    public void saveAnswer(Answer answer){
        this.answerRepository.save(answer);
    }

    public void saveAllAnswers(List<Answer> answers){
        this.answerRepository.saveAll(answers);
    }

    public Optional<Answer> getAnswerByText(String answer) {
        return this.answerRepository.findByAnswerText(answer);
    }

    public List<Answer> createAnswers(String answersString) {
        String[] answersTextArray = answersString.split("; ");

        List<Answer> answers = new ArrayList<>();

        for (String answerText : answersTextArray) {
            Optional<Answer> optionalAnswerByText = this.getAnswerByText(answerText);

            if(optionalAnswerByText.isEmpty()){
                Answer answer = Answer.builder().answerText(answerText).build();
                saveAnswer(answer);
                answers.add(answer);
            }else{
                answers.add(optionalAnswerByText.get());
            }

        }

        return answers;
    }
}
