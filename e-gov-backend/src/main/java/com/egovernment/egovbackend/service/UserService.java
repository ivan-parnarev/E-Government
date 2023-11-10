package com.egovernment.egovbackend.service;

import com.egovernment.egovbackend.domain.dto.UserVotedInfoDTO;
import com.egovernment.egovbackend.domain.entity.Role;
import com.egovernment.egovbackend.domain.entity.User;
import com.egovernment.egovbackend.domain.enums.RoleEnum;
import com.egovernment.egovbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;


    public void initAdministrator() {
        if(this.userRepository.count() == 0){
            User user = User.builder()
                    .name("admin")
                    .roles(List.of(this.roleService.getRole(RoleEnum.ADMINISTRATOR)))
                    .build();
            this.userRepository.save(user);
        }
    }

    public Optional<User> getUserByRole(Role role) {
        return this.userRepository
                .findAll()
                .stream()
                .filter(p -> p.getRoles().contains(role))
                .findFirst();
    }
}
