package de.badwalden.schule.model;

import de.badwalden.schule.dao.StudentDAO;
import de.badwalden.schule.model.outOfScope.Sclass;
import de.badwalden.schule.model.outOfScope.Subject;

import java.util.List;

public class Student extends User {
    private static final StudentDAO studentDao = new StudentDAO();
    private boolean compulsorySchooling;
    private boolean rightOfService;
    private Sclass sclass;
    private int age;
    private int classYear; // we only need class year for the implementation of the age checks for care_offers
    private List<Service> serviceList;
    private List<Subject> grades;

    public Student(int id, int class_year, String firstName, String lastName, int age, boolean compulsorySchooling, boolean rightOfService, List<Service> serviceList) {
        super();

        this.setId(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAge(age);
        this.setClassYear(class_year);
        this.setCompulsorySchooling(compulsorySchooling);
        this.setRightOfService(rightOfService);
        this.setServiceList(serviceList);
    }

    public Student() {
        super();
    }

    @Override
    public String toString() {
        return getFirstName(); // Assuming getFirstName() returns the first name of the user
    }

    public void dergisterStudentFromService(CareOffer careOfferToRemove) {
        if (studentDao.removeChildFromCareOffer(careOfferToRemove.getId(), this.getId())) {
            this.serviceList.remove(careOfferToRemove);
            // set new seats available for CareOffer
            careOfferToRemove.setSeatsAvailable(careOfferToRemove.getSeatsAvailable() + 1);
            careOfferToRemove.update();
        }
        else {
            System.out.println("Deregistration for Student failed!!!");
        }
    }

    public void registerStudentFromService(CareOffer careOfferToAdd) {
        if (studentDao.addChildToCareOffer(careOfferToAdd.getId(), this.getId())) {
            this.serviceList.add(careOfferToAdd);
            // set new seats available for CareOffer
            careOfferToAdd.setSeatsAvailable(careOfferToAdd.getSeatsAvailable() - 1);
            careOfferToAdd.update();
        }
        else {
            System.out.println("Registration for Student failed!!!");
        }
    }

    public boolean isCompulsorySchooling() {
        return compulsorySchooling;
    }

    public void setCompulsorySchooling(boolean compulsorySchooling) {
        this.compulsorySchooling = compulsorySchooling;
    }

    public void setClassYear(int class_year) {
        this.classYear = class_year;
    }

    public int getClassYear() {
        return classYear;
    }

    public void setAge(int Age) {
        this.age = age;
    }

    public boolean isRightOfService(CareOffer careOffer) {
        if(this.getClassYear() >= careOffer.getYoungestGrade() && this.getClassYear() <= careOffer.getOldestGrade() && this.rightOfService) {
            return true;
        } else {
            return false;
        }
    }

    public void setRightOfService(boolean rightOfService) {
        this.rightOfService = rightOfService;
    }

    public List<Service> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<Service> serviceList) {
        this.serviceList = serviceList;
    }

    public boolean isRegisteredForOffer(CareOffer careOffer) {
        for (Service currentService : this.getServiceList()) {
            if (careOffer.getId() == currentService.getId()) {
                return true;
            }
        }
        return false;
    }

}
