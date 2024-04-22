package de.badwalden.schule.ui.controller;

import de.badwalden.schule.model.CareOffer;
import de.badwalden.schule.model.Service;
import de.badwalden.schule.model.Student;
import de.badwalden.schule.ui.helper.Session;
import de.badwalden.schule.ui.views.CareOfferMarketplaceView;
import de.badwalden.schule.ui.views.CareOfferView;
import de.badwalden.schule.ui.views.DataController;
import de.badwalden.schule.ui.views.MainView;
import javafx.scene.control.Button;

public class CareOfferController implements DataController {
    CareOfferView careOfferView;
    public CareOfferController(CareOfferView careOfferView) {
        this.careOfferView = careOfferView;
    }

    @Override
    public Object[] getData(){
        CareOffer careOffer = Session.getInstance().getCachedCareOffer();
        return new Object[] {careOffer};
    }

    public void changeCareOfferAttendance(CareOffer careOffer, Student student, Button dialogRegistrationButton) {
        if( isChildRegisteredForOffer(student) ) {
            careOffer.getStudentList().remove(student);
            student.getServiceList().remove(careOffer);
            dialogRegistrationButton.setText("Register");
        } else {
            careOffer.getStudentList().add(student);
            student.getServiceList().add(careOffer);
            dialogRegistrationButton.setText("Unregister");
        }
    }

    public void changeCareOfferAttendance(CareOffer careOffer, Student student) {
        if( isChildRegisteredForOffer(student) ) {
            careOffer.getStudentList().remove(student);
            student.getServiceList().remove(careOffer);
        } else {
            careOffer.getStudentList().add(student);
            student.getServiceList().add(careOffer);
        }
    }

    public boolean isChildRegisteredForOffer(Student student) {
        for (Service service  : student.getServiceList()) {
            CareOffer careOffer = (CareOffer) service;
            if (careOffer.getStudentList().contains(student)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Sets the values of a CareOffer object based on the input from a CareOfferView.
     *
     * @param  careOffer  the CareOffer object to set values for
     */
    public void setValuesOfObject(CareOffer careOffer) {
        careOffer.setName(careOfferView.titleTextField.getText());
        careOffer.setDescription(careOfferView.descriptionTextField.getText());
        careOffer.setNumberOfSeats(Integer.parseInt(careOfferView.numberOfSeatsTextField.getText()));
        careOffer.setYoungestGrade(Integer.parseInt(careOfferView.youngestGradeTextField.getText()));
        careOffer.setOldestGrade(Integer.parseInt(careOfferView.oldestGradeTextField.getText()));
    }

    /**
     * Updates the values in the care offer view based on the provided CareOffer object.
     *
     * @param  careOffer  the CareOffer object containing the values to update the view with
     */
    public void updateValuesFromObject(CareOffer careOffer) {
        careOfferView.titleLabelValue.setText(careOffer.getName());
        careOfferView.titleTextField.setText(careOffer.getName());
        careOfferView.descriptionLabelValue.setText(careOffer.getDescription());
        careOfferView.descriptionTextField.setText(careOffer.getDescription());
        careOfferView.numberOfSeatsLabelValue.setText(String.valueOf(careOffer.getNumberOfSeats()));
        careOfferView.numberOfSeatsTextField.setText(String.valueOf(careOffer.getNumberOfSeats()));
        careOfferView.youngestGradeLabelValue.setText(String.valueOf(careOffer.getYoungestGrade()));
        careOfferView.youngestGradeTextField.setText(String.valueOf(careOffer.getYoungestGrade()));
        careOfferView.oldestGradeLabelValue.setText(String.valueOf(careOffer.getOldestGrade()));
        careOfferView.oldestGradeTextField.setText(String.valueOf(careOffer.getOldestGrade()));
    }

}
