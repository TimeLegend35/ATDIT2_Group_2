package de.badwalden.schule.model;

import java.util.Calendar;
import java.util.List;

public class Service {
    private int id;
    private String name;
    private String description;
    private User mainSupervisor;
    private List<User> coSupervisors;
    private List<Student> studentList;
    private int numberOfSeats;
    private Calendar startTime;
    private Calendar endTime;
    private int youngestGrade;
    private int oldestGrade;
    private List<Attendance> attendanceList;

    public Service() {

    }

}
