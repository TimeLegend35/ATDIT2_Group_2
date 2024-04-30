package de.badwalden.schule.model;

import de.badwalden.schule.dao.CareOfferDAO;
import de.badwalden.schule.dao.DBConnector;
import kotlin.collections.ArrayDeque;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CareOffer extends Service implements ModelSyncRequirements {
        private static final Logger logger = Logger.getLogger(DBConnector.class.getName());
        final static CareOfferDAO careOfferDao = new CareOfferDAO();

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

        @Override
        public void update() {
                logger.log(Level.INFO, "Updating CareOffer in Model");
                List<Object[]> list = new ArrayList<>();
                list.add(toObjectArray());
                if (careOfferDao.write(list) == 1) {
                        logger.log(Level.INFO, "Successful updated Care Offer");
                }
        }

        // Method to convert CareOffer properties to an Object array
        @Override
        public Object[] toObjectArray() {
                return new Object[]{
                        this.getMainSupervisor().getId(), // Assuming Supervisor class has this method
                        this.getOldestGrade(),
                        this.getYoungestGrade(),
                        this.getName(),
                        this.getDescription(),
                        this.getSeatsAvailable(),
                        this.getId() // Assuming order is as required by SQL statement
                };
        }
}
