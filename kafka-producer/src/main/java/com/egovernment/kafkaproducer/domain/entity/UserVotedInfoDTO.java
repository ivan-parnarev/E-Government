package com.egovernment.kafkaproducer.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserVotedInfoDTO {

    private String userPin;
    private Long electionId;
    private Long candidateId;
    private String campaignTitle;

}
