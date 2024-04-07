--CREATE SCHEMA Company;
--go

if object_id('C_dept_location', 'u') is not null
	drop table dbo.C_dept_location;
if object_id('C_dependent', 'u') is not null
	drop table dbo.C_dependent;
if object_id('C_works_on', 'u') is not null
	drop table dbo.C_works_on;
if object_id('C_project', 'u') is not null
	drop table dbo.C_project;
if object_id('C_employee', 'u') is not null
	drop table dbo.C_employee;
if object_id('C_department', 'u') is not null
	drop table dbo.C_department;

CREATE TABLE C_department (
    Dname VARCHAR(50),
    Dnumber INT PRIMARY KEY,
    Mgr_ssn CHAR(9),
    Mgr_start_date DATE
);

CREATE TABLE C_employee (
    Fname VARCHAR(50),
    Minit CHAR,
    Lname VARCHAR(50),
    Ssn CHAR(9) PRIMARY KEY,
    Bdate DATE,
    Address VARCHAR(100),
    Sex CHAR(1),
    Salary DECIMAL(10, 2),
    Super_ssn CHAR(9),
    Dno INT,
    FOREIGN KEY (Super_ssn) REFERENCES C_employee(Ssn),
    FOREIGN KEY (Dno) REFERENCES C_department(Dnumber)
);

CREATE TABLE C_project (
    Pname VARCHAR(50),
    Pnumber INT PRIMARY KEY,
    Plocation VARCHAR(50),
    Dnum INT,
    FOREIGN KEY (Dnum) REFERENCES C_department(Dnumber)
);

CREATE TABLE C_works_on (
    Essn CHAR(9),
    Pno INT,
    Hours DECIMAL(5, 2),
    PRIMARY KEY (Essn, Pno),
    FOREIGN KEY (Essn) REFERENCES C_employee(Ssn),
    FOREIGN KEY (Pno) REFERENCES C_project(Pnumber)
);

CREATE TABLE C_dependent (
    Essn CHAR(9),
    Dependent_name VARCHAR(50),
    Sex CHAR(1),
    Bdate DATE,
    Relationship VARCHAR(50),
    PRIMARY KEY (Essn, Dependent_name),
    FOREIGN KEY (Essn) REFERENCES C_employee(Ssn)
);

CREATE TABLE C_dept_location (
    Dnumber INT,
    Dlocation VARCHAR(50),
    PRIMARY KEY (Dnumber, Dlocation),
    FOREIGN KEY (Dnumber) REFERENCES C_department(Dnumber)
);