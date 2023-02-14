package com.webhook.tsxjava14feb2023.controller;


import com.webhook.tsxjava14feb2023.service.LoggingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/hook")
@RequiredArgsConstructor
public class Webhook {
    private final LoggingService loggingService;
    @PostMapping
    public ResponseEntity<HttpStatus> handle(@RequestBody String payload) {
        try {
            loggingService.log(payload);
        } catch (IOException e) {
            return ResponseEntity.status(500).build();
        }

        return ResponseEntity.status(200).build();
    }

}
