create schema rms;
use rms;

create table employees(
emp_id int primary key auto_increment,
name varchar(255) not null,
email varchar(155) unique not null,
phone varchar(10) check(length(phone)=10) unique not null,
status varchar(10) check(status in ('Active','Inactive')) not null,
designation varchar(20) check(designation in ('Admin','Staff')) not null,
join_date date not null,
leaving_date date);

create table customers(
cust_id int primary key auto_increment,
name varchar(255) not null,
phone varchar(10) check(length(phone)=10) unique not null);

create table staff(
stf_id int primary key auto_increment,
emp_id int not null,
availability varchar(20) check(availability in ('Available','Busy')) not null,
constraint fk_employees_staff foreign key(emp_id) references employees(emp_id));

create table orders(
ord_id int primary key auto_increment,
cust_id int not null,
stf_id int not null,
ord_date date not null,
amount decimal not null,
status varchar(20) check(status in ('Pending','Cancelled','Completed')) not null,
constraint fk_customers_orders foreign key(cust_id) references customers(cust_id),
constraint fk_staff_orders foreign key(stf_id) references staff(stf_id));

create table items(
item_id int primary key auto_increment,
name varchar(255) not null,
image varchar(155),
description varchar(255) not null,
category varchar(55) not null,
availability varchar(20) check(status in ('Available','Unavailable')) not null,
status varchar(20) check(status in ('Active','Inactive')) not null);

create table order_details(
ord_details_id int primary key auto_increment,
ord_id int not null,
item_id int not null,
quantity int not null check(quantity>0),
price decimal not null check(price>0),
constraint fk_orders_order_details foreign key(ord_id) references orders(ord_id),
constraint fk_items_order_details foreign key(item_id) references items(item_id));

create table credentials(
username VARCHAR(50) NOT NULL PRIMARY KEY,
password VARCHAR(100) NOT NULL,
authority VARCHAR(50) check(authority in ('Admin','Staff')) NOT NULL,
enabled BOOLEAN NOT NULL);

alter table items modify image varchar(155);

desc items;






