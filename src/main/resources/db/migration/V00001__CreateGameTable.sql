create table GAME(
ID INTEGER not null primary key,
STATUS VARCHAR(30) not null,
TURN VARCHAR(15) not null,
WINNER VARCHAR(15),
PIT_COUNT INTEGER not null,
CREATION_DATE TIMESTAMP not null
);