package de.badwalden.schule.model;

public class CareOffer extends Service{

        public CareOffer(int id, Supervisor supervisor, int oldestClassLevel, int youngestClassLevel, String careOfferName, String description, int seatsAvailable) {
        super();

        this.setId(id);
        this.setMainSupervisor(supervisor);
        this.setOldestGrade(oldestClassLevel);
        this.setYoungestGrade(youngestClassLevel);
        this.setName(careOfferName);
        this.setDescription(description);
        this.setSeatsAvailable(seatsAvailable);
    }


}
