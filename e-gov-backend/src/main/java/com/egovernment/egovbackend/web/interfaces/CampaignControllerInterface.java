package com.egovernment.egovbackend.web.interfaces;

import com.egovernment.egovbackend.domain.dto.CampaignViewDTO;
import com.egovernment.egovbackend.domain.dto.campaignDto.VoteCampaignDTO;
import com.egovernment.egovbackend.web.path.ApiPaths;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(ApiPaths.BASE_API_PATH + ApiPaths.CAMPAIGN_PATH)
public interface CampaignControllerInterface {
     ResponseEntity<List<CampaignViewDTO>> getAllActiveCampaigns();
     ResponseEntity<List<VoteCampaignDTO>> getAllActiveVoteCampaigns();
}
