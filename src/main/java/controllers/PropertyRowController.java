package controllers;

import Property.Property;
import Utils.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * <h1>PropertyRowController Class</h1>
 * The PropertyRowController class is a controller class that
 * aids in controlling the PropertyRow component
 *
 * @author Boe Chang Horn
 * @version 1.0
 * @since 2021-10-12
 */
public class PropertyRowController {
    @FXML
    private Label nameLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label rateLabel;
    @FXML
    private Label ownerLabel;
    @FXML
    private Label agentLabel;
    @FXML
    private Label buildingDetailLabel;
    @FXML
    private Label roomNumLabel;
    @FXML
    private Label bathRoomNumLabel;
    @FXML
    private Label tvNumLabel;
    @FXML
    private Label fridgeNumLabel;
    @FXML
    private Label waterHeaterNumLabel;
    @FXML
    private Label airCondNumLabel;
    @FXML
    private Label wifiLabel;
    @FXML
    private Label spLabel;
    @FXML
    private Label commentLabel;

    private Property property;
    private PropertyListener myListener;

    /**
     * A public method that populate the PropertyRow component inputs
     * by using the selected property object
     *
     * @param property the selected property,
     * @param myListener the PropertyListener
     */
    public void setData(Property property, PropertyListener myListener) {
        this.property = property;
        this.myListener = myListener;
        nameLabel.setText(property.getName());
        String addressTxt = property.getAddress().getFullAddress();
        addressLabel.setText(addressTxt);
        rateLabel.setText(Utils.CURRENCY + " " + property.getRate());
        buildingDetailLabel.setText(Utils.getPropertyTypeTxt(property.getType()) + "  •  " + property.getSize() + " " + Utils.SIZE_UNIT);
        roomNumLabel.setText(Integer.toString(property.getRoomNum()));
        bathRoomNumLabel.setText(Integer.toString(property.getBathRoomNum()));
        tvNumLabel.setText(Integer.toString(property.getFacilities().getTv()));
        fridgeNumLabel.setText(Integer.toString(property.getFacilities().getFridge()));
        waterHeaterNumLabel.setText(Integer.toString(property.getFacilities().getWaterHeater()));
        airCondNumLabel.setText(Integer.toString(property.getFacilities().getAirCond()));
        wifiLabel.setText(Utils.getYesOrNo(property.getFacilities().isWifi()));
        spLabel.setText(Utils.getYesOrNo(property.getFacilities().isSwimmingPool()));
        ownerLabel.setText(property.getOwner().getUserName() + " ( " + property.getOwner().getPhone().getNumber() + " )");
        agentLabel.setText(property.getAgent().getUserName() + " ( " + property.getAgent().getPhone().getNumber() + " )");

        String commentStr = property.getComment();
        if (commentStr == null) {
            commentLabel.setText("N/A");
            commentLabel.setStyle("-fx-text-fill: #697684");
        } else {
            commentLabel.setText(property.getComment());
        }
    }
}

