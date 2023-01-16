drop database if exists eshop;

create database eshop;

use eshop;

drop table if exists customers;

create table customers (
    name varchar(32) not null,
    address varchar(128) not null,
    email varchar(128) not null,
    primary key (name)
);

insert into customers (name, address, email)
values 
('fred', '201 Cobblestone Lane', 'fredflintstone@bedrock.com'),
('sherlock', '221B Baker Street, London', 'sherlock@consultingdetective.org'),
('spongebob', '124 Conch Street, Bikini Bottom', 'spongebob@yahoo.com'),
('jessica', '698 Candlewood Land, Cabot Cove', 'fletcher@gmail.com'),
('dursley', '4 Privet Drive, Little Whinging, Surrey', 'dursley@gmail.com');

drop table if exists orders;

create table orders (
    order_id char(8) not null,
    name varchar(32) not null,
    order_date date not null,
    primary key (order_id),
    foreign key (name) references customers (name)
);

drop table if exists line_items;

create table line_items (
    order_id char(8) not null,
    item varchar(128) not null,
    quantity int not null,
    primary key(order_id, item),
    foreign key (order_id) references orders (order_id)
);

drop table if exists order_status;

create table order_status (
    order_id char(8) not null,
    delivery_id varchar(128),
    status enum('pending', 'dispatched'),
    status_update date,
    primary key (order_id),
    foreign key (order_id) references orders (order_id)
);