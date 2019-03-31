CREATE DATABASE IF NOT EXISTS nati;

ALTER DATABASE nati
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;

GRANT ALL PRIVILEGES ON nati.* TO pc@localhost IDENTIFIED BY 'pc';

USE nati;

CREATE TABLE IF NOT EXISTS subjects (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(30),
  total_credits INT(4) UNSIGNED NOT NULL,
  INDEX (name)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS courses (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(30),
  total_credits INT(4) UNSIGNED NOT NULL,
  INDEX (id)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS semesters (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  id_course INT(4) UNSIGNED NOT NULL,
  number INT(4) UNSIGNED NOT NULL,
  total_credits INT(4) UNSIGNED NOT NULL, 
  FOREIGN KEY (id_course) REFERENCES courses(id),
  UNIQUE(id_course)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS semester_subject (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  id_subject INT(4) UNSIGNED NOT NULL,
  id_semester INT(4) UNSIGNED NOT NULL,
  FOREIGN KEY (id_subject) REFERENCES subjects(id),
  FOREIGN KEY (id_semester) REFERENCES semesters(id),
  UNIQUE(id_subject, id_semester)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS users (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(30),
  email VARCHAR(30),
  password VARCHAR(30),
  type VARCHAR(30),
  index(email)
) engine=InnoDB;


