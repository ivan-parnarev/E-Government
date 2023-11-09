package com.egovernment.egovbackend.service;

import com.egovernment.egovbackend.domain.dto.CampaignViewDTO;
import com.egovernment.egovbackend.domain.entity.Campaign;
import com.egovernment.egovbackend.domain.entity.Role;
import com.egovernment.egovbackend.domain.entity.User;
import com.egovernment.egovbackend.domain.enums.CampaignType;
import com.egovernment.egovbackend.domain.enums.RoleEnum;
import com.egovernment.egovbackend.domain.factory.CampaignFactory;
import com.egovernment.egovbackend.domain.template.CandidateTemplate;
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

    public void initSampleCampaign() {
        if (this.campaignRepository.count() == 0){

            String answerJson = produceSampleAnswerJson();

            Role administratorRole = this.roleService.getRole(RoleEnum.ADMINISTRATOR);
            Optional<User> optUser = this.userService.getUserByRole(administratorRole);

            if(optUser.isPresent()){
                User administrator = optUser.get();
                Campaign campaign = launchCampaign(CampaignType.VOTING, "Парламентарни избори"
                        ,administrator, 356, answerJson);
                this.campaignRepository.save(campaign);
            }

        }
    }

    private String produceSampleAnswerJson() {
        Gson gson = new Gson();

        CandidateTemplate firstCandidate = new CandidateTemplate(1, 11, "ГЕРБ");
        CandidateTemplate secondCandidate = new CandidateTemplate(2, 13, "ПП");
        CandidateTemplate thirdCandidate = new CandidateTemplate(3, 14, "БЗНС");

        return gson.toJson(List.of(firstCandidate, secondCandidate, thirdCandidate));
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
