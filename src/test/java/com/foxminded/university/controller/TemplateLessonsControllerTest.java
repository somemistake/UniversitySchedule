package com.foxminded.university.controller;

import com.foxminded.university.model.lesson.TemplateLesson;
import com.foxminded.university.model.main.Audience;
import com.foxminded.university.model.main.Course;
import com.foxminded.university.model.main.Group;
import com.foxminded.university.model.main.Teacher;
import com.foxminded.university.model.time.Period;
import com.foxminded.university.model.time.TimeSlot;
import com.foxminded.university.model.time.WeekDay;
import com.foxminded.university.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class TemplateLessonsControllerTest {
    private MockMvc mockMvc;

    @MockBean
    private TemplateLessonService service;

    @MockBean
    private GroupService groupService;

    @MockBean
    private CourseService courseService;

    @MockBean
    private TeacherService teacherService;

    @MockBean
    private AudienceService audienceService;

    @MockBean
    private TimeSlotService timeSlotService;

    @MockBean
    private WeekDayService weekDayService;

    @MockBean
    private PeriodService periodService;

    @Autowired
    private TemplateLessonsController controller;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void shouldGetTemplateLessonsHomePage() throws Exception {
        mockMvc.perform(get("/templateLessons"))
                .andExpect(status().isOk())
                .andExpect(view().name("templateLesson/templateLessons"));
    }

    @Test
    void shouldGetTemplateLessonCreateForm() throws Exception {
        mockMvc.perform(get("/templateLessons/createTemplateLessonForm"))
                .andExpect(status().isOk())
                .andExpect(view().name("templateLesson/createTemplateLessonForm"));
    }

    @Test
    void shouldCreateTemplateLessonAndRedirectTemplateLessonsHomePage() throws Exception {
        when(groupService.findById(1l)).thenReturn(Optional.of(new Group()));
        when(courseService.findById(1l)).thenReturn(Optional.of(new Course()));
        when(teacherService.findById(1l)).thenReturn(Optional.of(new Teacher()));
        when(audienceService.findById(1l)).thenReturn(Optional.of(new Audience()));
        when(timeSlotService.findById(1l)).thenReturn(Optional.of(new TimeSlot()));
        when(weekDayService.findById(1l)).thenReturn(Optional.of(new WeekDay()));
        when(periodService.findById(1l)).thenReturn(Optional.of(new Period()));
        mockMvc.perform(post("/templateLessons/createTemplateLesson")
                .param("groupId", "1")
                .param("courseId", "1")
                .param("teacherId", "1")
                .param("audienceId", "1")
                .param("timeSlotId", "1")
                .param("weekDayId", "1")
                .param("periodId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/templateLessons?groupId=1&courseId=1&teacherId=1&audienceId=1&timeSlotId=1&weekDayId=1&periodId=1"));
    }

    @Test
    void shouldGetTemplateLessonEditForm() throws Exception {
        when(service.findById(1l)).thenReturn(Optional.of(new TemplateLesson()));
        mockMvc.perform(get("/templateLessons/editTemplateLessonForm/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("templateLesson/editTemplateLessonForm"));
    }

    @Test
    void shouldEditTemplateLessonAndRedirectTemplateLessonsHomePage() throws Exception {
        when(groupService.findById(1l)).thenReturn(Optional.of(new Group()));
        when(courseService.findById(1l)).thenReturn(Optional.of(new Course()));
        when(teacherService.findById(1l)).thenReturn(Optional.of(new Teacher()));
        when(audienceService.findById(1l)).thenReturn(Optional.of(new Audience()));
        when(timeSlotService.findById(1l)).thenReturn(Optional.of(new TimeSlot()));
        when(weekDayService.findById(1l)).thenReturn(Optional.of(new WeekDay()));
        when(periodService.findById(1l)).thenReturn(Optional.of(new Period()));
        mockMvc.perform(post("/templateLessons/editTemplateLesson")
                .param("id", "1")
                .param("groupId", "1")
                .param("courseId", "1")
                .param("teacherId", "1")
                .param("audienceId", "1")
                .param("timeSlotId", "1")
                .param("weekDayId", "1")
                .param("periodId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/templateLessons?id=1&groupId=1&courseId=1&teacherId=1&audienceId=1&timeSlotId=1&weekDayId=1&periodId=1"));
    }

    @Test
    void shouldDeleteTemplateLessonAndRedirectTemplateLessonsHomePage() throws Exception {
        mockMvc.perform(get("/templateLessons/deleteTemplateLesson/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/templateLessons"));
    }
}