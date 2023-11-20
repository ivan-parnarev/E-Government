package com.egovernment.egovbackend.domain.factory.question;

import com.egovernment.egovbackend.domain.entity.Campaign;
import com.egovernment.egovbackend.domain.entity.CensusQuestion;
import com.egovernment.egovbackend.domain.enums.QuestionCategory;

import java.util.List;

public class QuestionFactory implements QuestionFactoryInterface {
    @Override
    public CensusQuestion createQuestion(Campaign campaign, QuestionCategory questionCategory, String text) {
        return CensusQuestion.builder()
                .campaign(List.of(campaign))
                .questionCategory(questionCategory)
                .text(text)
                .build();
    }
}
