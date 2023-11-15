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

import static org.junit.jupiter.api.Assertions.assertEquals;
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
                .name("Administartor")
                .build();

        User secondTestUser = User.builder()
                .roles(secondUserRoles)
                .name("Not Administrator")
                .build();

        when(this.userRepository.findAll()).thenReturn(List.of(firstTestUser, secondTestUser));

        Optional<User> userByRole = this.userServiceToTest.getUserByRole(administratorRole);
        Assertions.assertTrue(userByRole.isPresent());

        User user = userByRole.get();
        assertEquals(firstTestUser.getName(), user.getName());

    }

    @Test
    void testNoUserWithSpecifiedRole() {
        when(userRepository.findAll()).thenReturn(List.of());

        Optional<User> result = this.userServiceToTest.getUserByRole(administratorRole);

        Assertions.assertFalse(result.isPresent());
    }

}
