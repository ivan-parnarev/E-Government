package com.egovernment.main.domain.entity;

import com.egovernment.main.domain.enums.ElectionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "elections")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Election extends BaseEntity{

    @Enumerated(EnumType.STRING)
    @Column
    private ElectionType electionType;
    @OneToOne
    private Campaign campaign;

}
