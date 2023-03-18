--liquibase formatted sql

--changeset marybokhan:create_hotel_tables

create schema if not exists hotels_schema;
set schema 'hotels_schema';

create table if not exists users
(
    id bigserial,
	role varchar(45) not null,
	email varchar(45) not null unique,
	password varchar(60) not null,
	name varchar(45) not null,
	surname varchar(45) not null,
	birthday date not null,
	primary key (id)
);

create table if not exists hotels
(
    id bigserial,
    name varchar(45) not null,
    city varchar(45) not null,
    country varchar(45) not null,
    nutrition_type varchar(45) not null,
    stars integer not null,
    rooms integer not null,
    description varchar(1000) not null,
    image varchar(200) not null,
    min_price decimal not null,
    max_price decimal not null,
	primary key (id)
);

create table if not exists costs
(
    id bigserial,
    hotel_id bigint not null,
    room_type varchar(45) not null,
    price decimal not null,
    foreign key (hotel_id) references hotels (id) on update cascade on delete cascade,
	primary key (id)
);

create table if not exists bookings
(
    id bigserial,
    user_id bigint not null,
    cost_id bigint not null,
    price decimal,
    start_date date not null,
    end_date date not null,
    is_confirmed boolean not null default false,
    foreign key (cost_id) references costs (id) on update cascade on delete cascade,
    foreign key (user_id) references users (id) on update cascade on delete cascade,
	primary key (id)
);

create table if not exists feedbacks
(
    id bigserial,
    user_id bigint not null,
    hotel_id bigint not null,
    stars integer not null,
    text varchar(300) not null,
    foreign key (hotel_id) references hotels (id) on update cascade on delete cascade,
    foreign key (user_id) references users (id) on update cascade on delete cascade,
	primary key (id)
);