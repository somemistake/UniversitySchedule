package com.foxminded.university.data;

import com.foxminded.university.model.lesson.TemplateLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TemplateLessonRepository extends JpaRepository<TemplateLesson, Long> {
    @Query("select templateLesson from TemplateLesson templateLesson where " +
            ":date between templateLesson.period.periodStart and templateLesson.period.periodFinish")
    List<TemplateLesson> findAllByDate(@Param("date") LocalDate date);
}