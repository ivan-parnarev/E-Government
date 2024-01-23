package com.egovernment.main.domain.entity;

import com.egovernment.main.domain.enums.QuestionCategory;
import com.egovernment.main.hashing.HashUtil;
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
public class CensusQuestion {

    @Id
    private String questionHashedText;
    @Column
    private String text;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Campaign> campaign;
    @Enumerated(EnumType.STRING)
    private QuestionCategory questionCategory;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Answer> answers;

    @PrePersist
    private void generateId() {
        this.questionHashedText = HashUtil.hashText(text);
    }

}
