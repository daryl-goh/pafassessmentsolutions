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
