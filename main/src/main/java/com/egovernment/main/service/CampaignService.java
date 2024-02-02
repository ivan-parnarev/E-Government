package com.egovernment.main.service;

import com.egovernment.main.component.CampaignsCatalog;
import com.egovernment.main.domain.dto.censusCampaign.CensusCampaignDTO;
import com.egovernment.main.domain.dto.censusCampaign.CensusQuestionDTO;
import com.egovernment.main.domain.dto.censusCampaign.CreateCensusCampaignDTO;
import com.egovernment.main.domain.dto.common.CampaignFilteredDTO;
import com.egovernment.main.domain.dto.common.CreateCampaignCommon;
import com.egovernment.main.domain.dto.voteCampaign.*;
import com.egovernment.main.domain.entity.*;
import com.egovernment.main.domain.enums.*;
import com.egovernment.main.domain.factory.CampaignFactory;
import com.egovernment.main.exceptions.CampaignNotFoundException;
import com.egovernment.main.exceptions.CustomValidationException;
import com.egovernment.main.filter.ActiveElectionFilter;
import com.egovernment.main.repository.CampaignRepository;
import com.egovernment.main.validation.ActiveCampaignValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private final CampaignsCatalog campaignsCatalog;

    public void initSampleCampaign() {
        if (this.campaignRepository.count() == 0) {

            Role administratorRole = this.roleService.getRole(RoleEnum.ADMINISTRATOR);
            Optional<User> optUser = this.userService.getUserByRole(administratorRole);

            LocalDateTime today = LocalDateTime.now();
            LocalDateTime tomorrow = today.plusDays(1);

            if (optUser.isPresent()) {
                User administrator = optUser.get();

                String voteCampaignDescription = "Парламентарните избори са ключов момент в " +
                        "демократичния живот на една страна, където гражданите имат" +
                        " възможността да изразят своята воля и да изберат своите представители в законодателния орган.";

                String voteLocalCampaignDescription = "Местните избори са ключов момент в " +
                        "демократичния живот на една страна, където гражданите имат" +
                        " възможността да изразят своята воля и да изберат своите представители в законодателния орган.";

                Campaign votingGlobalCampaign = launchCampaign(CampaignType.VOTING, "Парламентарни избори",
                        voteCampaignDescription, administrator, today,
                        LocalDateTime.of(tomorrow.getYear(),tomorrow.getMonth(), tomorrow.getDayOfMonth(), 23, 59),
                        true, CampaignRegion.GLOBAL, CampaignStatus.ONGOING);

                Campaign votingLocalCampaign = launchCampaign(CampaignType.VOTING, "Кметски избори",
                        voteLocalCampaignDescription, administrator, today,
                        LocalDateTime.of(tomorrow.getYear(),tomorrow.getMonth(), tomorrow.getDayOfMonth(), 23, 59),
                        true, CampaignRegion.LOCAL, CampaignStatus.ONGOING);

                String censusCampaignDescription = "Кампанията за преброяване на населението." +
                        "Този процес помага на правителството" +
                        " и други институции да разберат демографската структура, социалните и икономическите условия";

                Campaign censusCampaign = launchCampaign(CampaignType.CENSUS, "Преброяване",
                        censusCampaignDescription, administrator, today,
                        LocalDateTime.of(tomorrow.getYear(),tomorrow.getMonth(), tomorrow.getDayOfMonth(), 23, 59),
                        true, CampaignRegion.GLOBAL, CampaignStatus.ONGOING);

                this.campaignRepository.save(votingGlobalCampaign);
                this.campaignRepository.save(votingLocalCampaign);
                this.campaignRepository.save(censusCampaign);

                CandidateDTO firstCandidate = CandidateDTO.builder()
                        .candidateName("Асен Василев")
                        .candidateNumber(1)
                        .candidateParty("Продължаваме Промяната").build();

                CandidateDTO secondCandidate = CandidateDTO.builder()
                        .candidateName("Корнелия Нинова")
                        .candidateNumber(2)
                        .candidateParty("БСП").build();

                CandidateDTO thirdCandidate = CandidateDTO.builder()
                        .candidateName("Бойко Борисов")
                        .candidateNumber(3)
                        .candidateParty("ГЕРБ").build();

                ElectionDTO globalElection = ElectionDTO.builder()
                        .electionType(String.valueOf(ElectionType.PARLIAMENT))
                        .electionRegion("GLOBAL")
                        .candidates(List.of(firstCandidate, secondCandidate, thirdCandidate)).build();

                ElectionDTO localElectionSof = ElectionDTO.builder()
                        .electionType(String.valueOf(ElectionType.MAYOR))
                        .electionRegion("София")
                        .candidates(List.of(firstCandidate, secondCandidate, thirdCandidate)).build();

                ElectionDTO localElectionPld = ElectionDTO.builder()
                        .electionType(String.valueOf(ElectionType.MAYOR))
                        .electionRegion("Пловдив")
                        .candidates(List.of(firstCandidate, secondCandidate, thirdCandidate)).build();

                this.electionService.createElectionsWithCandidates(List.of(globalElection), votingGlobalCampaign);
                this.electionService.createElectionsWithCandidates(List.of(localElectionSof, localElectionPld), votingLocalCampaign);
            }

        }
    }

    public Campaign launchCampaign(CampaignType type, String title, String description
            , User from, LocalDateTime startDate, LocalDateTime endDate,
                                   boolean isActive, CampaignRegion campaignRegion, CampaignStatus campaignStatus) {
        return campaignFactory.createCampaign(type, title, description,
                from, startDate, endDate, isActive, campaignRegion, campaignStatus);
    }

    public List<CampaignFilteredDTO> getActiveCampaigns(String regionName){

        List<Campaign> all = this.campaignRepository
                .findAll();
        List<Campaign> allActiveCampaigns = all
                .stream()
                .filter(ActiveCampaignValidator::isCampaignActive)
                .toList();

        List<CampaignFilteredDTO> filteredCampaigns = new ArrayList<>();

        for (Campaign campaign : allActiveCampaigns) {

            if(campaign.getCampaignType().equals(CampaignType.VOTING)){

                List<CampaignFilteredDTO> activeSuitableElections = this.electionService
                        .getElectionsByCampaignId(campaign.getId())
                        .stream()
                        .filter(e -> ActiveElectionFilter.filterByRegionAndIsActive(e, regionName))
                        .map(this.electionService::mapElectionToCampaignFilteredDTO)
                        .toList();

                filteredCampaigns.addAll(activeSuitableElections);

            }else if(campaign.getCampaignType().equals(CampaignType.CENSUS)){

                CampaignFilteredDTO campaignFilteredDTO = CampaignFilteredDTO
                        .builder()
                        .campaignId(campaign.getId())
                        .campaignType(campaign.getCampaignType().toString())
                        .campaignTitle(campaign.getTitle())
                        .build();

                filteredCampaigns.add(campaignFilteredDTO);
            }

        }

        return filteredCampaigns;
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

        this.campaignsCatalog.getCampaigns().add(campaign);

        List<ElectionDTO> electionsDTOList = createVotingCampaignDTO.getElections();

        this.electionService.createElectionsWithCandidates(electionsDTOList, campaign);
    }

    public void createCensusCampaign(CreateCensusCampaignDTO createCensusCampaignDTO) {
        Campaign campaign = createCampaignCommonInformation(createCensusCampaignDTO);

        this.campaignRepository.save(campaign);

        this.campaignsCatalog.getCampaigns().add(campaign);

        this.censusQuestionService.createCensusQuestions(createCensusCampaignDTO.getQuestions(), campaign);
    }


    private Campaign createCampaignCommonInformation(CreateCampaignCommon commonCampaignInformation) {
        CampaignType campaignType = commonCampaignInformation.getCampaignType();

        if (!this.userService.userIsAdmin(commonCampaignInformation.getCreatorUserPin())) {
            throw new CustomValidationException("Validation failed: User does not exist or is not admin !");
        }

        CampaignRegion campaignRegion;
        if (commonCampaignInformation.getCampaignRegion() == null) {
            campaignRegion = CampaignRegion.GLOBAL;
        } else {
            campaignRegion = commonCampaignInformation.getCampaignRegion();
        }

        User owner = userService.getUserByPin(commonCampaignInformation.getCreatorUserPin()).get();

        Campaign campaign = launchCampaign(campaignType, commonCampaignInformation.getCampaignTitle(),
                commonCampaignInformation.getCampaignDescription(), owner,
                commonCampaignInformation.getCampaignStartDate(), commonCampaignInformation.getCampaignEndDate(), true,
                campaignRegion, CampaignStatus.UPCOMING);
        return campaign;
    }

}