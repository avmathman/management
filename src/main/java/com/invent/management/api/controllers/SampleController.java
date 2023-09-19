package com.invent.management.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {
    
    @GetMapping("/home")
    public String home() {
        return "Welcome";
    }

    @GetMapping("/temp")
    public String user() {
        return "Welcome USER";
    }

    @GetMapping("/admin")
    public String admin() {
        return "Welcome ADMIN";
    }
}
