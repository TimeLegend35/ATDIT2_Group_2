package de.badwalden.schule.model;

import de.badwalden.schule.dao.CareOfferDAO;
import de.badwalden.schule.dao.DBConnector;
import de.badwalden.schule.dao.StudentDAO;
import de.badwalden.schule.model.outOfScope.Sclass;
import de.badwalden.schule.model.outOfScope.Subject;
import de.badwalden.schule.ui.helper.LanguageHelper;
import de.badwalden.schule.ui.helper.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.stream.Collectors;

public class Student extends User implements ModelSyncRequirements {
    private static final Logger logger = Logger.getLogger(DBConnector.class.getName());
    private static final Session session = Session.getInstance();
    private static final StudentDAO studentDao = new StudentDAO();
    private static final CareOfferDAO careOfferDao = new CareOfferDAO();
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

    private void deregisterStudentFromService(CareOffer careOfferToRemove) {
        studentDao.removeChildFromCareOffer(careOfferToRemove.getId(), this.getId());

        // set new seats available for CareOffer
        careOfferToRemove.setSeatsAvailable(careOfferToRemove.getSeatsAvailable() + 1);
        careOfferToRemove.update();

        logger.log(Level.INFO, LanguageHelper.getString("deregistered_student") + this.getId() + LanguageHelper.getString("from_service") + careOfferToRemove.getId());
    }

    private void registerStudentFromService(CareOffer careOfferToAdd) {
        studentDao.addChildToCareOffer(careOfferToAdd.getId(), this.getId());

        // set new seats available for CareOffer
        careOfferToAdd.setSeatsAvailable(careOfferToAdd.getSeatsAvailable() - 1);
        careOfferToAdd.update();

        logger.log(Level.INFO, LanguageHelper.getString("registered_student") + this.getId() + LanguageHelper.getString("from_service") + careOfferToAdd.getId());
    }

    public boolean isCompulsorySchooling() {
        return compulsorySchooling;
    }

    public void setCompulsorySchooling(boolean compulsorySchooling) {
        this.compulsorySchooling = compulsorySchooling;
    }

    public boolean getCompulsorySchooling() {return this.compulsorySchooling; }

    public void setClassYear(int class_year) {
        this.classYear = class_year;
    }

    public int getClassYear() {
        return classYear;
    }

    public void setAge(int Age) {
        this.age = age;
    }

    public int getAge() { return this.age; }

    public boolean isRightOfService(CareOffer careOffer) {
        if (this.getClassYear() >= careOffer.getYoungestGrade() && this.getClassYear() <= careOffer.getOldestGrade() && this.rightOfService && careOffer.getSeatsAvailable() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void setRightOfService(boolean rightOfService) {
        this.rightOfService = rightOfService;
    }

    public boolean getRightOfService() { return this.rightOfService; }

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

    @Override
    public void update() {
        // Update the student's own information
        logger.log(Level.INFO, LanguageHelper.getString("update_student"));
        List<Object[]> updateList = new ArrayList<>();
        Object[] studentData = this.toObjectArray();
        updateList.add(studentData);
        if (studentDao.write(updateList) == 1) {
            logger.log(Level.INFO, LanguageHelper.getString("success_update_student"));
        }

        // Update serviceList in the intermediate table
        logger.log(Level.INFO, LanguageHelper.getString("chk_serive_list"));
        updateServiceRegistrations();
    }

    private void updateServiceRegistrations() {
        // Retrieve current registered services from the database and transform it into a List of Integer IDs
        List<Integer> currentRegisteredIds = new ArrayList<>();
        List<Object[]> idArrays = careOfferDao.getCareOffersIdsForStudent(this.getId());
        for (Object[] idArray : idArrays) {
            currentRegisteredIds.add((Integer) idArray[0]);
        }

        // Get IDs from the local serviceList
        List<Integer> newServiceIds = new ArrayList<>();
        for (Service service : this.serviceList) {
            newServiceIds.add(service.getId());
        }

        // Determine new services to add to the database
        for (Integer serviceId : newServiceIds) {
            if (!currentRegisteredIds.contains(serviceId)) {
                CareOffer careOfferToAdd = session.getCareOfferById(serviceId);
                if (careOfferToAdd != null) {
                    logger.log(Level.INFO, LanguageHelper.getString("register_care_offer") + careOfferToAdd.getName());
                    registerStudentFromService(careOfferToAdd);
                } else {
                    logger.log(Level.WARNING, LanguageHelper.getString("fail_co_id") + serviceId);
                }
            }
        }

        // Determine services to remove from the database
        for (Integer registeredId : currentRegisteredIds) {
            if (!newServiceIds.contains(registeredId)) {
                CareOffer careOfferToRemove = session.getCareOfferById(registeredId);
                if (careOfferToRemove != null) {
                    logger.log(Level.INFO, LanguageHelper.getString("deregister_care_offer") + careOfferToRemove.getName());
                    deregisterStudentFromService(careOfferToRemove);
                } else {
                    logger.log(Level.WARNING, LanguageHelper.getString("fail_co_id") + registeredId);
                }
            }
        }
    }

    @Override
    public Object[] toObjectArray() {
        return new Object[]{
                this.getId(),
                this.getClassYear(),
                this.getFirstName(),
                this.getLastName(),
                this.getAge(),
                this.getCompulsorySchooling(),
                this.getRightOfService()
        };
    }
}
