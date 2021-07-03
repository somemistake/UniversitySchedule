package com.foxminded.university.service;

import com.foxminded.university.data.CourseRepository;
import com.foxminded.university.exception.DAOException;
import com.foxminded.university.model.main.Course;
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
class CourseServiceTest {
    @MockBean
    private CourseRepository repository;

    @MockBean
    private EntityManager entityManager;

    @MockBean
    private Query query;

    @Autowired
    private CourseService service;

    @Test
    void shouldFindById() {
        Course expected = new Course(1l, "a", "a");
        when(entityManager.find(Course.class, 1l)).thenReturn(expected);
        when(repository.findById(1l)).thenReturn(Optional.of(expected));
        assertEquals(Optional.of(expected), service.findById(1l));
    }

    @Test
    void shouldFindAll() {
        List<Course> expected = new ArrayList<>();
        when(entityManager.createQuery(any(String.class))).thenReturn(query);
        when(query.getResultList()).thenReturn(expected);
        when(repository.findAll()).thenReturn(expected);
        assertEquals(expected, service.findAll());
    }

    @Test
    void shouldSaveInstance() throws DAOException {
        Course expected = new Course(1l, "a", "a");
        when(repository.save(expected)).thenReturn(expected);
        assertEquals(expected, service.save(expected));
    }
}