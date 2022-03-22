--Составить первый JOIN-запрос, чтобы получить информацию обо всех студентах
-- (достаточно получить только имя и возраст студента) школы Хогвартс вместе с названиями факультетов.

select s.age, s.name, f.name
from faculty f
         inner join student s
                    on f.id = s.faculty_id;

--Составить второй JOIN-запрос, чтобы получить только тех студентов, у которых есть аватарки.
select s.*
from avatar a
         inner join student s on s.id = a.student_id
