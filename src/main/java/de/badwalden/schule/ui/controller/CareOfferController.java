package de.badwalden.schule.ui.controller;

import de.badwalden.schule.model.CareOffer;
import de.badwalden.schule.ui.views.CareOfferMarketplaceView;
import de.badwalden.schule.ui.views.CareOfferView;
import de.badwalden.schule.ui.views.MainView;

public class CareOfferController {
    CareOfferView careOfferView;
    public CareOfferController(CareOfferView careOfferView) {
        this.careOfferView = careOfferView;
    }

    public void setValuesOfObject(CareOffer careOffer) {
        careOffer.setName(careOfferView.titleTextField.getText());
        careOffer.setDescription(careOfferView.descriptionTextField.getText());
        careOffer.setNumberOfSeats(Integer.parseInt(careOfferView.numberOfSeatsTextField.getText()));
        careOffer.setYoungestGrade(Integer.parseInt(careOfferView.youngestGradeTextField.getText()));
    }

    public void updateValuesFromObject(CareOffer careOffer) {
        careOfferView.titleLabelValue.setText(careOffer.getName());
        careOfferView.titleTextField.setText(careOffer.getName());
        careOfferView.descriptionLabelValue.setText(careOffer.getDescription());
        careOfferView.descriptionTextField.setText(careOffer.getDescription());
        careOfferView.numberOfSeatsLabelValue.setText(String.valueOf(careOffer.getNumberOfSeats()));
        careOfferView.numberOfSeatsTextField.setText(String.valueOf(careOffer.getNumberOfSeats()));
        careOfferView.youngestGradeLabelValue.setText(String.valueOf(careOffer.getYoungestGrade()));
        careOfferView.youngestGradeTextField.setText(String.valueOf(careOffer.getYoungestGrade()));
    }

}
