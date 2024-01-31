<<<<<<< Updated upstream
package com.egovernment.main.domain.dto;
=======
package com.egovernment.egovbackend.domain.dto;
>>>>>>> Stashed changes


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CampaignViewDTO {
    private int id;
    private String campaignTitle;

}
