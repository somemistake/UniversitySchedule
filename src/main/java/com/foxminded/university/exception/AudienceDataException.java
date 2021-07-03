package com.foxminded.university.exception;

import com.foxminded.university.model.main.Audience;

public class AudienceDataException extends RuntimeException {
    private Audience audience;

    public AudienceDataException() {
    }

    public AudienceDataException(String message) {
        super(message);
    }

    public AudienceDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public AudienceDataException(Throwable cause) {
        super(cause);
    }

    public AudienceDataException(Audience audience) {
        this.audience = audience;
    }

    public AudienceDataException(String message, Audience audience) {
        super(message);
        this.audience = audience;
    }

    public AudienceDataException(String message, Throwable cause, Audience audience) {
        super(message, cause);
        this.audience = audience;
    }

    public AudienceDataException(Throwable cause, Audience audience) {
        super(cause);
        this.audience = audience;
    }

    public Audience getAudience() {
        return audience;
    }

    public void setAudience(Audience audience) {
        this.audience = audience;
    }
}
