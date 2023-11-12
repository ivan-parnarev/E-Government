package com.egovernment.egovbackend.domain.template;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnswerCategory {

    private List<QuestionAnswerTemplate> demographicInfo;
    private List<QuestionAnswerTemplate> educationInfo;

}
