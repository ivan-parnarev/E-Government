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
    private List<CandidateTemplateDTO> global;
    @Valid
    private List<CandidateTemplateDTO> blagoevgrad;
    @Valid
    private List<CandidateTemplateDTO> burgas;
    @Valid
    private List<CandidateTemplateDTO> varna;
    @Valid
    private List<CandidateTemplateDTO> velikoTarnovo;
    @Valid
    private List<CandidateTemplateDTO> vidin;
    @Valid
    private List<CandidateTemplateDTO> vratsa;
    @Valid
    private List<CandidateTemplateDTO> gabrovo;
    @Valid
    private List<CandidateTemplateDTO> dobrich;
    @Valid
    private List<CandidateTemplateDTO> kardzhali;
    @Valid
    private List<CandidateTemplateDTO> kyustendil;
    @Valid
    private List<CandidateTemplateDTO> lovech;
    @Valid
    private List<CandidateTemplateDTO> montana;
    @Valid
    private List<CandidateTemplateDTO> pazardzhik;
    @Valid
    private List<CandidateTemplateDTO> pernik;
    @Valid
    private List<CandidateTemplateDTO> pleven;
    @Valid
    private List<CandidateTemplateDTO> plovdiv;
    @Valid
    private List<CandidateTemplateDTO> razgrad;
    @Valid
    private List<CandidateTemplateDTO> ruse;
    @Valid
    private List<CandidateTemplateDTO> silistra;
    @Valid
    private List<CandidateTemplateDTO> sliven;
    @Valid
    private List<CandidateTemplateDTO> smolyan;
    @Valid
    private List<CandidateTemplateDTO> sofia;
    @Valid
    private List<CandidateTemplateDTO> sofiaProvince;
    @Valid
    private List<CandidateTemplateDTO> staraZagora;
    @Valid
    private List<CandidateTemplateDTO> targovishte;
    @Valid
    private List<CandidateTemplateDTO> haskovo;
    @Valid
    private List<CandidateTemplateDTO> shumen;
    @Valid
    private List<CandidateTemplateDTO> yambol;

}
