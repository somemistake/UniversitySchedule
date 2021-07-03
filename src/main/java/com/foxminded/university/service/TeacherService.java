package com.foxminded.university.service;

import com.foxminded.university.data.TeacherRepository;
import com.foxminded.university.exception.DAOException;
import com.foxminded.university.model.main.Teacher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    private final TeacherRepository repository;

    public TeacherService(TeacherRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Optional<Teacher> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public List<Teacher> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Teacher save(Teacher value) throws DAOException {
        return repository.save(value);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
