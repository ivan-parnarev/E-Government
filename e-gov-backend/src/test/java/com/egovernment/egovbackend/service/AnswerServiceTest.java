package com.egovernment.egovbackend.service;

import com.egovernment.egovbackend.domain.entity.Answer;
import com.egovernment.egovbackend.repository.AnswerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)
public class AnswerServiceTest {

    @Mock
    private AnswerRepository answerRepository;

    @InjectMocks
    private AnswerService answerService;
    private final String FIRST_ANSWER_TEXT = "First Answer Test";
    private final String SECOND_ANSWER_TEXT = "Second Answer Test";
    private final Answer ANSWER = Answer.builder().answerText(FIRST_ANSWER_TEXT).build();

    @Test
    void saveAnswer() {
        answerService.saveAnswer(ANSWER);
        verify(answerRepository).save(ANSWER);
    }

    @Test
    void saveAllAnswers() {
        List<Answer> answers = List.of(new Answer(), new Answer());
        answerService.saveAllAnswers(answers);
        verify(answerRepository).saveAll(answers);
    }

    @Test
    void getAnswerByTextExistingAnswer() {

        when(answerRepository.findByAnswerText(FIRST_ANSWER_TEXT)).thenReturn(Optional.of(ANSWER));

        Optional<Answer> result = answerService.getAnswerByText(FIRST_ANSWER_TEXT);

        assertTrue(result.isPresent());
        assertEquals(ANSWER, result.get());
        assertEquals(ANSWER.getAnswerText(), result.get().getAnswerText());
    }

    @Test
    void getAnswerByTextNoSuchAnswer() {
        when(answerRepository.findByAnswerText(FIRST_ANSWER_TEXT)).thenReturn(Optional.empty());
        Optional<Answer> result = answerService.getAnswerByText(FIRST_ANSWER_TEXT);
        assertFalse(result.isPresent());
    }

    @Test
    void createAnswers_NewAndExistingAnswers() {
        String answersString = FIRST_ANSWER_TEXT + "; " + SECOND_ANSWER_TEXT;
        Answer existingAnswer = new Answer();
        when(answerRepository.findByAnswerText(FIRST_ANSWER_TEXT)).thenReturn(Optional.of(existingAnswer));
        when(answerRepository.findByAnswerText(SECOND_ANSWER_TEXT)).thenReturn(Optional.empty());

        List<Answer> result = answerService.createAnswers(answersString);

        assertEquals(2, result.size());
        assertEquals(existingAnswer.getAnswerText(), result.get(0).getAnswerText());
        assertEquals(SECOND_ANSWER_TEXT, result.get(1).getAnswerText());
    }
}
