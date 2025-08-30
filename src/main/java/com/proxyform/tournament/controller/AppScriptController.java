package com.proxyform.tournament.controller;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

@RestController
@RequestMapping("/api")
public class AppScriptController {

    private final String appScriptUrl;

    public AppScriptController() {
        // Load environment variables safely
        Dotenv dotenv = Dotenv.configure()
                .directory("./")       // root of project
                .ignoreIfMissing()     // ignore if .env is missing
                .load();

        this.appScriptUrl = dotenv.get("APPSCRIPT_URL");

        if (this.appScriptUrl == null || this.appScriptUrl.isEmpty()) {
            throw new IllegalStateException(
                    "APPSCRIPT_URL is not set! " +
                            "Please configure it in Render environment variables."
            );
        }
    }

    @PostMapping("/submit")
    public ResponseEntity<String> submitForm(@RequestBody String body) {
        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> request = new HttpEntity<>(body, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(appScriptUrl, request, String.class);

            return ResponseEntity.ok(response.getBody());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"status\":\"error\",\"message\":\"Failed to reach Apps Script\"}");
        }
    }
}
