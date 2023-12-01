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
        Campaign campaignToTest = Campaign.builder()
                .campaignType(CampaignType.VOTING)
                .title(VOTE_CAMPAIGN_TITLE)
                .description(VOTE_CAMPAIGN_DESCRIPTION)
                .build();

        when(campaignRepository.findAll()).thenReturn(List.of(campaignToTest));

        VoteCampaignDTO campaignViewDTO = VoteCampaignDTO.builder()
                .campaignType(CampaignType.VOTING.name())
                .campaignTitle(campaignToTest.getTitle())
                .campaignDescription(campaignToTest.getDescription())
                .build();


        List<VoteCampaignDTO> result = campaignServiceToTest.getActiveVotingCampaigns();

        assertNotNull(result);
        assertEquals(campaignToTest.getCampaignType().name(), campaignViewDTO.getCampaignType());
        assertEquals(campaignToTest.getTitle(), campaignViewDTO.getCampaignTitle());
        assertEquals(campaignToTest.getDescription(), campaignViewDTO.getCampaignDescription());
    }

    @Test
    void testGetActiveCensusCampaign() {
        Campaign campaignToTest = Campaign.builder()
                .campaignType(CampaignType.CENSUS)
                .title(CENSUS_CAMPAIGN_TITLE)
                .description(CENSUS_CAMPAIGN_DESCRIPTION)
                .isActive(true)
                .build();

        when(campaignRepository.getAllByCampaignType(CampaignType.CENSUS)).thenReturn(List.of(campaignToTest));

        CensusCampaignDTO censusCampaignDTO = CensusCampaignDTO.builder()
                .campaignTitle(campaignToTest.getTitle())
                .campaignDescription(campaignToTest.getDescription())
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
        Campaign campaignToTest = Campaign.builder()
                .campaignType(CampaignType.VOTING)
                .title(VOTE_CAMPAIGN_TITLE)
                .description(VOTE_CAMPAIGN_DESCRIPTION)
                .build();

        when(campaignRepository.findById(anyLong())).thenReturn(Optional.of(campaignToTest));

        Optional<Campaign> result = campaignServiceToTest.getCampaignById(1L);

        assertTrue(result.isPresent());
        assertEquals(campaignToTest.getCampaignType().name(), result.get().getCampaignType().name());
        assertEquals(campaignToTest.getTitle(), result.get().getTitle());
        assertEquals(campaignToTest.getDescription(), result.get().getDescription());
    }

    @Test
    void testGetCampaignByIdReturnsOptionalEmptyWhenIdIsNotPresent() {

        when(campaignRepository.findById(anyLong())).thenReturn(Optional.empty());
        Optional<Campaign> result = campaignServiceToTest.getCampaignById(1L);
        assertTrue(result.isEmpty());

    }

    @Test
    void launchCampaignCreatesCampaign() {
        User testUserFrom = User.builder().firstName(TEST_USER).build();

        Campaign campaign = Campaign.builder()
                .campaignType(CampaignType.VOTING)
                .title(VOTE_CAMPAIGN_TITLE)
                .description(VOTE_CAMPAIGN_DESCRIPTION)
                .from(testUserFrom)
                .isActive(true)
                .startDate(null)
                .endDate(null)
                .build();

        Campaign resultCampaign = this.campaignServiceToTest.launchCampaign(CampaignType.VOTING,
                VOTE_CAMPAIGN_TITLE, VOTE_CAMPAIGN_DESCRIPTION, testUserFrom, null, null, true);

        assertNotNull(resultCampaign);
        assertEquals(campaign.getCampaignType(), resultCampaign.getCampaignType());
        assertEquals(campaign.getTitle(), resultCampaign.getTitle());
        assertEquals(campaign.getDescription(), resultCampaign.getDescription());
        assertEquals(campaign.getFrom(), resultCampaign.getFrom());
        assertNull(resultCampaign.getStartDate());
        assertNull(resultCampaign.getEndDate());
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
