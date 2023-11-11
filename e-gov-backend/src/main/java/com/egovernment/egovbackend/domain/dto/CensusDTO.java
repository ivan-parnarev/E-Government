package com.egovernment.egovbackend.domain.dto;

import com.egovernment.egovbackend.domain.template.AnswerCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CensusDTO {
    private String campaignId;
    private String userPin;
    private List<AnswerCategory> answers;

}
