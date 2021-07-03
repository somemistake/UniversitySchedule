package com.foxminded.university.controller;

import com.foxminded.university.exception.DAOException;
import com.foxminded.university.model.time.Period;
import com.foxminded.university.service.PeriodService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/periods")
public class PeriodsController {
    private final PeriodService service;

    public PeriodsController(PeriodService service) {
        this.service = service;
    }

    @GetMapping()
    public String getPeriodTable(Model model) {
        model.addAttribute("periods", service.findAll());
        return "period/periods";
    }

    @GetMapping("/createPeriodForm")
    public String getCreatePeriodForm(@ModelAttribute("period") Period period) {
        return "period/createPeriodForm";
    }

    @PostMapping("/createPeriod")
    public String createPeriod(@ModelAttribute("periodStart") String periodStart,
                               @ModelAttribute("periodFinish") String periodFinish) throws DAOException {
        service.save(new Period(
                LocalDate.parse(periodStart),
                LocalDate.parse(periodFinish)
        ));
        return "redirect:/periods";
    }

    @GetMapping("/editPeriodForm/{id}")
    public String getEditPeriodForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("period", service.findById(id).get());
        return "period/editPeriodForm";
    }

    @PostMapping("/editPeriod")
    public String editPeriod(@ModelAttribute("id") long id,
                             @ModelAttribute("periodStart") String periodStart,
                             @ModelAttribute("periodFinish") String periodFinish) throws DAOException {
        service.save(new Period(
                id,
                LocalDate.parse(periodStart),
                LocalDate.parse(periodFinish)
        ));
        return "redirect:/periods";
    }

    @GetMapping("/deletePeriod/{id}")
    public String deletePeriod(@PathVariable("id") long id) {
        service.deleteById(id);
        return "redirect:/periods";
    }
}
