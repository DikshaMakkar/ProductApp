package com.example.product.controller;

import com.example.product.services.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GitHubDemoController {

    @Autowired
    private final DemoService demoService;

    public GitHubDemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    @GetMapping("data")
    public String getDemoData(){
        String data = demoService.callApi("/users/demo");
        return data;
    }
}
