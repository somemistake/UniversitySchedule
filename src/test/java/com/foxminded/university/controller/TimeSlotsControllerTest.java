package com.foxminded.university.controller;

import com.foxminded.university.model.time.TimeSlot;
import com.foxminded.university.service.TimeSlotService;
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
class TimeSlotsControllerTest {
    private MockMvc mockMvc;

    @MockBean
    private TimeSlotService service;

    @Autowired
    private TimeSlotsController controller;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void shouldGetTimeSlotsHomePage() throws Exception {
        mockMvc.perform(get("/timeSlots"))
                .andExpect(status().isOk())
                .andExpect(view().name("timeSlot/timeSlots"));
    }

    @Test
    void shouldGetTimeSLotCreateForm() throws Exception {
        mockMvc.perform(get("/timeSlots/createTimeSlotForm"))
                .andExpect(status().isOk())
                .andExpect(view().name("timeSlot/createTimeSlotForm"));
    }

    @Test
    void shouldCreateTimeSlotAndRedirectTimeSlotsHomePage() throws Exception {
        mockMvc.perform(post("/timeSlots/createTimeSlot"))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("timeSlot"))
                .andExpect(redirectedUrl("/timeSlots"));
    }

    @Test
    void shouldGetTimeSlotEditForm() throws Exception {
        when(service.findById(1l)).thenReturn(Optional.of(new TimeSlot()));
        mockMvc.perform(get("/timeSlots/editTimeSlotForm/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("timeSlot/editTimeSlotForm"));
    }

    @Test
    void shouldEditTimeSlotAndRedirectTimeSlotsHomePage() throws Exception {
        mockMvc.perform(post("/timeSlots/editTimeSlot"))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("timeSlot"))
                .andExpect(redirectedUrl("/timeSlots"));
    }

    @Test
    void shouldDeleteTimeSlotAndRedirectTimeSlotsHomePage() throws Exception {
        mockMvc.perform(get("/timeSlots/deleteTimeSlot/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/timeSlots"));
    }
}