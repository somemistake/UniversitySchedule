package com.foxminded.university.service;

import com.foxminded.university.data.WeekDayRepository;
import com.foxminded.university.exception.DAOException;
import com.foxminded.university.model.time.WeekDay;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class WeekDayService {
    private final WeekDayRepository repository;

    public WeekDayService(WeekDayRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Optional<WeekDay> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public List<WeekDay> findAll() {
        return repository.findAll();
    }

    @Transactional
    public WeekDay save(WeekDay value) throws DAOException {
        return repository.save(value);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
