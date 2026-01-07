create  type  continent_name as enum ('AFRICA','EUROPA','ASIA','AMERICA');
create table team (
    id serial primary key,
    name varchar(100),
    continentEnum continent_name
)
;
CREATE TYPE player_position AS ENUM ('GK', 'DEF', 'MIDF', 'STR');
create table player(
    id serial primary key,
    name varchar(100),
    age int,
    position player_position,
    id_team int references team(id)

);