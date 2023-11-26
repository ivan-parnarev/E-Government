package com.egovernment.egovbackend.service;

import com.egovernment.egovbackend.domain.dto.censusCampaign.CensusDTO;
import com.egovernment.egovbackend.domain.dto.censusCampaign.UserAnswerDTO;
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

    @Mock
    private UserAnswerRepository userAnswerRepository;

    @Mock
    private UserService userService;

    @Mock
    private CensusQuestionService censusQuestionService;

    @Mock
    private CampaignService campaignService;

    @InjectMocks
    private UserAnswerService userAnswerService;

//    @Test
//    void saveUserCensusDataUserAnswersSaved() {
//        UserAnswerDTO userAnswerDTO = UserAnswerDTO.builder()
//                .answer("George")
//                .build();
//
//        List<UserAnswerDTO> answersFromDto = List.of(userAnswerDTO);
//
//        CensusDTO censusDTO = CensusDTO.builder()
//                .userPin("8888888888")
//                .censusAnswers(answersFromDto)
//                .build();
//
//        User user = User.builder()
//                .PIN("8888888888")
//                .build();
//
//        Campaign campaign = Campaign.builder()
//                .title("Test Campaign title")
//                .isActive(true)
//                .build();
//
//        UserAnswer userAnswer = UserAnswer.builder()
//                .user(user)
//                .campaign(campaign)
//                .answer("George")
//                .build();
//
//        when(userService.getUserByPin(any())).thenReturn(Optional.of(user));
//        when(campaignService.getCampaignById(any())).thenReturn(Optional.of(campaign));
//
//        userAnswerService.saveUserCensusData(censusDTO);
//
//        verify(userAnswerRepository, times(1)).saveAll(List.of(userAnswer));
//    }
}
