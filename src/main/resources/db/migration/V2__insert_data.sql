insert into audiences(id, number) values (1, 'a-1'),
                                         (2, 'a-2'),
                                         (3, 'a-3');

insert into courses(id, name, description) values (1, 'maths', 'learning maths'),
                                                  (2, 'physics', 'learning physics'),
                                                  (3, 'bio', 'learning bio'),
                                                  (4, 'history', 'learning history'),
                                                  (5, 'social science', 'learning social science'),
                                                  (6, 'literature', 'learning literature'),
                                                  (7, 'russian', 'learning russian'),
                                                  (8, 'english', 'learning english');

insert into groups(id, name) values (1, 'g-1'),
                                    (2, 'g-2'),
                                    (3, 'g-3');

insert into teachers(id, first_name, last_name) values (1, 'Edward', 'Crock'),
                                                       (2, 'Mathew', 'Allen'),
                                                       (3, 'Lewis', 'Scott'),
                                                       (4, 'Hope', 'Holder'),
                                                       (5, 'Sophie', 'McCartney'),
                                                       (6, 'Clare', 'Grey'),
                                                       (7, 'Nick', 'Stanford'),
                                                       (8, 'Claus', 'Bridge');

insert into students(id, first_name, last_name, group_id) values (1, 'Edward', 'Crock', 1),
                                                                 (2, 'Mathew', 'Allen', 1),
                                                                 (3, 'Lewis', 'Scott', 1),
                                                                 (4, 'Hope', 'Holder', 1),
                                                                 (5, 'Sophie', 'McCartney', 2),
                                                                 (6, 'Clare', 'Grey', 2),
                                                                 (7, 'Nick', 'Stanford', 2),
                                                                 (8, 'Claus', 'Bridge', 2),
                                                                 (9, 'Sophie', 'Rose', 3),
                                                                 (10, 'Peter', 'Norton', 3),
                                                                 (11, 'Harry', 'Potter', 3),
                                                                 (12, 'Stephanie', 'Brown', 3);

insert into time_slots(id, slot) values (1, 1), (2, 2), (3, 3), (4, 4), (5, 5), (6, 6);

insert into week_days(id, day) values (1, 'Monday'),
                                      (2, 'Tuesday'),
                                      (3, 'Wednesday'),
                                      (4, 'Thursday'),
                                      (5, 'Friday'),
                                      (6, 'Saturday');

insert into periods(id, period_start, period_finish) values (1, '2020-09-01', '2021-01-30'), (2, '2021-02-15', '2021-06-30');

insert into template_lessons(group_id, course_id, teacher_id, audience_id, time_slot_id, week_day_id, period_id) values
(1, 1, 1, 1, 1, 1, 1),
(1, 1, 1, 1, 2, 1, 1),
(1, 5, 5, 1, 3, 1, 1),
(1, 4, 4, 1, 4, 1, 1),
(1, 4, 4, 1, 5, 1, 1),
(2, 8, 8, 2, 1, 1, 1),
(2, 3, 3, 2, 2, 1, 1),
(2, 6, 6, 2, 3, 1, 1),
(3, 2, 2, 3, 5, 1, 1),
(3, 2, 2, 3, 6, 1, 1),
(1, 8, 8, 1, 1, 2, 1),
(1, 8, 8, 1, 2, 2, 1),
(1, 7, 7, 1, 4, 2, 1),
(2, 4, 4, 2, 1, 2, 1),
(3, 5, 5, 3, 1, 2, 1),
(3, 6, 6, 3, 5, 2, 1),
(1, 1, 1, 1, 1, 3, 1),
(1, 1, 1, 1, 2, 3, 1),
(1, 1, 1, 1, 3, 3, 1),
(1, 2, 2, 1, 5, 3, 1),
(2, 3, 3, 2, 1, 3, 1),
(2, 8, 8, 2, 3, 3, 1),
(3, 7, 7, 3, 1, 3, 1),
(3, 7, 7, 3, 2, 3, 1),
(3, 7, 7, 3, 3, 3, 1),
(1, 7, 7, 1, 1, 5, 1),
(1, 8, 8, 1, 2, 5, 1),
(2, 1, 1, 2, 1, 5, 1),
(2, 2, 2, 2, 2, 5, 1),
(2, 2, 2, 2, 3, 5, 1),
(3, 3, 3, 3, 1, 5, 1),
(3, 3, 3, 3, 2, 5, 1),
(3, 4, 4, 3, 3, 5, 1),
(1, 1, 1, 1, 1, 1, 2),
(1, 1, 1, 1, 2, 1, 2),
(1, 5, 5, 1, 3, 1, 2),
(1, 4, 4, 1, 4, 1, 2),
(1, 4, 4, 1, 5, 1, 2),
(2, 8, 8, 2, 1, 1, 2),
(2, 3, 3, 2, 2, 1, 2),
(2, 6, 6, 2, 3, 1, 2),
(3, 2, 2, 3, 5, 1, 2),
(3, 2, 2, 3, 6, 1, 2),
(1, 8, 8, 1, 1, 2, 2),
(1, 8, 8, 1, 2, 2, 2),
(1, 7, 7, 1, 4, 2, 2),
(2, 4, 4, 2, 1, 2, 2),
(3, 5, 5, 3, 1, 2, 2),
(3, 6, 6, 3, 5, 2, 2),
(1, 1, 1, 1, 1, 3, 2),
(1, 1, 1, 1, 2, 3, 2),
(1, 1, 1, 1, 3, 3, 2),
(1, 2, 2, 1, 5, 3, 2),
(2, 3, 3, 2, 1, 3, 2),
(2, 8, 8, 2, 3, 3, 2),
(3, 7, 7, 3, 1, 3, 2),
(3, 7, 7, 3, 2, 3, 2),
(3, 7, 7, 3, 3, 3, 2),
(1, 7, 7, 1, 1, 5, 2),
(1, 8, 8, 1, 2, 5, 2),
(2, 1, 1, 2, 1, 5, 2),
(2, 2, 2, 2, 2, 5, 2),
(2, 2, 2, 2, 3, 5, 2),
(3, 3, 3, 3, 1, 5, 2),
(3, 3, 3, 3, 2, 5, 2),
(3, 4, 4, 3, 3, 5, 2);