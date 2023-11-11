package com.egovernment.egovbackend.domain.template;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerCategory {

    private List<QuestionAnswer> demographicInfo;
    private List<QuestionAnswer> educationInfo;

}
