package com.foxminded.university.controller;

import com.foxminded.university.exception.DAOException;
import com.foxminded.university.model.main.Teacher;
import com.foxminded.university.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/teachers")
public class TeachersController {
    private final TeacherService service;

    public TeachersController(TeacherService service) {
        this.service = service;
    }

    @GetMapping()
    public String getTeacherTable(Model model) {
        model.addAttribute("teachers", service.findAll());
        return "teacher/teachers";
    }

    @GetMapping("/createTeacherForm")
    public String getCreateTeacherForm(@ModelAttribute("teacher") Teacher teacher) {
        return "teacher/createTeacherForm";
    }

    @PostMapping("/createTeacher")
    public String createTeacher(@ModelAttribute("teacher") Teacher teacher) throws DAOException {
        service.save(teacher);
        return "redirect:/teachers";
    }

    @GetMapping("/editTeacherForm/{id}")
    public String getEditTeacherForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("teacher", service.findById(id).get());
        return "teacher/editTeacherForm";
    }

    @PostMapping("/editTeacher")
    public String editTeacher(@ModelAttribute("teacher") Teacher teacher) throws DAOException {
        service.save(teacher);
        return "redirect:/teachers";
    }

    @GetMapping("/deleteTeacher/{id}")
    public String deleteTeacher(@PathVariable("id") long id) {
        service.deleteById(id);
        return "redirect:/teachers";
    }
}
