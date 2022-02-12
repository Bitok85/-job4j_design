insert into category(name) values('18+');

insert into statee(name) values('open');

insert into rules(name) values('remotely');

insert into rolee(name) values('Admin');

insert into role_rules(rolee_id, rules_id) values (1, 1);

insert into users(name, rolee_id) values('Med', 1);

insert into item(name, users_id, category_id, statee_id) values('Matroskin', 1, 1, 1);

insert into attachs(name, item_id) values ('readme.txt', 1);

insert into commentss(name, item_id) values('unchecked', 1);