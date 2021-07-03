package com.foxminded.university.model.time;

import com.foxminded.university.model.lesson.TemplateLesson;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "week_days")
public class WeekDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "day")
    private String day;

    @OneToMany(mappedBy = "weekDay")
    private Set<TemplateLesson> templateLessons;

    public WeekDay() {
    }

    public WeekDay(String day) {
        this.day = day;
    }

    public WeekDay(Long id, String day) {
        this.id = id;
        this.day = day;
    }

    public Long getId() {
        return id;
    }

    public String getDay() {
        return day;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Set<TemplateLesson> getTemplateLessons() {
        return templateLessons;
    }

    public void setTemplateLessons(Set<TemplateLesson> templateLessons) {
        this.templateLessons = templateLessons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeekDay weekDay = (WeekDay) o;
        return day.equals(weekDay.day);
    }

    @Override
    public int hashCode() {
        return Objects.hash(day);
    }
}
