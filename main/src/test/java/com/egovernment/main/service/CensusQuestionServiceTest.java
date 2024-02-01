package com.egovernment.main.service;

import com.egovernment.main.domain.dto.censusCampaign.AnswerDTO;
import com.egovernment.main.domain.dto.censusCampaign.CensusQuestionDTO;
import com.egovernment.main.domain.entity.Answer;
import com.egovernment.main.domain.entity.Campaign;
import com.egovernment.main.domain.entity.CensusQuestion;
import com.egovernment.main.domain.enums.QuestionCategory;
import com.egovernment.main.hashing.HashUtil;
import com.egovernment.main.repository.CampaignRepository;
import com.egovernment.main.repository.CensusQuestionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CensusQuestionServiceTest {

    @InjectMocks
    private CensusQuestionService censusQuestionService;
    @Mock
    private CampaignRepository campaignRepository;
    @Mock
    private CensusQuestionRepository censusQuestionRepository;
    @Mock
    private AnswerService answerService;
    @Mock
    private ModelMapper modelMapper;

    private final String FIRST_QUESTION_TEXT = "First question";
    private final String FIRST_ANSWER_TEXT = "First answer";
    private final String SECOND_ANSWER_TEXT = "Second answer";
    private final Long ID = 1L;
    private final QuestionCategory QUESTION_CATEGORY = QuestionCategory.PERSONAL;

    private final Answer FIRST_ANSWER = Answer.builder().answerText(FIRST_ANSWER_TEXT).build();

    private final AnswerDTO FIRST_ANSWER_DTO = AnswerDTO.builder().answerText(FIRST_ANSWER_TEXT).build();


    @Test
    void testGetCensusQuestionsForCampaign() {

        Answer secondAnswer = Answer.builder().answerText(SECOND_ANSWER_TEXT).build();

        AnswerDTO secondAnswerDTO = AnswerDTO.builder().answerText(SECOND_ANSWER_TEXT).build();

        CensusQuestion censusQuestion = CensusQuestion.builder()
                .questionCategory(QUESTION_CATEGORY)
                .text(FIRST_QUESTION_TEXT)
                .answers(List.of(FIRST_ANSWER, secondAnswer))
                .build();

        CensusQuestionDTO censusQuestionDTO = CensusQuestionDTO.builder()
                .QuestionCategory(QUESTION_CATEGORY.name())
                .text(FIRST_QUESTION_TEXT)
                .answers(List.of(FIRST_ANSWER_DTO, secondAnswerDTO))
                .build();

        List<CensusQuestion> questions = Arrays.asList(censusQuestion);
        lenient().when(modelMapper.map(any(Answer.class), eq(AnswerDTO.class))).thenReturn(FIRST_ANSWER_DTO, secondAnswerDTO);

        lenient().when(modelMapper.map(any(CensusQuestion.class), eq(CensusQuestionDTO.class))).thenReturn(censusQuestionDTO);

        when(censusQuestionRepository.findAllByCampaignId(ID)).thenReturn(questions);

        List<CensusQuestionDTO> result = censusQuestionService.getCensusQuestionsForCampaign(ID);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(result.get(0).getQuestionCategory(), QUESTION_CATEGORY.name());
        assertEquals(result.get(0).getText(), FIRST_QUESTION_TEXT);
        assertEquals(result.get(0).getAnswers().get(0).getAnswerText(), FIRST_ANSWER_TEXT);
        assertEquals(result.get(0).getAnswers().get(1).getAnswerText(), SECOND_ANSWER_TEXT);
        verify(censusQuestionRepository).findAllByCampaignId(ID);
    }

    @Test
    void initTestQuestionsShouldNotInitializeIfQuestionsExist() {
        when(censusQuestionRepository.count()).thenReturn(ID);
        censusQuestionService.initTestQuestions();
        verify(censusQuestionRepository, never()).saveAll(anyList());
    }

    @Test
    void shouldInitializeQuestionsWhenRepositoryIsEmpty() {
        when(censusQuestionRepository.count()).thenReturn(0L);
        Campaign censusCampaign = Campaign.builder().build();
        when(this.campaignRepository.findById(2L)).thenReturn(Optional.of(censusCampaign));

        censusQuestionService.initTestQuestions();

        verify(censusQuestionRepository).saveAll(anyList());
    }

    @Test
    void getAllQuestionsAndTheirAnswers() {
        when(this.modelMapper.map(FIRST_ANSWER, AnswerDTO.class)).thenReturn(FIRST_ANSWER_DTO);

        CensusQuestion censusQuestion = CensusQuestion.builder()
                .questionCategory(QUESTION_CATEGORY)
                .text(FIRST_QUESTION_TEXT)
                .answers(List.of(FIRST_ANSWER))
                .build();

        when(this.censusQuestionRepository.findAll()).thenReturn(List.of(censusQuestion));

        List<CensusQuestionDTO> questions = this.censusQuestionService.getAllQuestionsAndTheirAnswers();

        assertEquals(1, questions.size());
        assertEquals(censusQuestion.getQuestionCategory().name(), questions.get(0).getQuestionCategory());
        assertEquals(censusQuestion.getAnswers().get(0).getAnswerText(), questions.get(0).getAnswers().get(0).getAnswerText());

    }

    @Test
    void getQuestionByTextReturnsTheRightQuestionIfItExists() {

        CensusQuestion censusQuestion = CensusQuestion.builder()
                .questionCategory(QUESTION_CATEGORY)
                .text(FIRST_QUESTION_TEXT)
                .build();

        String hashText = HashUtil.hashText("hashedTextTest");
        when(this.censusQuestionRepository.findByQuestionHashedText(hashText)).thenReturn(Optional.of(censusQuestion));

        Optional<CensusQuestion> question = this.censusQuestionService.getQuestionByText("hashedTextTest");

        assertTrue(question.isPresent());
        assertEquals(censusQuestion.getQuestionCategory(), question.get().getQuestionCategory());
        assertEquals(censusQuestion.getText(), question.get().getText());

    }


    @Test
    void createCensusQuestionsAddsTheNewCampaignToCampaignsListIfQuestionExists() {
        CensusQuestionDTO censusQuestionDTO = CensusQuestionDTO
                .builder()
                .text(FIRST_QUESTION_TEXT)
                .QuestionCategory(QUESTION_CATEGORY.name())
                .answers(List.of(FIRST_ANSWER_DTO))
                .build();

        Campaign campaign = Campaign.builder().build();
        List<Campaign> campaigns = new ArrayList<>();
        CensusQuestion existingQuestion = CensusQuestion.builder()
                .text(FIRST_QUESTION_TEXT)
                .campaign(campaigns)
                .build();

        when(censusQuestionRepository.findByQuestionHashedText(HashUtil.hashText(FIRST_QUESTION_TEXT))).thenReturn(Optional.of(existingQuestion));
        when(answerService.createAnswers(FIRST_ANSWER_TEXT)).thenReturn(List.of(FIRST_ANSWER));

        censusQuestionService.createCensusQuestions(List.of(censusQuestionDTO), campaign);

        verify(censusQuestionRepository).save(any(CensusQuestion.class));
    }


    @Test
    void createCensusQuestionsShouldCreateNewQuestionIfItDoesNotExists() {
        CensusQuestionDTO censusQuestionDTO = CensusQuestionDTO
                .builder()
                .text(FIRST_QUESTION_TEXT)
                .QuestionCategory(QUESTION_CATEGORY.name())
                .answers(List.of(FIRST_ANSWER_DTO))
                .build();

        Campaign campaign = Campaign.builder().build();

        when(censusQuestionRepository.findByQuestionHashedText( HashUtil.hashText(FIRST_QUESTION_TEXT))).thenReturn(Optional.empty());

        when(answerService.createAnswers(FIRST_ANSWER_TEXT)).thenReturn(List.of(FIRST_ANSWER));

        censusQuestionService.createCensusQuestions(List.of(censusQuestionDTO), campaign);

        verify(censusQuestionRepository).save(any(CensusQuestion.class));
    }

}


