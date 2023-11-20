package com.egovernment.egovbackend.service;

import com.egovernment.egovbackend.domain.entity.Role;
import com.egovernment.egovbackend.domain.entity.User;
import com.egovernment.egovbackend.domain.enums.RoleEnum;
import com.egovernment.egovbackend.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleService roleService;
    private UserService userServiceToTest;
    Role administratorRole;
    private final String USER_PIN = "123456";

    @BeforeEach
    void setUp() {
        this.userServiceToTest = new UserService(userRepository, roleService);
        administratorRole = new Role(RoleEnum.ADMINISTRATOR);
    }

    @Test
    public void testInitAdministratorAddsUserWithRoleAdministrator(){

        when(this.userRepository.count()).thenReturn(0L);

        when(this.roleService.getRole(RoleEnum.ADMINISTRATOR))
                .thenReturn(administratorRole);

        this.userServiceToTest.initAdministrator();

        verify(this.userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testShouldNotInitializeAdministratorWhenUserExists() {
        when(userRepository.count()).thenReturn(1L);

        this.userServiceToTest.initAdministrator();

        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    public void testGetAllUserVotesReturnsAllUserVotes(){

        Role userRole = new Role(RoleEnum.USER);
        Role ownerRole = new Role(RoleEnum.OWNER);

        List<Role> firstUserRoles = List.of(userRole, ownerRole, administratorRole);
        List<Role> secondUserRoles = List.of(ownerRole, userRole);

        User firstTestUser = User.builder()
                .roles(firstUserRoles)
                .firstName("Administartor")
                .build();

        User secondTestUser = User.builder()
                .roles(secondUserRoles)
                .firstName("Not Administrator")
                .build();

        when(this.userRepository.findAll()).thenReturn(List.of(firstTestUser, secondTestUser));

        Optional<User> userByRole = this.userServiceToTest.getUserByRole(administratorRole);
        assertTrue(userByRole.isPresent());

        User user = userByRole.get();
        assertEquals(firstTestUser.getFirstName(), user.getFirstName());

    }

    @Test
    void testNoUserWithSpecifiedRole() {
        when(userRepository.findAll()).thenReturn(List.of());

        Optional<User> result = this.userServiceToTest.getUserByRole(administratorRole);

        Assertions.assertFalse(result.isPresent());
    }

    @Test
    void createUserWithUserPinShouldSaveAndReturnUser() {
        String pin = "123456";
        User user = User.builder().PIN(pin).build();
        when(userRepository.save(any(User.class))).thenReturn(user);

        User createdUser = userServiceToTest.createUserWithUserPin(pin);

        assertNotNull(createdUser);
        assertEquals(pin, createdUser.getPIN());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void getUserByPinShouldReturnUserIfExists() {
        User userToTest = User.builder().PIN(USER_PIN).build();
        Optional<User> expectedUser = Optional.of(userToTest);
        when(userRepository.findByPIN(USER_PIN)).thenReturn(expectedUser);

        Optional<User> foundUser = userServiceToTest.getUserByPin(USER_PIN);

        assertTrue(foundUser.isPresent());
        assertEquals(USER_PIN, foundUser.get().getPIN());
    }

    @Test
    void getUserByPinShouldReturnEmptyIfNotExists() {
        when(userRepository.findByPIN(USER_PIN)).thenReturn(Optional.empty());
        Optional<User> foundUser = userServiceToTest.getUserByPin(USER_PIN);
        assertFalse(foundUser.isPresent());
    }

}
