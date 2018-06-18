CREATE SCHEMA cinematest;

CREATE TABLE "customer" (
"id" serial PRIMARY key not null,
"name" varchar(30)
);

CREATE TABLE "movie" (
"id" serial PRIMARY key,
"name" varchar(30),
"description" varchar(30)
);

CREATE TABLE "theatre" (
"id" serial PRIMARY key,
"name" varchar(30)
);

CREATE TABLE "show" (
"id" serial primary key not null,
"movie_id" int references movie(id) not null,
"theatre_id" int references theatre(id) not null,
"starttime" timestamp,
"endtime" timestamp
);


CREATE TABLE "booking" (
"id" serial PRIMARY key,
"show_id" int references show(id),
"customer_id" int references customer(id)

);


CREATE TABLE "ticket" (
"id" serial PRIMARY key,
"booking_id" int references booking(id),
"row" int,
"colum" int
);

insert into cinematronic.movie(name, description) values ('terminator', 'A seemingly indestructible android is sent from 2029 to 1984 to assassinate a waitress, whose unborn son will lead humanity in a war against the machines, while a soldier from that war is sent to protect her at all costs.');
insert into cinematronic.movie(name, description) values ('scarface', 'In Miami in 1980, a determined Cuban immigrant takes over a drug cartel and succumbs to greed.');
insert into cinematronic.customer(name) values ('admin');
insert into cinematronic.theatre(name) values ('Blå hallen');
insert into cinematronic.theatre(name) values ('Röd hallen');