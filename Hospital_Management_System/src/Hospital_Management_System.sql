SHOW DATABASES LIKE 'Hospital_Management_System';
USE Hospital_Management_System;

create table login(ID varchar(20), PW varchar(20));
select*from login;

insert into login value("Pranav", "pranav4122005@");

create table patient_info(ID varchar(20), Number varchar(40), Name varchar(20), Gender varchar(20), Patient_Disease varchar(20), Room_Number varchar(20), Time varchar(100), Deposite varchar(20));
select * from patient_info;

create table Room(room_no varchar(20), Availability varchar(20), Price varchar(20), Room_Type varchar(100));
-- select * from  Room;
