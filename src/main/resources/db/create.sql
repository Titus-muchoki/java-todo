SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS task(
id int PRIMARY KEY auto_increment,
description VARCHAR,
completed BOOLEAN
);