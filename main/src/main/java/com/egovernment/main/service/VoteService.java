package com.egovernment.main.service;

import com.egovernment.main.domain.dto.voteCampaign.UserVotedInfoDTO;
import com.egovernment.main.domain.entity.Candidate;
import com.egovernment.main.domain.entity.Election;
import com.egovernment.main.domain.entity.User;
import com.egovernment.main.domain.entity.Vote;
import com.egovernment.main.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;
    private final ElectionService electionService;
    private final CandidateService candidateService;
    private final UserService userService;

    public boolean hasUserVotedForCampaign(String userPin, Long electionId) {
        return this.voteRepository.voteExistsByUserPinAndElectionId(userPin, electionId);
    }

    public void saveVote(UserVotedInfoDTO voteDTO) {
        Optional<User> optUser = this.userService.getUserByPin(voteDTO.getUserPin());

        User votedUser;
        if (optUser.isPresent()) {
            votedUser = optUser.get();
        } else {
            votedUser = this.userService.createUserWithUserPin(voteDTO.getUserPin());
        }

        Optional<Election> optElection = this.electionService.getElectionById(voteDTO.getElectionId());
        Optional<Candidate> optCandidate = this.candidateService.getCandidateById(voteDTO.getCandidateId());

        if(optElection.isPresent() && optCandidate.isPresent()){
            Vote vote = Vote.builder()
                    .user(votedUser)
                    .election(optElection.get())
                    .candidate(optCandidate.get())
                    .timestamp(LocalDateTime.now())
                    .build();

            this.voteRepository.save(vote);
        }

    }
}
