package com.foxminded.university.model.lesson;

import com.foxminded.university.model.main.Audience;
import com.foxminded.university.model.main.Course;
import com.foxminded.university.model.main.Group;
import com.foxminded.university.model.main.Teacher;
import com.foxminded.university.model.time.TimeSlot;

import javax.persistence.*;
import java.util.Objects;

@MappedSuperclass
public abstract class LessonInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
    @ManyToOne
    @JoinColumn(name = "audience_id")
    private Audience audience;
    @ManyToOne
    @JoinColumn(name = "time_slot_id")
    private TimeSlot timeSlot;

    public LessonInfo() {
    }

    public LessonInfo(Group group, Course course, Teacher teacher, Audience audience, TimeSlot timeSlot) {
        this.group = group;
        this.course = course;
        this.teacher = teacher;
        this.audience = audience;
        this.timeSlot = timeSlot;
    }

    public LessonInfo(Long id, Group group, Course course, Teacher teacher, Audience audience, TimeSlot timeSlot) {
        this.id = id;
        this.group = group;
        this.course = course;
        this.teacher = teacher;
        this.audience = audience;
        this.timeSlot = timeSlot;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Audience getAudience() {
        return audience;
    }

    public void setAudience(Audience audience) {
        this.audience = audience;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LessonInfo that = (LessonInfo) o;
        return group.equals(that.group) &&
                course.equals(that.course) &&
                teacher.equals(that.teacher) &&
                audience.equals(that.audience) &&
                timeSlot.equals(that.timeSlot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(group, course, teacher, audience, timeSlot);
    }
}
