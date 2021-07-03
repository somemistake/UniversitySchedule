package com.foxminded.university.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TablesController {
    @GetMapping()
    public String getTables() {
        return "index";
    }
}
