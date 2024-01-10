package com.example.egovernmentaccesscontrol.domain;

import com.example.egovernmentaccesscontrol.enums.RoleEnum;
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

