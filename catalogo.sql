

CREATE DATABASE CATALOGO;


USE catalogo;

CREATE TABLE categoria(
id_categoria INT  AUTO_INCREMENT PRIMARY KEY,
nombre_categoria VARCHAR(100) NOT NULL);

CREATE TABLE marca (
id_marca INT AUTO_INCREMENT PRIMARY KEY,
nombre_marca VARCHAR(100) NOT NULL,
descripcion TEXT);
