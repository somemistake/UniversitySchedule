package com.foxminded.university.data;

import com.foxminded.university.exception.DAOException;
import com.foxminded.university.model.time.Period;
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
class PeriodRepositoryTest {
    @Autowired
    private PeriodRepository repository;

    @AfterAll
    @Sql("/test/sql/period/cleanTable.sql")
    static void cleanUp() {

    }

    @Test
    void shouldPersistPeriod() throws DAOException {
        Period actual = repository.save(new Period(LocalDate.of(2000, 1, 1),
                LocalDate.of(2000, 2, 1)));
        assertEquals(LocalDate.of(2000, 1, 1), actual.getPeriodStart());
        assertEquals(LocalDate.of(2000, 2, 1), actual.getPeriodFinish());
    }

    @Test
    void shouldThrowDataIntegrityViolationException() {
        assertThrows(DataIntegrityViolationException.class, () -> repository.save(new Period()));
    }

    @Test
    void shouldThrowInvalidDataAccessApiUsageException() {
        assertThrows(InvalidDataAccessApiUsageException.class, () -> repository.save(null));
    }

    @Test
    @Sql(scripts = {"/test/sql/period/cleanTable.sql", "/test/sql/period/shouldFindAll.sql"})
    void shouldFindAllPeriods() {
        List<Period> expected = Arrays.asList(
                new Period(1l, LocalDate.of(2000, 1, 1),
                        LocalDate.of(2000, 2, 1)),
                new Period(2l, LocalDate.of(2000, 3, 1),
                        LocalDate.of(2000, 4, 1)));
        assertEquals(expected, repository.findAll());
    }

    @Test
    @Sql("/test/sql/period/cleanTable.sql")
    void shouldReturnEmptyList() {
        List<Period> expected = new ArrayList<>();
        assertEquals(expected, repository.findAll());
    }

    @Test
    @Sql("/test/sql/period/shouldFindById.sql")
    void shouldFindById() {
        assertEquals(Optional.of(new Period(100l, LocalDate.of(2000, 1, 1),
                LocalDate.of(2000, 2, 1))), repository.findById(100l));
    }

    @Test
    void shouldReturnEmptyOptional() {
        assertEquals(Optional.empty(), repository.findById(1000l));
    }
}