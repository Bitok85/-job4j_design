
select t1.name, t2.name 
from person as t1 inner join company as t2 on t1.company_id = t2.id
where t2.id != 5;

select t2.name, count(*)
from person as t1 inner join company as t2 on t1.company_id = t2.id
group by t2.name
having count(*) = (
	select max(empl_count) 
	from (
			select t2.name, count(*) as empl_count
			from person as t1 inner join company as t2 on t1.company_id = t2.id
			group by t2.name
		) as t3
	);
