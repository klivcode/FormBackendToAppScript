package com.proxyform.tournament;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProxyformtournamnetformApplication {
    Dotenv dotenv = Dotenv.load();

    String appScriptUrl = dotenv.get("APPSCRIPT_URL");
    public static void main(String[] args) {
        SpringApplication.run(ProxyformtournamnetformApplication.class, args);
    }

}
