create table category (
	id serial primary key,
	name text
);

create table statee (
	id serial primary key,
	name text
);

create table rules (
	id serial primary key,
	name text
);

create table rolee (
	id serial primary key,
	name text
);

create table users (
	id serial primary key,
	name text,
	rolee_id int references rolee(id)
);

create table item (
	id serial primary key,
	name text,
	users_id int references users(id),
	category_id int references category(id),
	statee_id int references statee(id)
);

create table attachs (
	id serial primary key,
	name text,
	item_id int references item(id)
);

create table commentss (
	id serial primary key,
	name text,
	item_id int references item(id)
);

create table role_rules (
	id serial primary key,
	rolee_id int references rolee(id),
	rules_id int references rules(id)
);

