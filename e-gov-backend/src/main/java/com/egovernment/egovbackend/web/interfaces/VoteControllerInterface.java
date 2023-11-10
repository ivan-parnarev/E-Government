package com.egovernment.egovbackend.web.interfaces;

import com.egovernment.egovbackend.domain.dto.UserVotedInfoDTO;
import com.egovernment.egovbackend.web.path.ApiPaths;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@RequestMapping(ApiPaths.BASE_API_PATH + ApiPaths.VOTE_PATH)
public interface VoteControllerInterface {

    RedirectView saveUserVoteData(@Valid @RequestBody UserVotedInfoDTO user);
    ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex);

}
