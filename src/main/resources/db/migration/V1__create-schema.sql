CREATE TABLE hoges (
    hoge_id INT PRIMARY KEY AUTO_INCREMENT,
    squad_number INT,
    name VARCHAR(100),
    position_code INT,
    fuga_id INT
);

CREATE TABLE fugas (
    fuga_id INT PRIMARY KEY,
    name VARCHAR(100)
);
