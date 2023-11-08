package com.egovernment.egovbackend.web;

import com.egovernment.egovbackend.domain.dto.UserVotedInfoDTO;
import com.egovernment.egovbackend.service.VoteService;
import com.egovernment.egovbackend.web.interfaces.VoteControllerInterface;
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

@RestController
@RequiredArgsConstructor
public class VoteController implements VoteControllerInterface {

    private final VoteService voteService;
    @Operation(summary = "Receives and returns information about the user name and vote option")
    @ApiResponses(
            value = @ApiResponse(responseCode = "200",
                    description = "The user data have been received successfully " +
                            "and returned back to the response body so that they can be visualized.",
                    content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserVotedInfoDTO.class))
                    }
            )
    )
    @Override
    @PostMapping
    public ResponseEntity<UserVotedInfoDTO> saveUserVoteData(@Valid @RequestBody UserVotedInfoDTO user){
        return ResponseEntity.ok(user);
    }

    @Override
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex){

            return ResponseEntity.badRequest().body("There was a problem with the validation of the DTO: " +
                    ex.getMessage());
    }

}
