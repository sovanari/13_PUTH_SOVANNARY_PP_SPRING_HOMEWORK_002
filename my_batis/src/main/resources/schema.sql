CREATE TABLE IF NOT EXISTS instructors (
    instructor_id   SERIAL PRIMARY KEY,
    instructor_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE
    );

CREATE TABLE IF NOT EXISTS courses (
    course_id       SERIAL PRIMARY KEY,
    course_name     VARCHAR(100) NOT NULL,
    description     TEXT,
    instructor_id   INT REFERENCES instructors(instructor_id)
    );

CREATE TABLE IF NOT EXISTS students (
    student_id      SERIAL PRIMARY KEY,
    student_name    VARCHAR(100) NOT NULL,
    email           VARCHAR(100) NOT NULL UNIQUE,
    phone_number    VARCHAR(20)
    );

CREATE TABLE IF NOT EXISTS student_course (
    student_id  INT REFERENCES students(student_id),
    course_id   INT REFERENCES courses(course_id),
    PRIMARY KEY (student_id, course_id)
    );
