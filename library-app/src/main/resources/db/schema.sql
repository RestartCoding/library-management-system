drop table if exists user;
create table user(
    id int auto_increment,
    username varchar(64) not null,
    password varchar(64) not null,
    create_time timestamp not null default current_timestamp,
    update_time timestamp not null default current_timestamp,
    primary key(id),
    unique(username)
);

drop table if exists book;
create table book(
    id int auto_increment,
    name varchar(256) not null,
    author varchar(256) not null,
    price real not null,
    isbn varchar(64) not null,
    borrower int,
    create_time timestamp not null default current_timestamp,
    update_time timestamp not null default current_timestamp,
    primary key (id),
    unique(name),
    unique(isbn)
);

drop table if exists role;
create table role(
    id int auto_increment,
    name varchar(64) not null ,
    create_time timestamp not null default current_timestamp,
    update_time timestamp not null default current_timestamp,
    primary key (id),
    unique(name)
);

drop table if exists user_role;
create table user_role(
    id int auto_increment,
    username varchar(64) not null,
    role_name varchar(64) not null,
    primary key (id),
    unique(username, role_name)
);