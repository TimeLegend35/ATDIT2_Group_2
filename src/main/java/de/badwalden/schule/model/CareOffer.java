package de.badwalden.schule.model;

import de.badwalden.schule.dao.CareOfferDAO;
import de.badwalden.schule.dao.DBConnector;
import de.badwalden.schule.ui.helper.LanguageHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Represents a care offer, including details such as supervisory staff and availability.
 * Implements ModelSyncRequirements for database synchronization.
 */
public class CareOffer extends Service implements ModelSyncRequirements {
        private static final Logger logger = Logger.getLogger(DBConnector.class.getName());
        public static CareOfferDAO careOfferDao = new CareOfferDAO();

        /**
         * Constructs a CareOffer with the specified details.
         */
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

        /**
         * Updates this care offer in the database.
         */
        @Override
        public void update() {
                logger.log(Level.INFO, LanguageHelper.getString("update_careoffer"));
                List<Object[]> list = new ArrayList<>();
                list.add(toObjectArray());
                if (careOfferDao.write(list) == 1) {
                        logger.log(Level.INFO, LanguageHelper.getString("success_update_co"));
                }
        }

        /**
         * Converts this CareOffer's attributes to an Object array suitable for database operations.
         * @return an Object array containing care offer details
         */
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
