package com.egovernment.main.web.interfaces;

import com.egovernment.main.domain.dto.censusCampaign.CensusCampaignDTO;
import com.egovernment.main.domain.dto.censusCampaign.CreateCensusCampaignDTO;
import com.egovernment.main.domain.dto.voteCampaign.CreateVotingCampaignDTO;
import com.egovernment.main.domain.dto.voteCampaign.VoteCampaignDTO;
import com.egovernment.main.domain.response.ApiCustomResponse;
import com.egovernment.main.web.path.ApiPaths;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping(ApiPaths.BASE_API_PATH + ApiPaths.CAMPAIGN_PATH)
public interface CampaignControllerInterface {
    //removed get operations for each campaign type because we are sending campaign title and id and
    // then we get each campaign by its id
    ResponseEntity<VoteCampaignDTO> getVotingCampaignById(@PathVariable("campaignId") Long campaignId);
    ResponseEntity<CensusCampaignDTO> getCensusCampaignById(@PathVariable("campaignId") Long campaignId);
    ResponseEntity<ApiCustomResponse> saveNewVoteCampaign(@Valid @RequestBody CreateVotingCampaignDTO createVotingCampaignDTO);
    ResponseEntity<ApiCustomResponse> saveNewCensusCampaign(@Valid @RequestBody
                                                            CreateCensusCampaignDTO createCensusCampaignDTO);
}
