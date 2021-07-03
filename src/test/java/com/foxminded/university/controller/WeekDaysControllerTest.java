package com.foxminded.university.controller;

import com.foxminded.university.model.time.WeekDay;
import com.foxminded.university.service.WeekDayService;
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
class WeekDaysControllerTest {
    private MockMvc mockMvc;

    @MockBean
    private WeekDayService service;

    @Autowired
    private WeekDaysController controller;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void shouldGetWeekDayHomePage() throws Exception {
        mockMvc.perform(get("/weekDays"))
                .andExpect(status().isOk())
                .andExpect(view().name("weekDay/weekDays"));
    }

    @Test
    void shouldGetWeekDayCreateForm() throws Exception {
        mockMvc.perform(get("/weekDays/createWeekDayForm"))
                .andExpect(status().isOk())
                .andExpect(view().name("weekDay/createWeekDayForm"));
    }

    @Test
    void shouldCreateWeekDayAndRedirectWeekDaysHomePage() throws Exception {
        mockMvc.perform(post("/weekDays/createWeekDay"))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("weekDay"))
                .andExpect(redirectedUrl("/weekDays"));
    }

    @Test
    void shouldGetWeekDayEditForm() throws Exception {
        when(service.findById(1l)).thenReturn(Optional.of(new WeekDay()));
        mockMvc.perform(get("/weekDays/editWeekDayForm/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("weekDay/editWeekDayForm"));
    }

    @Test
    void shouldEditWeekDayAndRedirectWeekDaysHomePage() throws Exception {
        mockMvc.perform(post("/weekDays/editWeekDay"))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("weekDay"))
                .andExpect(redirectedUrl("/weekDays"));
    }

    @Test
    void shouldDeleteWeekDayAndRedirectWeekDaysHomePage() throws Exception {
        mockMvc.perform(get("/weekDays/deleteWeekDay/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/weekDays"));
    }
}