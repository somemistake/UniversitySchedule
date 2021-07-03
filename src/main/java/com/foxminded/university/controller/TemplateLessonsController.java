package com.foxminded.university.controller;

import com.foxminded.university.exception.DAOException;
import com.foxminded.university.exception.TemplateLessonDataException;
import com.foxminded.university.model.lesson.TemplateLesson;
import com.foxminded.university.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/templateLessons")
public class TemplateLessonsController {
    private final TemplateLessonService service;
    private final GroupService groupService;
    private final CourseService courseService;
    private final TeacherService teacherService;
    private final AudienceService audienceService;
    private final TimeSlotService timeSlotService;
    private final WeekDayService weekDayService;
    private final PeriodService periodService;

    public TemplateLessonsController(TemplateLessonService service, GroupService groupService, CourseService courseService, TeacherService teacherService, AudienceService audienceService, TimeSlotService timeSlotService, WeekDayService weekDayService, PeriodService periodService) {
        this.service = service;
        this.groupService = groupService;
        this.courseService = courseService;
        this.teacherService = teacherService;
        this.audienceService = audienceService;
        this.timeSlotService = timeSlotService;
        this.weekDayService = weekDayService;
        this.periodService = periodService;
    }

    @GetMapping()
    public String getTemplateLessonTable(Model model) {
        model.addAttribute("templateLessons", service.findAll());
        return "templateLesson/templateLessons";
    }

    @GetMapping("/createTemplateLessonForm")
    public String getCreateTemplateLessonForm(@ModelAttribute("templateLesson") TemplateLesson templateLesson, Model model) {
        model.addAttribute("groups", groupService.findAll());
        model.addAttribute("courses", courseService.findAll());
        model.addAttribute("teachers", teacherService.findAll());
        model.addAttribute("audiences", audienceService.findAll());
        model.addAttribute("timeSlots", timeSlotService.findAll());
        model.addAttribute("weekDays", weekDayService.findAll());
        model.addAttribute("periods", periodService.findAll());
        return "templateLesson/createTemplateLessonForm";
    }

    @PostMapping("/createTemplateLesson")
    public String createTemplateLesson(@ModelAttribute("groupId") long groupId,
                                       @ModelAttribute("courseId") long courseId,
                                       @ModelAttribute("teacherId") long teacherId,
                                       @ModelAttribute("audienceId") long audienceId,
                                       @ModelAttribute("timeSlotId") long timeSlotId,
                                       @ModelAttribute("weekDayId") long weekDayId,
                                       @ModelAttribute("periodId") long periodId) throws DAOException {
        service.save(
                new TemplateLesson(
                        groupService.findById(groupId).get(),
                        courseService.findById(courseId).get(),
                        teacherService.findById(teacherId).get(),
                        audienceService.findById(audienceId).get(),
                        timeSlotService.findById(timeSlotId).get(),
                        weekDayService.findById(weekDayId).get(),
                        periodService.findById(periodId).get()
                )
        );
        return "redirect:/templateLessons";
    }

    @GetMapping("/editTemplateLessonForm/{id}")
    public String getEditTemplateLessonForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("templateLesson", service.findById(id).get());
        model.addAttribute("groups", groupService.findAll());
        model.addAttribute("courses", courseService.findAll());
        model.addAttribute("teachers", teacherService.findAll());
        model.addAttribute("audiences", audienceService.findAll());
        model.addAttribute("timeSlots", timeSlotService.findAll());
        model.addAttribute("weekDays", weekDayService.findAll());
        model.addAttribute("periods", periodService.findAll());
        return "templateLesson/editTemplateLessonForm";
    }

    @PostMapping("/editTemplateLesson")
    public String editTemplateLesson(@ModelAttribute("id") long id,
                                     @ModelAttribute("groupId") long groupId,
                                     @ModelAttribute("courseId") long courseId,
                                     @ModelAttribute("teacherId") long teacherId,
                                     @ModelAttribute("audienceId") long audienceId,
                                     @ModelAttribute("timeSlotId") long timeSlotId,
                                     @ModelAttribute("weekDayId") long weekDayId,
                                     @ModelAttribute("periodId") long periodId) throws DAOException {
        service.save(
                new TemplateLesson(
                        id,
                        groupService.findById(groupId).get(),
                        courseService.findById(courseId).get(),
                        teacherService.findById(teacherId).get(),
                        audienceService.findById(audienceId).get(),
                        timeSlotService.findById(timeSlotId).get(),
                        weekDayService.findById(weekDayId).get(),
                        periodService.findById(periodId).get()
                )
        );
        return "redirect:/templateLessons";
    }

    @GetMapping("/deleteTemplateLesson/{id}")
    public String deleteTemplateLesson(@PathVariable("id") long id) {
        service.deleteById(id);
        return "redirect:/templateLessons";
    }

    @ExceptionHandler(value = TemplateLessonDataException.class)
    public ModelAndView handleTemplateLessonDataException(HttpServletRequest request,
                                                          TemplateLessonDataException exception) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", exception);
        modelAndView.addObject("groups", groupService.findAll());
        modelAndView.addObject("courses", courseService.findAll());
        modelAndView.addObject("teachers", teacherService.findAll());
        modelAndView.addObject("audiences", audienceService.findAll());
        modelAndView.addObject("timeSlots", timeSlotService.findAll());
        modelAndView.addObject("weekDays", weekDayService.findAll());
        modelAndView.addObject("periods", periodService.findAll());
        modelAndView.addObject("templateLesson", new TemplateLesson());
        modelAndView.setViewName("templateLesson/createTemplateLessonForm");
        return modelAndView;
    }

}
