package com.egovernment.egovbackend.web;

import com.egovernment.egovbackend.domain.dto.UserVotedInfoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserVoteController {

    @PostMapping("/api/votes")
    public ResponseEntity<UserVotedInfoDTO> getUserVoteData(@RequestBody UserVotedInfoDTO user){
        //userToBeAddedToTheDataBase
        return ResponseEntity.ok(user);
    }

}
