package com.egovernment.main.domain.dto.auth;

import com.egovernment.main.domain.dto.common.CampaignFilteredDTO;
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
    private boolean isAdmin;
    private List<CampaignFilteredDTO> filteredCampaigns;
    //added filtered campaign
}
