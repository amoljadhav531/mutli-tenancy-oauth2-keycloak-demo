CREATE TABLE Device 
(
	id	  			SERIAL NOT NULL PRIMARY KEY,
	name			VARCHAR(255),
	ip				VARCHAR(255),
	location 		VARCHAR(255)
);