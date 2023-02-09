package com.example.dream_job.controller;

/**
 * @author Igor Suvorov
 */
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class IndexController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }
}