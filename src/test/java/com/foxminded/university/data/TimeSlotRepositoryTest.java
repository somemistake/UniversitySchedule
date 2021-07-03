package com.foxminded.university.data;

import com.foxminded.university.exception.DAOException;
import com.foxminded.university.model.time.TimeSlot;
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
class TimeSlotRepositoryTest {
    @Autowired
    private TimeSlotRepository repository;

    @AfterAll
    @Sql("/test/sql/timeslot/cleanTable.sql")
    static void cleanUp() {

    }

    @Test
    void shouldPersistTimeSlot() throws DAOException {
        TimeSlot actual = repository.save(new TimeSlot(1));
        assertEquals(1, actual.getSlot());
    }

    @Test
    void shouldThrowDataIntegrityViolationException() {
        assertThrows(DataIntegrityViolationException.class, () -> repository.save(new TimeSlot()));
    }

    @Test
    void shouldThrowInvalidDataAccessApiUsageException() {
        assertThrows(InvalidDataAccessApiUsageException.class, () -> repository.save(null));
    }

    @Test
    @Sql(scripts = {"/test/sql/timeslot/cleanTable.sql", "/test/sql/timeslot/shouldFindAll.sql"})
    void shouldFindAllTimeSlots() {
        List<TimeSlot> expected = Arrays.asList(
                new TimeSlot(1l, 1),
                new TimeSlot(2l, 2),
                new TimeSlot(3l, 3)
        );
        assertEquals(expected, repository.findAll());
    }

    @Test
    @Sql("/test/sql/timeslot/cleanTable.sql")
    void shouldReturnEmptyList() {
        List<TimeSlot> expected = new ArrayList<>();
        assertEquals(expected, repository.findAll());
    }

    @Test
    @Sql("/test/sql/timeslot/shouldFindById.sql")
    void shouldFindById() {
        assertEquals(Optional.of(new TimeSlot(100l, 3)), repository.findById(100l));
    }

    @Test
    void shouldReturnEmptyOptional() {
        assertEquals(Optional.empty(), repository.findById(1000l));
    }
}