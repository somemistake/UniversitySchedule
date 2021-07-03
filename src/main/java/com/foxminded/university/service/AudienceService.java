package com.foxminded.university.service;

import com.foxminded.university.data.AudienceRepository;
import com.foxminded.university.exception.DAOException;
import com.foxminded.university.model.main.Audience;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AudienceService {
    private final AudienceRepository repository;

    public AudienceService(AudienceRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Optional<Audience> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public List<Audience> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Audience save(Audience value) throws DAOException {
        return repository.save(value);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
