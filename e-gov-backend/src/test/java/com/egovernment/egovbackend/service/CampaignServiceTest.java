package com.egovernment.egovbackend.service;

import com.egovernment.egovbackend.domain.dto.CampaignViewDTO;
import com.egovernment.egovbackend.domain.dto.campaignDto.VoteCampaignDTO;
import com.egovernment.egovbackend.domain.dto.censusCampaignDTO.CensusCampaignDTO;
import com.egovernment.egovbackend.domain.entity.Campaign;
import com.egovernment.egovbackend.domain.entity.Role;
import com.egovernment.egovbackend.domain.entity.User;
import com.egovernment.egovbackend.domain.enums.CampaignType;
import com.egovernment.egovbackend.domain.enums.RoleEnum;
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
    void testGetActiveCampaigns() {
        Campaign campaignToTest = Campaign.builder()
                .campaignType(CampaignType.VOTING).build();
        when(campaignRepository.findAll()).thenReturn(List.of(campaignToTest));

        CampaignViewDTO campaignViewDTO = CampaignViewDTO.builder()
                .campaignType(CampaignType.VOTING.name())
                .build();

        when(modelMapper.map(campaignToTest, CampaignViewDTO.class)).thenReturn(campaignViewDTO);

        List<CampaignViewDTO> result = campaignServiceToTest.getActiveCampaigns();

        assertNotNull(result);
        assertEquals(campaignToTest.getCampaignType().name(), campaignViewDTO.getCampaignType());
    }

    @Test
    void testGetActiveVotingCampaigns() {
        Campaign campaignToTest = Campaign.builder()
                .campaignType(CampaignType.VOTING)
                .title("Test Vote Campaign")
                .description("Test description vote campaign")
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
                .title("Test Census Campaign Title")
                .description("Test census campaign description")
                .isActive(true)
                .build();

        when(campaignRepository.getAllByCampaignType(CampaignType.CENSUS)).thenReturn(List.of(campaignToTest));

        CensusCampaignDTO censusCampaignDTO = CensusCampaignDTO.builder()
                .campaignTitle(campaignToTest.getTitle())
                .campaignDescription(campaignToTest.getDescription())
                .campaignType(String.valueOf(CampaignType.CENSUS))
                .build();

        CensusCampaignDTO result = campaignServiceToTest.getActiveCensusCampaign();

        assertNotNull(result);
        assertEquals(censusCampaignDTO.getCampaignTitle(), result.getCampaignTitle());
        assertEquals(censusCampaignDTO.getCampaignDescription(), result.getCampaignDescription());
        assertEquals(censusCampaignDTO.getCampaignType(), result.getCampaignType());
    }

    @Test
    void testGetCampaignByIdReturnsTheRightCampaignWhenIsPresent() {
        Campaign campaignToTest = Campaign.builder()
                .campaignType(CampaignType.VOTING)
                .title("Test Vote Campaign")
                .description("Test description vote campaign")
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
        User testUserFrom = User.builder().firstName("TestUser").build();

        Campaign campaign = Campaign.builder()
                .campaignType(CampaignType.VOTING)
                .title("Campaign Test Topic")
                .description("Test Description")
                .from(testUserFrom)
                .isActive(true)
                .startDate(null)
                .endDate(null)
                .build();

        Campaign resultCampaign = this.campaignServiceToTest.launchCampaign(CampaignType.VOTING,
                "Campaign Test Topic", "Test Description", testUserFrom, null, null, true);

        assertNotNull(resultCampaign);
        assertEquals(campaign.getCampaignType(), resultCampaign.getCampaignType());
        assertEquals(campaign.getTitle(), resultCampaign.getTitle());
        assertEquals(campaign.getDescription(), resultCampaign.getDescription());
        assertEquals(campaign.getFrom(), resultCampaign.getFrom());
        assertNull(resultCampaign.getStartDate());
        assertNull(resultCampaign.getEndDate());
        assertTrue(resultCampaign.isActive());

    }

}
