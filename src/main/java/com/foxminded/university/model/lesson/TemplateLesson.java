package com.foxminded.university.model.lesson;

import com.foxminded.university.model.main.Audience;
import com.foxminded.university.model.main.Course;
import com.foxminded.university.model.main.Group;
import com.foxminded.university.model.main.Teacher;
import com.foxminded.university.model.time.Period;
import com.foxminded.university.model.time.TimeSlot;
import com.foxminded.university.model.time.WeekDay;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "template_lessons")
public class TemplateLesson extends LessonInfo {
    @ManyToOne
    @JoinColumn(name = "week_day_id")
    private WeekDay weekDay;
    @ManyToOne
    @JoinColumn(name = "period_id")
    private Period period;

    public TemplateLesson() {
    }

    public TemplateLesson(Group group, Course course, Teacher teacher, Audience audience, TimeSlot timeSlot, WeekDay weekDay, Period period) {
        super(group, course, teacher, audience, timeSlot);
        this.weekDay = weekDay;
        this.period = period;
    }

    public TemplateLesson(Long id, Group group, Course course, Teacher teacher, Audience audience, TimeSlot timeSlot, WeekDay weekDay, Period period) {
        super(id, group, course, teacher, audience, timeSlot);
        this.weekDay = weekDay;
        this.period = period;
    }

    public WeekDay getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(WeekDay weekDay) {
        this.weekDay = weekDay;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TemplateLesson that = (TemplateLesson) o;
        return weekDay.equals(that.weekDay) &&
                period.equals(that.period);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), weekDay, period);
    }
}
