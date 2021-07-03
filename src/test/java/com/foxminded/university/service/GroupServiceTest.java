package com.foxminded.university.service;

import com.foxminded.university.data.GroupRepository;
import com.foxminded.university.exception.DAOException;
import com.foxminded.university.model.main.Group;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class GroupServiceTest {
    @MockBean
    private GroupRepository repository;

    @MockBean
    private EntityManager entityManager;

    @MockBean
    private Query query;

    @Autowired
    private GroupService service;

    @Test
    void shouldFindById() {
        Group expected = new Group(1l, "a");
        when(entityManager.find(Group.class, 1l)).thenReturn(expected);
        when(repository.findById(1l)).thenReturn(Optional.of(expected));
        assertEquals(Optional.of(expected), service.findById(1l));
    }

    @Test
    void shouldFindAll() {
        List<Group> expected = new ArrayList<>();
        when(entityManager.createQuery(any(String.class))).thenReturn(query);
        when(query.getResultList()).thenReturn(expected);
        when(repository.findAll()).thenReturn(expected);
        assertEquals(expected, service.findAll());
    }

    @Test
    void shouldSaveInstance() throws DAOException {
        Group expected = new Group(1l, "a");
        when(repository.save(expected)).thenReturn(expected);
        assertEquals(expected, service.save(expected));
    }
}