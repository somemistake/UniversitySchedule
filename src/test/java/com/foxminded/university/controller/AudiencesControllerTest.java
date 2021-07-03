package com.foxminded.university.controller;

import com.foxminded.university.model.main.Audience;
import com.foxminded.university.service.AudienceService;
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
class AudiencesControllerTest {
    private MockMvc mockMvc;

    @MockBean
    private AudienceService service;

    @Autowired
    private AudiencesController controller;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void shouldGetAudiencesHomePage() throws Exception {
        mockMvc.perform(get("/audiences"))
                .andExpect(status().isOk())
                .andExpect(view().name("audience/audiences"));
    }

    @Test
    void shouldGetAudienceCreateForm() throws Exception {
        mockMvc.perform(get("/audiences/createAudienceForm"))
                .andExpect(status().isOk())
                .andExpect(view().name("audience/createAudienceForm"));
    }

    @Test
    void shouldCreateAudienceAndRedirectAudiencesHomePage() throws Exception {
        mockMvc.perform(post("/audiences/createAudience"))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("audience"))
                .andExpect(redirectedUrl("/audiences"));
    }

    @Test
    void shouldGetAudienceEditForm() throws Exception {
        when(service.findById(1l)).thenReturn(Optional.of(new Audience()));
        mockMvc.perform(get("/audiences/editAudienceForm/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("audience/editAudienceForm"));
    }

    @Test
    void shouldEditAudienceAndRedirectAudiencesHomePage() throws Exception {
        mockMvc.perform(post("/audiences/editAudience"))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("audience"))
                .andExpect(redirectedUrl("/audiences"));
    }

    @Test
    void shouldDeleteAudienceAndRedirectAudiencesHomePage() throws Exception {
        mockMvc.perform(get("/audiences/deleteAudience/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/audiences"));
    }
}