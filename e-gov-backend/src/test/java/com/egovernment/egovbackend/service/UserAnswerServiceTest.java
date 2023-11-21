package com.egovernment.egovbackend.service;

import com.egovernment.egovbackend.domain.dto.CensusDTO;
import com.egovernment.egovbackend.domain.dto.censusCampaignDTO.UserAnswerDTO;
import com.egovernment.egovbackend.domain.entity.Campaign;
import com.egovernment.egovbackend.domain.entity.User;
import com.egovernment.egovbackend.domain.entity.UserAnswer;
import com.egovernment.egovbackend.repository.UserAnswerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

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

    @InjectMocks
    private UserAnswerService userAnswerService;

    @Test
    void saveUserCensusDataUserAnswersSaved() {
        UserAnswerDTO userAnswerDTO = UserAnswerDTO.builder()
                .answer(TEST_FIRSTNAME_ANSWER)
                .build();

        List<UserAnswerDTO> answersFromDto = List.of(userAnswerDTO);

        CensusDTO censusDTO = CensusDTO.builder()
                .userPin(TEST_USER_PIN)
                .censusAnswers(answersFromDto)
                .build();

        User user = User.builder()
                .PIN(TEST_USER_PIN)
                .build();

        Campaign campaign = Campaign.builder()
                .title(TEST_CAMPAIGN_TITLE)
                .isActive(true)
                .build();

        UserAnswer userAnswer = UserAnswer.builder()
                .user(user)
                .campaign(campaign)
                .answer(TEST_FIRSTNAME_ANSWER)
                .build();

        when(userService.getUserByPin(any())).thenReturn(Optional.of(user));
        when(campaignService.getCampaignById(any())).thenReturn(Optional.of(campaign));

        userAnswerService.saveUserCensusData(censusDTO);

        verify(userAnswerRepository, times(1)).saveAll(List.of(userAnswer));
    }
}
