create table car_body(
	id serial primary key,
	body_type varchar(255),
	color varchar(255)
);

create table engine(
	id serial primary key,
	engine_type varchar(255),
	vol float
);

create table transmission(
	id serial primary key,
	transmission_type varchar(255)
);

create table car(
	id serial primary key,
	name varchar(255),
	car_body_id int references car_body(id),
	engine_id int references engine(id),
	transmission_id int references transmission(id)
);

insert into car_body(body_type, color) values('sedan', 'black');
insert into car_body(body_type, color) values('hatchback', 'white');
insert into car_body(body_type, color) values('coupe', 'green');
insert into car_body(body_type, color) values('raodster', 'purple');

insert into engine(engine_type, vol) values('gasoline', 2.0);
insert into engine(engine_type, vol) values('gasoline', 3.5);
insert into engine(engine_type, vol) values('diesel', 1.8);
insert into engine(engine_type, vol) values('diesel', 2.4);

insert into transmission(transmission_type) values('Manual');
insert into transmission(transmission_type) values('Automat');
insert into transmission(transmission_type) values('Robot');
insert into transmission(transmission_type) values('DSG');

insert into car(name, car_body_id, engine_id, transmission_id) values('Lada Vesta', 1, 1, 2);
insert into car(name, car_body_id, engine_id, transmission_id) values('Lada Kalina', 2, 2, 1);
insert into car(name, car_body_id, engine_id, transmission_id) values('Lada Priora', 1, 3, 1);
insert into car(name, car_body_id, engine_id, transmission_id) values('Hundai Tiburon', 3, 2, 4);
