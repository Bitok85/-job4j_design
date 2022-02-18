create table food(
	id serial primary key,
	 type_food varchar(255)
);

create table coock_time(
	id serial primary key,
	timing varchar(255),
	boil int,
	fry int,
	stew int,
	food_id int references food(id)
);

insert into food(type_food) values ('vegetables');
insert into food(type_food) values ('roots');
insert into food(type_food) values ('fish');
insert into food(type_food) values ('meat');

insert into coock_time(food_id, timing, boil, fry, stew) values (1, 'raw', 3, 2, 4);
insert into coock_time(food_id, timing, boil, fry, stew) values (2, 'short', 10, 5, 15);
insert into coock_time(food_id, timing, boil, fry, stew) values (3, 'medium', 13, 8, 20);
insert into coock_time(food_id, timing, boil, fry, stew) values (4, 'long', 20, 15, 30);
