insert into user(username, password)
-- password 123456
values ('admin', 'e10adc3949ba59abbe56e057f20f883e');

insert into role(name)
values ('administrator');

insert into user_role(username, role_name)
values ('admin', 'administrator');

