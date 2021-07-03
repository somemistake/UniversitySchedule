package com.foxminded.university.service;

import com.foxminded.university.data.TemplateLessonRepository;
import com.foxminded.university.data.TimeSlotRepository;
import com.foxminded.university.model.lesson.TemplateLesson;
import com.foxminded.university.model.table.TimeTable;
import com.foxminded.university.model.time.TimeSlot;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TimeTableService {
    private final TimeSlotRepository timeSlotDAO;
    private final TemplateLessonRepository templateLessonDAO;

    public TimeTableService(TimeSlotRepository timeSlotDAO, TemplateLessonRepository templateLessonDAO) {
        this.timeSlotDAO = timeSlotDAO;
        this.templateLessonDAO = templateLessonDAO;
    }

    @Transactional
    public TimeTable getTimeTableByDate(LocalDate date) {
        List<TemplateLesson> lessons = templateLessonDAO.findAllByDate(date);
        List<TimeSlot> timeSlots = timeSlotDAO.findAll();
        List<String> groups = lessons
                .stream()
                .map(lesson -> lesson.getGroup().getName())
                .distinct()
                .collect(Collectors.toList());
        Map<String, List<String>> schedule = new HashMap<>();
        groups.forEach(group -> schedule.put(group,
                Stream
                        .generate(String::new)
                        .limit(timeSlots.size())
                        .collect(Collectors.toList())));
        lessons
                .stream()
                .filter(lesson -> lesson.getWeekDay().getDay().toUpperCase().equals(date.getDayOfWeek().toString()))
                .forEach(lesson -> schedule.get(lesson.getGroup().getName())
                        .add(lesson.getTimeSlot().getSlot() - 1, lesson.getCourse().getName()));
        return new TimeTable(date, timeSlots, groups, schedule);
    }
}
