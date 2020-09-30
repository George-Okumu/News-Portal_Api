CREATE DATABASE newsportal;
\c news_portal;

CREATE TABLE IF NOT EXISTS departments (
 id int PRIMARY KEY auto_increment,
 departmentName VARCHAR,
 description VARCHAR,
 numberOfEmployees VARCHAR
);

CREATE TABLE IF NOT EXISTS news (
 id int PRIMARY KEY auto_increment,
 title VARCHAR,
 content VARCHAR,
 departmentID INTEGER
);

CREATE TABLE IF NOT EXISTS employees (
 id int PRIMARY KEY auto_increment,
 EmployeeName VARCHAR,
 position VARCHAR,
 role VARCHAR,
 email VARCHAR,
 phoneNumber VARCHAR,
 departmentId INTEGER

);

 CREATE TABLE IF NOT EXISTS departments_employees (
 id int PRIMARY KEY auto_increment,
 employeeId INTEGER,
  departmentId INTEGER
);

CREATE DATABASE news_portal_test WITH TEMPLATE news_portal;