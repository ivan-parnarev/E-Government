package com.egovernment.egovbackend.domain.entity;

import com.egovernment.egovbackend.domain.enums.QuestionCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_answers")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAnswer extends BaseEntity {

    @ManyToOne
    private User user;
    @OneToOne
    private CensusQuestion censusQuestion;
    @OneToOne
    private Campaign campaign;
    @Enumerated(EnumType.STRING)
    private QuestionCategory questionCategory;
    @Column
    private String answer;
}
