package com.egovernment.main.domain.dto.auth;

import com.egovernment.kafka.domain.dto.CampaignFilteredDTO;
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
    private List<CampaignFilteredDTO> filteredCampaigns;
}
