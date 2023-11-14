package com.egovernment.egovbackend.service;

import com.egovernment.egovbackend.domain.dto.CampaignViewDTO;
import com.egovernment.egovbackend.domain.entity.Campaign;
import com.egovernment.egovbackend.domain.entity.Role;
import com.egovernment.egovbackend.domain.entity.User;
import com.egovernment.egovbackend.domain.enums.CampaignType;
import com.egovernment.egovbackend.domain.enums.RoleEnum;
import com.egovernment.egovbackend.domain.factory.CampaignFactory;
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

//    @Mock
//    private CampaignRepository campaignRepository;
//    @Mock
//    private UserService userService;
//    @Mock
//    private RoleService roleService;
//    @Mock
//    private ModelMapper modelMapper;
//    private CampaignService campaignServiceToTest;
//
//    @BeforeEach
//    void setUp() {
//        this.campaignServiceToTest = new CampaignService(campaignRepository,
//                userService, roleService, modelMapper);
//    }
//
//    @Test
//    void campaignsAreSavedWhenThereAreNoEntitiesInTheDatabase(){
//        when(this.campaignRepository.count()).thenReturn(0L);
//
//        Role administrator = new Role(RoleEnum.ADMINISTRATOR);
//        when(this.roleService.getRole(RoleEnum.ADMINISTRATOR)).thenReturn(administrator);
//
//        when(userService.getUserByRole(administrator)).thenReturn(Optional.of(new User()));
//
//        this.campaignServiceToTest.initSampleCampaign();
//        verify(campaignRepository, times(2)).save(any(Campaign.class));
//    }
//
//    @Test
//    void campaignsAreNotSavedWhenThereAreEntitiesInTheDatabase(){
//        when(this.campaignRepository.count()).thenReturn(1L);
//        this.campaignServiceToTest.initSampleCampaign();
//        verify(campaignRepository, never()).save(any(Campaign.class));
//    }
//
//    @Test
//    void launchCampaignCreatesCampaign(){
//        User testUserFrom = User.builder().name("TestUser").build();
//
//        Campaign campaign = Campaign.builder()
//                .campaignType(CampaignType.VOTING)
//                .campaignTopic("Campaign Test Topic")
//                .from(testUserFrom)
//                .duration(1)
//                .answersJson("AnswersTest")
//                .build();
//
//        Campaign resultCampaign = this.campaignServiceToTest.launchCampaign(CampaignType.VOTING,
//                "Campaign Test Topic", testUserFrom,1, "AnswersTest");
//
//        assertNotNull(resultCampaign);
//        assertEquals(campaign.getCampaignType(), resultCampaign.getCampaignType());
//        assertEquals(campaign.getCampaignTopic(), resultCampaign.getCampaignTopic());
//        assertEquals(campaign.getFrom(), resultCampaign.getFrom());
//        assertEquals(campaign.getDuration(), resultCampaign.getDuration());
//        assertEquals(campaign.getAnswersJson(), resultCampaign.getAnswersJson());
//
//    }
//
//    @Test
//    void getActiveCampaignsReturnListOfCampaignDTOs() {
//
//        Campaign campaign = Campaign.builder()
//                .campaignType(CampaignType.VOTING)
//                .campaignTopic("Campaign Test Topic")
//                .duration(1)
//                .answersJson("AnswersTest")
//                .build();
//
//        CampaignViewDTO campaignViewDTO = CampaignViewDTO.builder()
//                .campaignType("VOTING")
//                .campaignTopic("Campaign Test Topic")
//                .duration(1)
//                .answersJson("AnswersTest")
//                .build();
//
//        when(campaignRepository.findAll()).thenReturn(List.of(campaign));
//        when(modelMapper.map(campaign, CampaignViewDTO.class)).thenReturn(campaignViewDTO);
//
//        List<CampaignViewDTO> campaigns = this.campaignServiceToTest.getActiveCampaigns();
//
//        assertFalse(campaigns.isEmpty());
//        assertEquals(1, campaigns.size());
//        assertEquals(campaignViewDTO.getCampaignTopic(), campaigns.get(0).getCampaignTopic());
//        assertEquals(campaignViewDTO.getCampaignType(), campaigns.get(0).getCampaignType());
//        assertEquals(campaignViewDTO.getDuration(), campaigns.get(0).getDuration());
//        assertEquals(campaignViewDTO.getAnswersJson(), campaigns.get(0).getAnswersJson());
//    }
//    @Test
//    void testGetCampaignByExistingIdReturnsTheRightCampaign(){
//        Campaign expectedCampaign = Campaign.builder()
//                .campaignType(CampaignType.VOTING)
//                .campaignTopic("Campaign Test Topic")
//                .build();
//
//        when(this.campaignRepository.findById(1L)).thenReturn(Optional.of(expectedCampaign));
//
//        Optional<Campaign> campaign = this.campaignServiceToTest.getCampaignById(1L);
//
//        assertTrue(campaign.isPresent());
//        assertEquals(expectedCampaign, campaign.get());
//        assertEquals(expectedCampaign.getCampaignTopic(), campaign.get().getCampaignTopic());
//        assertEquals(expectedCampaign.getCampaignType(), campaign.get().getCampaignType());
//
//    }
//
//    @Test
//    void testGetCampaignByNonExistingIdReturnsEmptyOptional(){
//
//        when(this.campaignRepository.findById(1L)).thenReturn(Optional.empty());
//        Optional<Campaign> campaign = this.campaignServiceToTest.getCampaignById(1L);
//        assertTrue(campaign.isEmpty());
//
//    }

}
