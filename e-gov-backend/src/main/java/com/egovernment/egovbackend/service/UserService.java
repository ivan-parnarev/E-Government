package com.egovernment.egovbackend.service;

import com.egovernment.egovbackend.domain.entity.Role;
import com.egovernment.egovbackend.domain.entity.User;
import com.egovernment.egovbackend.domain.enums.RoleEnum;
import com.egovernment.egovbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;


    public void initAdministrator() {
        if(this.userRepository.count() == 0){
            User user = User.builder()
                    .firstName("admin")
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

    public User createUserWithUserPin(String pin) {
        User user = User.builder().PIN(pin).build();
        this.userRepository.save(user);
        return user;
    }

    public Optional<User> getUserByPin(String pin) {
        return this.userRepository.findByPIN(pin);
    }
}
