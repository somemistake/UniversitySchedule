package com.foxminded.university.model.main;

import com.foxminded.university.model.lesson.TemplateLesson;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @OneToMany(mappedBy = "teacher")
    private Set<TemplateLesson> templateLessons;

//    @ManyToMany
//    @JoinTable(name = "teacher_to_course",
//            joinColumns = {@JoinColumn(name = "teacher_id")},
//            inverseJoinColumns = {@JoinColumn(name = "course_id")})
//    private Set<Course> courses;

    public Teacher() {
    }

    public Teacher(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Teacher(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<TemplateLesson> getTemplateLessons() {
        return templateLessons;
    }

    public void setTemplateLessons(Set<TemplateLesson> templateLessons) {
        this.templateLessons = templateLessons;
    }

//    public Set<Course> getCourses() {
//        return courses;
//    }
//
//    public void setCourses(Set<Course> courses) {
//        this.courses = courses;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return firstName.equals(teacher.firstName) &&
                lastName.equals(teacher.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}
