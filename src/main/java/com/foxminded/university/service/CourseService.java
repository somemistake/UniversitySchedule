package com.foxminded.university.service;

import com.foxminded.university.data.CourseRepository;
import com.foxminded.university.exception.DAOException;
import com.foxminded.university.model.main.Course;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository repository;

    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Optional<Course> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public List<Course> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Course save(Course value) throws DAOException {
        return repository.save(value);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
