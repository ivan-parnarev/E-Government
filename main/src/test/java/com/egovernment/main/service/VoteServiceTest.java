package com.egovernment.main.service;

import com.egovernment.main.domain.dto.voteCampaign.UserVotedInfoDTO;
import com.egovernment.main.domain.entity.Candidate;
import com.egovernment.main.domain.entity.Election;
import com.egovernment.main.domain.entity.Vote;
import com.egovernment.main.domain.enums.ElectionType;
import com.egovernment.main.repository.VoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VoteServiceTest {

    @Mock
    private  VoteRepository voteRepository;
    @Mock
    private  CandidateService candidateService;
    @Mock
    private UserService userService;
    @Mock
    private ElectionService electionService;
    @Mock
    private StringRedisTemplate redisTemplate;
    @Mock
    private ValueOperations<String, String> valueOperations;
    private VoteService voteServiceToTest;
    private final String CANDIDATE_NAME = "Test Candidate";
    private final String USER_PIN = "0000000000";
    private final Long ID = 1L;
    private final UserVotedInfoDTO VOTE_DTO = UserVotedInfoDTO.builder()
            .userPin(USER_PIN)
            .electionId(ID)
            .candidateId(ID)
            .build();

    @BeforeEach
    void setUp() {
        this.voteServiceToTest = new VoteService(voteRepository, electionService, candidateService, userService, redisTemplate);
        lenient().when(redisTemplate.opsForValue()).thenReturn(valueOperations);
    }

    @Test
    void testVoteIsSavedCorrectlyWhenCampaignIsPresent(){

        Candidate candidate = Candidate.builder()
                .name(CANDIDATE_NAME)
                .build();

        Election election = Election.builder()
                .electionType(ElectionType.PARLIAMENT)
                .build();

        when(this.candidateService.getCandidateById(ID)).thenReturn(Optional.of(candidate));
        when(this.electionService.getElectionById(ID)).thenReturn(Optional.of(election));

        this.voteServiceToTest.saveVote(VOTE_DTO);

        verify(voteRepository, times(1)).save(any(Vote.class));

    }

    @Test
    void testVoteIsSavedCorrectlyWhenCampaignIsNotPresent(){

        when(this.electionService.getElectionById(ID)).thenReturn(Optional.empty());

        this.voteServiceToTest.saveVote(VOTE_DTO);

        verify(voteRepository, never()).save(any(Vote.class));
    }

    @Test
    void testHasUserVotedForCampaignCacheHit() {
        when(redisTemplate.hasKey(anyString())).thenReturn(true);

        assertTrue(voteServiceToTest.hasUserVotedForCampaign(USER_PIN, ID));
        verify(redisTemplate, times(1)).hasKey(anyString());
    }

    @Test
    void testHasUserVotedForCampaign_CacheMissAndDatabaseCheck() {
        when(redisTemplate.hasKey(anyString())).thenReturn(false);

        assertFalse(voteServiceToTest.hasUserVotedForCampaign(USER_PIN, ID));
        verify(redisTemplate, times(1)).hasKey(anyString());
    }

}
