package com.foxminded.university.data;

import com.foxminded.university.model.main.Group;
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
class GroupRepositoryTest {
    @Autowired
    private GroupRepository repository;

    @AfterAll
    @Sql("/test/sql/group/cleanTable.sql")
    static void cleanUp() {

    }

    @Test
    void shouldPersistGroup() {
        Group actual = repository.save(new Group("100"));
        assertEquals("100", actual.getName());
    }

    @Test
    void shouldThrowDataIntegrityViolationException() {
        assertThrows(DataIntegrityViolationException.class, () -> repository.save(new Group()));
        repository.save(new Group("1"));
        assertThrows(DataIntegrityViolationException.class, () -> repository.save(new Group("1")));
    }

    @Test
    void shouldThrowInvalidDataAccessApiUsageException() {
        assertThrows(InvalidDataAccessApiUsageException.class, () -> repository.save(null));
    }

    @Test
    @Sql(scripts = {"/test/sql/group/cleanTable.sql", "/test/sql/group/shouldFindAll.sql"})
    void shouldFindAllGroups() {
        List<Group> expected = Arrays.asList(
                new Group(1l, "101"),
                new Group(2l, "102"),
                new Group(3l, "103")
        );
        assertEquals(expected, repository.findAll());
    }

    @Test
    @Sql("/test/sql/group/cleanTable.sql")
    void shouldReturnEmptyList() {
        List<Group> expected = new ArrayList<>();
        assertEquals(expected, repository.findAll());
    }

    @Test
    @Sql("/test/sql/group/shouldFindById.sql")
    void shouldFindById() {
        assertEquals(Optional.of(new Group(100l, "9")), repository.findById(100l));
    }

    @Test
    void shouldReturnEmptyOptional() {
        assertEquals(Optional.empty(), repository.findById(1000l));
    }
}
