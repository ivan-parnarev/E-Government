package com.egovernment.egovbackend.domain.dto.voteCampaign;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateRegionTemplateDTO {
    @Valid
    private List<CandidateTemplateDTO> GLOBAL;
    @Valid
    private List<CandidateTemplateDTO> BLAGOEVGRAD;
    @Valid
    private List<CandidateTemplateDTO> BURGAS;
    @Valid
    private List<CandidateTemplateDTO> VARNA;
    @Valid
    private List<CandidateTemplateDTO> VELIKO_TARNOVO;
    @Valid
    private List<CandidateTemplateDTO> VIDIN;
    @Valid
    private List<CandidateTemplateDTO> VRATSA;
    @Valid
    private List<CandidateTemplateDTO> GABROVO;
    @Valid
    private List<CandidateTemplateDTO> DOBRICH;
    @Valid
    private List<CandidateTemplateDTO> KARDJALI;
    @Valid
    private List<CandidateTemplateDTO> KYUSTENDIL;
    @Valid
    private List<CandidateTemplateDTO> LOVECH;
    @Valid
    private List<CandidateTemplateDTO> MONTANA;
    @Valid
    private List<CandidateTemplateDTO> PAZARDJIK;
    @Valid
    private List<CandidateTemplateDTO> PERNIK;
    @Valid
    private List<CandidateTemplateDTO> PLEVEN;
    @Valid
    private List<CandidateTemplateDTO> PLOVDIV;
    @Valid
    private List<CandidateTemplateDTO> RUSE;
    @Valid
    private List<CandidateTemplateDTO> SILISTRA;
    @Valid
    private List<CandidateTemplateDTO> SLIVEN;
    @Valid
    private List<CandidateTemplateDTO> SMOLYAN;
    @Valid
    private List<CandidateTemplateDTO> SOFIA;
    @Valid
    private List<CandidateTemplateDTO> SOFIA_PROVINCE;
    @Valid
    private List<CandidateTemplateDTO> STARA_ZAGORA;
    @Valid
    private List<CandidateTemplateDTO> TARGOVISHTE;
    @Valid
    private List<CandidateTemplateDTO> HASKOVO;
    @Valid
    private List<CandidateTemplateDTO> SHUMEN;
    @Valid
    private List<CandidateTemplateDTO> YAMBOL;

}
