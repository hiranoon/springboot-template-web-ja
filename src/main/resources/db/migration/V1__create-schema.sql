create table hoges (
    hoge_id int primary key auto_increment,
    squad_number int,
    name varchar(100),
    position_code varchar(1),
    fuga_id int
);

create table fugas (
    fuga_id int primary key,
    name varchar(100)
);
