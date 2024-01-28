package com.egovernment.main.domain.dto.voteCampaign;

import com.egovernment.main.domain.enums.ElectionType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ElectionDTO {
    @NotBlank(message = "Election type must be PARLIAMENT, PRESIDENT, MAYOR or COUNCIL but was not found")
    private String electionType;
    @NotBlank(message = "Election region must not be blank")
    private String electionRegion;
    private List<CandidateDTO> candidates;
}
