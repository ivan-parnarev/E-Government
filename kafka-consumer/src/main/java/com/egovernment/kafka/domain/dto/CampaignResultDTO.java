package com.egovernment.kafka.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CampaignResultDTO {

    private String campaignTitle;
    private List<UserVotedInfoDTO> votes;

}
