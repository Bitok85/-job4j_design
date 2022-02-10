create table developers(
	id serial primary key,
	name varchar(255)
);

create table repositories(
	id serial primary key,
	name varchar(255)
);

create table devs_reps(
	id serial primary key,
	developer_id int references developers(id),
	repositorie_id int references repositories(id)
);