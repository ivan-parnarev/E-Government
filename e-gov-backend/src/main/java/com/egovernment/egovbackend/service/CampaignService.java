package com.egovernment.egovbackend.service;

import com.egovernment.egovbackend.client.AccessControlClient;
import com.egovernment.egovbackend.domain.dto.censusCampaign.CreateCensusCampaignDTO;
import com.egovernment.egovbackend.domain.dto.common.CampaignFilteredDTO;
import com.egovernment.egovbackend.domain.dto.common.CreateCampaignCommon;
import com.egovernment.egovbackend.domain.dto.voteCampaign.CandidateTemplateDTO;
import com.egovernment.egovbackend.domain.dto.voteCampaign.CreateVotingCampaignDTO;
import com.egovernment.egovbackend.domain.dto.voteCampaign.VoteCampaignDTO;
import com.egovernment.egovbackend.domain.dto.censusCampaign.CensusCampaignDTO;
import com.egovernment.egovbackend.domain.dto.censusCampaign.CensusQuestionDTO;
import com.egovernment.egovbackend.domain.entity.Campaign;
import com.egovernment.egovbackend.domain.entity.Election;
import com.egovernment.egovbackend.domain.entity.Role;
import com.egovernment.egovbackend.domain.entity.User;
import com.egovernment.egovbackend.domain.enums.CampaignType;
import com.egovernment.egovbackend.domain.enums.RoleEnum;
import com.egovernment.egovbackend.domain.factory.CampaignFactory;
import com.egovernment.egovbackend.exceptions.CustomValidationException;
import com.egovernment.egovbackend.repository.CampaignRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private final ElectionService electionService;
    private final CandidateService candidateService;
    private final CensusQuestionService censusQuestionService;
    private final AccessControlClient accessControlClient;


    public void initSampleCampaign() {
        if (this.campaignRepository.count() == 0) {

            Role administratorRole = this.roleService.getRole(RoleEnum.ADMINISTRATOR);
            Optional<User> optUser = this.userService.getUserByRole(administratorRole);

            if (optUser.isPresent()) {
                User administrator = optUser.get();

                String voteCampaignDescription = "Парламентарните избори са ключов момент в " +
                        "демократичния живот на една страна, където гражданите имат" +
                        " възможността да изразят своята воля и да изберат своите представители в законодателния орган.";

                Campaign votingCampaign = launchCampaign(CampaignType.VOTING, "Парламентарни избори",
                        voteCampaignDescription, administrator, LocalDateTime.now(),
                        LocalDateTime.of(2023, 12, 5, 23, 59),
                        true, "GLOBAL", null);

                String censusCampaignDescription = "Кампанията за преброяване на населението." +
                        "Този процес помага на правителството" +
                        " и други институции да разберат демографската структура, социалните и икономическите условия";

                Campaign censusCampaign = launchCampaign(CampaignType.CENSUS, "Преброяване",
                        censusCampaignDescription, administrator, LocalDateTime.now(),
                        LocalDateTime.of(2023, 12, 5, 23, 59),
                        true, "GLOBAL", null);

                this.campaignRepository.save(votingCampaign);
                this.campaignRepository.save(censusCampaign);
            }

        }
    }

    public Campaign launchCampaign(CampaignType type, String title, String description
            , User from, LocalDateTime startDate, LocalDateTime endDate,
            boolean isActive, String campaignRegion, Long campaignReferenceId) {
        return campaignFactory.createCampaign(type, title, description,
                from, startDate, endDate, isActive, campaignRegion, campaignReferenceId);
    }

    public List<CampaignFilteredDTO> getActiveCampaigns() {
        return this.accessControlClient.getActiveCampaigns().getBody();
    }

    public List<VoteCampaignDTO> getActiveVotingCampaigns() {
        return this.campaignRepository
                .findAll()
                .stream()
                .filter(c -> c.getCampaignType().equals(CampaignType.VOTING))
                .filter(Campaign::isActive)
                .map(this::mapCampaignToVotingCampaignDTO)
                .collect(Collectors.toList());
    }

    private VoteCampaignDTO mapCampaignToVotingCampaignDTO(Campaign campaign) {

        Optional<Election> election = this.electionService
                .getElectionByCampaignId(campaign.getId());

        if (election.isEmpty()) {
            return null;
        }

        List<CandidateTemplateDTO> candidates = this.candidateService
                .getCandidatesForElection(election.get().getId());


        VoteCampaignDTO voteCampaignDTO = VoteCampaignDTO.builder()
                .campaignType(campaign.getCampaignType().name())
                .campaignDescription(campaign.getDescription())
                .campaignTitle(campaign.getTitle())
                .campaignStartDate(campaign.getStartDate())
                .campaignEndDate(campaign.getEndDate())
                .electionType(election.get().getElectionType().name())
                .electionId(election.get().getId())
                .electionCandidates(candidates)
                .build();


        return voteCampaignDTO;
    }

    public Optional<Campaign> getCampaignById(Long campaignId) {
        return this.campaignRepository.findById(campaignId);
    }

    public List<CensusCampaignDTO> getActiveCensusCampaigns() {
        List<CensusCampaignDTO> censusCampaignDTOS = new ArrayList<>();
        List<Campaign> censusCampaigns = this.campaignRepository
                .getAllByCampaignType(CampaignType.CENSUS);

        List<Campaign> activeCensusCampaigns = censusCampaigns
                .stream()
                .filter(Campaign::isActive)
                .toList();

        for (Campaign censusCampaign : activeCensusCampaigns) {
            List<CensusQuestionDTO> censusQuestionsForCampaign = this.censusQuestionService
                    .getCensusQuestionsForCampaign(censusCampaign.getId());
            censusCampaignDTOS.add(mapCampaignToCensusCampaignDTO(censusCampaign, censusQuestionsForCampaign));
        }
        return censusCampaignDTOS;
    }

    private CensusCampaignDTO mapCampaignToCensusCampaignDTO(Campaign campaign, List<CensusQuestionDTO> questions) {
        return CensusCampaignDTO.builder()
                .campaignId(campaign.getId())
                .campaignType(String.valueOf(campaign.getCampaignType()))
                .campaignTitle(campaign.getTitle())
                .campaignDescription(campaign.getDescription())
                .campaignStartDate(campaign.getStartDate())
                .campaignEndDate(campaign.getEndDate())
                .censusQuestions(questions)
                .build();
    }

    public void createVotingCampaign(CreateVotingCampaignDTO createVotingCampaignDTO) {
        Campaign campaign = createCampaignCommonInformation(createVotingCampaignDTO);

        this.campaignRepository.save(campaign);

        Election election = this.electionService.createElection(createVotingCampaignDTO.getElectionType(), campaign);
        //this.candidateService.createCandidates(createVotingCampaignDTO.getCandidates(), election);

    }

    public void createCensusCampaign(CreateCensusCampaignDTO createCensusCampaignDTO) {
        Campaign campaign = createCampaignCommonInformation(createCensusCampaignDTO);

        this.campaignRepository.save(campaign);

        this.censusQuestionService.createCensusQuestions(createCensusCampaignDTO.getQuestions(), campaign);
    }


    private Campaign createCampaignCommonInformation(CreateCampaignCommon commonCampaignInformation) {
        CampaignType campaignType = CampaignType.valueOf(commonCampaignInformation.getCampaignType());

        if (!this.userService.userIsAdmin(commonCampaignInformation.getCreatorUserPin())) {
            throw new CustomValidationException("Validation failed: User does not exist or is not admin !");
        }

        String campaignRegion;
        if(commonCampaignInformation.getCampaignRegion() == null){
            campaignRegion = "GLOBAL";
        }else{
            campaignRegion = commonCampaignInformation.getCampaignRegion();
        }

        User owner = userService.getUserByPin(commonCampaignInformation.getCreatorUserPin()).get();

        Campaign campaign = launchCampaign(campaignType, commonCampaignInformation.getCampaignTitle(),
                commonCampaignInformation.getCampaignDescription(), owner,
                commonCampaignInformation.getCampaignStartDate(), commonCampaignInformation.getCampaignEndDate(), true,
                campaignRegion, commonCampaignInformation.getCampaignReferenceId());
        return campaign;
    }

}
