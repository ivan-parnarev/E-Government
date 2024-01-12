package com.egovernment.authentication.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "municipalities")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Municipality extends BaseEntity {

    @Column
    private String name;
}
