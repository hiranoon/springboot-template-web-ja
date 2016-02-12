create table users (
    username            varchar(255)    primary key,
    encrypted_password  varchar(255),
    sign_in_count       int             default 0 not null,
    current_sign_in_at  datetime,
    last_sign_in_at     datetime,
    current_sign_in_ip  varchar(255),
    last_sign_in_ip     varchar(255),
    failed_attempts     int             default 0 not null,
    locked_at           datetime,
    created_at          datetime,
    updated_at          datetime
);
