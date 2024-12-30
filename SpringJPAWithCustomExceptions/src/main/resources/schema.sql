create table department5(
dep_id int primary key,
dep_address varchar(50)
);

create table Employees3(
name varchar(50),
empid int primary key,
dep_name varchar(50),
salary int,
dep_id int,
manager_id int,
foreign key(dep_id) references department5(dep_id)
);





