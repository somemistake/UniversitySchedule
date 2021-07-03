package com.foxminded.university.controller;

import com.foxminded.university.model.main.Group;
import com.foxminded.university.model.main.Student;
import com.foxminded.university.service.GroupService;
import com.foxminded.university.service.StudentService;
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
class StudentsControllerTest {
    private MockMvc mockMvc;

    @MockBean
    private StudentService service;

    @MockBean
    private GroupService groupService;

    @Autowired
    private StudentsController controller;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void shouldGetStudentsHomePage() throws Exception {
        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(view().name("student/students"));
    }

    @Test
    void shouldGetStudentCreateForm() throws Exception {
        mockMvc.perform(get("/students/createStudentForm"))
                .andExpect(status().isOk())
                .andExpect(view().name("student/createStudentForm"));
    }

    @Test
    void shouldCreateStudentAndRedirectStudentsHomePage() throws Exception {
        when(groupService.findById(1l)).thenReturn(Optional.of(new Group()));
        mockMvc.perform(post("/students/createStudent")
                .param("groupId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("student"))
                .andExpect(redirectedUrl("/students?groupId=1"));
    }

    @Test
    void shouldGetStudentEditForm() throws Exception {
        when(service.findById(1l)).thenReturn(Optional.of(new Student()));
        mockMvc.perform(get("/students/editStudentForm/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("student/editStudentForm"));
    }

    @Test
    void shouldEditStudentAndRedirectStudentsHomePage() throws Exception {
        when(groupService.findById(1l)).thenReturn(Optional.of(new Group()));
        mockMvc.perform(post("/students/editStudent")
                .param("groupId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("student"))
                .andExpect(redirectedUrl("/students?groupId=1"));
    }

    @Test
    void shouldDeleteStudentAndRedirectStudentsHomePage() throws Exception {
        mockMvc.perform(get("/students/deleteStudent/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/students"));
    }
}