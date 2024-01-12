package com.example.accesscontrol.service;

import com.example.accesscontrol.domain.entity.Address;
import com.example.accesscontrol.domain.entity.Campaign;
import com.example.accesscontrol.domain.dto.CampaignFilteredDTO;
import com.example.accesscontrol.repository.CampaignRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CampaignServiceTest {
    @Mock
    private CampaignRepository campaignRepository;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private KeyService keyService;

    @InjectMocks
    private CampaignService campaignServiceToTest;

    private final String TEST_REGION_NAME = "TestRegion";
    private final String TEST_GLOBAL_REGION_NAME = "GLOBAL";
    private final String NON_EXISTING_TEST_REGION_NAME_ = "TestRegion does not exist";
    private final Campaign mockCampaign = Campaign.builder()
            .regionName(TEST_REGION_NAME)
            .build();
    private final CampaignFilteredDTO mockDto = CampaignFilteredDTO.builder()
            .regionName(TEST_REGION_NAME)
            .build();

    @Test
    void getActiveLocalCampaignsSuccessfullyFound() throws Exception {
        Address mockAddress = Address.builder()
                .region(TEST_REGION_NAME)
                .build();

        List<Campaign> campaigns = new ArrayList<>();
        campaigns.add(mockCampaign);

        when(keyService.extractAddress()).thenReturn(mockAddress);
        when(campaignRepository.findAll()).thenReturn(campaigns);
        when(modelMapper.map(mockCampaign, CampaignFilteredDTO.class)).thenReturn(mockDto);

        List<CampaignFilteredDTO> result = campaignServiceToTest.getActiveLocalCampaigns();

        assertFalse(result.isEmpty());
        assertEquals(mockCampaign.getRegionName(), mockDto.getRegionName());

        verify(keyService).extractAddress();
        verify(campaignRepository).findAll();
        verify(modelMapper).map(any(Campaign.class), eq(CampaignFilteredDTO.class));
    }

    @Test
    void getActiveLocalCampaignsSuccessfullyFoundForCampaignGLOBAL() throws Exception {
        Address mockAddress = Address.builder()
                .region(TEST_REGION_NAME)
                .build();

        Campaign mockCampaignGlobal = Campaign.builder()
                .regionName(TEST_GLOBAL_REGION_NAME)
                .build();

        CampaignFilteredDTO mockDtoGlobal = CampaignFilteredDTO.builder()
                .regionName(TEST_GLOBAL_REGION_NAME)
                .build();

        List<Campaign> campaigns = new ArrayList<>();
        campaigns.add(mockCampaignGlobal);

        when(keyService.extractAddress()).thenReturn(mockAddress);
        when(campaignRepository.findAll()).thenReturn(campaigns);
        when(modelMapper.map(mockCampaignGlobal, CampaignFilteredDTO.class)).thenReturn(mockDtoGlobal);

        List<CampaignFilteredDTO> result = campaignServiceToTest.getActiveLocalCampaigns();

        assertFalse(result.isEmpty());
        assertEquals(mockCampaignGlobal.getRegionName(), mockDtoGlobal.getRegionName());

        verify(keyService).extractAddress();
        verify(campaignRepository).findAll();
        verify(modelMapper).map(any(Campaign.class), eq(CampaignFilteredDTO.class));
    }

    @Test
    void getActiveLocalCampaignsWithNoSuchRegionReturnsEmptyList() throws Exception {
        Address mockAddress = Address.builder()
                .region(NON_EXISTING_TEST_REGION_NAME_)
                .build();

        List<Campaign> campaigns = new ArrayList<>();
        campaigns.add(mockCampaign);

        when(keyService.extractAddress()).thenReturn(mockAddress);
        when(campaignRepository.findAll()).thenReturn(campaigns);

        List<CampaignFilteredDTO> result = campaignServiceToTest.getActiveLocalCampaigns();

        assertTrue(result.isEmpty());

        verify(keyService).extractAddress();
        verify(campaignRepository).findAll();
    }

    @Test
    void getActiveLocalCampaignsExceptionThrownAndEmptyResultSetReturned() throws Exception {
        when(keyService.extractAddress()).thenThrow(new JsonProcessingException("Test Exception") {
        });

        List<CampaignFilteredDTO> result = campaignServiceToTest.getActiveLocalCampaigns();

        assertTrue(result.isEmpty());
        verify(keyService).extractAddress();
        verify(campaignRepository, never()).findAll();
        verify(modelMapper, never()).map(any(), any());
    }

}
