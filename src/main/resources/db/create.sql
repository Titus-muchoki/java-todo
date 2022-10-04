
--SET MODE PostgreSQL;
--
--CREATE TABLE IF NOT EXISTS tasks (
--  id int PRIMARY KEY auto_increment,
--  description VARCHAR,
--  completed BOOLEAN,
--  categoryid INTEGER
--);
--
--CREATE TABLE IF NOT EXISTS categories (
--  id int PRIMARY KEY auto_increment,
--  name VARCHAR
--);
CREATE DATABASE todolist;
\c todolist;
CREATE TABLE tasks (id SERIAL PRIMARY KEY, description VARCHAR, completed BOOLEAN, categoryid INTEGER);
CREATE TABLE categories (id SERIAL PRIMARY KEY, name VARCHAR);
CREATE DATABASE todolist_test WITH TEMPLATE todolist;