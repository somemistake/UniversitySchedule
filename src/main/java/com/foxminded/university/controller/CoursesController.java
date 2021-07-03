package com.foxminded.university.controller;

import com.foxminded.university.exception.CourseDataException;
import com.foxminded.university.exception.DAOException;
import com.foxminded.university.model.main.Course;
import com.foxminded.university.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/courses")
public class CoursesController {
    private final CourseService service;

    public CoursesController(CourseService service) {
        this.service = service;
    }

    @GetMapping()
    public String getCourseTable(Model model) {
        model.addAttribute("courses", service.findAll());
        return "course/courses";
    }

    @GetMapping("/createCourseForm")
    public String getCreateCourseForm(@ModelAttribute("course") Course course) {
        return "course/createCourseForm";
    }

    @PostMapping("/createCourse")
    public String createCourse(@ModelAttribute("course") Course course) throws DAOException {
        service.save(course);
        return "redirect:/courses";
    }

    @GetMapping("/editCourseForm/{id}")
    public String getEditCourseFrom(@PathVariable("id") long id, Model model) {
        model.addAttribute("course", service.findById(id).get());
        return "course/editCourseForm";
    }

    @PostMapping("/editCourse")
    public String editCourse(@ModelAttribute("course") Course course) throws DAOException {
        service.save(course);
        return "redirect:/courses";
    }

    @GetMapping("/deleteCourse/{id}")
    public String deleteCourse(@PathVariable("id") long id) {
        service.deleteById(id);
        return "redirect:/courses";
    }

    @ExceptionHandler(value = CourseDataException.class)
    public ModelAndView handleCourseDataException(HttpServletRequest request, CourseDataException exception) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", exception);
        modelAndView.addObject("course", exception.getCourse());
        modelAndView.setViewName("course/createCourseForm");
        return modelAndView;
    }
}
