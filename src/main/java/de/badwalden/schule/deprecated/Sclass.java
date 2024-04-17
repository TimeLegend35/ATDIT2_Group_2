package de.badwalden.schule.deprecated;

import de.badwalden.schule.deprecated.Attendance;
import de.badwalden.schule.model.Student;
import de.badwalden.schule.model.Teacher;

import java.util.List;

public class Sclass {
    private char classCode;
    private int level;
    private List<Student> studentList;
    private Teacher teacher;
    private int amountStudents;

    private List<Attendance> attendanceList;

    public Sclass() {

    }

}
