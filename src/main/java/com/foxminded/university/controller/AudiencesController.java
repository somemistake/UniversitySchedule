package com.foxminded.university.controller;

import com.foxminded.university.exception.AudienceDataException;
import com.foxminded.university.exception.DAOException;
import com.foxminded.university.model.main.Audience;
import com.foxminded.university.service.AudienceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/audiences")
public class AudiencesController {

    private final AudienceService service;

    public AudiencesController(AudienceService service) {
        this.service = service;
    }

    @GetMapping
    public String getAudienceTable(Model model) {
        model.addAttribute("audiences", service.findAll());
        return "audience/audiences";
    }

    @GetMapping("/createAudienceForm")
    public String getCreateAudienceForm(@ModelAttribute("audience") Audience audience) {
        return "audience/createAudienceForm";
    }

    @PostMapping("/createAudience")
    public String createAudience(@ModelAttribute("audience") Audience audience) throws DAOException {
        service.save(audience);
        return "redirect:/audiences";
    }

    @GetMapping("/editAudienceForm/{id}")
    public String getEditAudienceFrom(@PathVariable("id") long id, Model model) {
        model.addAttribute("audience", service.findById(id).get());
        return "audience/editAudienceForm";
    }

    @PostMapping("/editAudience")
    public String editAudience(@ModelAttribute("audience") Audience audience) throws DAOException {
        service.save(audience);
        return "redirect:/audiences";
    }

    @GetMapping("/deleteAudience/{id}")
    public String deleteAudience(@PathVariable("id") long id) {
        service.deleteById(id);
        return "redirect:/audiences";
    }

    @ExceptionHandler(value = AudienceDataException.class)
    public ModelAndView handleAudienceDataException(HttpServletRequest request, AudienceDataException exception) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", exception);
        modelAndView.addObject("audience", exception.getAudience());
        modelAndView.setViewName("audience/createAudienceForm");
        return modelAndView;
    }
}
