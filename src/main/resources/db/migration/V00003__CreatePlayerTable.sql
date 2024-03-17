create table PLAYER(
ID INTEGER not null primary key,
GAME_ID INTEGER not null,
PLAYER_SIDE VARCHAR(5) not null,
PLAYER_NAME VARCHAR(50) not null,
CREATION_DATE TIMESTAMP not null
)