package com.foxminded.university.data;

import com.foxminded.university.exception.DAOException;
import com.foxminded.university.model.main.Group;
import com.foxminded.university.model.main.Student;
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
class StudentRepositoryTest {
    @Autowired
    private StudentRepository repository;

    @AfterAll
    @Sql("/test/sql/student/cleanTable.sql")
    static void cleanUp() {

    }

    @Test
    @Sql(scripts = {"/test/sql/student/cleanTable.sql", "/test/sql/student/shouldPersist.sql"})
    void shouldPersistStudent() throws DAOException {
        Student actual = repository.save(new Student("1", "1", new Group(1l, "1")));
        assertEquals("1", actual.getFirstName());
        assertEquals("1", actual.getLastName());
        assertEquals("1", actual.getGroup().getName());
    }

    @Test
    void shouldThrowDataIntegrityViolationException() {
        assertThrows(DataIntegrityViolationException.class, () -> repository.save(new Student()));
    }

    @Test
    void shouldThrowInvalidDataAccessApiUsageException() {
        assertThrows(InvalidDataAccessApiUsageException.class, () -> repository.save(null));
    }

    @Test
    @Sql(scripts = {"/test/sql/student/cleanTable.sql", "/test/sql/student/shouldFindAll.sql"})
    void shouldFindAllStudents() {
        List<Student> expected = Arrays.asList(
                new Student(1l, "1", "1", new Group(1l, "1")),
                new Student(2l, "2", "2", new Group(1l, "1")),
                new Student(3l, "3", "3", new Group(1l, "1"))
        );
        assertEquals(expected, repository.findAll());
    }

    @Test
    @Sql("/test/sql/student/cleanTable.sql")
    void shouldReturnEmptyList() {
        List<Student> expected = new ArrayList<>();
        assertEquals(expected, repository.findAll());
    }

    @Test
    @Sql(scripts = {"/test/sql/student/cleanTable.sql", "/test/sql/student/shouldFindById.sql"})
    void shouldFindById() {
        assertEquals(Optional.of(new Student(100l,
                "3",
                "3",
                new Group(1l, "1"))), repository.findById(100l));
    }

    @Test
    void shouldReturnEmptyOptional() {
        assertEquals(Optional.empty(), repository.findById(1000l));
    }
}