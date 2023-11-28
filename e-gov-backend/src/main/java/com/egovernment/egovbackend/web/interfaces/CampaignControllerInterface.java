package com.egovernment.egovbackend.web.interfaces;

import com.egovernment.egovbackend.domain.dto.CampaignViewDTO;
import com.egovernment.egovbackend.domain.dto.voteCampaign.CreateVotingCampaignDTO;
import com.egovernment.egovbackend.domain.dto.censusCampaign.CensusCampaignDTO;
import com.egovernment.egovbackend.domain.dto.voteCampaign.VoteCampaignDTO;
import com.egovernment.egovbackend.domain.response.ApiCustomResponse;
import com.egovernment.egovbackend.web.path.ApiPaths;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(ApiPaths.BASE_API_PATH + ApiPaths.CAMPAIGN_PATH)
public interface CampaignControllerInterface {
     ResponseEntity<List<CampaignViewDTO>> getAllActiveCampaigns();
     ResponseEntity<List<VoteCampaignDTO>> getAllActiveVoteCampaigns();

     ResponseEntity<CensusCampaignDTO> getActiveCensusCampaign();
     ResponseEntity<ApiCustomResponse> saveNewVoteCampaign(@Valid @RequestBody CreateVotingCampaignDTO createVotingCampaignDTO);

}
