We are hosting our MySQL DB on Azure. 

Here is the Set up SQL Code to create the tables and populate the tables with mock data.

`````
-- Create class_levels table
CREATE TABLE class_levels (
                              class_level INT PRIMARY KEY,
                              class_size INT
);
-- Create employees table
CREATE TABLE employees (
                           employee_number INT PRIMARY KEY,
                           given_name VARCHAR(255),
                           surname VARCHAR(255)
);

-- Create children table
CREATE TABLE children (
                          student_id INT PRIMARY KEY,
                          class_year INT,
                          given_name VARCHAR(255),
                          surname VARCHAR(255),
                          age INT,
                          compulsory_school_attendance bit,
                          allowed_for_care_offers bit,
                          FOREIGN KEY (class_year) REFERENCES class_levels (class_level)
);

-- Create parents table
CREATE TABLE parents (
                         parent_id INT PRIMARY KEY,
                         given_name VARCHAR(255),
                         surname VARCHAR(255),
                         city_of_residence VARCHAR(255)
);
-- Create care_offers table
CREATE TABLE care_offers (
                             care_offer_id INT PRIMARY KEY,
                             supervisor_employee_number INT,
                             oldest_class_level INT,
                             youngest_class_level INT,
                             care_offer_name VARCHAR(255),
                             description TEXT,
                             places_available INT,
                             FOREIGN KEY (supervisor_employee_number) REFERENCES employees (employee_number),
                             FOREIGN KEY (oldest_class_level) REFERENCES class_levels (class_level),
                             FOREIGN KEY (youngest_class_level) REFERENCES class_levels (class_level)
);
-- Create parent and child assignment table
CREATE TABLE parent_child_assignment (
                                         parent_id INT,
                                         child_id INT,
                                         PRIMARY KEY (parent_id, child_id),
                                         FOREIGN KEY (parent_id) REFERENCES parents(parent_id),
                                         FOREIGN KEY (child_id) REFERENCES children(student_id)
);
--create child and care offer assignment table
CREATE TABLE child_care_offer_assignment (
                                             student_id INT,
                                             care_offer_id INT,
                                             PRIMARY KEY (student_id, care_offer_id),
                                             FOREIGN KEY (student_id) REFERENCES children(student_id),
                                             FOREIGN KEY (care_offer_id) REFERENCES care_offers(care_offer_id)
);

--Load Data to DB

--Class Levels
INSERT INTO class_levels (class_level, class_size)
VALUES (1, 30), (2, 30), (3, 25), (4, 25);

--Employees
INSERT INTO employees (employee_number, given_name, surname)
VALUES
    (101, 'Anna', 'Müller'),
    (102, 'Thomas', 'Schmidt'),
    (103, 'Sarah', 'Fischer'),
    (104, 'Markus', 'Weber'),
    (105, 'Laura', 'Wagner');

--Care_offers
INSERT INTO care_offers (care_offer_id, supervisor_employee_number, oldest_class_level, youngest_class_level, care_offer_name, description, places_available)
VALUES
    (1, 101, 1, 2, 'Hausaufgabenbetreuung', 'Hilfe bei Hausaufgaben für Klassenstufe 1 und 2', 20),
    (2, 102, 3, 4, 'Ferienbetreuung', 'Betreuung während der Schulferien für Klassenstufe 3 und 4', 15),
    (3, 103, 1, 2, 'Kreativwerkstatt', 'Kreatives Gestalten für Klassenstufe 1 und 2', 25),
    (4, 104, 3, 3, 'Sport und Bewegung', 'Sportliche Aktivitäten für Klassenstufe 3', 20),
    (5, 105, 4, 4, 'Naturerlebnisse', 'Erkundung der Natur für Klassenstufe 4', 15);


-- parents
INSERT INTO parents (parent_id, given_name, surname, city_of_residence)
VALUES
    (1, 'Stefan', 'Schulz', 'Bad Walden'),
    (2, 'Julia', 'Schulz', 'Bad Walden'),
    (3, 'Andreas', 'Meier', 'Ludwigshafen'),
    (4, 'Katrin', 'Meier', 'Ludwigshafen'),
    (5, 'Simon', 'Becker', 'Speyer'),
    (6, 'Laura', 'Becker', 'Speyer'),
    (7, 'Felix', 'Hoffmann', 'Bad Walden'),
    (8, 'Marie', 'Hoffmann', 'Bad Walden'),
    (9, 'Martin', 'Klein', 'Worms'),
    (10, 'Christina', 'Klein', 'Worms'),
    (11, 'Thomas', 'Bauer', 'Bad Walden'),
    (12, 'Caroline', 'Mauser', 'Bad Walden');

-- Insert children and assign them to parents in one statement

INSERT INTO children (student_id, class_year, given_name, surname, age, compulsory_school_attendance, allowed_for_care_offers)
VALUES
    (1, 1, 'Max', 'Schulz', 7, 1, 1),
    (2, 2, 'Emma', 'Schulz', 5, 0, 1),
    (3, 1, 'Paul', 'Meier', 8, 1, 1),
    (4, 3, 'Sophie', 'Meier', 4, 0, 1),
    (5, 1, 'Lukas', 'Becker', 7, 1, 1),
    (6, 2, 'Hannah', 'Becker', 5, 0, 1),
    (7, 3, 'Tim', 'Hoffmann', 9, 1, 0),
    (8, 1, 'Lisa', 'Hoffmann', 6, 1, 1),
    (9, 4, 'Julian', 'Klein', 10, 1, 1),
    (10, 2, 'Mia', 'Klein', 3, 0, 1),
    (11, 1, 'Finn', 'Bauer', 7, 1, 1),
    (12, 3, 'Sophia', 'Mauser', 6, 0, 1),
    (13, 2, 'Paula', 'Mauser', 8, 1, 0),
    (14, 4, 'David', 'Schulz', 9, 1, 1),
    (15, 1, 'Emilia', 'Schulz', 5, 0, 1),
    (16, 2, 'Elias', 'Meier', 7, 1, 1),
    (17, 3, 'Lea', 'Hoffmann', 8, 1, 0),
    (18, 4, 'Ben', 'Klein', 10, 1, 1),
    (19, 2, 'Johanna', 'Bauer', 6, 0, 1),
    (20, 1, 'Nina', 'Mauser', 7, 1, 1);

-- Insert parent-child assignments
-- Populate parent_child_assignment table to link children with their parents (adjusted for valid parent_id values)
-- Assign children to their respective parents based on given relationships with valid parent_id values (1 to 12)
INSERT INTO parent_child_assignment (parent_id, child_id)
VALUES
    (1, 1),
    (1, 2),
    (2, 3),
    (2, 4),
    (3, 5),
    (3, 6),
    (4, 7),
    (4, 8),
    (5, 9),
    (5, 10),
    (6, 11),
    (6, 12),
    (7, 13),
    (7, 14),
    (8, 15),
    (8, 16),
    (9, 17),
    (9, 18),
    (10, 19),
    (10, 20);


-- Assign  children to each care offer based on eligibility criteria
-- Assign children to care offers
INSERT INTO child_care_offer_assignment (student_id, care_offer_id)
SELECT c.student_id, co.care_offer_id
FROM children c
         JOIN care_offers co ON c.class_year >= co.oldest_class_level AND c.class_year <= co.youngest_class_level
WHERE c.allowed_for_care_offers = 1
GROUP BY co.care_offer_id, c.student_id
ORDER BY co.care_offer_id, c.student_id
`````


