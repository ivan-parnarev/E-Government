package com.egovernment.egovbackend.service;

import com.egovernment.egovbackend.domain.dto.censusCampaign.CensusDTO;
import com.egovernment.egovbackend.domain.dto.censusCampaign.UserAnswerDTO;
import com.egovernment.egovbackend.domain.entity.Answer;
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
    private final AnswerService answerService;

    public boolean hasUserCensusedInCampaign(String userPin, Long campaignId) {
        return this.userAnswerRepository.censusExistsByUserPinAndCampaignId(userPin, campaignId);
    }

    public void saveUserCensusData(CensusDTO censusDTO) {
        Optional<User> optUser = this.userService.getUserByPin(censusDTO.getUserPin());
        Campaign campaign = this.campaignService.getCampaignById(censusDTO.getCampaignId()).get();

        User censusUser;

        if (optUser.isPresent()) {
            censusUser = optUser.get();
        } else {
            censusUser = this.userService.createUserWithUserPin(censusDTO.getUserPin());
        }

        List<UserAnswerDTO> answersFromDto = censusDTO.getCensusAnswers();

        List<UserAnswer> answers = answersFromDto.stream().map(userAnswerDTO -> UserAnswer.builder()
                .user(censusUser)
                .answer(mapStringTextAnswerToAnswerClass(userAnswerDTO.getAnswer()))
                .campaign(campaign)
                .questionCategory(userAnswerDTO.getQuestionCategory())
                .censusQuestion(this.censusQuestionService.getQuestionByText(userAnswerDTO.getQuestionText()).get())
                .build()).collect(Collectors.toList());

        this.userAnswerRepository.saveAll(answers);
    }

    private Answer mapStringTextAnswerToAnswerClass(String answerText) {
        Optional<Answer> optionalAnswer = this.answerService.getAnswerByText(answerText);
        return optionalAnswer.get();

    }

}
