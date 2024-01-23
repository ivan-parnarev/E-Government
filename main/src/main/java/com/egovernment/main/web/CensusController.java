package com.egovernment.main.web;

import com.egovernment.main.domain.dto.censusCampaign.CensusDTO;
import com.egovernment.main.domain.dto.censusCampaign.CensusQuestionDTO;
import com.egovernment.main.service.CensusQuestionService;
import com.egovernment.main.service.UserAnswerService;
import com.egovernment.main.web.interfaces.CensusControllerInterface;
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

    @Operation(summary = "Get All Questions with Their Answers",
            description = "Retrieves a list of all census questions along with their respective answers.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successfully retrieved the list of questions and their answers",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CensusQuestionDTO.class))}),
            @ApiResponse(responseCode = "400",
                    description = "Bad Request - The request could not be understood by the server due to wrong syntax.",
                    content = {@Content(mediaType = "application/json")})
    })
    @Override
    @GetMapping("/questions")
    public ResponseEntity<List<CensusQuestionDTO>> getAllQuestionsAndTheirAnswers() {
        return ResponseEntity.ok(this.censusQuestionService.getAllQuestionsAndTheirAnswers());
    }


}
