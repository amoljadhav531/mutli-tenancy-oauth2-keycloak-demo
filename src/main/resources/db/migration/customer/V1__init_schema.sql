CREATE TABLE Customer 
(
	id	  			SERIAL NOT NULL PRIMARY KEY,
	username		VARCHAR(255) UNIQUE,
	password		VARCHAR(255),
	name 			VARCHAR(255),
	email			VARCHAR(255)
);