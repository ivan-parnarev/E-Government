package com.egovernment.egovbackend.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "census")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Census extends BaseEntity{

    @ManyToOne
    private Campaign campaign;
    @Column
    private String userPin;
    @Column
    private String answers;

}
