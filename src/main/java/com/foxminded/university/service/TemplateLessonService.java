package com.foxminded.university.service;

import com.foxminded.university.data.TemplateLessonRepository;
import com.foxminded.university.exception.DAOException;
import com.foxminded.university.model.lesson.TemplateLesson;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TemplateLessonService {
    private final TemplateLessonRepository repository;

    public TemplateLessonService(TemplateLessonRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Optional<TemplateLesson> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public List<TemplateLesson> findAll() {
        return repository.findAll();
    }

    @Transactional
    public TemplateLesson save(TemplateLesson value) throws DAOException {
        return repository.save(value);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
