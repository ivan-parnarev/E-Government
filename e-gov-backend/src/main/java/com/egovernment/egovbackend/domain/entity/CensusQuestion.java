package com.egovernment.egovbackend.domain.entity;

import com.egovernment.egovbackend.domain.enums.CensusAnswerType;
import com.egovernment.egovbackend.domain.enums.QuestionCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "census_questions")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CensusQuestion extends BaseEntity {

    @Column
    private String text;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Campaign> campaign;
    @Enumerated(EnumType.STRING)
    private CensusAnswerType type;
    @Enumerated(EnumType.STRING)
    private QuestionCategory questionCategory;
}
