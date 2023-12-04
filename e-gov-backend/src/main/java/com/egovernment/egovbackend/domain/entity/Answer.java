package com.egovernment.egovbackend.domain.entity;

import com.egovernment.egovbackend.hashing.HashUtil;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "answers")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Answer{
    @Id
    private String id;
    @Column
    private String answerText;

    @PrePersist
    private void generateId(){
        this.id = HashUtil.hashText(this.answerText);
    }

}
