insert into user_details(id, birth_date, name)
values(10001, current_date(), 'Yiğit');

insert into user_details(id, birth_date, name)
values(10002, current_date(), 'Lara');

insert into user_details(id, birth_date, name)
values(10003, current_date(), 'Elif');

insert into post(id, description, user_id)
values(20001, 'Did you guys hear that first human trials for Neuralink has happened !?', 10001);

insert into post(id, description, user_id)
values(20002, 'Theres no transfer rumors for Beşiktaş, sadly.', 10001);

insert into post(id, description, user_id)
values(20003, 'Playing drums are my life', 10002);

insert into post(id, description, user_id)
values(20004, 'Im a good chef yo.', 10003);