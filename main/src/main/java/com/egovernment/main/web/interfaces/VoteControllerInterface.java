package com.egovernment.egovbackend.web.interfaces;

import com.egovernment.egovbackend.domain.dto.voteCampaign.UserVotedInfoDTO;
import com.egovernment.egovbackend.web.path.ApiPaths;
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
