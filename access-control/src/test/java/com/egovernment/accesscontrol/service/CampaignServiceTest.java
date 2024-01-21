package com.egovernment.accesscontrol.service;

import com.egovernment.accesscontrol.domain.entity.Campaign;
import com.egovernment.accesscontrol.domain.dto.CampaignFilteredDTO;
import com.egovernment.accesscontrol.enums.CampaignType;
import com.egovernment.accesscontrol.repository.CampaignRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CampaignServiceTest {
    @Mock
    private CampaignRepository campaignRepository;
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CampaignService campaignServiceToTest;

    private final String TEST_REGION_NAME = "TestRegion";
    private final String VOTING_CAMPAIGN_TYPE = "VOTING";
    private final String CENSUS_CAMPAIGN_TYPE = "CENSUS";
    private final String TEST_GLOBAL_REGION_NAME = "GLOBAL";
    private final Campaign firstMockCampaign = Campaign.builder()
            .campaignType(CampaignType.VOTING)
            .title(VOTING_CAMPAIGN_TYPE)
            .regionName(TEST_REGION_NAME)
            .build();

    private final Campaign secondMockCampaign = Campaign.builder()
            .campaignType(CampaignType.CENSUS)
            .title(CENSUS_CAMPAIGN_TYPE)
            .regionName(TEST_GLOBAL_REGION_NAME)
            .build();
    private final CampaignFilteredDTO firstMockCampaignDto = CampaignFilteredDTO.builder()
            .campaignType(VOTING_CAMPAIGN_TYPE)
            .campaignTitle(VOTING_CAMPAIGN_TYPE)
            .regionName(TEST_REGION_NAME)
            .build();

    private final CampaignFilteredDTO secondMockCampaignDto = CampaignFilteredDTO.builder()
            .campaignType(CENSUS_CAMPAIGN_TYPE)
            .campaignTitle(CENSUS_CAMPAIGN_TYPE)
            .regionName(TEST_GLOBAL_REGION_NAME)
            .build();
    @Test
    public void testGetActiveLocalCampaigns() {

        when(campaignRepository.findAll()).thenReturn(Arrays.asList(firstMockCampaign, secondMockCampaign));

        lenient().when(modelMapper.map(eq(firstMockCampaign), eq(CampaignFilteredDTO.class))).thenReturn(firstMockCampaignDto);
        lenient().when(modelMapper.map(eq(secondMockCampaign), eq(CampaignFilteredDTO.class))).thenReturn(secondMockCampaignDto);

        List<CampaignFilteredDTO> result = campaignServiceToTest.getActiveLocalCampaigns(TEST_REGION_NAME);

        assertEquals(2, result.size());

        CampaignFilteredDTO firstDto = result.get(0);
        assertEquals(firstMockCampaignDto.getCampaignTitle(), firstDto.getCampaignTitle());
        assertEquals(firstMockCampaignDto.getRegionName(), firstDto.getRegionName());
        assertEquals(firstMockCampaignDto.getCampaignType(), firstDto.getCampaignType());

        CampaignFilteredDTO secondDto = result.get(1);
        assertEquals(secondMockCampaignDto.getCampaignTitle(), secondDto.getCampaignTitle());
        assertEquals(secondMockCampaignDto.getRegionName(), secondDto.getRegionName());
        assertEquals(secondMockCampaignDto.getCampaignType(), secondDto.getCampaignType());

    }
}
