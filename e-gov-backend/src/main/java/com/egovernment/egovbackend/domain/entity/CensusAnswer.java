package com.egovernment.egovbackend.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "census_answers")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CensusAnswer extends BaseEntity {

    @ManyToOne
    private User user;
    @OneToOne
    private CensusQuestion censusQuestion;
    @Column
    private String answer;
}
