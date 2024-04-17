package de.badwalden.schule.model.outOfScope;

import de.badwalden.schule.model.Student;
import de.badwalden.schule.model.User;

import java.util.Calendar;
import java.util.Map;

public class Attendance {
    private Calendar date;
    private Map<Student, Boolean> studentAttendance;
    private Map<User, Boolean> supervisorAttendance;

    public Attendance() {

    }

}
