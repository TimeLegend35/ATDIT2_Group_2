package de.badwalden.schule.model;

public class CareOffer extends Service{

        public CareOffer(int id, String name, String description, int numberOfSeats, int youngestGrade, int oldestGrade) {
        this.setId(id);
        this.setName(name);
        this.setDescription(description);
        this.setNumberOfSeats(numberOfSeats);
        this.setYoungestGrade(youngestGrade);
        this.setOldestGrade(oldestGrade);
    }


}
