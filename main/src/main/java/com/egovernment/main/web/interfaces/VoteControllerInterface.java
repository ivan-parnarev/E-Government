package com.egovernment.main.web.interfaces;

import com.egovernment.main.domain.dto.voteCampaign.UserVotedInfoDTO;
import com.egovernment.main.web.path.ApiPaths;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(ApiPaths.BASE_API_PATH + ApiPaths.VOTE_PATH)
public interface VoteControllerInterface {

    ResponseEntity<UserVotedInfoDTO> saveUserVoteData(@Valid @RequestBody UserVotedInfoDTO user);
    ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex);

}
