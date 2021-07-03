package com.foxminded.university.controller;

import com.foxminded.university.model.main.Teacher;
import com.foxminded.university.service.TeacherService;
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
class TeachersControllerTest {
    private MockMvc mockMvc;

    @MockBean
    private TeacherService service;

    @Autowired
    private TeachersController controller;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void shouldGetTeachersHomePage() throws Exception {
        mockMvc.perform(get("/teachers"))
                .andExpect(status().isOk())
                .andExpect(view().name("teacher/teachers"));
    }

    @Test
    void shouldGetTeacherCreateForm() throws Exception {
        mockMvc.perform(get("/teachers/createTeacherForm"))
                .andExpect(status().isOk())
                .andExpect(view().name("teacher/createTeacherForm"));
    }

    @Test
    void shouldCreateTeacherAndRedirectTeachersHomePage() throws Exception {
        mockMvc.perform(post("/teachers/createTeacher"))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("teacher"))
                .andExpect(redirectedUrl("/teachers"));
    }

    @Test
    void shouldGetTeacherEditForm() throws Exception {
        when(service.findById(1l)).thenReturn(Optional.of(new Teacher()));
        mockMvc.perform(get("/teachers/editTeacherForm/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("teacher/editTeacherForm"));
    }

    @Test
    void shouldEditTeacherAndRedirectTeachersHomePage() throws Exception {
        mockMvc.perform(post("/teachers/editTeacher"))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("teacher"))
                .andExpect(redirectedUrl("/teachers"));
    }

    @Test
    void shouldDeleteTeacherAndRedirectTeachersHomePage() throws Exception {
        mockMvc.perform(get("/teachers/deleteTeacher/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/teachers"));
    }
}