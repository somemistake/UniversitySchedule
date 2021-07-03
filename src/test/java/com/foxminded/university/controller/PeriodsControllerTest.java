package com.foxminded.university.controller;

import com.foxminded.university.model.time.Period;
import com.foxminded.university.service.PeriodService;
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
class PeriodsControllerTest {
    private MockMvc mockMvc;

    @MockBean
    private PeriodService service;

    @Autowired
    private PeriodsController controller;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void shouldGetPeriodsHomePage() throws Exception {
        mockMvc.perform(get("/periods"))
                .andExpect(status().isOk())
                .andExpect(view().name("period/periods"));
    }

    @Test
    void shouldGetPeriodCreateForm() throws Exception {
        mockMvc.perform(get("/periods/createPeriodForm"))
                .andExpect(status().isOk())
                .andExpect(view().name("period/createPeriodForm"));
    }

    @Test
    void shouldCreatePeriodAndRedirectPeriodsHomePage() throws Exception {
        mockMvc.perform(post("/periods/createPeriod")
                .param("periodStart", "2000-01-01")
                .param("periodFinish", "2000-02-01"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/periods?periodStart=2000-01-01&periodFinish=2000-02-01"));
    }

    @Test
    void shouldGetPeriodEditForm() throws Exception {
        when(service.findById(1l)).thenReturn(Optional.of(new Period()));
        mockMvc.perform(get("/periods/editPeriodForm/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("period/editPeriodForm"));
    }

    @Test
    void shouldEditPeriodAndRedirectPeriodsHomePage() throws Exception {
        when(service.findById(1l)).thenReturn(Optional.of(new Period()));
        mockMvc.perform(post("/periods/editPeriod")
                .param("id", "1")
                .param("periodStart", "2000-01-01")
                .param("periodFinish", "2000-02-01"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/periods?id=1&periodStart=2000-01-01&periodFinish=2000-02-01"));
    }

    @Test
    void shouldDeletePeriodAndRedirectPeriodsHomePage() throws Exception {
        mockMvc.perform(get("/periods/deletePeriod/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/periods"));
    }
}