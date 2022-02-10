create table car_body(
	id serial primary key,
	VIN int,
	color text
);

create table car(
	id serial primary key,
	mark varchar(255),
	engine_vol double precision,
	VIN_id int references car_body(id) unique 
);