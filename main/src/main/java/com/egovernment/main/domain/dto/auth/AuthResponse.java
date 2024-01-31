package com.egovernment.egovbackend.domain.dto.auth;

import com.egovernment.egovbackend.domain.dto.common.CampaignFilteredDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    Boolean isAdmin;
    List<CampaignFilteredDTO> filteredCampaigns;
}
