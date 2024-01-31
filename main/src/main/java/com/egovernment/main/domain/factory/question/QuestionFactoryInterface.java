package com.egovernment.egovbackend.domain.factory.question;


import com.egovernment.egovbackend.domain.entity.Answer;
import com.egovernment.egovbackend.domain.entity.Campaign;
import com.egovernment.egovbackend.domain.entity.CensusQuestion;
import com.egovernment.egovbackend.domain.enums.QuestionCategory;

import java.util.List;

public interface QuestionFactoryInterface {

    CensusQuestion createQuestion(Campaign campaign, QuestionCategory questionCategory, String text, List<Answer> answers);
}
