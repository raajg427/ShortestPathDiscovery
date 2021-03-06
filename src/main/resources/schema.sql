--DROP TABLE IF EXISTS PLANETS;
--DROP TABLE IF EXISTS ROUTES;
CREATE TABLE IF NOT EXISTS PLANETS (node_name VARCHAR(250) PRIMARY KEY,
planet_name VARCHAR(250) NOT NULL);

CREATE TABLE IF NOT EXISTS ROUTES (route_id INT PRIMARY KEY,
origin VARCHAR(250) NOT NULL,
destination VARCHAR(250) NOT NULL,
distance DOUBLE NOT NULL);