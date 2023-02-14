package com.webhook.tsxjava14feb2023.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

@Service
public class LoggingService {
    private final Logger logger = Logger.getLogger("GitHub");
   private final FileHandler fh = new FileHandler("C:/temp/test/MyLogFile.log");

    public LoggingService() throws IOException {
    }

    public void log(String payload) throws IOException {
        HashMap<String,Object> mapping = new ObjectMapper().readValue(payload, HashMap.class);
        logger.addHandler(fh);
        if(mapping.containsKey("pusher")) {
            logger.info("push:"+payload);
        }else if(mapping.containsKey("action")) {
            if(mapping.get("action").equals("opened")) {
                logger.info("pull_request:" + payload);
            }else{
                logger.info("merged:" + payload);
            }
        }

    }
}
