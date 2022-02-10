
create table person(
	id serial primary key,
	name varchar(255),
	age smallint,
	birthDate date,
	vacant boolean
);

insert into person(name, age, birthdate, vacant)
values ('Kot Matroskin', '20', '2001-04-02 ', 'true');

select * from person;

update person set name = 'Sharik the Dog';

delete from person;