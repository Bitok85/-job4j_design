create table snacks(
	id serial primary key,
	name varchar(255)
);

create table buffet_menu(
	id serial primary key,
	name varchar(255),
	snacks_id int references snacks(id)
);