select *
from employees as t1 left join departments as t2 on t1.departments_id = t2.id

select *
from departments as t1 right join employees as t2 on t2.departments_id = t1.id

select *
from employees as t1 full join departments as t2 on t1.departments_id = t2.id

select * 
from employees as t1 cross join departments as t2

select t1.name, t2.name, (t1.name ||' '|| t2.name) as "пара" 
from teens t1 cross join teens t2
where t1.gender != t2.gender and t1.gender != 'Ж'
