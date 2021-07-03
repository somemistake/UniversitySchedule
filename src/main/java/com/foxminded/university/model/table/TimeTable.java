package com.foxminded.university.model.table;

import com.foxminded.university.model.time.TimeSlot;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class TimeTable {
    private LocalDate date;
    private List<TimeSlot> timeSlots;
    private List<String> groups;
    private Map<String, List<String>> schedule;

    public TimeTable() {
    }

    public TimeTable(LocalDate date, List<TimeSlot> timeSlots, List<String> groups, Map<String, List<String>> schedule) {
        this.date = date;
        this.timeSlots = timeSlots;
        this.groups = groups;
        this.schedule = schedule;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<TimeSlot> getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(List<TimeSlot> timeSlots) {
        this.timeSlots = timeSlots;
    }

    public List<String> getGroups() {
        return groups;
    }

    public void setGroups(List<String> groups) {
        this.groups = groups;
    }

    public Map<String, List<String>> getSchedule() {
        return schedule;
    }

    public void setSchedule(Map<String, List<String>> schedule) {
        this.schedule = schedule;
    }
}
