package com.egovernment.egovauth.domain.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

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
