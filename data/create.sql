create table attachs (
	id serial primary key,
	name text
);

create table comments (
	id serial primary key,
	name text
);

create table item (
	id serial primary key,
	name text,
	comments_id int references comments_(id),
	attachs_id int references attachs(id)	
);

create table users (
	id serial primary key,
	name text,
	item_id int references item(id)
);

create table role (
	id serial primary key,
	name text,
	users_id int references users(id)
);

create table rules (
	id serial primary key,
	name text
);

create table role_rules (
	id serial primary key,
	role_id int references role_(id),
	rules_id int references rules(id)
);


create table category (
	id serial primary key,
	name text,
	item_id int references item(id)
);

create table state (
	id serial primary key,
	name text,
	item_id int references item(id)
);


