package com.egovernment.egovbackend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserVotedInfoDTO {

    private String voterName;

    private String voteOption;
}
