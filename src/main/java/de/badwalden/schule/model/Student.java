package de.badwalden.schule.model;

import java.util.List;

public class Student extends User {
    private boolean compulsorySchooling;
    private boolean rightOfService;
    private Sclass sclass;
    private List<Service> serviceList;
    private List<Subject> grades;

    public Student() {

    }

}
