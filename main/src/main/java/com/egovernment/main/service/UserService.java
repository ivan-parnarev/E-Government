package com.egovernment.main.service;

import com.egovernment.main.domain.entity.Role;
import com.egovernment.main.domain.entity.User;
import com.egovernment.main.domain.enums.RoleEnum;
import com.egovernment.main.repository.UserRepository;
import jakarta.transaction.Transactional;
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
                    .PIN("1111111111")
                    .build();
            this.userRepository.save(user);
        }
    }

    @Transactional
    public Optional<User> getUserByRole(Role role) {
        return this.userRepository
                .findAll()
                .stream()
                .filter(p -> p.getRoles().contains(role))
                .findFirst();
    }

    @Transactional
    public boolean userIsAdmin(String userPin){
        Optional<User> optionalUser = this.getUserByPin(userPin);

        return optionalUser.map(user -> user.getRoles()
                .stream()
                .map(Role::getRoleName)
                .toList()
                .contains(RoleEnum.ADMINISTRATOR)).orElse(false);
    }

    public User createUserWithUserPin(String pin) {
        Role guestRole = this.roleService.getRole(RoleEnum.GUEST);
        User user = User.builder().PIN(pin).roles(List.of(guestRole)).build();
        this.userRepository.save(user);
        return user;
    }

    public Optional<User> getUserByPin(String pin) {
        return this.userRepository.findByPIN(pin);
    }
}
