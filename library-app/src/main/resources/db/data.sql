insert into book(name, author, price, isbn)
values('Thinking In Java', 'Bruce Eckel', 260, '9787111002871');

insert into user(username, password)
-- password 123456
values ('admin', '$2a$10$FUQQ3R3I5EH8yfrOqdghz.Gcxfcco7gC0vxYN.SOJAa0THnFp712i');

insert into role(name)
values ('administrator');

insert into user_role(username, role_name)
values ('admin', 'administrator');