package de.badwalden.schule.ui.controller;

import de.badwalden.schule.model.CareOffer;
import de.badwalden.schule.model.Student;
import de.badwalden.schule.ui.helper.LanguageHelper;
import de.badwalden.schule.ui.helper.Session;
import de.badwalden.schule.ui.views.CareOfferView;
import de.badwalden.schule.ui.views.DataController;
import javafx.animation.PauseTransition;
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

    /**
     * Updates the registration status of a care offer for a student and updates the UI accordingly.
     *
     * @param  careOffer                  the care offer to update the registration status for
     * @param  student                    the student whose registration status is being updated
     * @param  dialogRegistrationButton   the button that triggered the registration update
     */
    public void changeCareOfferRegistration(CareOffer careOffer, Student student, Button dialogRegistrationButton) {
        if (student.isRegisteredForOffer(careOffer)) {
            student.getServiceList().remove(careOffer);
            student.update();
            dialogRegistrationButton.setText(LanguageHelper.getString("add_child"));
        } else {
            student.getServiceList().add(careOffer);
            student.update();
            dialogRegistrationButton.setText(LanguageHelper.getString("remove_child"));
        }
        pauseButton(dialogRegistrationButton, 3);
        updateValuesFromObject(careOffer);
    }

    /**
     * Updates the registration status of a care offer for a student and updates the UI accordingly.
     *
     * @param  careOffer  the care offer to update the registration status for
     * @param  student    the student whose registration status is being updated
     */
    public void changeCareOfferRegistration(CareOffer careOffer, Student student) {
        if (student.isRegisteredForOffer(careOffer)) {
            student.getServiceList().remove(careOffer);
            student.update();
        } else {
            student.getServiceList().add(careOffer);
            student.update();
        }
    }

    /**
     * Determines if the student is right of service for the given care offer.
     *
     * @param  careOffer  the care offer to check
     * @param  student    the student to check
     * @return            true if the student is right of service for the care offer, false otherwise
     */
    public boolean isRightOfSerice(CareOffer careOffer, Student student) {
        return student.isRightOfService(careOffer);
    }

    /**
     * Disables the given button for the specified number of seconds, then re-enables it.
     *
     * @param  button   the button to pause
     * @param  seconds  the number of seconds to pause the button for
     */
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

//    /**
//     * Sets the values of a CareOffer object based on the input from a CareOfferView.
//     *
//     * @param careOffer the CareOffer object to set values for
//     */
//    public void setValuesOfObject(CareOffer careOffer) {
//        careOffer.setName(careOfferView.titleTextField.getText());
//        careOffer.setDescription(careOfferView.descriptionTextField.getText());
//        careOffer.setSeatsAvailable(Integer.parseInt(careOfferView.seatsAvailableTextField.getText()));
//        careOffer.setYoungestGrade(Integer.parseInt(careOfferView.youngestGradeTextField.getText()));
//        careOffer.setOldestGrade(Integer.parseInt(careOfferView.oldestGradeTextField.getText()));
//    }
}
