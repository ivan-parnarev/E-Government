package com.egovernment.egovbackend.web;

import com.egovernment.egovbackend.domain.dto.CensusDTO;
import com.egovernment.egovbackend.web.interfaces.CensusControllerInterface;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CensusController implements CensusControllerInterface {


    @Override
    @PostMapping
    public ResponseEntity<CensusDTO> saveUserVoteData(@Valid @RequestBody CensusDTO census) {

        return ResponseEntity.ok(census);
    }
}
