package com.foxminded.university.data;


import com.foxminded.university.model.main.Audience;
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
class AudienceRepositoryTest {
    @Autowired
    private AudienceRepository repository;

    @AfterAll
    @Sql("/test/sql/audience/cleanTable.sql")
    static void cleanUp() {

    }

    @Test
    void shouldPersistAudience() {
        Audience actual = repository.save(new Audience("100"));
        assertEquals("100", actual.getNumber());
    }

    @Test
    void shouldThrowDataIntegrityViolationException() {
        assertThrows(DataIntegrityViolationException.class, () -> repository.save(new Audience()));
        repository.save(new Audience("1"));
        assertThrows(DataIntegrityViolationException.class, () -> repository.save(new Audience("1")));
    }

    @Test
    void shouldThrowInvalidDataAccessApiUsageException() {
        assertThrows(InvalidDataAccessApiUsageException.class, () -> repository.save(null));
    }

    @Test
    @Sql(scripts = {"/test/sql/audience/cleanTable.sql", "/test/sql/audience/shouldFindAll.sql"})
    void shouldFindAllAudiences() {
        List<Audience> expected = Arrays.asList(
                new Audience(1l, "100"),
                new Audience(2l, "101"),
                new Audience(3l, "102"));

        assertEquals(expected, repository.findAll());
    }

    @Test
    @Sql("/test/sql/audience/cleanTable.sql")
    void shouldReturnEmptyList() {
        List<Audience> expected = new ArrayList<>();
        assertEquals(expected, repository.findAll());
    }

    @Test
    @Sql("/test/sql/audience/shouldFindById.sql")
    void shouldFindById() {
        assertEquals(Optional.of(new Audience(10000l, "300")), repository.findById(10000l));
    }

    @Test
    void shouldReturnEmptyOptional() {
        assertEquals(Optional.empty(), repository.findById(2000l));
    }
}