package com.foxminded.university.model.time;

import com.foxminded.university.model.lesson.TemplateLesson;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "periods")
public class Period {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "period_start")
    private LocalDate periodStart;
    @Column(name = "period_finish")
    private LocalDate periodFinish;

    @OneToMany(mappedBy = "period")
    private Set<TemplateLesson> templateLessons;

    public Period() {
    }

    public Period(LocalDate periodStart, LocalDate periodFinish) {
        this.periodStart = periodStart;
        this.periodFinish = periodFinish;
    }

    public Period(Long id, LocalDate periodStart, LocalDate periodFinish) {
        this.id = id;
        this.periodStart = periodStart;
        this.periodFinish = periodFinish;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getPeriodStart() {
        return periodStart;
    }

    public void setPeriodStart(LocalDate periodStart) {
        this.periodStart = periodStart;
    }

    public LocalDate getPeriodFinish() {
        return periodFinish;
    }

    public void setPeriodFinish(LocalDate periodFinish) {
        this.periodFinish = periodFinish;
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
        Period period = (Period) o;
        return periodStart.equals(period.periodStart) &&
                periodFinish.equals(period.periodFinish);
    }

    @Override
    public int hashCode() {
        return Objects.hash(periodStart, periodFinish);
    }
}
