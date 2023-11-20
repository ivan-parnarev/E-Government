package com.egovernment.egovbackend.service;

import com.egovernment.egovbackend.domain.dto.campaignDto.UserVotedInfoDTO;
import com.egovernment.egovbackend.domain.entity.Candidate;
import com.egovernment.egovbackend.domain.entity.Election;
import com.egovernment.egovbackend.domain.entity.Vote;
import com.egovernment.egovbackend.domain.enums.ElectionType;
import com.egovernment.egovbackend.repository.VoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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


    private VoteService voteServiceToTest;
    private final String CANDIDATE_NAME = "Test Candidate";
    private final String USER_PIN = "0000000000";
    private final Long ID = 1L;

    @BeforeEach
    void setUp() {
        this.voteServiceToTest = new VoteService(voteRepository, electionService, candidateService, userService);
    }

    @Test
    void testVoteIsSavedCorrectlyWhenCampaignIsPresent(){

        Candidate candidate = Candidate.builder()
                .name(CANDIDATE_NAME)
                .build();

        Election election = Election.builder()
                .electionType(ElectionType.PARLIAMENT)
                .build();

        UserVotedInfoDTO voteDTO = UserVotedInfoDTO.builder()
                .userPin(USER_PIN)
                .electionId(ID)
                .candidateId(ID)
                .build();

        when(this.candidateService.getCandidateById(ID)).thenReturn(Optional.of(candidate));
        when(this.electionService.getElectionById(ID)).thenReturn(Optional.of(election));

        this.voteServiceToTest.saveVote(voteDTO);

        verify(voteRepository, times(1)).save(any(Vote.class));

    }

    @Test
    void testVoteIsSavedCorrectlyWhenCampaignIsNotPresent(){
        UserVotedInfoDTO voteDTO = UserVotedInfoDTO.builder()
                .electionId(ID)
                .build();

        when(this.electionService.getElectionById(ID)).thenReturn(Optional.empty());

        this.voteServiceToTest.saveVote(voteDTO);

        verify(voteRepository, never()).save(any(Vote.class));
    }

    @Test
    void testHasUserVotedForElectionReturnsFalseWhenUserVoted(){

        when(this.voteRepository
                .voteExistsByUserPinAndElectionId("0000000000", ID))
                .thenReturn(true);

        boolean result = this.voteServiceToTest.hasUserVotedForCampaign(USER_PIN, ID);

        assertTrue(result);

    }

    @Test
    void testHasUserVotedForCampaignReturnsTrueWhenUserHasNotVoted(){

        when(this.voteRepository
                .voteExistsByUserPinAndElectionId("0000000000", ID))
                .thenReturn(false);

        boolean result = this.voteServiceToTest.hasUserVotedForCampaign(USER_PIN, ID);

        assertFalse(result);

    }

}
