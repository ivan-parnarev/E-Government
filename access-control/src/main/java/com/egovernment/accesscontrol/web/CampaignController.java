package com.egovernment.accesscontrol.web;

import com.egovernment.accesscontrol.domain.dto.CampaignFilteredDTO;
import com.egovernment.accesscontrol.service.CampaignService;
import com.egovernment.accesscontrol.web.interfaces.CampaignControllerInterface;
import com.egovernment.accesscontrol.web.path.ApiPaths;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CampaignController implements CampaignControllerInterface {

    private final CampaignService campaignService;
    //retrieve all active campaigns by region name

    @Operation(summary = "Get Active Campaigns", description = "Retrieves a list of all active voting campaigns available.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of active campaigns",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CampaignFilteredDTO.class)))
    @Override
    @PostMapping(ApiPaths.ACTIVE_PATH)
    public ResponseEntity<List<CampaignFilteredDTO>> getActiveCampaigns(@RequestBody String regionName) {

        List<CampaignFilteredDTO> activeVotingCampaigns = this.campaignService
                .getActiveLocalCampaigns(regionName);

        return ResponseEntity.ok().body(activeVotingCampaigns);
    }

}
