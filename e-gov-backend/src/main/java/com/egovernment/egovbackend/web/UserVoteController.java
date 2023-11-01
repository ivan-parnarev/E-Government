package com.egovernment.egovbackend.web;

import com.egovernment.egovbackend.domain.dto.UserVotedInfoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserVoteController {

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/api/votes")
    public ResponseEntity<UserVotedInfoDTO> getUserVoteData(@RequestBody UserVotedInfoDTO user){
        //userToBeAddedToTheDataBase
        return ResponseEntity.ok(user);
    }

}
