create table players (
    id              int             primary key auto_increment,
    squad_number    int,
    name            varchar(100),
    position_code   varchar(1),
    nationality_id  int,
    created_at      datetime        default current_timestamp not null,
    updated_at      datetime,
    version         int             default 0 not null
);

create table nationalities (
    id          int             primary key,
    name        varchar(100),
    created_at  datetime        default current_timestamp not null,
    updated_at  datetime,
    version     int             default 0 not null
);
