package com.egovernment.egovbackend.service;

import com.egovernment.egovbackend.domain.dto.UserVotedInfoDTO;
import com.egovernment.egovbackend.domain.entities.User;
import com.egovernment.egovbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserVotedInfoDTO addNewUserVote(UserVotedInfoDTO userDTO) {
        User userToBeSaved = User.builder()
                .voterName(userDTO.getVoterName())
                .voteOption(userDTO.getVoteOption())
                .build();

        User savedUser = this.userRepository.save(userToBeSaved);

        return mapSavedUserToDTO(savedUser);
    }
    public List<UserVotedInfoDTO> getAllUserVotes() {
        List<User> allUserVotes = this.userRepository.findAll();

        List<UserVotedInfoDTO> savedUserVotesList = allUserVotes.stream()
                .map(this::mapSavedUserToDTO)
                .collect(Collectors.toList());
        return savedUserVotesList;
    }

    private UserVotedInfoDTO mapSavedUserToDTO(User savedUser) {
        return UserVotedInfoDTO.builder()
                .voterName(savedUser.getVoterName())
                .voteOption(savedUser.getVoteOption())
                .build();
    }

}
