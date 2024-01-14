package com.egovernment.main.web.interfaces;

import com.egovernment.main.domain.dto.censusCampaign.CreateCensusCampaignDTO;
import com.egovernment.main.domain.dto.common.CampaignFilteredDTO;
import com.egovernment.main.domain.dto.voteCampaign.CreateVotingCampaignDTO;
import com.egovernment.main.domain.dto.censusCampaign.CensusCampaignDTO;
import com.egovernment.main.domain.dto.voteCampaign.VoteCampaignDTO;
import com.egovernment.main.domain.response.ApiCustomResponse;
import com.egovernment.main.web.path.ApiPaths;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(ApiPaths.BASE_API_PATH + ApiPaths.CAMPAIGN_PATH)
public interface CampaignControllerInterface {
     ResponseEntity<List<CampaignFilteredDTO>> getAllActiveCampaigns();
     ResponseEntity<List<VoteCampaignDTO>> getAllActiveVoteCampaigns();

     ResponseEntity<List<CensusCampaignDTO>> getActiveCensusCampaigns();
     ResponseEntity<ApiCustomResponse> saveNewVoteCampaign(@Valid @RequestBody CreateVotingCampaignDTO createVotingCampaignDTO);
     ResponseEntity<ApiCustomResponse> saveNewCensusCampaign(@Valid @RequestBody
                                                                    CreateCensusCampaignDTO createCensusCampaignDTO);
}
