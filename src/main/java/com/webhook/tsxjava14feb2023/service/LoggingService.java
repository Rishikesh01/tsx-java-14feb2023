package com.webhook.tsxjava14feb2023.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.startup.ClassLoaderFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Logger;



@Service
public class LoggingService {
    private final Logger logger = Logger.getLogger("GitHub");
    private final RestTemplate restTemplate = new RestTemplate();



    public void log(String json) throws IOException {
        Map<String,Object> mapping = new ObjectMapper().readValue(json,HashMap.class);
        String repoUrl = (String) ((Map<String, Object>) mapping.get("repository")).get("html_url");

        System.out.println(repoUrl);
        final FileHandler fh = new FileHandler(System.getProperty("user.home") +"/MyLogFile.log");

        logger.addHandler(fh);
        if(mapping.containsKey("pusher")) {
            logger.info("\npush:"+json+"\ncommit:"+this.getResponse(repoUrl));
        }else if(mapping.containsKey("action")) {
            if(mapping.get("action").equals("opened")) {
                logger.info("pull_request:" + json+"\ncommit:"+this.getResponse(repoUrl));
            }else{
                logger.info("merged:" + json+"\ncommit:"+this.getResponse(repoUrl));
            }
        }

    }

    private String getResponse(String url)   {
        String trim = url.replace("https://github.com/","");
        String[] something = trim.split("/");
        String rest = restTemplate.getForObject( "https://api.github.com/repos/"+something[0]+"/"+something[1]+"/commits",String.class);
        return rest;
    }
}
