package com.egovernment.egovbackend.service;

import com.egovernment.egovbackend.domain.dto.censusCampaignDTO.CensusQuestionDTO;
import com.egovernment.egovbackend.domain.entity.CensusQuestion;
import com.egovernment.egovbackend.domain.enums.QuestionCategory;
import com.egovernment.egovbackend.repository.CensusQuestionRepository;
import com.egovernment.egovbackend.utils.Questions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CensusQuestionServiceTest {

    @Mock
    private CensusQuestionRepository censusQuestionRepository;

    @InjectMocks
    private CensusQuestionService censusQuestionService;

    @Test
    void testGetCensusQuestionsForCampaignQuestionsReturned() {

        CensusQuestion firstQuestion = CensusQuestion.builder()
                .text(Questions.FIRST_NAME)
                .questionCategory(QuestionCategory.PERSONAL)
                .build();

         CensusQuestion secondQuestion = CensusQuestion.builder()
                 .text(Questions.AGE)
                 .questionCategory(QuestionCategory.PERSONAL)
                 .build();

         when(censusQuestionRepository.findAllByCampaignId(any()))
                 .thenReturn(List.of(firstQuestion, secondQuestion));

        List<CensusQuestionDTO> result = censusQuestionService.getCensusQuestionsForCampaign(2L);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.size(), 2);
        Assertions.assertEquals(result.get(0).getText(), firstQuestion.getText());
        Assertions.assertEquals(result.get(0).getQuestionCategory(), firstQuestion.getQuestionCategory().name());
    }
}
