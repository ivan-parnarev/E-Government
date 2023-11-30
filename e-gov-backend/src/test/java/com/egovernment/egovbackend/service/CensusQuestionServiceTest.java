package com.egovernment.egovbackend.service;

import com.egovernment.egovbackend.domain.dto.censusCampaign.CensusQuestionDTO;
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
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CensusQuestionServiceTest {

    @Mock
    private CensusQuestionRepository censusQuestionRepository;

    @InjectMocks
    private CensusQuestionService censusQuestionService;

//    @Test
//    void testGetCensusQuestionsForCampaignQuestionsReturned() {
//
//        CensusQuestion firstQuestion = CensusQuestion.builder()
//                .text(Questions.CITIZENSHIP)
//                .questionCategory(QuestionCategory.PERSONAL)
//                .build();
//
//         CensusQuestion secondQuestion = CensusQuestion.builder()
//                 .text(Questions.)
//                 .questionCategory(QuestionCategory.PERSONAL)
//                 .build();
//
//         when(censusQuestionRepository.findAllByCampaignId(any()))
//                 .thenReturn(List.of(firstQuestion, secondQuestion));
//
//        List<CensusQuestionDTO> result = censusQuestionService.getCensusQuestionsForCampaign(2L);
//
//        Assertions.assertNotNull(result);
//        Assertions.assertEquals(result.size(), 2);
//        Assertions.assertEquals(result.get(0).getText(), firstQuestion.getText());
//        Assertions.assertEquals(result.get(0).getQuestionCategory(), firstQuestion.getQuestionCategory().name());
//    }
//
//    @Test
//    void testGetQuestionByIdQuestionReturned() {
//        CensusQuestion censusQuestion = CensusQuestion.builder()
//                .questionCategory(QuestionCategory.PERSONAL)
//                .text(Questions.FIRST_NAME)
//                .build();
//
//        when(censusQuestionRepository.findById(any())).thenReturn(Optional.of(censusQuestion));
//
//        CensusQuestion questionById = censusQuestionService.getQuestionById(1L);
//
//        Assertions.assertNotNull(questionById);
//        Assertions.assertEquals(questionById.getText(), censusQuestion.getText());
//        Assertions.assertEquals(questionById.getQuestionCategory(), censusQuestion.getQuestionCategory());
//    }
}
