package com.egovernment.accesscontrol.service;

import com.egovernment.accesscontrol.domain.entity.FilteredCampaign;
import com.egovernment.accesscontrol.repository.FilteredCampaignRepository;
import com.egovernment.kafka.domain.dto.CampaignFilteredDTO;
import com.egovernment.accesscontrol.enums.CampaignType;
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
public class FilteredCampaignServiceTest {
    @Mock
    private FilteredCampaignRepository filteredCampaignRepository;
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private FilteredCampaignService filteredCampaignServiceToTest;

    private final String TEST_REGION_NAME = "TestRegion";
    private final String VOTING_CAMPAIGN_TYPE = "VOTING";
    private final String CENSUS_CAMPAIGN_TYPE = "CENSUS";
    private final String TEST_GLOBAL_REGION_NAME = "GLOBAL";
    private final FilteredCampaign firstMockCampaign = FilteredCampaign.builder()
            .campaignType(String.valueOf(CampaignType.VOTING))
            .campaignTitle(VOTING_CAMPAIGN_TYPE)
            .regionName(TEST_REGION_NAME)
            .build();

    private final FilteredCampaign secondMockCampaign = FilteredCampaign.builder()
            .campaignType(String.valueOf(CampaignType.CENSUS))
            .campaignTitle(CENSUS_CAMPAIGN_TYPE)
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

        when(filteredCampaignRepository.findAll()).thenReturn(Arrays.asList(firstMockCampaign, secondMockCampaign));

        lenient().when(modelMapper.map(eq(firstMockCampaign), eq(CampaignFilteredDTO.class))).thenReturn(firstMockCampaignDto);
        lenient().when(modelMapper.map(eq(secondMockCampaign), eq(CampaignFilteredDTO.class))).thenReturn(secondMockCampaignDto);

        List<CampaignFilteredDTO> result = filteredCampaignServiceToTest.getActiveLocalCampaigns(TEST_REGION_NAME);

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