CREATE TABLE UID(
	user_name varchar(50) not null,
    status varchar(15),
	password varchar(50),
    friendlist integer(9),
	key (user_name)
);

LOAD DATA LOCAL INFILE "C:/Users/Dawson/Documents/Dawson/College/Bellevue College/Software Engineering/Sprint 1/SQLdatabase/UID.csv"
INTO TABLE UID
FIELDS ENCLOSED BY '"' TERMINATED BY ',' LINES TERMINATED BY '\n'
IGNORE 1 ROWS;