package com.egovernment.egovauth.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Column(unique = true)
    private String userPin;
    @Column
    private String firstName;
    @Column
    private String middleName;
    @Column
    private String lastName;
    @ManyToOne
    private Address address;
    @Column
    private boolean isAdmin;
}
