package com.foxminded.university.controller;

import com.foxminded.university.exception.DAOException;
import com.foxminded.university.exception.GroupDataException;
import com.foxminded.university.model.main.Group;
import com.foxminded.university.service.GroupService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/groups")
public class GroupsController {
    private final GroupService service;

    public GroupsController(GroupService service) {
        this.service = service;
    }

    @GetMapping()
    public String getGroupTable(Model model) {
        model.addAttribute("groups", service.findAll());
        return "group/groups";
    }

    @GetMapping("/createGroupForm")
    public String getCreateGroupForm(@ModelAttribute("group") Group group) {
        return "group/createGroupForm";
    }

    @PostMapping("/createGroup")
    public String createGroup(@ModelAttribute("group") Group group) throws DAOException {
        service.save(group);
        return "redirect:/groups";
    }

    @GetMapping("/editGroupForm/{id}")
    public String getEditGroupForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("group", service.findById(id).get());
        return "group/editGroupForm";
    }

    @PostMapping("/editGroup")
    public String editGroup(@ModelAttribute("group") Group group) throws DAOException {
        service.save(group);
        return "redirect:/groups";
    }

    @GetMapping("/deleteGroup/{id}")
    public String deleteGroup(@PathVariable("id") long id) {
        service.deleteById(id);
        return "redirect:/groups";
    }

    @ExceptionHandler(value = GroupDataException.class)
    public ModelAndView handleGroupDataException(HttpServletRequest request, GroupDataException exception) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", exception);
        modelAndView.addObject("group", exception.getGroup());
        modelAndView.setViewName("group/createGroupForm");
        return modelAndView;
    }
}
