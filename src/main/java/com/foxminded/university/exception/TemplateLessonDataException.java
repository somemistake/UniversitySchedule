package com.foxminded.university.exception;

public class TemplateLessonDataException extends RuntimeException {
    public TemplateLessonDataException() {
    }

    public TemplateLessonDataException(String message) {
        super(message);
    }

    public TemplateLessonDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public TemplateLessonDataException(Throwable cause) {
        super(cause);
    }
}
