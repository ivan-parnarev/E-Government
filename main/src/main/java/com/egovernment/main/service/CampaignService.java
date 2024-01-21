package com.egovernment.main.service;

import com.egovernment.main.domain.dto.censusCampaign.CreateCensusCampaignDTO;
import com.egovernment.main.domain.dto.common.CampaignFilteredDTO;
import com.egovernment.main.domain.dto.common.CreateCampaignCommon;
import com.egovernment.main.domain.dto.voteCampaign.CandidateTemplateDTO;
import com.egovernment.main.domain.dto.voteCampaign.CreateVotingCampaignDTO;
import com.egovernment.main.domain.dto.voteCampaign.VoteCampaignDTO;
import com.egovernment.main.domain.dto.censusCampaign.CensusCampaignDTO;
import com.egovernment.main.domain.dto.censusCampaign.CensusQuestionDTO;
import com.egovernment.main.domain.entity.Campaign;
import com.egovernment.main.domain.entity.Election;
import com.egovernment.main.domain.entity.Role;
import com.egovernment.main.domain.entity.User;
import com.egovernment.main.domain.enums.CampaignType;
import com.egovernment.main.domain.enums.RoleEnum;
import com.egovernment.main.domain.factory.CampaignFactory;
import com.egovernment.main.exceptions.CampaignNotFoundException;
import com.egovernment.main.exceptions.CustomValidationException;
import com.egovernment.main.repository.CampaignRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
    private final CacheService cacheService;

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

//    @Cacheable(value = "filteredCampaignsCache", key = "#regionName")
    public List<CampaignFilteredDTO> getActiveCampaigns(String regionName){
        return this.cacheService.getCachedCampaigns(regionName);
    }

    public VoteCampaignDTO getVotingCampaignById(Long campaignId) {
        Optional<Campaign> optCampaign = this.getCampaignById(campaignId);

        if (optCampaign.isEmpty()) {
            throw new CampaignNotFoundException("Voting campaign with id " + campaignId + " is not found !");
        }

        return this.mapCampaignToVotingCampaignDTO(optCampaign.get());
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


    public CensusCampaignDTO getCensusCampaignById(Long campaignId) {
        Optional<Campaign> optCampaign = this.getCampaignById(campaignId);

        if (optCampaign.isEmpty()) {
            throw new CampaignNotFoundException("Census campaign with id " + campaignId + " is not found !");
        }

        Campaign censusCampaign = optCampaign.get();

        List<CensusQuestionDTO> censusQuestionsForCampaign = this.censusQuestionService
                .getCensusQuestionsForCampaign(censusCampaign.getId());

        return this.mapCampaignToCensusCampaignDTO(censusCampaign, censusQuestionsForCampaign);
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
        if (commonCampaignInformation.getCampaignRegion() == null) {
            campaignRegion = "GLOBAL";
        } else {
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
