package com.example.accesscontrol.domain;

import com.example.accesscontrol.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roles")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role extends BaseEntity  {

    @Enumerated(EnumType.STRING)
    @Column
    private RoleEnum roleName;

}

