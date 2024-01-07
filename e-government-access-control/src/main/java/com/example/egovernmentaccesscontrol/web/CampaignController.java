package com.example.egovernmentaccesscontrol.web;

import com.example.egovernmentaccesscontrol.domain.dto.CampaignFilteredDTO;
import com.example.egovernmentaccesscontrol.service.CampaignService;
import com.example.egovernmentaccesscontrol.web.interfaces.CampaignControllerInterface;
import com.example.egovernmentaccesscontrol.web.path.ApiPaths;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CampaignController implements CampaignControllerInterface {

    private final CampaignService campaignService;

    @Operation(summary = "Get Active Campaigns", description = "Retrieves a list of all active voting campaigns available.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of active campaigns",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CampaignFilteredDTO.class)))
    @Override
    @GetMapping(ApiPaths.ACTIVE_PATH)
    public ResponseEntity<List<CampaignFilteredDTO>> getActiveCampaigns(){
        List<CampaignFilteredDTO> activeVotingCampaigns = this.campaignService.getActiveVotingCampaigns();
        return ResponseEntity.ok().body(activeVotingCampaigns);
    }

}
