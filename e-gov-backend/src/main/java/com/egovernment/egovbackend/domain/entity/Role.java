package com.egovernment.egovbackend.domain.entity;

import com.egovernment.egovbackend.domain.enums.RoleEnum;
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
