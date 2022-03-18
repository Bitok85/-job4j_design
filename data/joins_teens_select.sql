select *
from employees as t1 left join departments as t2 on t1.departments_id = t2.id

select *
from departments as t1 right join employees as t2 on t2.departments_id = t1.id

select *
from employees as t1 full join departments as t2 on t1.departments_id = t2.id

select * 
from employees as t1 cross join departments as t2

--у меня не было депертаментов без сотрудников, добавил здесь.
insert into departments(name) values ('Налоговой');
insert into employees(name, departments_id) values(null, 4);

select t2.name, count(t1.name)
from employees as t1 left join departments as t2 on t1.departments_id = t2.id
group by t2.name
having count(t1.name) = 0

--полагаю, что под одинаковым результатом подразумевается именно порядок присоединения столбцов, т.к.
--при наличии null полей в разных положениях в обеих таблицах без каких-то "костыльных" манипуляций
--идентичный результат не вырисовывается
select *
from employees as t1 left join departments as t2 on t1.departments_id = t2.id

select *
from employees as t1 right join departments as t2 on t1.departments_id = t2.id

select t1.name, t2.name, (t1.name ||' '|| t2.name) as "пара" 
from teens t1 cross join teens t2
where t1.gender != t2.gender and t1.gender != 'Ж'