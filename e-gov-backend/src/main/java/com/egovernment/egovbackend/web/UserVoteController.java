package com.egovernment.egovbackend.web;

import com.egovernment.egovbackend.domain.dto.UserVotedInfoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserVoteController {

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
    @PostMapping("/api/votes")
    public ResponseEntity<UserVotedInfoDTO> saveUserVoteData(@RequestBody UserVotedInfoDTO user){
        //userToBeAddedToTheDataBase
        return ResponseEntity.ok(user);
    }

}
