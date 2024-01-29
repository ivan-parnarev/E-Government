package com.egovernment.main.web;

import com.egovernment.main.domain.dto.censusCampaign.CensusCampaignDTO;
import com.egovernment.main.domain.dto.censusCampaign.CreateCensusCampaignDTO;
import com.egovernment.main.domain.dto.voteCampaign.CreateVotingCampaignDTO;
import com.egovernment.main.domain.dto.voteCampaign.VoteCampaignDTO;
import com.egovernment.main.domain.response.ApiCustomResponse;
import com.egovernment.main.domain.response.message.ApiResponseMessage;
import com.egovernment.main.exceptions.CampaignNotFoundException;
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
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class CampaignController implements CampaignControllerInterface {

    private final CampaignService campaignService;


    @Operation(summary = "Get Voting Campaign by ID",
            description = "Retrieves detailed information about a specific voting campaign based on its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successfully retrieved the voting campaign details",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CensusCampaignDTO.class))}),
            @ApiResponse(responseCode = "404",
                    description = "Voting campaign not found for the given ID",
                    content = @Content(schema = @Schema(implementation = CampaignNotFoundException.class)))
    })
    @Override
    @GetMapping("/{campaignId}/VOTING")
    public ResponseEntity<VoteCampaignDTO> getVotingCampaignById(@PathVariable("campaignId") Long campaignId) {
        VoteCampaignDTO votingCampaign = this.campaignService.getVotingCampaignById(campaignId);
        return ResponseEntity.ok(votingCampaign);
    }

    @Operation(summary = "Get Census Campaign by ID",
            description = "Retrieves detailed information about a specific census campaign based on its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successfully retrieved the census campaign details",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CensusCampaignDTO.class))}),
            @ApiResponse(responseCode = "404",
                    description = "Census campaign not found for the given ID",
                    content = @Content(schema = @Schema(implementation = CampaignNotFoundException.class)))
    })
    @Override
    @GetMapping("/{campaignId}/CENSUS")
    public ResponseEntity<CensusCampaignDTO> getCensusCampaignById(@PathVariable("campaignId") Long campaignId) {
        CensusCampaignDTO censusCampaignDTO = this.campaignService.getCensusCampaignById(campaignId);
        return ResponseEntity.ok(censusCampaignDTO);
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