package com.egovernment.main.web;

import com.egovernment.main.domain.dto.voteCampaign.UserVotedInfoDTO;
import com.egovernment.main.service.VoteService;
import com.egovernment.main.web.interfaces.VoteControllerInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class VoteController implements VoteControllerInterface {

    private final VoteService voteService;
    @Operation(summary = "Receives information about voting and saves the vote in the database")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201",
                            description = "Vote data has been successfully received and saved in the database. " +
                                    "After that you are redirected to active campaigns.",
                            content = {
                                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserVotedInfoDTO.class))
                            }
                    )
                    ,@ApiResponse(responseCode = "400",
                                  description = "Bad Request - Validation error or incorrect data format.",
                    content = {@Content(mediaType = "application/json")}
                    )
            }
    )


    @Override
    @PostMapping
    public ResponseEntity<UserVotedInfoDTO> saveUserVoteData(@Valid @RequestBody UserVotedInfoDTO vote){
        this.voteService.saveVote(vote);
        URI location = URI.create("http://localhost:3000/active-campaigns");
        return ResponseEntity.created(location).body(vote);
    }

    @Override
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex){

            return ResponseEntity.badRequest().body("There was a problem with the validation of the DTO: " +
                    ex.getMessage());
    }

}
