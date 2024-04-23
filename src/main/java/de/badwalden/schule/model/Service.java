package de.badwalden.schule.model;

import de.badwalden.schule.model.outOfScope.Attendance;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Service {
    private int id;
    private String name;
    private String description;
    private User mainSupervisor;
    private List<User> coSupervisors;
    private List<Student> studentList = new ArrayList<>() {
    };
    private int numberOfSeats;
    private int seatsAvailable;
    private Calendar startTime;
    private Calendar endTime;
    private int youngestGrade;
    private int oldestGrade;
    private List<Attendance> attendanceList;

    public Service() {

    }

    //temporary test getters and settter:
    // Getter and Setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setSeatsAvailable(int seatsAvailable) { this.seatsAvailable = seatsAvailable;}

    public int getSeatsAvailable() { return seatsAvailable;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getMainSupervisor() {
        return mainSupervisor;
    }

    public void setMainSupervisor(User mainSupervisor) {
        this.mainSupervisor = mainSupervisor;
    }

    public List<User> getCoSupervisors() {
        return coSupervisors;
    }

    public void setCoSupervisors(List<User> coSupervisors) {
        this.coSupervisors = coSupervisors;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public void addStudentToStudentList(Student student) {
        //this.studentList.add(student);
        if (studentList.size() < getSeatsAvailable()){
            this.studentList.add(student);
        } else {
            System.out.println("Cannot add student, the service is full.");
        }
    }
    public void removeStudentFromStudentList(Student student) {
        //this.studentList.add(student);
        this.studentList.remove(student);
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    public Calendar getEndTime() {
        return endTime;
    }

    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }

    public int getYoungestGrade() {
        return youngestGrade;
    }

    public void setYoungestGrade(int youngestGrade) {
        this.youngestGrade = youngestGrade;
    }

    public int getOldestGrade() {
        return oldestGrade;
    }

    public void setOldestGrade(int oldestGrade) {
        this.oldestGrade = oldestGrade;
    }

    public List<Attendance> getAttendanceList() {
        return attendanceList;
    }

    public void setAttendanceList(List<Attendance> attendanceList) {
        this.attendanceList = attendanceList;
    }
}


