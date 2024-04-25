We are hosting our MySQL DB on Azure. 

Microsoft's Azure provides several advantages that might make it a preferable choice over local MySQL databases or DB Mocks, particularly for students engaged in projects. First, it offers exceptional security and compliance features that can be challenging to replicate with limited resources. Machine learning is employed to identify and deter potential data threats, with automatic handling of security updates. This makes the task of protecting data less daunting and more robust.

Moreover, Azure presents built-in data backup and disaster recovery solutions, significantly lowering the risk of data loss amidst faults or system failures. The restoration process of a local database after a failure might not be as straightforward or even achievable, making Azure an invaluable asset in data management.

Lastly, Azure eliminates the necessity for hardware and related maintenance costs, a key distinguishing feature from traditional local databases. There's no need for substantial expenditure on hardware, maintenance, or energy resources associated with running a database. This feature is particularly advantageous for student projects as we cannot require everyone to have sufficient hardware to run a local DB. All these points highlight Azure's strengths in security, cost-effectiveness, and data protection, contributing to its preference over local MySQL databases.

Here is the Set up SQL Code to create the tables

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

`````


