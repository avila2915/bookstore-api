package com.taller.bookstore.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/public")
    public String publicEndpoint() {
        return "Public endpoint works";
    }

    @GetMapping("/private")
    public String privateEndpoint() {
        return "Private endpoint works (JWT required)";
    }
}