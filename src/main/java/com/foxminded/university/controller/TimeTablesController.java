package com.foxminded.university.controller;

import com.foxminded.university.service.TimeTableService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("/timeTables")
public class TimeTablesController {
    private final TimeTableService service;

    public TimeTablesController(TimeTableService service) {
        this.service = service;
    }

    @GetMapping()
    public String showTimeTable(@ModelAttribute("date") String date, Model model) {
        model.addAttribute("timeTable", service.getTimeTableByDate(LocalDate.parse(date)));
        return "timeTable/showTimeTable";
    }
}
