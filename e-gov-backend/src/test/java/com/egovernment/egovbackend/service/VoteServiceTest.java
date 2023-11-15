package com.egovernment.egovbackend.service;

import com.egovernment.egovbackend.domain.dto.CandidateTemplateDTO;
import com.egovernment.egovbackend.domain.dto.campaignDto.UserVotedInfoDTO;
import com.egovernment.egovbackend.domain.entity.Campaign;
import com.egovernment.egovbackend.domain.entity.Vote;
import com.egovernment.egovbackend.domain.enums.CampaignType;
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

//    @Mock
//    private  VoteRepository voteRepository;
//    @Mock
//    private  CampaignService campaignService;
//
//    @Mock
//    private UserService userService;
//
//    private VoteService voteServiceToTest;
//
//    @BeforeEach
//    void setUp() {
//        this.voteServiceToTest = new VoteService(voteRepository, campaignService, userService);
//    }
//
//    @Test
//    void testVoteIsSavedCorrectlyWhenCampaignIsPresent(){
//
//        Campaign campaign = Campaign.builder()
//                .campaignType(CampaignType.VOTING)
//                .campaignTopic("Парламентарни избори")
//                .build();
//
//        CandidateTemplateDTO candidate = CandidateTemplateDTO.builder()
//                .name("ПП")
//                .number(12)
//                .id(3)
//                .build();
//
//        UserVotedInfoDTO voteDTO = UserVotedInfoDTO.builder()
//                .pin("0000000000")
//                .campaignId(1L)
//                .candidate(candidate)
//                .build();
//
//        when(this.campaignService.getCampaignById(1L)).thenReturn(Optional.of(campaign));
//
//        this.voteServiceToTest.saveVote(voteDTO);
//
//        verify(voteRepository, times(1)).save(any(Vote.class));
//
//    }
//
//    @Test
//    void testVoteIsSavedCorrectlyWhenCampaignIsNotPresent(){
//        UserVotedInfoDTO voteDTO = UserVotedInfoDTO.builder()
//                .campaignId(1L)
//                .build();
//
//        when(this.campaignService.getCampaignById(1L)).thenReturn(Optional.empty());
//
//        this.voteServiceToTest.saveVote(voteDTO);
//
//        verify(voteRepository, never()).save(any(Vote.class));
//    }
//
//    @Test
//    void testHasUserVotedForCampaignReturnsFalseWhenUserVoted(){
//        String userPin = "0000000000";
//        Long campaignId = 1L;
//
//        when(this.voteRepository
//                .voteExistsByUserPinAndCampaignId("0000000000", 1L))
//                .thenReturn(true);
//
//        boolean result = this.voteServiceToTest.hasUserVotedForCampaign(userPin, campaignId);
//
//        assertTrue(result);
//
//    }
//
//    @Test
//    void testHasUserVotedForCampaignReturnsTrueWhenUserHasNotVoted(){
//        String userPin = "0000000000";
//        Long campaignId = 1L;
//
//        when(this.voteRepository
//                .voteExistsByUserPinAndCampaignId("0000000000", 1L))
//                .thenReturn(false);
//
//        boolean result = this.voteServiceToTest.hasUserVotedForCampaign(userPin, campaignId);
//
//        assertFalse(result);
//
//    }
    @Mock
    private  VoteRepository voteRepository;
    @Mock
    private  CampaignService campaignService;

    @Mock
    private UserService userService;

    private VoteService voteServiceToTest;

    @BeforeEach
    void setUp() {
        this.voteServiceToTest = new VoteService(voteRepository, campaignService, userService);
    }

    @Test
    void testVoteIsSavedCorrectlyWhenCampaignIsPresent(){

        Campaign campaign = Campaign.builder()
                .campaignType(CampaignType.VOTING)
                .title("Парламентарни избори")
                .build();

        CandidateTemplateDTO candidate = CandidateTemplateDTO.builder()
                .name("ПП")
                .candidateNumber(12)
                .id(3)
                .build();

        UserVotedInfoDTO voteDTO = UserVotedInfoDTO.builder()
                .pin("0000000000")
                .campaignId(1L)
                .candidate(candidate)
                .build();

        when(this.campaignService.getCampaignById(1L)).thenReturn(Optional.of(campaign));

        this.voteServiceToTest.saveVote(voteDTO);

        verify(voteRepository, times(1)).save(any(Vote.class));

    }

    @Test
    void testVoteIsSavedCorrectlyWhenCampaignIsNotPresent(){
        UserVotedInfoDTO voteDTO = UserVotedInfoDTO.builder()
                .campaignId(1L)
                .build();

        when(this.campaignService.getCampaignById(1L)).thenReturn(Optional.empty());

        this.voteServiceToTest.saveVote(voteDTO);

        verify(voteRepository, never()).save(any(Vote.class));
    }

    @Test
    void testHasUserVotedForCampaignReturnsFalseWhenUserVoted(){
        String userPin = "0000000000";
        Long campaignId = 1L;

        when(this.voteRepository
                .voteExistsByUserPinAndCampaignId("0000000000", 1L))
                .thenReturn(true);

        boolean result = this.voteServiceToTest.hasUserVotedForCampaign(userPin, campaignId);

        assertTrue(result);

    }

    @Test
    void testHasUserVotedForCampaignReturnsTrueWhenUserHasNotVoted(){
        String userPin = "0000000000";
        Long campaignId = 1L;

        when(this.voteRepository
                .voteExistsByUserPinAndCampaignId("0000000000", 1L))
                .thenReturn(false);

        boolean result = this.voteServiceToTest.hasUserVotedForCampaign(userPin, campaignId);

        assertFalse(result);

    }


}
