package com.egovernment.egovbackend.web.interfaces;

import com.egovernment.egovbackend.domain.dto.censusCampaign.CensusDTO;
import com.egovernment.egovbackend.domain.dto.censusCampaign.CensusQuestionDTO;
import com.egovernment.egovbackend.web.path.ApiPaths;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(ApiPaths.BASE_API_PATH + ApiPaths.CENSUS_PATH)
public interface CensusControllerInterface {

    ResponseEntity<CensusDTO> saveUserCensusData(@Valid @RequestBody CensusDTO census);

    ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex);
    ResponseEntity<List<CensusQuestionDTO>>  getAllQuestionsAndTheirAnswers();
}
