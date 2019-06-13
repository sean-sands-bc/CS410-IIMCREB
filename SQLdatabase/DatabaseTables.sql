CREATE TABLE UID(
	user_name varchar(50) not null,
    status varchar(15),
	password varchar(50),
    email varchar(50),
	key (user_name)
);

LOAD DATA LOCAL INFILE "C:/Users/Dawson/Documents/Dawson/College/Bellevue College/Software Engineering/Sprint2/UID.csv"
INTO TABLE UID
FIELDS ENCLOSED BY '"' TERMINATED BY ',' LINES TERMINATED BY '\n'
IGNORE 1 ROWS;

CREATE TABLE ChattingDatabase(
	user_name_send varchar(50) not null,
    user_name_recieve varchar(50) not null,
    time_sent datetime,
    message_content varchar(200),
    primary key(user_name_send, user_name_recieve,time_sent)
);

LOAD DATA LOCAL INFILE "C:/Users/Dawson/Documents/Dawson/College/Bellevue College/Software Engineering/Sprint2/ChattingData.csv"
INTO TABLE ChattingDatabase
FIELDS OPTIONALLY ENCLOSED BY '"' TERMINATED BY ',' LINES TERMINATED BY '\n'
IGNORE 1 ROWS;

alter table ChattingDatabase add foreign key (user_name_send) references UID(user_name) on delete cascade;
alter table ChattingDatabase add foreign key (user_name_recieve) references UID(user_name) on delete cascade;

CREATE TABLE FriendList(
	user_name_friender varchar(50) not null,
    user_name_friendee varchar(50) not null
);

LOAD DATA LOCAL INFILE "C:/Users/Dawson/Documents/Dawson/College/Bellevue College/Software Engineering/Sprint2/FriendList.csv"
INTO TABLE FriendList
FIELDS OPTIONALLY ENCLOSED BY '"' TERMINATED BY ',' LINES TERMINATED BY '\n'
IGNORE 1 ROWS;

alter table FriendList add foreign key (user_name_friender) references UID(user_name) on delete cascade;
alter table FriendList add foreign key (user_name_friendee) references UID(user_name) on delete cascade;

drop table FriendList;
drop table ChattingDatabase;
drop table UID;
