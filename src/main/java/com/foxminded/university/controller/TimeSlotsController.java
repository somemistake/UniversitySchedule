package com.foxminded.university.controller;

import com.foxminded.university.exception.DAOException;
import com.foxminded.university.model.time.TimeSlot;
import com.foxminded.university.service.TimeSlotService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/timeSlots")
public class TimeSlotsController {
    private final TimeSlotService service;

    public TimeSlotsController(TimeSlotService service) {
        this.service = service;
    }

    @GetMapping()
    public String getTimeSlotTable(Model model) {
        model.addAttribute("timeSlots", service.findAll());
        return "timeSlot/timeSlots";
    }

    @GetMapping("/createTimeSlotForm")
    public String getCreateTimeSlotForm(@ModelAttribute("timeSlot") TimeSlot timeSlot) {
        return "timeSlot/createTimeSlotForm";
    }

    @PostMapping("/createTimeSlot")
    public String createTimeSlot(@ModelAttribute("timeSlot") TimeSlot timeSlot) throws DAOException {
        service.save(timeSlot);
        return "redirect:/timeSlots";
    }

    @GetMapping("/editTimeSlotForm/{id}")
    public String getEditTimeSlotForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("timeSlot", service.findById(id).get());
        return "timeSlot/editTimeSlotForm";
    }

    @PostMapping("/editTimeSlot")
    public String editTimeSlot(@ModelAttribute("timeSlot") TimeSlot timeSlot) throws DAOException {
        service.save(timeSlot);
        return "redirect:/timeSlots";
    }

    @GetMapping("/deleteTimeSlot/{id}")
    public String deleteTimeSlot(@PathVariable("id") long id) {
        service.deleteById(id);
        return "redirect:/timeSlots";
    }
}
