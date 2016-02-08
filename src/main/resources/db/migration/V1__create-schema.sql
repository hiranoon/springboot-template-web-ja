create table players (
    id int primary key auto_increment,
    squad_number int,
    name varchar(100),
    position_code varchar(1),
    nationality_id int
);

create table nationalities (
    id int primary key,
    name varchar(100)
);
