insert into attachs(name) values('readme.txt');

insert into comments(name) values('unchecked');

insert into item(name, comments_id, attachs_id) values ('Med', 1, 1);

insert into users(name, item_id) values ('Kot Matroskin', 1);

insert into role_(name, users_id) values ('Admin', 1);

insert into rules(name) values ('remotely');

insert into role_rules(role_id, rules_id) values (1, 1);

insert into category(name, item_id) values('18+', 1);

insert into state(name, item_id) values('open');