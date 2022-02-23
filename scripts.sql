-- Подключиться с помощью IDEA к базе данных и выполнить простой запрос select * from student
select *
from student;

-- 1. Получить всех студентов, возраст которых находится между 10 и 20 (можно подставить любые числа, главное, чтобы нижняя граница была меньше верхней).
select *
from student
where age between 4 and 8;
-- 2. Получить всех студентов, но отобразить только список их имен.
select name
from student;
-- 3. Получить всех студентов, у которых в имени присутствует буква «О» (или любая другая).
select *
from student
where name like '%5%';
-- 4. Получить всех студентов, у которых возраст меньше идентификатора.
select *
from student
where age < id;
-- 5. Получить всех студентов упорядоченных по возрасту.
select *
from student
order by age;

select *
from student
order by age desc;
