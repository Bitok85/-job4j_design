create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna (name, avg_age, discovery_date) values ('Sword fish', 13000, '22.06.1878');
insert into fauna (name, avg_age, discovery_date) values ('killer whale', 35000, '15.01.1826');
insert into fauna (name, avg_age, discovery_date) values ('flying fish', 5000, '13.08.1951');
insert into fauna (name, avg_age, discovery_date) values ('Polar bear', 29000, '03.11.1821');
insert into fauna (name, avg_age, discovery_date) values ('fur seal', 9550, '06.09.1833');
insert into fauna (name, avg_age, discovery_date) values ('sea lion', 14330, '23.05.1855');