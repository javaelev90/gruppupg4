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