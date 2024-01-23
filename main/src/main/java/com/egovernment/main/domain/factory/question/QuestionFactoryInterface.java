package com.egovernment.main.domain.factory.question;


import com.egovernment.main.domain.entity.Answer;
import com.egovernment.main.domain.entity.Campaign;
import com.egovernment.main.domain.entity.CensusQuestion;
import com.egovernment.main.domain.enums.QuestionCategory;

import java.util.List;

public interface QuestionFactoryInterface {

    CensusQuestion createQuestion(Campaign campaign, QuestionCategory questionCategory, String text, List<Answer> answers);
}
