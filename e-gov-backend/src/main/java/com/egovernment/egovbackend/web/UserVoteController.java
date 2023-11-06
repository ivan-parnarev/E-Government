package com.egovernment.egovbackend.web;

import com.egovernment.egovbackend.domain.dto.UserVotedInfoDTO;
import com.egovernment.egovbackend.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserVoteController {

    private final UserService userService;
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
        UserVotedInfoDTO savedUser = this.userService.addNewUserVote(user);
        return ResponseEntity.ok(savedUser);
    }

    @Operation(summary = "Gets all user votes from the data base")
    @ApiResponses(
            value = @ApiResponse(responseCode = "200",
                    description = "If all books are load successfully",
                    content = {
                            @Content(mediaType = "application/json")
                    }
            )
    )
    @GetMapping("/api/votes")
    public ResponseEntity<List<UserVotedInfoDTO>> getAllUserVotes() {
        List<UserVotedInfoDTO> allSavedUserVotes = this.userService.getAllUserVotes();
        return ResponseEntity.ok(allSavedUserVotes);
    }

}
