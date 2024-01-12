package com.example.accesscontrol.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    @Column
    private String firstName;
    @Column
    private String middleName;
    @Column
    private String lastName;
    @Column
    private String email;
    @Column
    private String password;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Role> roles = new ArrayList<>();
    @Column
    private String PIN;
    @Column
    private boolean canVote;
    @Column
    private boolean isGuest;

}