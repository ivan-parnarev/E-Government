package com.egovernment.egovbackend.service;

import com.egovernment.egovbackend.domain.dto.CampaignViewDTO;
import com.egovernment.egovbackend.domain.entity.Campaign;
import com.egovernment.egovbackend.domain.entity.Role;
import com.egovernment.egovbackend.domain.entity.User;
import com.egovernment.egovbackend.domain.enums.CampaignType;
import com.egovernment.egovbackend.domain.enums.RoleEnum;
import com.egovernment.egovbackend.domain.factory.CampaignFactory;
import com.egovernment.egovbackend.domain.template.AnswerCategory;
import com.egovernment.egovbackend.domain.template.CandidateTemplate;
import com.egovernment.egovbackend.domain.template.QuestionAnswerTemplate;
import com.egovernment.egovbackend.repository.CampaignRepository;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CampaignService {

    private final CampaignRepository campaignRepository;
    private final CampaignFactory campaignFactory = new CampaignFactory();
    private final UserService userService;
    private final RoleService roleService;
    private final ModelMapper modelMapper;
    Gson gson = new Gson();

    public void initSampleCampaign() {
        if (this.campaignRepository.count() == 0){

            String votingAnswerJson = produceSampleVotingCampaignAnswerJson();
            String censusCampaignAnswersJson = produceSampleCensusCampaignAnswerJson();

            Role administratorRole = this.roleService.getRole(RoleEnum.ADMINISTRATOR);
            Optional<User> optUser = this.userService.getUserByRole(administratorRole);

            if(optUser.isPresent()){
                User administrator = optUser.get();

                Campaign votingCampaign = launchCampaign(CampaignType.VOTING, "Парламентарни избори"
                        ,administrator, 356, votingAnswerJson);

                Campaign censusCampaign = launchCampaign(CampaignType.CENSUS, "Преброяване"
                        ,administrator, 356, censusCampaignAnswersJson);

                this.campaignRepository.save(votingCampaign);
                this.campaignRepository.save(censusCampaign);
            }

        }
    }

    private String produceSampleCensusCampaignAnswerJson() {
        QuestionAnswerTemplate firstDemographicQuestion = QuestionAnswerTemplate
                .builder().question("What is your age?").build();
        QuestionAnswerTemplate secondDemographicQuestion = QuestionAnswerTemplate
                .builder().question("What is your gender?").build();

        QuestionAnswerTemplate firstEducationQuestion = QuestionAnswerTemplate
                .builder().question("Are you currently attending school or college?").build();
        QuestionAnswerTemplate secondEducationQuestion = QuestionAnswerTemplate
                .builder().question("What is the field of study for your highest qualification?").build();

        List<QuestionAnswerTemplate> demographicInfo = List.of(firstDemographicQuestion, secondDemographicQuestion);
        List<QuestionAnswerTemplate> educationInfo = List.of(firstEducationQuestion, secondEducationQuestion);

        AnswerCategory answerCategory = AnswerCategory.builder()
                .demographicInfo(demographicInfo)
                .educationInfo(educationInfo)
                .build();

        return this.gson.toJson(answerCategory);
    }

    private String produceSampleVotingCampaignAnswerJson() {

        CandidateTemplate firstCandidate = new CandidateTemplate(1, 11, "ГЕРБ");
        CandidateTemplate secondCandidate = new CandidateTemplate(2, 13, "ПП");
        CandidateTemplate thirdCandidate = new CandidateTemplate(3, 14, "БЗНС");

        return this.gson.toJson(List.of(firstCandidate, secondCandidate, thirdCandidate));
    }

    public Campaign launchCampaign(CampaignType type, String campaignTopic, User from, int duration, String answersJson) {
        return campaignFactory.createCampaign(type, campaignTopic,from, duration, answersJson);
    }

    public List<CampaignViewDTO> getActiveCampaigns() {
        return this.campaignRepository
                .findAll()
                .stream()
                .map(c -> this.modelMapper.map(c, CampaignViewDTO.class))
                .collect(Collectors.toList());
    }

    public Optional<Campaign> getCampaignById(Long campaignId) {
        return this.campaignRepository.findById(campaignId);
    }
}
