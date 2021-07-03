package com.foxminded.university.service;

import com.foxminded.university.data.StudentRepository;
import com.foxminded.university.exception.DAOException;
import com.foxminded.university.model.main.Student;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Optional<Student> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public List<Student> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Student save(Student value) throws DAOException {
        return repository.save(value);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
