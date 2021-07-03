package com.foxminded.university.data;

import com.foxminded.university.model.lesson.TemplateLesson;
import com.foxminded.university.model.main.Audience;
import com.foxminded.university.model.main.Course;
import com.foxminded.university.model.main.Group;
import com.foxminded.university.model.main.Teacher;
import com.foxminded.university.model.time.Period;
import com.foxminded.university.model.time.TimeSlot;
import com.foxminded.university.model.time.WeekDay;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
class TemplateLessonRepositoryTest {
    @Autowired
    private TemplateLessonRepository repository;

    @AfterAll
    @Sql("/test/sql/templatelesson/cleanTable.sql")
    static void cleanUp() {

    }

    @Test
    @Sql(scripts = {"/test/sql/templatelesson/cleanTable.sql", "/test/sql/templatelesson/shouldPersist.sql"})
    void shouldPersistTemplateLesson() {
        TemplateLesson actual = repository.save(new TemplateLesson(
                new Group(1l, "1"),
                new Course(1l, "1", "1"),
                new Teacher(1l, "1", "1"),
                new Audience(1l, "1"),
                new TimeSlot(1l, 1),
                new WeekDay(1l, "1"),
                new Period(1l, LocalDate.of(2000, 1, 1),
                        LocalDate.of(2000, 2, 1))));
        assertEquals(1l, actual.getGroup().getId());
        assertEquals(1l, actual.getCourse().getId());
        assertEquals(1l, actual.getTeacher().getId());
        assertEquals(1l, actual.getAudience().getId());
        assertEquals(1l, actual.getTimeSlot().getId());
        assertEquals(1l, actual.getWeekDay().getId());
        assertEquals(1l, actual.getPeriod().getId());
    }

    @Test
    void shouldThrowDataIntegrityViolationException() {
        assertThrows(DataIntegrityViolationException.class, () -> repository.save(new TemplateLesson()));
    }

    @Test
    void shouldThrowInvalidDataAccessApiUsageException() {
        assertThrows(InvalidDataAccessApiUsageException.class, () -> repository.save(null));
    }

    @Test
    @Sql(scripts = {"/test/sql/templatelesson/cleanTable.sql", "/test/sql/templatelesson/shouldFindAll.sql"})
    void shouldFindAllTemplateLesson() {
        List<TemplateLesson> expected = Arrays.asList(
                new TemplateLesson(
                        new Group(1l, "1"),
                        new Course(1l, "1", "1"),
                        new Teacher(1l, "1", "1"),
                        new Audience(1l, "1"),
                        new TimeSlot(1l, 1),
                        new WeekDay(1l, "1"),
                        new Period(1l, LocalDate.of(2000, 1, 1),
                                LocalDate.of(2000, 2, 1)))
        );
        assertEquals(expected, repository.findAll());
    }

    @Test
    @Sql("/test/sql/templatelesson/cleanTable.sql")
    void shouldReturnEmptyList() {
        List<TemplateLesson> expected = new ArrayList<>();
        assertEquals(expected, repository.findAll());
    }

    @Test
    @Sql(scripts = {"/test/sql/templatelesson/cleanTable.sql", "/test/sql/templatelesson/shouldFindById.sql"})
    void shouldFindById() {
        assertEquals(Optional.of(new TemplateLesson(
                        100l,
                        new Group(1l, "1"),
                        new Course(1l, "1", "1"),
                        new Teacher(1l, "1", "1"),
                        new Audience(1l, "1"),
                        new TimeSlot(1l, 1),
                        new WeekDay(1l, "1"),
                        new Period(1l, LocalDate.of(2000, 1, 1),
                                LocalDate.of(2000, 2, 1)))),
                repository.findById(100l));
    }

    @Test
    void shouldReturnEmptyOptional() {
        assertEquals(Optional.empty(), repository.findById(1000l));
    }
}