drop database if exists eshop;

create database eshop;

use eshop;

create table customers (
    name varchar(32) not null,
    address varchar(128) not null,
    email varchar(128) not null,
    primary key (name)
)