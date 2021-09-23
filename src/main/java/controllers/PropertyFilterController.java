package controllers;

import AppHolder.*;
import Property.PropertyType;
import Utils.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class PropertyFilterController {
    public static final String[] STATUSES = {Utils.ACTIVE, Utils.INACTIVE};

    @FXML
    private DialogPane dialogPane;

    @FXML
    private CheckBox typeChecked;
    @FXML
    private CheckBox statusChecked;
    @FXML
    private CheckBox commentsChecked;
    @FXML
    private CheckBox facilitiesChecked;
    @FXML
    private CheckBox addressChecked;
    @FXML
    private CheckBox minRateChecked;
    @FXML
    private CheckBox maxRateChecked;
    @FXML
    private ChoiceBox<PropertyType> typeChoices;
    @FXML
    private ChoiceBox<String> statusChoices;
    @FXML
    private CheckBox isCommented;
    @FXML
    private CheckBox isWifi;
    @FXML
    private CheckBox isFridge;
    @FXML
    private CheckBox isTv;
    @FXML
    private CheckBox isAirCond;
    @FXML
    private CheckBox isWaterHeater;
    @FXML
    private CheckBox isSwimmingPool;
    @FXML
    private TextField addressField;
    @FXML
    private ChoiceBox<String> stateChoices;
    @FXML
    private TextField postcodeField;
    @FXML
    private TextField minRate;
    @FXML
    private TextField maxRate;


    @FXML
    private void initialize() {
        populateData();

        Button okButton = (Button) dialogPane.lookupButton(ButtonType.APPLY);
        okButton.addEventFilter(
                ActionEvent.ACTION, event -> {
                    setPropertyFilterHolder();
                });
    }

    private void setPropertyFilterHolder(){
        AppHolder holder = AppHolder.getInstance();
        PropertyFilterHolder propertyFilterHolder = new PropertyFilterHolder();
        propertyFilterHolder.setTypeChecked(typeChecked.isSelected());
        propertyFilterHolder.setStatusChecked(statusChecked.isSelected());
        propertyFilterHolder.setCommentsChecked(commentsChecked.isSelected());
        propertyFilterHolder.setFacilitiesChecked(facilitiesChecked.isSelected());
        propertyFilterHolder.setAddressChecked(addressChecked.isSelected());
        propertyFilterHolder.setMinRateChecked(minRateChecked.isSelected());
        propertyFilterHolder.setMaxRateChecked(maxRateChecked.isSelected());
        propertyFilterHolder.setTypeChoice(typeChoices.getValue());
        propertyFilterHolder.setStatusChoice(statusChoices.getValue());
        propertyFilterHolder.setIsCommented(isCommented.isSelected());
        propertyFilterHolder.setIsWifi(isWifi.isSelected());
        propertyFilterHolder.setIsFridge(isFridge.isSelected());
        propertyFilterHolder.setIsTv(isTv.isSelected());
        propertyFilterHolder.setIsAirCond(isAirCond.isSelected());
        propertyFilterHolder.setIsWaterHeater(isWaterHeater.isSelected());
        propertyFilterHolder.setIsSwimmingPool(isSwimmingPool.isSelected());
        propertyFilterHolder.setAddressField(addressField.getText());
        propertyFilterHolder.setStateChoice(stateChoices.getValue());
        propertyFilterHolder.setPostcodeField((postcodeField.getText()));
        propertyFilterHolder.setMinRate(minRate.getText());
        propertyFilterHolder.setMaxRate(maxRate.getText());

        holder.setPropertyFilterHolder((propertyFilterHolder));
    }

    @FXML
    private void populateData() {
        PropertyTypeStringConverter propertyTypeStringConverter = new PropertyTypeStringConverter();
        IntegerFormatter integerFormatter = new IntegerFormatter();
        DoubleFormatter doubleFormatter1 = new DoubleFormatter();
        DoubleFormatter doubleFormatter2 = new DoubleFormatter();

        typeChoices.getItems().addAll(PropertyType.values());
        typeChoices.setConverter(propertyTypeStringConverter);
        statusChoices.getItems().addAll(STATUSES);
        stateChoices.getItems().addAll(Utils.STATES);
        postcodeField.setTextFormatter(integerFormatter.getInstance());
        minRate.setTextFormatter(doubleFormatter1.getInstance());
        maxRate.setTextFormatter(doubleFormatter2.getInstance());

        AppHolder holder = AppHolder.getInstance();
        PropertyFilterHolder propertyFilterHolder = holder.getPropertyFilterHolder();
        if(propertyFilterHolder != null){
            typeChecked.setSelected(propertyFilterHolder.isTypeChecked());
            statusChecked.setSelected(propertyFilterHolder.isStatusChecked());
            commentsChecked.setSelected(propertyFilterHolder.isCommentsChecked());
            facilitiesChecked.setSelected(propertyFilterHolder.isFacilitiesChecked());
            addressChecked.setSelected(propertyFilterHolder.isAddressChecked());
            minRateChecked.setSelected(propertyFilterHolder.isMinRateChecked());
            maxRateChecked.setSelected(propertyFilterHolder.isMaxRateChecked());
            typeChoices.setValue(propertyFilterHolder.getTypeChoice());
            statusChoices.setValue(propertyFilterHolder.getStatusChoice());
            isCommented.setSelected(propertyFilterHolder.isCommented());
            isWifi.setSelected(propertyFilterHolder.isWifi());
            isFridge.setSelected(propertyFilterHolder.isFridge());
            isTv.setSelected(propertyFilterHolder.isTv());
            isAirCond.setSelected(propertyFilterHolder.isAirCond());
            isWaterHeater.setSelected(propertyFilterHolder.isWaterHeater());
            isSwimmingPool.setSelected(propertyFilterHolder.isSwimmingPool());
            addressField.setText(propertyFilterHolder.getAddressField());
            stateChoices.setValue(propertyFilterHolder.getStateChoice());
            postcodeField.setText(propertyFilterHolder.getPostcodeField());
            minRate.setText(propertyFilterHolder.getMinRate());
            maxRate.setText(propertyFilterHolder.getMaxRate());
        }
    }
}
