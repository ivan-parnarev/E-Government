package com.egovernment.kafka.service;

import com.egovernment.accesscontrol.domain.entity.FilteredCampaign;
import com.egovernment.accesscontrol.service.FilteredCampaignService;
import com.egovernment.kafka.domain.dto.CampaignFilteredDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class KafkaMessageConsumerServiceTest {
    @Mock
    private FilteredCampaignService filteredCampaignService;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private KafkaMessageConsumerService kafkaMessageConsumerService;
    private final String TEST_CAMPAIGN_TITLE = "Test Campaign";
    @Test
    public void testKafkaListenGroup(){
        CampaignFilteredDTO campaignFilteredDTO = CampaignFilteredDTO
                .builder()
                .campaignTitle(TEST_CAMPAIGN_TITLE)
                .build();
        FilteredCampaign campaign = FilteredCampaign
                .builder()
                .campaignTitle(TEST_CAMPAIGN_TITLE)
                .build();
        when(this.modelMapper.map(any(CampaignFilteredDTO.class), eq(FilteredCampaign.class)))
                .thenReturn(campaign);
        this.kafkaMessageConsumerService.listenGroup(campaignFilteredDTO);
        verify(this.filteredCampaignService, times(1)).saveCampaign(any());
    }

}
