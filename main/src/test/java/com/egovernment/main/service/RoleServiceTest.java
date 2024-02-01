package com.egovernment.main.service;

import com.egovernment.main.domain.entity.Role;
import com.egovernment.main.domain.enums.RoleEnum;
import com.egovernment.main.repository.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RoleServiceTest {

    @Mock
    private  RoleRepository roleRepository;

    private RoleService roleServiceToTest;

    @BeforeEach
    void setUp() {
        this.roleServiceToTest = new RoleService(roleRepository);
    }

    @Test
    void testRolesAreSavedWhenThereAreNoEntitiesInTheDatabase(){

        when(this.roleRepository.count()).thenReturn(0L);
        this.roleServiceToTest.initRoles();
        verify(roleRepository, times(1)).saveAll(anyList());

    }

    @Test
    void testRolesAreNotSavedWhenThereAreEntitiesInTheDatabase(){

        when(this.roleRepository.count()).thenReturn(2L);
        this.roleServiceToTest.initRoles();
        verify(roleRepository, never()).saveAll(anyList());

    }

    @Test
    void testGetRoleReturnsTheCorrectRole(){
        Role role = new Role(RoleEnum.ADMINISTRATOR);
        when(this.roleRepository.findByRoleName(RoleEnum.ADMINISTRATOR)).thenReturn(role);

        Role resultRole = this.roleServiceToTest.getRole(RoleEnum.ADMINISTRATOR);

        assertEquals(role.getRoleName(), resultRole.getRoleName());
    }

}
