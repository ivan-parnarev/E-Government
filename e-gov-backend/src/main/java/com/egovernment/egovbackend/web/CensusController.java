package com.egovernment.egovbackend.web;

import com.egovernment.egovbackend.domain.dto.censusCampaign.CensusDTO;
import com.egovernment.egovbackend.domain.dto.censusCampaign.CensusQuestionDTO;
import com.egovernment.egovbackend.service.CensusQuestionService;
import com.egovernment.egovbackend.service.UserAnswerService;
import com.egovernment.egovbackend.web.interfaces.CensusControllerInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CensusController implements CensusControllerInterface {

    private final UserAnswerService userAnswerService;
    private final CensusQuestionService censusQuestionService;

    @Operation(summary = "Receives information about census and saves the user census data in the database")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201",
                            description = "Census data has been successfully received and saved in the database. " +
                                    "After that you are redirected to active campaigns.",
                            content = {
                                    @Content(mediaType = "application/json", schema = @Schema(implementation = CensusDTO.class))
                            }
                    )
                    ,@ApiResponse(responseCode = "400",
                    description = "Bad Request - Validation error or incorrect data format.",
                    content = {@Content(mediaType = "application/json")}
            )
            }
    )

    @Override
    @PostMapping
    public ResponseEntity<CensusDTO> saveUserCensusData(@Valid @RequestBody CensusDTO census) {
        this.userAnswerService.saveUserCensusData(census);
        URI location = URI.create("http://localhost:3000/active-campaigns");
        return ResponseEntity.created(location).body(census);
    }

    @Override
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex){

        return ResponseEntity.badRequest().body("There was a problem with the validation of the DTO: " +
                ex.getMessage());
    }

    @Override
    @GetMapping("/questions")
    public ResponseEntity<List<CensusQuestionDTO>> getAllQuestionsAndTheirAnswers() {
        return ResponseEntity.ok(this.censusQuestionService.getAllQuestionsAndTheirAnswers());
    }


}
