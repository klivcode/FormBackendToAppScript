package com.proxyform.tournament.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

@RestController
@RequestMapping("/api")
public class AppScriptController {

    private final String APPSCRIPT_URL = "https://script.google.com/macros/s/AKfycbzYy9fJddcrjuImpqhVthyJNLX4Uatds562yZPFurOJSzH5oyRU-O6z4f_nSPCV1BOTwA/exec";

    @PostMapping("/submit")
    public ResponseEntity<String> submitForm(@RequestBody String body) {
        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> request = new HttpEntity<>(body, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(APPSCRIPT_URL, request, String.class);

            return ResponseEntity.ok(response.getBody());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"status\":\"error\",\"message\":\"Failed to reach Apps Script\"}");
        }
    }
}
