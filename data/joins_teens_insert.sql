create table departments(
	id serial primary key,
	name varchar(255)
);

create table employees(
	id serial primary key,
	name varchar(255),
	departments_id int references departments(id)
);

create table teens(
	id serial primary key,
	name varchar(255),
	gender varchar(255)
);

insert into departments(name) values ('Юридический департамент');
insert into departments(name) values ('Финансовый департамен');
insert into departments(name) values (null);

insert into employees(name, departments_id) values ('Иванов', 1);
insert into employees(name, departments_id) values ('Петров', 2);
insert into employees(name, departments_id) values (null, 1);
insert into employees(name, departments_id) values ('Сидоров', 3);

insert into teens(name, gender) values ('Вася', 'М');
insert into teens(name, gender) values ('Петя', 'М');
insert into teens(name, gender) values ('Коля', 'М');
insert into teens(name, gender) values ('Света', 'Ж');
insert into teens(name, gender) values ('Юля', 'Ж');
insert into teens(name, gender) values ('Лида', 'Ж');