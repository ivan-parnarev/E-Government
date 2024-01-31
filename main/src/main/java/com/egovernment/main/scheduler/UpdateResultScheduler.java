package com.egovernment.main.scheduler;

import com.egovernment.main.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateResultScheduler {

    private final ResultService resultService;
    private final Logger LOGGER = LoggerFactory.getLogger(UpdateResultScheduler.class);

    @Scheduled(cron = "0 0 * * * *")
    public void runEveryMinute() {
        this.resultService.updateResults();
        LOGGER.info("Scheduler updating results");
    }

}
