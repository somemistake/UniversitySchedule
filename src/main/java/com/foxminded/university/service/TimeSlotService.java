package com.foxminded.university.service;

import com.foxminded.university.data.TimeSlotRepository;
import com.foxminded.university.exception.DAOException;
import com.foxminded.university.model.time.TimeSlot;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TimeSlotService {
    private final TimeSlotRepository repository;

    public TimeSlotService(TimeSlotRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Optional<TimeSlot> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public List<TimeSlot> findAll() {
        return repository.findAll();
    }

    @Transactional
    public TimeSlot save(TimeSlot value) throws DAOException {
        return repository.save(value);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
