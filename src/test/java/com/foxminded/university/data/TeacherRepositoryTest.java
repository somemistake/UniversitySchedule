package com.foxminded.university.data;

import com.foxminded.university.exception.DAOException;
import com.foxminded.university.model.main.Teacher;
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
class TeacherRepositoryTest {
    @Autowired
    private TeacherRepository repository;

    @AfterAll
    @Sql("/test/sql/teacher/cleanTable.sql")
    static void cleanUp() {

    }

    @Test
    void shouldPersistTeacher() throws DAOException {
        Teacher actual = repository.save(new Teacher("1", "1"));
        assertEquals("1", actual.getFirstName());
        assertEquals("1", actual.getLastName());
    }

    @Test
    void shouldThrowDataIntegrityViolationException() {
        assertThrows(DataIntegrityViolationException.class, () -> repository.save(new Teacher()));
    }

    @Test
    void shouldThrowInvalidDataAccessApiUsageException() {
        assertThrows(InvalidDataAccessApiUsageException.class, () -> repository.save(null));
    }

    @Test
    @Sql(scripts = {"/test/sql/teacher/cleanTable.sql", "/test/sql/teacher/shouldFindAll.sql"})
    void shouldFindAllTeachers() {
        List<Teacher> expected = Arrays.asList(
                new Teacher(1l, "1", "1"),
                new Teacher(2l, "2", "2"),
                new Teacher(3l, "3", "3")
        );
        assertEquals(expected, repository.findAll());
    }

    @Test
    @Sql("/test/sql/teacher/cleanTable.sql")
    void shouldReturnEmptyList() {
        List<Teacher> expected = new ArrayList<>();
        assertEquals(expected, repository.findAll());
    }

    @Test
    @Sql("/test/sql/teacher/shouldFindById.sql")
    void shouldFindById() {
        assertEquals(Optional.of(new Teacher(100l, "3", "3")), repository.findById(100l));
    }

    @Test
    void shouldReturnEmptyOptional() {
        assertEquals(Optional.empty(), repository.findById(1000l));
    }
}