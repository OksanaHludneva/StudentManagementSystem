-- DROP DATABASE IF EXISTS java2728_student_mgmt_system;

CREATE DATABASE IF NOT EXISTS java2728_student_mgmt_system;

USE java2728_student_mgmt_system;

CREATE TABLE IF NOT EXISTS students(
	studentId INT AUTO_INCREMENT NOT NULL,
    studentName VARCHAR(100) UNIQUE NOT NULL,
    courseName VARCHAR(100) NOT NULL,
    PRIMARY KEY(studentId)
);

CREATE TABLE IF NOT EXISTS teachers(
	teacherId INT AUTO_INCREMENT NOT NULL,
    teacherName VARCHAR(100) UNIQUE NOT NULL,
    courseName VARCHAR(100) NOT NULL,
    PRIMARY KEY(teacherId)
);

CREATE TABLE IF NOT EXISTS courses(
	courseId INT AUTO_INCREMENT NOT NULL,
    courseName VARCHAR(100) UNIQUE NOT NULL,
    PRIMARY KEY(courseId)
);

CREATE TABLE IF NOT EXISTS examResults(
	examResultId INT AUTO_INCREMENT NOT NULL,
    studentName VARCHAR(100) UNIQUE NOT NULL,
    courseName VARCHAR(100) NOT NULL,
    examScore INTEGER NOT NULL,
    PRIMARY KEY(examResultId)
);

INSERT IGNORE INTO students(studentName, courseName) VALUES ("James Parker", "Human Resource Management"),
("Anne Smith", "Office Administration"),
("Elizabeth Doe", "E-Commerce"),
("Lewis Fisher", "Quality Management"),
("Miranda Ford", "Entrepreneurship");

INSERT IGNORE INTO teachers(teacherName, courseName) VALUES ("PROF Kevin Scott", "Human Resource Management"),
("ASSC PROF Sean Miller", "Office Administration"),
("ADJ PROF Rebecca Morgan", "E-Commerce"),
("VIS PROF Melissa Gross", "Quality Management"),
("ASST PROF Andy Brown", "Entrepreneurship");

INSERT IGNORE INTO courses(courseName) VALUES ("Human Resource Management"),
("Office Administration"),
("E-Commerce"),
("Quality Management"),
("Entrepreneurship");

INSERT IGNORE INTO examResults(studentName, courseName, examScore) VALUES ("James Parker", "Human Resource Management", 9),
("Anne Smith", "Office Administration", 8),
("Elizabeth Doe", "E-Commerce", 6),
("Lewis Fisher", "Quality Management", 10),
("Miranda Ford", "Entrepreneurship", 7);
