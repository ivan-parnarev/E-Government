package com.egovernment.egovbackend.service;

import com.egovernment.egovbackend.domain.dto.censusCampaign.CensusDTO;
import com.egovernment.egovbackend.domain.dto.censusCampaign.UserAnswerDTO;
import com.egovernment.egovbackend.domain.entity.*;
import com.egovernment.egovbackend.domain.enums.QuestionCategory;
import com.egovernment.egovbackend.repository.UserAnswerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserAnswerServiceTest {

    private static final String TEST_USER_PIN = "8888888888";
    private static final String TEST_CAMPAIGN_TITLE = "Test Campaign title";
    private static final String TEST_FIRSTNAME_ANSWER = "George";

    @Mock
    private UserAnswerRepository userAnswerRepository;

    @Mock
    private UserService userService;

    @Mock
    private CampaignService campaignService;
    @Mock
    private AnswerService answerService;

    @InjectMocks
    private UserAnswerService userAnswerService;

    private final String USER_PIN = "1234567890";
    private final String ANSWER_TEXT = "Answer Test";
    private final String QUESTION_TEXT = "Question Test";
    private final Long ID = 1L;

    @Test
    void hasUserCensusedInCampaignTest() {
        when(userAnswerRepository.censusExistsByUserPinAndCampaignId(USER_PIN, ID)).thenReturn(true);

        boolean result = userAnswerService.hasUserCensusedInCampaign(USER_PIN, ID);

        assertTrue(result);
        verify(userAnswerRepository).censusExistsByUserPinAndCampaignId(USER_PIN, ID);
    }

    @Test
    void saveUserCensusDataTest() {

        UserAnswerDTO userAnswerDTO = UserAnswerDTO.builder()
                .questionText(QUESTION_TEXT)
                .answer(ANSWER_TEXT)
                .questionCategory(QuestionCategory.PERSONAL)
                .build();

        CensusDTO censusDTO = CensusDTO.builder()
                .userPin(USER_PIN)
                .campaignId(ID)
                .censusAnswers(Arrays.asList(userAnswerDTO))
                .build();

        User user = new User();
        Campaign campaign = new Campaign();
        Answer answer = new Answer();
        answer.setAnswerText(ANSWER_TEXT);
        CensusQuestion censusQuestion = new CensusQuestion();

        when(userService.getUserByPin(USER_PIN)).thenReturn(Optional.of(user));
        when(campaignService.getCampaignById(ID)).thenReturn(Optional.of(campaign));
        when(answerService.getAnswerByText(ANSWER_TEXT)).thenReturn(Optional.of(answer));
        when(censusQuestionService.getQuestionByText(QUESTION_TEXT)).thenReturn(Optional.of(censusQuestion));

        userAnswerService.saveUserCensusData(censusDTO);

        verify(userAnswerRepository).saveAll(any(List.class));
        verify(userService, times(1)).getUserByPin(USER_PIN);
        verify(campaignService, times(1)).getCampaignById(ID);
        verify(answerService, times(1)).getAnswerByText(ANSWER_TEXT);
        verify(censusQuestionService, times(1)).getQuestionByText(QUESTION_TEXT);
    }

}
