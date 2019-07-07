create database tourists_flight_management;
use tourists_flight_management;

create table tourist(
	id int not null primary key,
	name varchar(32) not null,
    surname varchar(32) not null,
    gender varchar(32) not null,
    country varchar(32) not null,
    notes varchar(32) not null,
    birth_date varchar(32) not null
);

create table flight(
	id int not null primary key,
	departure varchar(32) not null,
    arrival varchar(32) not null,
    seats_number int not null,
    occupied_seats int not null
);

create table connector(
	id int not null primary key,
    touristID int not null,
    flightID int not null,
	FOREIGN KEY (touristID) REFERENCES tourist(id), 
    FOREIGN KEY (flightID) REFERENCES flight(id)
);