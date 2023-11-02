package com.egovernment.egovbackend.services;

import com.egovernment.egovbackend.domain.dto.UserVotedInfoDTO;
import com.egovernment.egovbackend.domain.entities.User;
import com.egovernment.egovbackend.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private final String FIRST_USER_NAME = "FirstTestUserName";
    private final String FIRST_USER_VOTE_OPTION = "FirstTestUserVoteOption";

    private final String SECOND_USER_NAME = "SecondTestUserName";
    private final String SECOND_USER_VOTE_OPTION = "SecondTestUserVoteOption";

    @Mock
    private UserRepository userRepository;

    private UserService userServiceToTest;


    @BeforeEach
    void setUp() {
        this.userServiceToTest = new UserService(userRepository);
    }

    @Test
    public void testAddNewUserVote(){

        UserVotedInfoDTO userDTO = new UserVotedInfoDTO();
        userDTO.setVoterName(FIRST_USER_NAME);
        userDTO.setVoteOption(FIRST_USER_VOTE_OPTION);

        User userToBeSaved = User.builder()
                .voterName(userDTO.getVoterName())
                .voteOption(userDTO.getVoteOption())
                .build();

        when(this.userRepository.save(any(User.class))).thenReturn(userToBeSaved);

        UserVotedInfoDTO resultUserVoted = this.userServiceToTest.addNewUserVote(userDTO);

        assertEquals(userDTO.getVoterName(), resultUserVoted.getVoterName());
        assertEquals(userDTO.getVoteOption(), resultUserVoted.getVoteOption());

        verify(this.userRepository, times(1)).save(any(User.class));
    }


    @Test
    public void testGetAllUserVotesReturnsAllUserVotes(){

        User firstTestUser = new User(FIRST_USER_NAME, FIRST_USER_VOTE_OPTION);
        User secondTestUser = new User(SECOND_USER_NAME, SECOND_USER_VOTE_OPTION);

        when(this.userRepository.findAll()).thenReturn(List.of(firstTestUser, secondTestUser));

        List<UserVotedInfoDTO> result = this.userServiceToTest.getAllUserVotes();

        assertEquals(2, result.size());

        assertEquals(firstTestUser.getVoterName(), result.get(0).getVoterName());
        assertEquals(firstTestUser.getVoteOption(), result.get(0).getVoteOption());

        assertEquals(secondTestUser.getVoterName(), result.get(1).getVoterName());
        assertEquals(secondTestUser.getVoteOption(), result.get(1).getVoteOption());

    }

}
