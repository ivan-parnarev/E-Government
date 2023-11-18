package com.egovernment.egovbackend.service;

import com.egovernment.egovbackend.domain.dto.CensusDTO;
import com.egovernment.egovbackend.domain.dto.censusCampaignDTO.UserAnswerDTO;
import com.egovernment.egovbackend.domain.entity.Campaign;
import com.egovernment.egovbackend.domain.entity.User;
import com.egovernment.egovbackend.domain.entity.UserAnswer;
import com.egovernment.egovbackend.repository.UserAnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserAnswerService {

    private final UserAnswerRepository userAnswerRepository;
    private final CensusQuestionService censusQuestionService;
    private final UserService userService;
    private final CampaignService campaignService;

    public boolean hasUserCensusedInCampaign(String userPin, Long campaignId){
        return this.userAnswerRepository.censusExistsByUserPinAndCampaignId(userPin, campaignId);
    }

    public void saveUserCensusData(CensusDTO censusDTO) {
        Optional<User> optUser = this.userService.getUserByPin(censusDTO.getUserPin());

        User censusUser;

        if (optUser.isPresent()) {
            censusUser = optUser.get();
        } else {
            censusUser = this.userService.createUserWithUserPin(censusDTO.getUserPin());
        }

        Campaign campaign = this.campaignService.getCampaignById(censusDTO.getCampaignId()).get();

        List<UserAnswerDTO> answers = censusDTO.getCensusAnswers();

        List<UserAnswer> collect = answers.stream().map(userAnswerDTO -> {
            return UserAnswer.builder()
                    .user(censusUser)
                    .answer(userAnswerDTO.getAnswer())
                    .campaign(campaign)
                    .questionCategory(userAnswerDTO.getQuestionCategory())
                    .censusQuestion(this.censusQuestionService.getQuestionById(userAnswerDTO.getQuestionId()))
                    .build();
        }).collect(Collectors.toList());

        this.userAnswerRepository.saveAll(collect);
    }

}
