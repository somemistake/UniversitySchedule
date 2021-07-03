package com.foxminded.university.exception;

import com.foxminded.university.model.main.Course;

public class CourseDataException extends RuntimeException {
    private Course course;

    public CourseDataException() {
    }

    public CourseDataException(String message) {
        super(message);
    }

    public CourseDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public CourseDataException(Throwable cause) {
        super(cause);
    }

    public CourseDataException(Course course) {
        this.course = course;
    }

    public CourseDataException(String message, Course course) {
        super(message);
        this.course = course;
    }

    public CourseDataException(String message, Throwable cause, Course course) {
        super(message, cause);
        this.course = course;
    }

    public CourseDataException(Throwable cause, Course course) {
        super(cause);
        this.course = course;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
