package backend.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class HealthCheckController {

    @GetMapping("/")
    public String healthCheck() {
        return "OK";
    }
}
