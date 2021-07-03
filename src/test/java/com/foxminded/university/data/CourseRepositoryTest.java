package com.foxminded.university.data;

import com.foxminded.university.model.main.Course;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
class CourseRepositoryTest {
    @Autowired
    private CourseRepository repository;

    @AfterAll
    @Sql("/test/sql/course/cleanTable.sql")
    static void cleanUp() {

    }

    @Test
    void shouldPersistCourse() {
        Course actual = repository.save(new Course("100", "100"));
        assertEquals("100", actual.getName());
        assertEquals("100", actual.getDescription());
    }

    @Test
    void shouldThrowDataIntegrityViolationException() {
        assertThrows(DataIntegrityViolationException.class, () -> repository.save(new Course()));
        repository.save(new Course("1", "1"));
        assertThrows(DataIntegrityViolationException.class, () -> repository.save(new Course("1", "1")));
    }

    @Test
    void shouldThrowInvalidDataAccessApiUsageException() {
        assertThrows(InvalidDataAccessApiUsageException.class, () -> repository.save(null));
    }

    @Test
    @Sql(scripts = {"/test/sql/course/cleanTable.sql", "/test/sql/course/shouldFindAll.sql"})
    void shouldFindAllCourses() {
        List<Course> expected = Arrays.asList(
                new Course(1l, "1", "1"),
                new Course(2l, "2", "2"),
                new Course(3l, "3", "3"));
        assertEquals(expected, repository.findAll());
    }

    @Test
    @Sql("/test/sql/course/cleanTable.sql")
    void shouldReturnEmptyList() {
        List<Course> expected = new ArrayList<>();
        assertEquals(expected, repository.findAll());
    }

    @Test
    @Sql("/test/sql/course/shouldFindById.sql")
    void shouldFindById() {
        assertEquals(Optional.of(new Course(100l, "1", "1")), repository.findById(100l));
    }

    @Test
    void shouldReturnEmptyOptional() {
        assertEquals(Optional.empty(), repository.findById(1000l));
    }
}