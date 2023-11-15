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

    @BeforeEach
    void setUp() {
        this.voteServiceToTest = new VoteService(voteRepository, electionService, candidateService, userService);
    }

    @Test
    void testVoteIsSavedCorrectlyWhenCampaignIsPresent(){

        Candidate candidate = Candidate.builder()
                .name("Test Candiadte")
                .build();

        Election election = Election.builder()
                .electionType(ElectionType.PARLIAMENT)
                .build();

        UserVotedInfoDTO voteDTO = UserVotedInfoDTO.builder()
                .userPin("0000000000")
                .electionId(1L)
                .candidateId(1L)
                .build();

        when(this.candidateService.getCandidateById(1L)).thenReturn(Optional.of(candidate));
        when(this.electionService.getElectionById(1L)).thenReturn(Optional.of(election));

        this.voteServiceToTest.saveVote(voteDTO);

        verify(voteRepository, times(1)).save(any(Vote.class));

    }

    @Test
    void testVoteIsSavedCorrectlyWhenCampaignIsNotPresent(){
        UserVotedInfoDTO voteDTO = UserVotedInfoDTO.builder()
                .electionId(1L)
                .build();

        when(this.electionService.getElectionById(1L)).thenReturn(Optional.empty());

        this.voteServiceToTest.saveVote(voteDTO);

        verify(voteRepository, never()).save(any(Vote.class));
    }

    @Test
    void testHasUserVotedForElectionReturnsFalseWhenUserVoted(){
        String userPin = "0000000000";
        Long electionId = 1L;

        when(this.voteRepository
                .voteExistsByUserPinAndElectionId("0000000000", 1L))
                .thenReturn(true);

        boolean result = this.voteServiceToTest.hasUserVotedForCampaign(userPin, electionId);

        assertTrue(result);

    }

    @Test
    void testHasUserVotedForCampaignReturnsTrueWhenUserHasNotVoted(){
        String userPin = "0000000000";
        Long electionId = 1L;

        when(this.voteRepository
                .voteExistsByUserPinAndElectionId("0000000000", 1L))
                .thenReturn(false);

        boolean result = this.voteServiceToTest.hasUserVotedForCampaign(userPin, electionId);

        assertFalse(result);

    }

}
