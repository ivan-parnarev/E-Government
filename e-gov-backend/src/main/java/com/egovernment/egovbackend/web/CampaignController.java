package com.egovernment.egovbackend.web;

import com.egovernment.egovbackend.domain.dto.CampaignViewDTO;
import com.egovernment.egovbackend.domain.dto.CreateVotingCampaignDTO;
import com.egovernment.egovbackend.domain.dto.censusCampaignDTO.CensusCampaignDTO;
import com.egovernment.egovbackend.domain.dto.campaignDto.VoteCampaignDTO;
import com.egovernment.egovbackend.exceptions.ActiveCensusCampaignNotFoundException;
import com.egovernment.egovbackend.service.CampaignService;
import com.egovernment.egovbackend.web.interfaces.CampaignControllerInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CampaignController implements CampaignControllerInterface {

    private final CampaignService campaignService;

    @Operation(summary = "Method Name: getAllActiveCampaigns; Retrieves a list of all active campaigns.")
    @ApiResponses(
            value = @ApiResponse(responseCode = "200",
                    description = "Successfully retrieved list of active campaigns",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(
                                    type = "array",
                                    implementation = CampaignViewDTO.class))})
    )
    @Override
    @GetMapping("/active")
    public ResponseEntity<List<CampaignViewDTO>> getAllActiveCampaigns() {
        List<CampaignViewDTO> campaigns = this.campaignService.getActiveCampaigns();
        return ResponseEntity.ok(campaigns);
    }

    @Operation(summary = "Method Name: getAllActiveVoteCampaigns; Retrieves a list of all active campaigns for VOTING.")
    @ApiResponses(
            value = @ApiResponse(responseCode = "200",
                    description = "Successfully retrieved list of active VOTING campaigns",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(
                                    type = "array",
                                    implementation = VoteCampaignDTO.class))})
    )
    @Override
    @GetMapping("/active/vote")
    public ResponseEntity<List<VoteCampaignDTO>> getAllActiveVoteCampaigns() {
        List<VoteCampaignDTO> activeVotingCampaigns = this.campaignService
                .getActiveVotingCampaigns();
        return ResponseEntity.ok(activeVotingCampaigns);
    }

    @Operation(summary = "Method Name: getActiveCensusCampaign; Retrieves active campaign for CENSUS.")
    @ApiResponses(
            value = {@ApiResponse(responseCode = "200",
                    description = "Successfully retrieved active CENSUS campaign",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(
                                    type = "object",
                                    implementation = CensusCampaignDTO.class))})
                    ,@ApiResponse(responseCode = "400",
                    description = "Bad Request - Active census campaign not found.",
                    content = {@Content(mediaType = "application/json")}
            )
            }
    )
    @Override
    @GetMapping("/active/census")
    public ResponseEntity<CensusCampaignDTO> getActiveCensusCampaign() {
        try {
            CensusCampaignDTO activeCensusCampaignDTO = this.campaignService.getActiveCensusCampaign();
            return ResponseEntity.ok(activeCensusCampaignDTO);
        } catch (ActiveCensusCampaignNotFoundException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    @PostMapping("/create")
    public ResponseEntity<CreateVotingCampaignDTO> saveNewCampaign(@Valid @RequestBody
                                                                   CreateVotingCampaignDTO createVotingCampaignDTO) {
        this.campaignService.createCampaign(createVotingCampaignDTO);
        return ResponseEntity.ok(createVotingCampaignDTO);
    }


}
