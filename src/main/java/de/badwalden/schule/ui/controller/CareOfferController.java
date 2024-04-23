package de.badwalden.schule.ui.controller;

import de.badwalden.schule.dao.CareOfferDAO;
import de.badwalden.schule.model.CareOffer;
import de.badwalden.schule.model.Service;
import de.badwalden.schule.model.Student;
import de.badwalden.schule.ui.helper.DialogHelper;
import de.badwalden.schule.ui.helper.LanguageHelper;
import de.badwalden.schule.ui.helper.Session;
import de.badwalden.schule.ui.views.CareOfferMarketplaceView;
import de.badwalden.schule.ui.views.CareOfferView;
import de.badwalden.schule.ui.views.DataController;
import de.badwalden.schule.ui.views.MainView;
import javafx.animation.PauseTransition;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.util.Duration;

public class CareOfferController implements DataController {
    CareOfferView careOfferView;

    public CareOfferController(CareOfferView careOfferView) {
        this.careOfferView = careOfferView;
    }

    @Override
    public Object[] getData() {
        CareOffer careOffer = Session.getInstance().getCachedCareOffer();
        return new Object[]{careOffer};
    }

    public void changeCareOfferRegistration(CareOffer careOffer, Student student, Button dialogRegistrationButton) {
        if (student.isRegisteredForOffer(careOffer)) {
            student.getServiceList().remove(careOffer);
            higherSeatsAvailable(careOffer);
            CareOfferDAO.removeChildFromCareOffer(student.getId(), careOffer.getId());
            dialogRegistrationButton.setText(LanguageHelper.getString("add_child"));
            pauseButton(dialogRegistrationButton, 2);
        } else {
            student.getServiceList().add(careOffer);
            CareOfferDAO.addChildtoCareoffer(student.getId(), careOffer.getId());
            lowerSeatsAvailable(careOffer);
            dialogRegistrationButton.setText(LanguageHelper.getString("remove_child"));
            pauseButton(dialogRegistrationButton, 2);
        }
    }

    public void changeCareOfferRegistration(CareOffer careOffer, Student student) {
        if (student.isRegisteredForOffer(careOffer)) {
            student.getServiceList().remove(careOffer);
            CareOfferDAO.removeChildFromCareOffer(student.getId(), careOffer.getId());
            higherSeatsAvailable(careOffer);
        } else {
            student.getServiceList().add(careOffer);
            student.getServiceList().add(careOffer);
            CareOfferDAO.addChildtoCareoffer(student.getId(), careOffer.getId());
            lowerSeatsAvailable(careOffer);
        }
    }

    public boolean isRightOfSerice(CareOffer careOffer, Student student) {
        if(student.getClassYear() >= careOffer.getYoungestGrade() && student.getClassYear() <= careOffer.getOldestGrade()) {
            return true;
        } else {
            return false;
        }
    }

    private void lowerSeatsAvailable(CareOffer careOffer) {
        careOffer.setSeatsAvailable(careOffer.getSeatsAvailable() - 1);
    }

    public void higherSeatsAvailable(CareOffer careOffer) {
        careOffer.setSeatsAvailable(careOffer.getSeatsAvailable() + 1);
    }

    public void pauseButton(Button button, int seconds) {
        // Disable the button
        button.setDisable(true);

        // Create a PauseTransition that lasts for 2 seconds
        PauseTransition pause = new PauseTransition(Duration.seconds(seconds));

        // Re-enable the button after the pause
        pause.setOnFinished(e -> button.setDisable(false));

        // Start the pause transition
        pause.play();
    }


    /**
     * Sets the values of a CareOffer object based on the input from a CareOfferView.
     *
     * @param careOffer the CareOffer object to set values for
     */
    public void setValuesOfObject(CareOffer careOffer) {
        careOffer.setName(careOfferView.titleTextField.getText());
        careOffer.setDescription(careOfferView.descriptionTextField.getText());
        careOffer.setSeatsAvailable(Integer.parseInt(careOfferView.seatsAvailableTextField.getText()));
        careOffer.setYoungestGrade(Integer.parseInt(careOfferView.youngestGradeTextField.getText()));
        careOffer.setOldestGrade(Integer.parseInt(careOfferView.oldestGradeTextField.getText()));
    }

    /**
     * Updates the values in the care offer view based on the provided CareOffer object.
     *
     * @param careOffer the CareOffer object containing the values to update the view with
     */
    public void updateValuesFromObject(CareOffer careOffer) {
        careOfferView.titleLabelValue.setText(careOffer.getName());
        careOfferView.titleTextField.setText(careOffer.getName());
        careOfferView.descriptionLabelValue.setText(careOffer.getDescription());
        careOfferView.descriptionTextField.setText(careOffer.getDescription());
        careOfferView.seatsAvailableLabelValue.setText(String.valueOf(careOffer.getSeatsAvailable()));
        careOfferView.seatsAvailableTextField.setText(String.valueOf(careOffer.getSeatsAvailable()));
        careOfferView.youngestGradeLabelValue.setText(String.valueOf(careOffer.getYoungestGrade()));
        careOfferView.youngestGradeTextField.setText(String.valueOf(careOffer.getYoungestGrade()));
        careOfferView.oldestGradeLabelValue.setText(String.valueOf(careOffer.getOldestGrade()));
        careOfferView.oldestGradeTextField.setText(String.valueOf(careOffer.getOldestGrade()));
    }

}
