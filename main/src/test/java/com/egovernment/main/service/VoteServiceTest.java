package com.egovernment.main.service;

import com.egovernment.main.client.KafkaProducerClient;
import com.egovernment.main.domain.dto.voteCampaign.UserVotedInfoDTO;
import com.egovernment.main.domain.entity.Campaign;
import com.egovernment.main.domain.entity.Candidate;
import com.egovernment.main.domain.entity.Election;
import com.egovernment.main.domain.enums.ElectionType;
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
    private  CandidateService candidateService;
    @Mock
    private UserService userService;
    @Mock
    private ElectionService electionService;
    @Mock
    private StringRedisTemplate redisTemplate;
    @Mock
    private ValueOperations<String, String> valueOperations;
    @Mock
    private  KafkaProducerClient kafkaProducerClient;
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
        this.voteServiceToTest = new VoteService(electionService, candidateService, userService, redisTemplate, kafkaProducerClient);
        lenient().when(redisTemplate.opsForValue()).thenReturn(valueOperations);
    }

    @Test
    void testVoteIsSavedCorrectlyWhenCampaignIsPresent(){

        Candidate candidate = Candidate.builder()
                .name(CANDIDATE_NAME)
                .build();

        Campaign campaign = Campaign.builder()
                .title("TestTitle")
                .build();

        Election election = Election.builder()
                .electionType(ElectionType.PARLIAMENT)
                .campaign(campaign)
                .build();

        when(this.candidateService.getCandidateById(ID)).thenReturn(Optional.of(candidate));
        when(this.electionService.getElectionById(ID)).thenReturn(Optional.of(election));

        this.voteServiceToTest.saveVote(VOTE_DTO);
        verify(kafkaProducerClient, times(1)).sendMessage(VOTE_DTO);

    }

    @Test
    void testVoteIsSavedCorrectlyWhenCampaignIsNotPresent(){

        when(this.electionService.getElectionById(ID)).thenReturn(Optional.empty());

        this.voteServiceToTest.saveVote(VOTE_DTO);

        verify(kafkaProducerClient, never()).sendMessage(VOTE_DTO);
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
