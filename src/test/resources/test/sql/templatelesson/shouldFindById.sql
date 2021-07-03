insert into groups(id, name) values (1, '1');
insert into courses(id, name, description) values (1, '1', '1');
insert into teachers(id, first_name, last_name) values (1, '1', '1');
insert into audiences(id, number) values (1, '1');
insert into time_slots(id, slot) values (1, 1);
insert into week_days(id, day) values (1, '1');
insert into periods(id, period_start, period_finish) values (1, '2000-01-01', '2000-02-01');
insert into template_lessons(id, group_id, course_id, teacher_id, audience_id, time_slot_id, week_day_id, period_id) values (100, 1, 1, 1, 1, 1, 1, 1);