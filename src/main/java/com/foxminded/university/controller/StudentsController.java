package com.foxminded.university.controller;

import com.foxminded.university.exception.DAOException;
import com.foxminded.university.model.main.Student;
import com.foxminded.university.service.GroupService;
import com.foxminded.university.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
public class StudentsController {
    private final StudentService service;
    private final GroupService groupService;

    public StudentsController(StudentService service, GroupService groupService) {
        this.service = service;
        this.groupService = groupService;
    }

    @GetMapping()
    public String getStudentTable(Model model) {
        model.addAttribute("students", service.findAll());
        return "student/students";
    }

    @GetMapping("/createStudentForm")
    public String getCreateStudentForm(@ModelAttribute("student") Student student, Model model) {
        model.addAttribute("groups", groupService.findAll());
        return "student/createStudentForm";
    }

    @PostMapping("/createStudent")
    public String createStudent(@ModelAttribute("student") Student student,
                                @ModelAttribute("groupId") long groupId) throws DAOException {
        student.setGroup(groupService.findById(groupId).get());
        service.save(student);
        return "redirect:/students";
    }

    @GetMapping("/editStudentForm/{id}")
    public String getEditStudentForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("student", service.findById(id).get());
        model.addAttribute("groups", groupService.findAll());
        return "student/editStudentForm";
    }

    @PostMapping("/editStudent")
    public String editStudent(@ModelAttribute("student") Student student,
                              @ModelAttribute("groupId") long groupId) throws DAOException {
        student.setGroup(groupService.findById(groupId).get());
        service.save(student);
        return "redirect:/students";
    }

    @GetMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable("id") long id) {
        service.deleteById(id);
        return "redirect:/students";
    }
}
