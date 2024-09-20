############################################################

CREATE USER 'webapp'@'%' IDENTIFIED BY 'password';

############################################################

CREATE DATABASE IF NOT EXISTS authentication;

USE authentication;

CREATE TABLE login (
    id VARCHAR(10) PRIMARY KEY,
    password VARCHAR(50) NOT NULL,
    type VARCHAR(20) NOT NULL
);

GRANT ALL PRIVILEGES ON authentication.* TO 'webapp'@'%';

FLUSH PRIVILEGES;

############################################################

CREATE DATABASE IF NOT EXISTS grades;

USE grades;

CREATE TABLE student (
    id VARCHAR(20) PRIMARY KEY,
    name VARCHAR(20) NOT NULL
);

CREATE TABLE instructor (
    id VARCHAR(20) PRIMARY KEY,
    name VARCHAR(20) NOT NULL
);

CREATE TABLE course (
    id VARCHAR(20) PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    coordinatorId VARCHAR(20) NOT NULL,
    FOREIGN KEY (coordinatorId) REFERENCES instructor(id)
);

CREATE TABLE lecture (
    id VARCHAR(20) PRIMARY KEY,
    courseId VARCHAR(20) NOT NULL,
    instructorId VARCHAR(20) NOT NULL,
     FOREIGN KEY (courseId) REFERENCES course(id),
     FOREIGN KEY (instructorId) REFERENCES instructor(id)
);

CREATE TABLE lectureEnrollment (
    lectureId VARCHAR(20),
    studentId VARCHAR(20),
    midTermGrade DECIMAL(5, 2),
    finalGrade DECIMAL(5, 2),
    PRIMARY KEY (lectureId, studentId),
    FOREIGN KEY (lectureId) REFERENCES lecture(id),
    FOREIGN KEY (studentId) REFERENCES student(id)
);

GRANT ALL PRIVILEGES ON grades.* TO 'webapp'@'%';

FLUSH PRIVILEGES;

############################################################

INSERT INTO authentication.login (id, password, type) VALUES ("1", "123", "student");
INSERT INTO authentication.login (id, password, type) VALUES ("2", "234", "instructor");
INSERT INTO authentication.login (id, password, type) VALUES ("3", "345", "admin");

INSERT INTO grades.student (id, name) VALUES ("1", "SAID");
INSERT INTO grades.instructor (id, name) VALUES ("2", "Robin");
INSERT INTO grades.course (id, name, coordinatorId) VALUES ("10", "Intro to DBMS", "2");
INSERT INTO grades.course (id, name, coordinatorId) VALUES ("11", "Intro to Robotics", "2");
INSERT INTO grades.lecture (id, courseId, instructorId) VALUES ("1", "10", "2");
INSERT INTO grades.lecture (id, courseId, instructorId) VALUES ("2", "11", "2");
INSERT INTO grades.lectureEnrollment (lectureId, studentId, midTermGrade) VALUES ("1", "1", "33.25");
INSERT INTO grades.lectureEnrollment (lectureId, studentId, midTermGrade, finalGrade) VALUES ("2", "1", "5.0", "88.5");

############################################################
