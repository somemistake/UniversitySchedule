package com.foxminded.university.controller;

import com.foxminded.university.model.main.Course;
import com.foxminded.university.service.CourseService;
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
class CoursesControllerTest {
    private MockMvc mockMvc;

    @MockBean
    private CourseService service;

    @Autowired
    private CoursesController controller;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void shouldGetCoursesHomePage() throws Exception {
        mockMvc.perform(get("/courses"))
                .andExpect(status().isOk())
                .andExpect(view().name("course/courses"));
    }

    @Test
    void shouldGetCourseCreateForm() throws Exception {
        mockMvc.perform(get("/courses/createCourseForm"))
                .andExpect(status().isOk())
                .andExpect(view().name("course/createCourseForm"));
    }

    @Test
    void shouldCreateCourseAndRedirectCoursesHomePage() throws Exception {
        mockMvc.perform(post("/courses/createCourse"))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("course"))
                .andExpect(redirectedUrl("/courses"));
    }

    @Test
    void shouldGetCourseEditForm() throws Exception {
        when(service.findById(1l)).thenReturn(Optional.of(new Course()));
        mockMvc.perform(get("/courses/editCourseForm/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("course/editCourseForm"));
    }

    @Test
    void shouldEditCourseAndRedirectCoursesHomePage() throws Exception {
        mockMvc.perform(post("/courses/editCourse"))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("course"))
                .andExpect(redirectedUrl("/courses"));
    }

    @Test
    void shouldDeleteCourseAndRedirectCoursesHomePage() throws Exception {
        mockMvc.perform(get("/courses/deleteCourse/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/courses"));
    }
}