package com.egovernment.egovauth.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    @Column
    private String address;
}
