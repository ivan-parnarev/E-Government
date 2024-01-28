package com.egovernment.main.service;

import com.egovernment.main.domain.dto.voteCampaign.UserVotedInfoDTO;
import com.egovernment.main.domain.entity.Candidate;
import com.egovernment.main.domain.entity.Election;
import com.egovernment.main.domain.entity.User;
import com.egovernment.main.domain.entity.Vote;
import com.egovernment.main.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;
    private final ElectionService electionService;
    private final CandidateService candidateService;
    private final UserService userService;
    private final StringRedisTemplate redisTemplate;

    public boolean hasUserVotedForCampaign(String userPin, Long electionId) {
        String key = buildCacheKey(userPin, electionId);
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
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

            String key = buildCacheKey(voteDTO.getUserPin(), voteDTO.getElectionId());
            redisTemplate.opsForValue().set(key, "voted", 3, TimeUnit.DAYS.DAYS);

        }

    }

    private String buildCacheKey(String userPin, Long electionId) {
        return "vote:" + userPin + ":" + electionId;
    }

}
