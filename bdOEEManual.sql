CREATE DATABASE IF NOT EXISTS oeemanual;
USE oeemanual;

CREATE TABLE produto(
	id MEDIUMINT(10000000) AUTO_INCREMENT,
    descricao VARCHAR(255),
    codigo CHAR(7) UNIQUE,
    velocidade DOUBLE(10,3),
    batelada BOOL,
    PRIMARY KEY(id)
);