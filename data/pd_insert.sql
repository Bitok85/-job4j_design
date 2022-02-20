create table devices(
	id serial primary key,
	name varchar(255),
	price float
);

create table people(
	id serial primary key,
	name varchar(255)
);

create table devices_people(
	id serial primary key,
	people_id int references people(id),
	device_id int references devices(id)
);

insert into devices(name, price) values('iphone12', 100000);
insert into devices(name, price) values('redmi note', 20000);
insert into devices(name, price) values('apple watch', 42000.50);
insert into devices(name, price) values('android watch', 12000);
insert into devices(name, price) values('ps5', 52000.40);
insert into devices(name, price) values('xbox_x',53000);

insert into people(name) values('Fedor the unckle');
insert into people(name) values('Matroskin the cat');
insert into people(name) values('Sharik the dogo');

insert into devices_people(people_id, device_id) values(1, 2), (1, 4), (1, 6);
insert into devices_people(people_id, device_id) values(2, 1), (2, 3), (2, 5);
insert into devices_people(people_id, device_id) values(3, 1), (3, 4), (3, 6);

