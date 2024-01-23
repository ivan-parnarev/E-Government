package com.egovernment.main.web;

import com.egovernment.main.domain.dto.CampaignViewDTO;
import com.egovernment.main.domain.dto.censusCampaign.CreateCensusCampaignDTO;
import com.egovernment.main.domain.dto.common.CampaignFilteredDTO;
import com.egovernment.main.domain.dto.voteCampaign.CreateVotingCampaignDTO;
import com.egovernment.main.domain.dto.censusCampaign.CensusCampaignDTO;
import com.egovernment.main.domain.dto.voteCampaign.VoteCampaignDTO;
import com.egovernment.main.domain.response.ApiCustomResponse;
import com.egovernment.main.domain.response.message.ApiResponseMessage;
import com.egovernment.main.exceptions.ActiveCensusCampaignNotFoundException;
import com.egovernment.main.service.CampaignService;
import com.egovernment.main.web.interfaces.CampaignControllerInterface;
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

import java.net.URI;
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
    public ResponseEntity<List<CampaignFilteredDTO>> getAllActiveCampaigns() {
        List<CampaignFilteredDTO> campaigns = this.campaignService.getActiveCampaigns();
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
    public ResponseEntity<List<CensusCampaignDTO>> getActiveCensusCampaigns() {
        try {
            List<CensusCampaignDTO> activeCensusCampaignDTO = this.campaignService.getActiveCensusCampaigns();
            return ResponseEntity.ok(activeCensusCampaignDTO);
        } catch (ActiveCensusCampaignNotFoundException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Create a new voting campaign",
            description = "Creates a new census campaign with the given details. " +
                    "The response contains a Location header that is used to redirect " +
                    "the user to the particular URI - http://localhost:3000 - home page")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Voting campaign successfully created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiCustomResponse.class))}),
            @ApiResponse(responseCode = "400",
                    description = "Bad Request - Request failed explicit validation checks on fields -" +
                            " Two exceptions can be causing that - MethodArgumentNotValidException(validation annotations)" +
                            " and CustomValidationException (explicit checking for a field to see if it is valid)")
    })
    @Override
    @PostMapping("/create/vote")
    public ResponseEntity<ApiCustomResponse> saveNewVoteCampaign(@Valid @RequestBody
                                                                   CreateVotingCampaignDTO createVotingCampaignDTO) {
        this.campaignService.createVotingCampaign(createVotingCampaignDTO);
        URI location = URI.create("http://localhost:3000");
        ApiCustomResponse apiResponse = ApiCustomResponse.builder()
                .message(ApiResponseMessage.SUCCESSFULLY_CREATED_CAMPAIGN).build();
        return ResponseEntity.created(location).body(apiResponse);
    }

    @Operation(summary = "Create a new census campaign",
            description = "Creates a new census campaign with the given details. " +
                    "The response contains a Location header that is used to redirect " +
                    "the user to the particular URI - http://localhost:3000 - home page")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Census campaign successfully created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiCustomResponse.class))}),
            @ApiResponse(responseCode = "400",
                    description = "Bad Request - Request failed explicit validation checks on fields -" +
                            " Two exceptions can be causing that - MethodArgumentNotValidException(validation annotations)" +
                            " and CustomValidationException (explicit checking for a field to see if it is valid)")
    })
    @Override
    @PostMapping("/create/census")
    public ResponseEntity<ApiCustomResponse> saveNewCensusCampaign(@Valid @RequestBody
                                                                   CreateCensusCampaignDTO createCensusCampaignDTO) {
        this.campaignService.createCensusCampaign(createCensusCampaignDTO);
        URI location = URI.create("http://localhost:3000");
        ApiCustomResponse apiResponse = ApiCustomResponse.builder()
                .message(ApiResponseMessage.SUCCESSFULLY_CREATED_CAMPAIGN).build();
        return ResponseEntity.created(location).body(apiResponse);
    }

}
