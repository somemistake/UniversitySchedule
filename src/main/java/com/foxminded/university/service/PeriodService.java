package com.foxminded.university.service;

import com.foxminded.university.data.PeriodRepository;
import com.foxminded.university.exception.DAOException;
import com.foxminded.university.model.time.Period;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PeriodService {
    private final PeriodRepository repository;

    public PeriodService(PeriodRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Optional<Period> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public List<Period> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Period save(Period value) throws DAOException {
        return repository.save(value);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
