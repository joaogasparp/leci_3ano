--CREATE SCHEMA Reservas_De_Voos;
--go

if object_id('RDV_seat', 'u') is not null
	drop table dbo.RDV_seat;
if object_id('RDV_flightleg', 'u') is not null
	drop table dbo.RDV_flightleg;
if object_id('RDV_fare', 'u') is not null
	drop table dbo.RDV_fare;
if object_id('RDV_flight', 'u') is not null
	drop table dbo.RDV_flight;
if object_id('RDV_leginstance', 'u') is not null
	drop table dbo.RDV_leginstance;
if object_id('RDV_canland', 'u') is not null
	drop table dbo.RDV_canland;
if object_id('RDV_airplane', 'u') is not null
	drop table dbo.RDV_airplane;
if object_id('RDV_airplanetype', 'u') is not null
	drop table dbo.RDV_airplanetype;
if object_id('RDV_airport', 'u') is not null
	drop table dbo.RDV_airport;

CREATE TABLE RDV_airport(
	airport_code int primary key(airport_code),
	[state] varchar(128),
	city varchar(128),
	[name] varchar(128),
)

CREATE TABLE RDV_airplanetype(
	[type_name] varchar(128) primary key([type_name]),
	max_seats int,
	company_airplane varchar(128),
)

CREATE TABLE RDV_airplane(
	airplane_id int primary key(airplane_id),
	total_no_of_seats int,
	airplane_type_name varchar(128) references RDV_airplanetype([type_name]),
)

CREATE TABLE RDV_canland(
	airport_code int references RDV_airport(airport_code),
	airplane_type_name varchar(128) references RDV_airplanetype([type_name]),
)

CREATE TABLE RDV_leginstance(
	[date] date,
	no_of_avail_seats int,
	airplane_id int references RDV_airplane(airplane_id),
	departs_time time,
	arrives_time time,
	primary key([date], no_of_avail_seats, airplane_id),
)

CREATE TABLE RDV_flight(
	number int primary key(number),
	airline varchar(128),
	weekdays varchar(128),
)

CREATE TABLE RDV_fare(
	code int primary key(code),
	amount int,
	restrictions varchar(512),
	flight_number int references RDV_flight(number),
)

CREATE TABLE RDV_flightleg(
	leg_no int,
	flight_number int,
	airport_depar int references RDV_airport(airport_code),
	airport_arr int references RDV_airport(airport_code),
	sch_dep_time time,
	sch_arr_time time,
	primary key(leg_no, airport_depar, airport_arr),
)

CREATE TABLE RDV_seat(
	seat_no int primary key(seat_no),
	reservation_customer_name varchar(128),
	reservation_cphone int,
)