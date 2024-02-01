package com.egovernment.main.service;

import com.egovernment.main.client.KafkaProducerClient;
import com.egovernment.main.domain.dto.censusCampaign.CensusCampaignDTO;
import com.egovernment.main.domain.dto.censusCampaign.CensusQuestionDTO;
import com.egovernment.main.domain.dto.censusCampaign.CreateCensusCampaignDTO;
import com.egovernment.main.domain.dto.common.CampaignFilteredDTO;
import com.egovernment.main.domain.dto.voteCampaign.CreateVotingCampaignDTO;
import com.egovernment.main.domain.dto.voteCampaign.ElectionDTO;
import com.egovernment.main.domain.dto.voteCampaign.VoteCampaignDTO;
import com.egovernment.main.domain.entity.Campaign;
import com.egovernment.main.domain.entity.Election;
import com.egovernment.main.domain.entity.Role;
import com.egovernment.main.domain.entity.User;
import com.egovernment.main.domain.enums.CampaignRegion;
import com.egovernment.main.domain.enums.CampaignType;
import com.egovernment.main.domain.enums.RoleEnum;
import com.egovernment.main.exceptions.CampaignNotFoundException;
import com.egovernment.main.exceptions.CustomValidationException;
import com.egovernment.main.filter.ActiveElectionFilter;
import com.egovernment.main.repository.CampaignRepository;
import com.egovernment.main.validation.ActiveCampaignValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CampaignServiceTest {

    @Mock
    private CampaignRepository campaignRepository;
    @Mock
    private UserService userService;
    @Mock
    private RoleService roleService;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private ElectionService electionService;
    @Mock
    private CandidateService candidateService;
    @Mock
    private CensusQuestionService censusQuestionService;
    @Mock
    private KafkaProducerClient kafkaProducerClient;
    private final String VOTE_CAMPAIGN_TITLE = "Test Vote Campaign";
    private final String VOTE_CAMPAIGN_DESCRIPTION = "Test description vote campaign";
    private final String CENSUS_CAMPAIGN_TITLE = "Test Census Campaign Title";
    private final String CENSUS_CAMPAIGN_DESCRIPTION = "Test census campaign description";
    private final String TEST_USER = "Test User";
    private final String TEST_USER_PIN = "1111111111";
    private final User TEST_CREATOR_USER = User.builder().firstName(TEST_USER).build();
    private final Long ID = 1L;
    private final String TEST_REGION = "TestRegion";
    private final Campaign VOTING_CAMPAIGN_TO_TEST = Campaign.builder()
            .campaignType(CampaignType.VOTING)
            .title(VOTE_CAMPAIGN_TITLE)
            .description(VOTE_CAMPAIGN_DESCRIPTION)
            .from(TEST_CREATOR_USER)
            .campaignRegion(CampaignRegion.LOCAL)
            .isActive(true)
            .build();
    private final Campaign CENSUS_CAMPAIGN_TO_TEST = Campaign.builder()
            .campaignType(CampaignType.CENSUS)
            .title(CENSUS_CAMPAIGN_TITLE)
            .description(CENSUS_CAMPAIGN_DESCRIPTION)
            .isActive(true)
            .build();

    @InjectMocks
    private CampaignService campaignServiceToTest;

    @Test
    void campaignsAreSavedWhenThereAreNoEntitiesInTheDatabase() {
        when(this.campaignRepository.count()).thenReturn(0L);

        Role administrator = new Role(RoleEnum.ADMINISTRATOR);
        when(this.roleService.getRole(RoleEnum.ADMINISTRATOR)).thenReturn(administrator);

        when(userService.getUserByRole(administrator)).thenReturn(Optional.of(new User()));

        this.campaignServiceToTest.initSampleCampaign();
        verify(campaignRepository, times(2)).save(any(Campaign.class));
    }

    @Test
    void campaignsAreNotSavedWhenThereAreEntitiesInTheDatabase() {
        when(this.campaignRepository.count()).thenReturn(1L);
        this.campaignServiceToTest.initSampleCampaign();
        verify(campaignRepository, never()).save(any(Campaign.class));
    }

    @Test
    void testGetCampaignByIdReturnsTheRightCampaignWhenIsPresent() {
        when(campaignRepository.findById(anyLong())).thenReturn(Optional.of(VOTING_CAMPAIGN_TO_TEST));

        Optional<Campaign> result = campaignServiceToTest.getCampaignById(1L);

        assertTrue(result.isPresent());
        assertEquals(VOTING_CAMPAIGN_TO_TEST.getCampaignType().name(), result.get().getCampaignType().name());
        assertEquals(VOTING_CAMPAIGN_TO_TEST.getTitle(), result.get().getTitle());
        assertEquals(VOTING_CAMPAIGN_TO_TEST.getDescription(), result.get().getDescription());
    }

    @Test
    void testGetCampaignByIdReturnsOptionalEmptyWhenIdIsNotPresent() {

        when(campaignRepository.findById(anyLong())).thenReturn(Optional.empty());
        Optional<Campaign> result = campaignServiceToTest.getCampaignById(1L);
        assertTrue(result.isEmpty());

    }

    @Test
    void launchCampaignCreatesCampaign() {

        Campaign resultCampaign = this.campaignServiceToTest.launchCampaign(CampaignType.VOTING,
                VOTE_CAMPAIGN_TITLE, VOTE_CAMPAIGN_DESCRIPTION, TEST_CREATOR_USER, null, null, true, CampaignRegion.LOCAL);

        assertNotNull(resultCampaign);
        assertEquals(VOTING_CAMPAIGN_TO_TEST.getCampaignType(), resultCampaign.getCampaignType());
        assertEquals(VOTING_CAMPAIGN_TO_TEST.getTitle(), resultCampaign.getTitle());
        assertEquals(VOTING_CAMPAIGN_TO_TEST.getDescription(), resultCampaign.getDescription());
        assertEquals(VOTING_CAMPAIGN_TO_TEST.getFrom(), resultCampaign.getFrom());
        assertTrue(resultCampaign.isActive());

    }

    @Test
    void testCreateVotingCampaign() {
        CreateVotingCampaignDTO createVotingCampaignDTO = CreateVotingCampaignDTO.builder()
                .campaignType(CampaignType.VOTING)
                .creatorUserPin(TEST_USER_PIN)
                .campaignDescription(VOTE_CAMPAIGN_DESCRIPTION)
                .campaignTitle(VOTE_CAMPAIGN_TITLE)
                .build();

        User mockUser = User.builder().PIN(TEST_USER_PIN).build();

        Campaign mockCampaign = Campaign.builder()
                .description(VOTE_CAMPAIGN_DESCRIPTION)
                .title(VOTE_CAMPAIGN_TITLE)
                .campaignType(CampaignType.VOTING)
                .build();

        Election mockElection = Election.builder()
                .campaign(mockCampaign)
                .build();

        when(userService.userIsAdmin(anyString())).thenReturn(true);
        when(userService.getUserByPin(anyString())).thenReturn(Optional.of(mockUser));
        when(campaignRepository.save(any(Campaign.class))).thenReturn(mockCampaign);

        this.campaignServiceToTest.createVotingCampaign(createVotingCampaignDTO);

        verify(campaignRepository).save(any(Campaign.class));

    }

    @Test
    void testCreateCensusCampaignSuccessfully() {
        CreateCensusCampaignDTO createCensusCampaignDTO = CreateCensusCampaignDTO.builder()
                .campaignType(CampaignType.CENSUS)
                .creatorUserPin(TEST_USER_PIN)
                .campaignDescription(CENSUS_CAMPAIGN_DESCRIPTION)
                .campaignTitle(CENSUS_CAMPAIGN_TITLE)
                .build();

        when(userService.userIsAdmin(TEST_USER_PIN)).thenReturn(true);

        User mockUser = User.builder().PIN(TEST_USER_PIN).build();
        when(userService.getUserByPin(TEST_USER_PIN)).thenReturn(Optional.of(mockUser));

        campaignServiceToTest.createCensusCampaign(createCensusCampaignDTO);

        verify(campaignRepository).save(any(Campaign.class));
    }


    @Test
    void testCreateVotingCampaignThrowsExceptionForNonAdminUser() {
        CreateVotingCampaignDTO createVotingCampaignDTO = CreateVotingCampaignDTO.builder()
                .campaignType(CampaignType.VOTING)
                .creatorUserPin(TEST_USER_PIN)
                .campaignDescription(VOTE_CAMPAIGN_DESCRIPTION)
                .campaignTitle(VOTE_CAMPAIGN_TITLE)
                .build();

        when(userService.userIsAdmin(anyString())).thenReturn(false);

        assertThrows(CustomValidationException.class, () -> {
            this.campaignServiceToTest.createVotingCampaign(createVotingCampaignDTO);
        });

        verify(campaignRepository, never()).save(any(Campaign.class));
    }

    @Test
    public void getAllVotingCampaigns() {
        when(campaignRepository.getAllByCampaignType(CampaignType.VOTING))
                .thenReturn(List.of(VOTING_CAMPAIGN_TO_TEST));

        List<Campaign> result = this.campaignServiceToTest.getAllVotingCampaigns();

        assertEquals(1, result.size());
        assertEquals(CampaignType.VOTING, result.get(0).getCampaignType());
    }

    @Test
    public void getActiveCampaignReturnsTheRightDtoWhenCampaignIsVoting() {
        LocalDateTime startDate = LocalDateTime.now().minusDays(1);
        LocalDateTime endDate = LocalDateTime.now().plusDays(2);

        System.out.println(startDate);
        System.out.println(endDate);
        VOTING_CAMPAIGN_TO_TEST.setId(ID);
        VOTING_CAMPAIGN_TO_TEST.setStartDate(startDate);
        VOTING_CAMPAIGN_TO_TEST.setEndDate(endDate);

        CampaignFilteredDTO campaignFilteredDTO = CampaignFilteredDTO.builder()
                .regionName(TEST_REGION)
                .build();

        Election election = Election.builder()
                .electionRegion(TEST_REGION)
                .campaign(VOTING_CAMPAIGN_TO_TEST)
                .build();

        when(this.campaignRepository.findAll()).thenReturn(List.of(VOTING_CAMPAIGN_TO_TEST));
        when(this.electionService.getElectionsByCampaignId(ID)).thenReturn(List.of(election));
        lenient().when(this.electionService.mapElectionToCampaignFilteredDTO(election))
                .thenReturn(campaignFilteredDTO);

        List<CampaignFilteredDTO> result = this.campaignServiceToTest.getActiveCampaigns(TEST_REGION);

        assertEquals(1, result.size());
        assertEquals(campaignFilteredDTO.getRegionName(), result.get(0).getRegionName());
    }

    @Test
    public void testGetCensusCampaignById() {
        CENSUS_CAMPAIGN_TO_TEST.setId(ID);
        CensusQuestionDTO censusQuestionDTO = CensusQuestionDTO.builder()
                .text("Test Text")
                .build();
        CensusCampaignDTO censusCampaignDTO = CensusCampaignDTO.builder()
                .campaignTitle(CENSUS_CAMPAIGN_TITLE)
                .campaignDescription(CENSUS_CAMPAIGN_DESCRIPTION)
                .build();
        when(this.campaignRepository.findById(ID)).thenReturn(Optional.of(CENSUS_CAMPAIGN_TO_TEST));
        when(this.censusQuestionService.getCensusQuestionsForCampaign(ID)).thenReturn(List.of(censusQuestionDTO));

        CensusCampaignDTO result = this.campaignServiceToTest.getCensusCampaignById(ID);

        assertNotNull(result);
        assertEquals(censusCampaignDTO.getCampaignTitle(), result.getCampaignTitle());
        assertEquals(censusCampaignDTO.getCampaignDescription(), result.getCampaignDescription());
    }

    @Test
    public void getCensusCampaignById_ShouldThrowCampaignNotFoundException_WhenCampaignIsNotFound() {
        when(campaignRepository.findById(ID)).thenReturn(Optional.empty());

        assertThrows(CampaignNotFoundException.class, () -> {
            campaignServiceToTest.getCensusCampaignById(ID);
        });

        verifyNoInteractions(censusQuestionService);
    }

}
