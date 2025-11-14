create database colegio;
use colegio;
CREATE TABLE estudiantes (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre_completo VARCHAR(200) NOT NULL,
  correo VARCHAR(150) NOT NULL UNIQUE,
  carrera VARCHAR(120) NOT NULL
);
