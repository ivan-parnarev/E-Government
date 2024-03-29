package com.egovernment.main.domain.entity;

import com.egovernment.main.domain.enums.QuestionCategory;
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
    @ManyToOne
    private CensusQuestion censusQuestion;
    @ManyToOne
    private Campaign campaign;
    @Enumerated(EnumType.STRING)
    private QuestionCategory questionCategory;
    @ManyToOne
    private Answer answer;
}
