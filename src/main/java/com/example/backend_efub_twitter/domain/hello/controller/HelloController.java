package com.example.backend_efub_twitter.domain.hello.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class HelloController {

    @GetMapping("/hello")
    public Map<String, Object> getHello() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("result", "Success!");
        result.put("success", true);

        return result;
    }

}