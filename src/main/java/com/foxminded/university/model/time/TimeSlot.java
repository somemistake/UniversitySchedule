package com.foxminded.university.model.time;

import com.foxminded.university.model.lesson.TemplateLesson;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "time_slots")
public class TimeSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "slot")
    private Integer slot;

    @OneToMany(mappedBy = "timeSlot")
    private Set<TemplateLesson> templateLessons;

    public TimeSlot() {
    }

    public TimeSlot(int slot) {
        this.slot = slot;
    }

    public TimeSlot(Long id, int slot) {
        this.id = id;
        this.slot = slot;
    }

    public Long getId() {
        return id;
    }

    public Integer getSlot() {
        return slot;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSlot(Integer slot) {
        this.slot = slot;
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
        TimeSlot timeSlot = (TimeSlot) o;
        return slot.equals(timeSlot.slot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(slot);
    }
}
