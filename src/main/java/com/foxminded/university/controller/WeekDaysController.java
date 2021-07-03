package com.foxminded.university.controller;

import com.foxminded.university.exception.DAOException;
import com.foxminded.university.model.time.WeekDay;
import com.foxminded.university.service.WeekDayService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/weekDays")
public class WeekDaysController {
    private final WeekDayService service;

    public WeekDaysController(WeekDayService service) {
        this.service = service;
    }

    @GetMapping()
    public String getWeekDayTable(Model model) {
        model.addAttribute("weekDays", service.findAll());
        return "weekDay/weekDays";
    }

    @GetMapping("/createWeekDayForm")
    public String getCreateWeekDayForm(@ModelAttribute("weekDay") WeekDay weekDay) {
        return "weekDay/createWeekDayForm";
    }

    @PostMapping("/createWeekDay")
    public String createWeekDay(@ModelAttribute("weekDay") WeekDay weekDay) throws DAOException {
        service.save(weekDay);
        return "redirect:/weekDays";
    }

    @GetMapping("/editWeekDayForm/{id}")
    public String getEditWeekDayForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("weekDay", service.findById(id).get());
        return "weekDay/editWeekDayForm";
    }

    @PostMapping("/editWeekDay")
    public String editWeekDay(@ModelAttribute("weekDay") WeekDay weekDay) throws DAOException {
        service.save(weekDay);
        return "redirect:/weekDays";
    }

    @GetMapping("/deleteWeekDay/{id}")
    public String deleteWeekDay(@PathVariable("id") long id) {
        service.deleteById(id);
        return "redirect:/weekDays";
    }
}
