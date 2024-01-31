package com.egovernment.main.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "results")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Result extends BaseEntity {

    @ManyToOne
    private Candidate candidate;
    @ManyToOne
    private Election election;
    @Column
    private Long votesCount;

}
