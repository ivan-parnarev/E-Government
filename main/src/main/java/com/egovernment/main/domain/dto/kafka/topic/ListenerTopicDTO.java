package com.egovernment.main.domain.dto.kafka.topic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ListenerTopicDTO {

    private String topic;

}
