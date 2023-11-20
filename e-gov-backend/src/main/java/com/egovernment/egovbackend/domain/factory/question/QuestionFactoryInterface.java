package com.egovernment.egovbackend.domain.factory.question;


import com.egovernment.egovbackend.domain.entity.Campaign;
import com.egovernment.egovbackend.domain.entity.CensusQuestion;
import com.egovernment.egovbackend.domain.enums.QuestionCategory;

public interface QuestionFactoryInterface {

    CensusQuestion createQuestion(Campaign campaign, QuestionCategory questionCategory, String text);
}
