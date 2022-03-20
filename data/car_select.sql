select *
from car as t1 left join car_body as t2 on t1.car_body_id = t2.id
			   left join engine as t3 on t1.engine_id = t3.id
			   left join transmission as t4 on t1.transmission_id = t4.id;

select t1.id, body_type, color
from car_body as t1 left join car as t2 on t2.car_body_id = t1.id
where t2.id is null;

select t1.id, engine_type, vol
from engine as t1 left join car as t2 on t2.engine_id = t1.id
where t2.id is null;

select t1.id, transmission_type
from transmission as t1 left join car as t2 on t2.transmission_id = t1.id
where t2.id is null;