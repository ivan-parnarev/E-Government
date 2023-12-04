package com.egovernment.egovbackend.service;

import com.egovernment.egovbackend.domain.dto.censusCampaign.CreateCensusCampaignDTO;
import com.egovernment.egovbackend.domain.dto.voteCampaign.CreateVotingCampaignDTO;
import com.egovernment.egovbackend.domain.dto.voteCampaign.VoteCampaignDTO;
import com.egovernment.egovbackend.domain.dto.censusCampaign.CensusCampaignDTO;
import com.egovernment.egovbackend.domain.entity.Campaign;
import com.egovernment.egovbackend.domain.entity.Election;
import com.egovernment.egovbackend.domain.entity.Role;
import com.egovernment.egovbackend.domain.entity.User;
import com.egovernment.egovbackend.domain.enums.CampaignType;
import com.egovernment.egovbackend.domain.enums.RoleEnum;
import com.egovernment.egovbackend.exceptions.CustomValidationException;
import com.egovernment.egovbackend.repository.CampaignRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

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
    private CampaignService campaignServiceToTest;
    private final String VOTE_CAMPAIGN_TITLE = "Test Vote Campaign";
    private final String VOTE_CAMPAIGN_DESCRIPTION = "Test description vote campaign";
    private final String CENSUS_CAMPAIGN_TITLE = "Test Census Campaign Title";
    private final String CENSUS_CAMPAIGN_DESCRIPTION = "Test census campaign description";
    private final String TEST_USER = "Test User";
    private final String TEST_USER_PIN = "1111111111";
    private final User TEST_CREATOR_USER = User.builder().firstName(TEST_USER).build();
    private final Campaign VOTING_CAMPAIGN_TO_TEST = Campaign.builder()
            .campaignType(CampaignType.VOTING)
            .title(VOTE_CAMPAIGN_TITLE)
            .description(VOTE_CAMPAIGN_DESCRIPTION)
            .from(TEST_CREATOR_USER)
            .isActive(true)
            .build();
    private final Campaign CENSUS_CAMPAIGN_TO_TEST = Campaign.builder()
            .campaignType(CampaignType.CENSUS)
            .title(CENSUS_CAMPAIGN_TITLE)
            .description(CENSUS_CAMPAIGN_DESCRIPTION)
            .isActive(true)
            .build();

    @BeforeEach
    void setUp() {
        this.campaignServiceToTest = new CampaignService(campaignRepository,
                userService, roleService, modelMapper, electionService, candidateService, censusQuestionService);
    }

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
    void testGetActiveVotingCampaigns() {

        when(campaignRepository.findAll()).thenReturn(List.of(VOTING_CAMPAIGN_TO_TEST));

        VoteCampaignDTO campaignViewDTO = VoteCampaignDTO.builder()
                .campaignType(CampaignType.VOTING.name())
                .campaignTitle(VOTING_CAMPAIGN_TO_TEST.getTitle())
                .campaignDescription(VOTING_CAMPAIGN_TO_TEST.getDescription())
                .build();


        List<VoteCampaignDTO> result = campaignServiceToTest.getActiveVotingCampaigns();

        assertNotNull(result);
        assertEquals(VOTING_CAMPAIGN_TO_TEST.getCampaignType().name(), campaignViewDTO.getCampaignType());
        assertEquals(VOTING_CAMPAIGN_TO_TEST.getTitle(), campaignViewDTO.getCampaignTitle());
        assertEquals(VOTING_CAMPAIGN_TO_TEST.getDescription(), campaignViewDTO.getCampaignDescription());
    }

    @Test
    void testGetActiveCensusCampaign() {

        when(campaignRepository.getAllByCampaignType(CampaignType.CENSUS))
                .thenReturn(List.of(CENSUS_CAMPAIGN_TO_TEST));

        CensusCampaignDTO censusCampaignDTO = CensusCampaignDTO.builder()
                .campaignTitle(CENSUS_CAMPAIGN_TO_TEST.getTitle())
                .campaignDescription(CENSUS_CAMPAIGN_TO_TEST.getDescription())
                .campaignType(String.valueOf(CampaignType.CENSUS))
                .build();

        CensusCampaignDTO result = campaignServiceToTest.getActiveCensusCampaigns().get(0);

        assertNotNull(result);
        assertEquals(censusCampaignDTO.getCampaignTitle(), result.getCampaignTitle());
        assertEquals(censusCampaignDTO.getCampaignDescription(), result.getCampaignDescription());
        assertEquals(censusCampaignDTO.getCampaignType(), result.getCampaignType());
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
                VOTE_CAMPAIGN_TITLE, VOTE_CAMPAIGN_DESCRIPTION, TEST_CREATOR_USER, null, null, true);

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
                .campaignType("VOTING")
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
        when(electionService.createElection(any(CreateVotingCampaignDTO.class), any(Campaign.class))).thenReturn(mockElection);

        this.campaignServiceToTest.createVotingCampaign(createVotingCampaignDTO);

        verify(campaignRepository).save(any(Campaign.class));
        verify(electionService).createElection(any(CreateVotingCampaignDTO.class), any(Campaign.class));
    }

    @Test
    void testCreateCensusCampaignSuccessfully() {
        CreateCensusCampaignDTO createCensusCampaignDTO = CreateCensusCampaignDTO.builder()
                .campaignType("CENSUS")
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
                .campaignType("VOTING")
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

}
