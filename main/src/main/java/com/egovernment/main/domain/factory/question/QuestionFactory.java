package com.egovernment.main.domain.factory.question;

import com.egovernment.main.domain.entity.Answer;
import com.egovernment.main.domain.entity.Campaign;
import com.egovernment.main.domain.entity.CensusQuestion;
import com.egovernment.main.domain.enums.QuestionCategory;

import java.util.List;

public class QuestionFactory implements QuestionFactoryInterface {

    @Override
    public CensusQuestion createQuestion(Campaign campaign, QuestionCategory questionCategory
            , String questionText, List<Answer> answers) {
        return CensusQuestion.builder()
                .campaign(List.of(campaign))
                .questionCategory(questionCategory)
                .text(questionText)
                .answers(answers)
                .build();
    }
}
