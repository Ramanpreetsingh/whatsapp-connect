package com.whatsappconnect.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
 @author Ramanpreet Singh on 21/01/18
 *
 */

@EnableScheduling
@Component
public class Scheduler {

    private static Logger LOGGER = LoggerFactory.getLogger(Scheduler.class);

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Scheduled(cron = "0 * * * * ?")
    public void schedulePythonScript() {

        LOGGER.info("Cron Task :: Execution Start Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
        try {

            //TODO: load script path from config files
            String pythonScriptPath = "/Users/raman/Repos/whatsapp-connect/src/main/resources/demand_products.py";
            String[] cmd = new String[2];
            cmd[0] = "python";
            cmd[1] = pythonScriptPath;

            Process process = Runtime.getRuntime().exec(cmd);

            BufferedReader bfr = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";
            while((line = bfr.readLine()) != null) {
                // display each output line form python script
                LOGGER.info(line);
            }
        } catch (IOException e) {
            LOGGER.error("Exception while scheduling script",e);
        }

        LOGGER.info("Cron Task :: Execution End Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
    }

}
