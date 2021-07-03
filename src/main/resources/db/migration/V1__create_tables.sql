drop table if exists audiences;
drop table if exists courses;
drop table if exists groups;
drop table if exists teachers;
drop table if exists students;
drop table if exists template_lessons;
drop table if exists time_slots;
drop table if exists week_days;
drop table if exists periods;

create table audiences(
    id serial not null primary key,
    number varchar(100) not null unique
);

create table courses(
    id serial not null primary key,
    name varchar(100) not null unique,
    description text not null
);

create table groups(
    id serial not null primary key,
    name varchar(100) not null unique
);

create table periods(
    id serial not null primary key,
    period_start date not null,
    period_finish date not null
);

create table students(
    id serial not null primary key,
    first_name varchar(100) not null,
    last_name varchar(100) not null,
    group_id integer not null,
    foreign key (group_id) references groups(id)
);

create table teachers(
    id serial not null primary key,
    first_name varchar(100) not null,
    last_name varchar(100) not null
);

create table time_slots(
    id serial not null primary key,
    slot integer not null
);

create table week_days(
    id serial not null primary key,
    day varchar(100) not null
);

create table template_lessons(
    id serial not null primary key,
    group_id integer not null,
    course_id integer not null,
    teacher_id integer not null,
    audience_id integer not null,
    time_slot_id integer not null,
    week_day_id integer not null,
    period_id integer not null,
    foreign key (group_id) references groups(id),
    foreign key (course_id) references courses(id),
    foreign key (teacher_id) references teachers(id),
    foreign key (audience_id) references audiences(id),
    foreign key (time_slot_id) references time_slots(id),
    foreign key (week_day_id) references week_days(id),
    foreign key (period_id) references periods(id)
);

alter table template_lessons add constraint template_lessons_single_audience_per_time unique
(audience_id, time_slot_id, week_day_id, period_id);

alter table template_lessons add constraint template_lessons_single_group_per_time unique
(group_id, time_slot_id, week_day_id, period_id);

alter table template_lessons add constraint template_lessons_single_teacher_per_time unique
(teacher_id, time_slot_id, week_day_id, period_id);