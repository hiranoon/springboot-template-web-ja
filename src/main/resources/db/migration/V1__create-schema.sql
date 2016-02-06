CREATE TABLE hoges (
    id INT PRIMARY KEY AUTO_INCREMENT,
    squad_number INT,
    name VARCHAR(100),
    position_code INT,
    fuga_id INT
);

CREATE TABLE fugas (
    id INT PRIMARY KEY,
    name VARCHAR(100)
);
