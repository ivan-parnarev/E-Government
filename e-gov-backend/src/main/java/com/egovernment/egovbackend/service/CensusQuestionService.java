package com.egovernment.egovbackend.service;

import com.egovernment.egovbackend.domain.dto.censusCampaignDTO.CensusQuestionDTO;
import com.egovernment.egovbackend.domain.entity.Campaign;
import com.egovernment.egovbackend.domain.entity.CensusQuestion;
import com.egovernment.egovbackend.domain.enums.QuestionCategory;
import com.egovernment.egovbackend.domain.factory.question.QuestionFactory;
import com.egovernment.egovbackend.repository.CampaignRepository;
import com.egovernment.egovbackend.repository.CensusQuestionRepository;
import com.egovernment.egovbackend.utils.Questions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CensusQuestionService {

    private final CampaignRepository campaignRepository;
    private final CensusQuestionRepository censusQuestionRepository;
    private final QuestionFactory questionFactory = new QuestionFactory();

    public List<CensusQuestionDTO> getCensusQuestionsForCampaign(Long campaignId) {
        return this.censusQuestionRepository.findAllByCampaignId(campaignId)
                .stream().map(this::mapQuestionToDTO)
                .collect(Collectors.toList());
    }

    public void initTestQuestions() {

        Campaign censusCampaign = campaignRepository.findById(2L).get();

        QuestionCategory category = QuestionCategory.PERSONAL;

        CensusQuestion testQuestion1 = questionFactory.createQuestion(censusCampaign, category, Questions.FIRST_NAME);
        CensusQuestion testQuestion2 = questionFactory.createQuestion(censusCampaign, category, Questions.MIDDLE_NAME);
        CensusQuestion testQuestion3 = questionFactory.createQuestion(censusCampaign, category, Questions.LAST_NAME);
        CensusQuestion testQuestion4 = questionFactory.createQuestion(censusCampaign, category, Questions.GENDER);
        CensusQuestion testQuestion5 = questionFactory.createQuestion(censusCampaign, category, Questions.AGE);
        CensusQuestion testQuestion6 = questionFactory.createQuestion(censusCampaign, category, Questions.PLACE_OF_BIRTH);
        CensusQuestion testQuestion7 = questionFactory.createQuestion(censusCampaign, category, Questions.MARITAL_STATUS);
        CensusQuestion testQuestion8 = questionFactory.createQuestion(censusCampaign, category, Questions.EDUCATIONAL_STATUS);
        CensusQuestion testQuestion9 = questionFactory.createQuestion(censusCampaign, category, Questions.CITIZENSHIP);

        List<CensusQuestion> testCensusQuestions = List.of(testQuestion1, testQuestion2, testQuestion3, testQuestion4,
                testQuestion5, testQuestion6, testQuestion7, testQuestion8, testQuestion9);

        this.censusQuestionRepository.saveAll(testCensusQuestions);
    }

    private CensusQuestionDTO mapQuestionToDTO(CensusQuestion question) {
        return CensusQuestionDTO.builder()
                .text(question.getText())
                .QuestionCategory(String.valueOf(question.getQuestionCategory()))
                .build();
    }
}
