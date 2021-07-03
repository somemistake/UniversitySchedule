package com.foxminded.university.exception;

import com.foxminded.university.model.main.Group;

public class GroupDataException extends RuntimeException {
    private Group group;

    public GroupDataException() {
    }

    public GroupDataException(String message) {
        super(message);
    }

    public GroupDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public GroupDataException(Throwable cause) {
        super(cause);
    }

    public GroupDataException(Group group) {
        this.group = group;
    }

    public GroupDataException(String message, Group group) {
        super(message);
        this.group = group;
    }

    public GroupDataException(String message, Throwable cause, Group group) {
        super(message, cause);
        this.group = group;
    }

    public GroupDataException(Throwable cause, Group group) {
        super(cause);
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
