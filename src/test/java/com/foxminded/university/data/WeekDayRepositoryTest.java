package com.foxminded.university.data;

import com.foxminded.university.exception.DAOException;
import com.foxminded.university.model.time.WeekDay;
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
class WeekDayRepositoryTest {
    @Autowired
    private WeekDayRepository repository;

    @AfterAll
    @Sql("/test/sql/weekday/cleanTable.sql")
    static void cleanUp() {

    }

    @Test
    void shouldPersistWeekDay() throws DAOException {
        WeekDay actual = repository.save(new WeekDay("1"));
        assertEquals("1", actual.getDay());
    }

    @Test
    void shouldThrowDataIntegrityViolationException() {
        assertThrows(DataIntegrityViolationException.class, () -> repository.save(new WeekDay()));
    }

    @Test
    void shouldThrowInvalidDataAccessApiUsageException() {
        assertThrows(InvalidDataAccessApiUsageException.class, () -> repository.save(null));
    }

    @Test
    @Sql(scripts = {"/test/sql/weekday/cleanTable.sql", "/test/sql/weekday/shouldFindAll.sql"})
    void shouldFindAllWeekDays() {
        List<WeekDay> expected = Arrays.asList(
                new WeekDay(1l, "1"),
                new WeekDay(2l, "2"),
                new WeekDay(3l, "3")
        );
        assertEquals(expected, repository.findAll());
    }

    @Test
    @Sql("/test/sql/weekday/cleanTable.sql")
    void shouldReturnEmptyList() {
        List<WeekDay> expected = new ArrayList<>();
        assertEquals(expected, repository.findAll());
    }

    @Test
    @Sql("/test/sql/weekday/shouldFindById.sql")
    void shouldFindById() {
        assertEquals(Optional.of(new WeekDay(100l, "3")), repository.findById(100l));
    }

    @Test
    void shouldReturnEmptyOptional() {
        assertEquals(Optional.empty(), repository.findById(1000l));
    }
}