package com.foxminded.university.service;

import com.foxminded.university.data.TemplateLessonRepository;
import com.foxminded.university.exception.DAOException;
import com.foxminded.university.model.lesson.TemplateLesson;
import com.foxminded.university.model.main.Audience;
import com.foxminded.university.model.main.Course;
import com.foxminded.university.model.main.Group;
import com.foxminded.university.model.main.Teacher;
import com.foxminded.university.model.time.Period;
import com.foxminded.university.model.time.TimeSlot;
import com.foxminded.university.model.time.WeekDay;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class TemplateLessonServiceTest {
    @MockBean
    private TemplateLessonRepository repository;

    @MockBean
    private EntityManager entityManager;

    @MockBean
    private Query query;

    @Autowired
    private TemplateLessonService service;

    @Test
    void shouldFindById() {
        TemplateLesson expected = new TemplateLesson(
                1l,
                new Group(1l, "1"),
                new Course(1l, "1", "1"),
                new Teacher(1l, "1", "1"),
                new Audience(1l, "1"),
                new TimeSlot(1l, 1),
                new WeekDay(1l, "1"),
                new Period(1l, LocalDate.of(2000, 1, 1),
                        LocalDate.of(2000, 2, 1)));
        when(entityManager.find(TemplateLesson.class, 1l)).thenReturn(expected);
        when(repository.findById(1l)).thenReturn(Optional.of(expected));
        assertEquals(Optional.of(expected), service.findById(1l));
    }

    @Test
    void shouldFindAll() {
        List<TemplateLesson> expected = new ArrayList<>();
        when(entityManager.createQuery(any(String.class))).thenReturn(query);
        when(query.getResultList()).thenReturn(expected);
        when(repository.findAll()).thenReturn(expected);
        assertEquals(expected, service.findAll());
    }

    @Test
    void shouldSaveInstance() throws DAOException {
        TemplateLesson expected = new TemplateLesson(
                1l,
                new Group(1l, "1"),
                new Course(1l, "1", "1"),
                new Teacher(1l, "1", "1"),
                new Audience(1l, "1"),
                new TimeSlot(1l, 1),
                new WeekDay(1l, "1"),
                new Period(1l, LocalDate.of(2000, 1, 1),
                        LocalDate.of(2000, 2, 1)));
        when(repository.save(expected)).thenReturn(expected);
        assertEquals(expected, service.save(expected));
    }
}