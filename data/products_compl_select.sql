select * from product
select * from pType

select *
from product as t1 inner join pType as t2 on t1.type_id = t2.id
where t2.name = 'Сыр'

select *
from product
where name like '%Лакомка%'

select *
from product
where expired_date < current_date

select name, price
from product
order by price desc limit 1

select t2.name, count(*)
from product as t1 inner join pType as t2 on t1.type_id = t2.id
group by t2.name

select *
from product as t1 inner join pType as t2 on t1.type_id = t2.id
where t2.name in ('Молоко', 'Сыр')

select t2.name, count(*)
from product as t1 inner join pType as t2 on t1.type_id = t2.id
group by t2.name
having count(*) < 3

select *
from product as t1 inner join pType as t2 on t1.type_id = t2.id






