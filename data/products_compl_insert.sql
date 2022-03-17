create table pType(
	id serial primary key,
	name varchar(255)
);

create table product(
	id serial primary key,
	name varchar(255),
	type_id int references pType(id),
	expired_date date, 
	price float
);

insert into pType(name) values ('Сыр');
insert into pType(name) values ('Молоко');
insert into pType(name) values ('Мороженное');

insert into product (name, type_id, expired_date, price) values ('Чеддер', 1, '20.03.2022', 795.44);
insert into product (name, type_id, expired_date, price) values ('Масдам', 1, '16.03.2022', 888.02);

insert into product (name, type_id, expired_date, price) values ('Коровье', 2, '21.03.2022', 68.01);
insert into product (name, type_id, expired_date, price) values ('Козбе', 2, '24.03.2022', 143.55);
insert into product (name, type_id, expired_date, price) values ('Кумыс', 2, '17.03.2022', 345.77);

insert into product (name, type_id, expired_date, price) values ('Лакомка', 3, '30.03.2022', 90.11);
insert into product (name, type_id, expired_date, price) values ('Пломбир', 3, '25.03.2022', 110.18);