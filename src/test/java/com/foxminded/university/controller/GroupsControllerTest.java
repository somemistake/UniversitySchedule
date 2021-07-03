package com.foxminded.university.controller;

import com.foxminded.university.model.main.Group;
import com.foxminded.university.service.GroupService;
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
class GroupsControllerTest {
    private MockMvc mockMvc;

    @MockBean
    private GroupService service;

    @Autowired
    private GroupsController controller;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void shouldGetGroupsHomePage() throws Exception {
        mockMvc.perform(get("/groups"))
                .andExpect(status().isOk())
                .andExpect(view().name("group/groups"));
    }

    @Test
    void shouldGetGroupCreateForm() throws Exception {
        mockMvc.perform(get("/groups/createGroupForm"))
                .andExpect(status().isOk())
                .andExpect(view().name("group/createGroupForm"));
    }

    @Test
    void shouldCreateGroupAndRedirectGroupsHomePage() throws Exception {
        mockMvc.perform(post("/groups/createGroup"))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("group"))
                .andExpect(redirectedUrl("/groups"));
    }

    @Test
    void shouldGetGroupEditForm() throws Exception {
        when(service.findById(1l)).thenReturn(Optional.of(new Group()));
        mockMvc.perform(get("/groups/editGroupForm/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("group/editGroupForm"));
    }

    @Test
    void shouldEditGroupAndRedirectGroupsHomePage() throws Exception {
        mockMvc.perform(post("/groups/editGroup"))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("group"))
                .andExpect(redirectedUrl("/groups"));
    }

    @Test
    void shouldDeleteGroupAndRedirectGroupsHomePage() throws Exception {
        mockMvc.perform(get("/groups/deleteGroup/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/groups"));
    }
}