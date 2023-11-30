package com.egovernment.egovbackend.service;

import com.egovernment.egovbackend.domain.dto.censusCampaign.CensusQuestionDTO;
import com.egovernment.egovbackend.domain.dto.censusCampaign.AnswerDTO;
import com.egovernment.egovbackend.domain.dto.censusCampaign.CensusQuestionDTO;
import com.egovernment.egovbackend.domain.entity.Answer;
import com.egovernment.egovbackend.domain.entity.Campaign;
import com.egovernment.egovbackend.domain.entity.CensusQuestion;
import com.egovernment.egovbackend.domain.enums.QuestionCategory;
import com.egovernment.egovbackend.domain.factory.question.QuestionFactory;
import com.egovernment.egovbackend.hashing.HashUtil;
import com.egovernment.egovbackend.repository.CampaignRepository;
import com.egovernment.egovbackend.repository.CensusQuestionRepository;
import com.egovernment.egovbackend.utils.Questions;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CensusQuestionService {

    private final CampaignRepository campaignRepository;
    private final CensusQuestionRepository censusQuestionRepository;
    private final AnswerService answerService;
    private final QuestionFactory questionFactory = new QuestionFactory();
    private final ModelMapper modelMapper;

    public List<CensusQuestionDTO> getCensusQuestionsForCampaign(Long campaignId) {
        return this.censusQuestionRepository.findAllByCampaignId(campaignId)
                .stream().map(this::mapQuestionToDTO)
                .collect(Collectors.toList());
    }

    public void initTestQuestions() {

        Campaign censusCampaign = campaignRepository.findById(2L).get();

        List<Answer> incomeAnswers = this.answerService.createAnswers
                ("933 - 1200; 1201 - 1500; 1501 - 2300; 2301 - 2700; 2701 - 3400; над 3401");
        CensusQuestion testQuestion1 = questionFactory.createQuestion(censusCampaign, QuestionCategory.PERSONAL, Questions.INCOME, incomeAnswers);

        List<Answer> religionAnswers = this.answerService.createAnswers
                ("Православно християнство; Католицизъм; Ислям; Протестантство; Атеизъм; Други религии; Не съм вярващ");
        CensusQuestion testQuestion2 = questionFactory.createQuestion(censusCampaign, QuestionCategory.PERSONAL, Questions.RELIGION, religionAnswers);

        List<Answer> languageAnswers = this.answerService.createAnswers
                ("Български език; Турски език; Ромски езици; Английски език; Руски език; Други езици");
        CensusQuestion testQuestion3 = questionFactory.createQuestion(censusCampaign, QuestionCategory.PERSONAL, Questions.NATIVE_LANGUAGE, languageAnswers);

        List<Answer> degreeAnswers = this.answerService.createAnswers
                ("Основно; Средно; Бакалавър; Магистър; Докторантура; Нямам");
        CensusQuestion testQuestion4 = questionFactory.createQuestion(censusCampaign, QuestionCategory.PERSONAL, Questions.DEGREE_LEVEL, degreeAnswers);

        List<Answer> jobPositionAnswers = this.answerService.createAnswers
                ("Стажант; Управител; Работник; Директор; Мениджър; Не работя");
        CensusQuestion testQuestion5 = questionFactory.createQuestion(censusCampaign, QuestionCategory.PERSONAL, Questions.JOB_POSITION, jobPositionAnswers);


        List<Answer> buildingTypeAnswers = this.answerService.createAnswers
                ("Къща; Жилищен блок, кооперация; Сграда от смесен тип; Общежитие; Вила; " +
                        "Сграда за колективно домакинство(пансион, манастир, дом за деца, дом за стари хора и др.); Дом за временно настаняване; Друго");
        CensusQuestion testQuestion6 = questionFactory.createQuestion(censusCampaign, QuestionCategory.HOUSING, Questions.BUILDING_TYPE, buildingTypeAnswers);

        List<Answer> dwellingTypeAnswers = this.answerService.createAnswers
                ("В жилищна сграда; В нежилищна сграда(административна, стопанска и др.); " +
                        "Колективно жилище (пансион, манастир, дом за деца, дом за стари хора и др.); " +
                        "Примитивно жилище (колиба, барака и др.); Подвижно жилище (фургон, каравана и др.); Без жилище");
        CensusQuestion testQuestion7 = questionFactory.createQuestion(censusCampaign, QuestionCategory.HOUSING, Questions.DWELLING_TYPE, dwellingTypeAnswers);

        List<Answer> dwellingUseAnswers = this.answerService.createAnswers
                ("Жилище за постоянно (обичайно) пребиваване; Жилище за сезонно или ваканционно пребиваване; Жилище, обитавано от лица, които не са обект на преброяване; " +
                        "Жилището е колективно; Жилището е необитавано по друга причина");
        CensusQuestion testQuestion8 = questionFactory.createQuestion(censusCampaign, QuestionCategory.HOUSING, Questions.DWELLING_USE, dwellingUseAnswers);

        List<Answer> dwellingOwnershipAnswers = this.answerService.createAnswers("Държавно; Общинско; " +
                "Частно, на българско физическо лице; Частно, на българско юридическо лице; " +
                "Частно, на чуждестранно физическо лице; Частно, на чуждестранно юридическо лице; Жилището е необитавано\n");
        CensusQuestion testQuestion9 = questionFactory.createQuestion(censusCampaign, QuestionCategory.HOUSING, Questions.DWELLING_OWNERSHIP, dwellingOwnershipAnswers);

        List<Answer> dwellingResidenceAnswers = this.answerService.createAnswers("Собственици; Наематели; Друг вид ползватели (нито едно лице не е собственик или наемател);" +
                " Жилището е колективно; Жилището е необитавано");
        CensusQuestion testQuestion10 = questionFactory.createQuestion(censusCampaign, QuestionCategory.HOUSING, Questions.DWELLING_RESIDENTS, dwellingResidenceAnswers);


        List<Answer> citizenshipAnswers = this.answerService.createAnswers("България; България и друга държава; Държава от ЕС; Друга държава; Без гражданство");
        CensusQuestion testQuestion11 = questionFactory.createQuestion(censusCampaign, QuestionCategory.POPULATION, Questions.CITIZENSHIP, citizenshipAnswers);

        List<Answer> ethnicityAnswers = this.answerService.createAnswers("Българска; Турска; Ромска; Друга; Не желая да отговоря");
        CensusQuestion testQuestion12 = questionFactory.createQuestion(censusCampaign, QuestionCategory.POPULATION, Questions.ETHNICITY, ethnicityAnswers);

        List<Answer> residenceAnswers = this.answerService.createAnswers("Статут на бежанец; Хуманитарен статут; Предоставено убежище; " +
                "Лице в процедура по предоставяне на международна закрила; Продължително пребиваване (с разрешен срок до една година);" +
                " Дългосрочно пребиваване (с разрешен срок до пет години); Постоянно пребиваване; Друго");
        CensusQuestion testQuestion13 = questionFactory.createQuestion(censusCampaign, QuestionCategory.POPULATION, Questions.RESIDENCE_STATUS, residenceAnswers);

        List<Answer> martialStatusAnswers = this.answerService.createAnswers("Никога не встъпвал/а в брак; Женен/омъжена; Разведен/разведена; Вдовец/вдовица");
        CensusQuestion testQuestion14 = questionFactory.createQuestion(censusCampaign, QuestionCategory.POPULATION, Questions.MARTIAL_STATUS, martialStatusAnswers);


        List<Answer> healthRateAnswers = this.answerService.createAnswers("Много добро; Добро; Лошо; Много лошо; Не мога да определя; Не желая да отговоря");
        CensusQuestion testQuestion15 = questionFactory.createQuestion(censusCampaign, QuestionCategory.HEALTH, Questions.HEALTH_RATE, healthRateAnswers);

        List<Answer> healthProblemAnswers = this.answerService.createAnswers("Да, силно ограничен/а; Да, ограничен/а, но не толкова силно; " +
                "Не, не съм бил/а ограничен/а; Не мога да определя; Не желая да отговоря");
        CensusQuestion testQuestion16 = questionFactory.createQuestion(censusCampaign, QuestionCategory.HEALTH, Questions.HEALTH_PROBLEM_LIMITATIONS, healthProblemAnswers);

        List<Answer> workCapacityAnswers = this.answerService.createAnswers("Нямам; До 50%; 50 - 70%; 71 - 90%; Над 90%; Не желая да отговоря");
        CensusQuestion testQuestion17 = questionFactory.createQuestion(censusCampaign, QuestionCategory.HEALTH, Questions.PERMANENT_REDUCED_WORK_CAPACITY, workCapacityAnswers);

        List<CensusQuestion> testCensusQuestions = List.of(testQuestion1, testQuestion2, testQuestion3, testQuestion4,
                testQuestion5, testQuestion6, testQuestion7, testQuestion8, testQuestion9, testQuestion10, testQuestion11,
                testQuestion12, testQuestion13, testQuestion14, testQuestion15, testQuestion16, testQuestion17);

        this.censusQuestionRepository.saveAll(testCensusQuestions);
    }

    private CensusQuestionDTO mapQuestionToDTO(CensusQuestion question) {

        List<AnswerDTO> answers = question.getAnswers()
                .stream()
                .map(a -> this.modelMapper.map(a, AnswerDTO.class))
                .collect(Collectors.toList());

        return CensusQuestionDTO.builder()
                .text(question.getText())
                .answers(answers)
                .QuestionCategory(String.valueOf(question.getQuestionCategory()))
                .build();
    }

    public List<CensusQuestionDTO> getAllQuestionsAndTheirAnswers(){
        return this.censusQuestionRepository.findAll()
                .stream()
                .map(this::mapQuestionToDTO)
                .collect(Collectors.toList());

    }

    public Optional<CensusQuestion> getQuestionByText(String questionText) {
        String hashedText = HashUtil.hashText(questionText);
        return this.censusQuestionRepository.findByQuestionHashedText(hashedText);
    }

    public void createCensusQuestions(List<CensusQuestionDTO> questions, Campaign campaign) {
        for (CensusQuestionDTO censusQuestionDTO : questions) {

            String answersString = censusQuestionDTO
                    .getAnswers()
                    .stream()
                    .map(AnswerDTO::getAnswerText)
                    .collect(Collectors.joining("; "));

            List<Answer> questionAnswers = this.answerService.createAnswers(answersString);
            QuestionCategory questionCategory = QuestionCategory.valueOf(censusQuestionDTO.getQuestionCategory());

            Optional<CensusQuestion> questionByText = this.getQuestionByText(censusQuestionDTO.getText());

            CensusQuestion question;
            if(questionByText.isEmpty()){
                question = this.questionFactory.createQuestion(campaign, questionCategory,
                        censusQuestionDTO.getText(), questionAnswers);
            }else{
                question = questionByText.get();
                question.getCampaign().add(campaign);
                question.setAnswers(questionAnswers);
            }

            this.censusQuestionRepository.save(question);

        }
    }


}
