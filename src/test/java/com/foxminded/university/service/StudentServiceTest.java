package com.foxminded.university.service;

import com.foxminded.university.data.StudentRepository;
import com.foxminded.university.exception.DAOException;
import com.foxminded.university.model.main.Group;
import com.foxminded.university.model.main.Student;
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
class StudentServiceTest {
    @MockBean
    private StudentRepository repository;

    @MockBean
    private EntityManager entityManager;

    @MockBean
    private Query query;

    @Autowired
    private StudentService service;

    @Test
    void shouldFindById() {
        Student expected = new Student(1l, "1", "1", new Group(1l, "1"));
        when(entityManager.find(Student.class, 1l)).thenReturn(expected);
        when(repository.findById(1l)).thenReturn(Optional.of(expected));
        assertEquals(Optional.of(expected), service.findById(1l));
    }

    @Test
    void shouldFindAll() {
        List<Student> expected = new ArrayList<>();
        when(entityManager.createQuery(any(String.class))).thenReturn(query);
        when(query.getResultList()).thenReturn(expected);
        when(repository.findAll()).thenReturn(expected);
        assertEquals(expected, service.findAll());
    }

    @Test
    void shouldSaveInstance() throws DAOException {
        Student expected = new Student(1l, "1", "1", new Group(1l, "1"));
        when(repository.save(expected)).thenReturn(expected);
        assertEquals(expected, service.save(expected));
    }
}