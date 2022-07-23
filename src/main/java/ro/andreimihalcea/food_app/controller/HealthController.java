package ro.andreimihalcea.food_app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {


    @GetMapping("/health")
    public ResponseEntity<?> checkApp(Authentication authentication){
        return ResponseEntity.ok("Hello "+ authentication.getPrincipal()) ;
    }
}
