package de.badwalden.schule.model;

import de.badwalden.schule.deprecated.Sclass;
import de.badwalden.schule.deprecated.Subject;

import java.util.List;

public class Student extends User {
    private boolean compulsorySchooling;
    private boolean rightOfService;
    private Sclass sclass;
    private int class_year; // we only need class year for the implementation of the age checks for care_offers
    private List<Service> serviceList;
    private List<Subject> grades;

    public Student() {

    }

    @Override
    public String toString() {
        return getFirstName(); // Assuming getFirstName() returns the first name of the user
    }

    public boolean isCompulsorySchooling() {
        return compulsorySchooling;
    }

    public void setCompulsorySchooling(boolean compulsorySchooling) {
        this.compulsorySchooling = compulsorySchooling;
    }

    public boolean isRightOfService() {
        return rightOfService;
    }

    public void setRightOfService(boolean rightOfService) {
        this.rightOfService = rightOfService;
    }

    public Sclass getSclass() {
        return sclass;
    }

    public void setSclass(Sclass sclass) {
        this.sclass = sclass;
    }

    public List<Service> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<Service> serviceList) {
        this.serviceList = serviceList;
    }

    public List<Subject> getGrades() {
        return grades;
    }

    public void setGrades(List<Subject> grades) {
        this.grades = grades;
    }
}
