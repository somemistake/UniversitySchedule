package com.foxminded.university.model.main;

import com.foxminded.university.model.lesson.TemplateLesson;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "audiences")
public class Audience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "number")
    private String number;

    @OneToMany(mappedBy = "audience")
    private Set<TemplateLesson> templateLessons;

    public Audience() {
    }

    public Audience(String number) {
        this.number = number;
    }

    public Audience(Long id, String number) {
        this.id = id;
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNumber(String number) {
        this.number = number;
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
        Audience audience = (Audience) o;
        return number.equals(audience.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
