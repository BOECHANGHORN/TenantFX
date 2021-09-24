package controllers;

import Agent.*;
import AppHolder.AppHolder;
import Owner.*;
import Property.*;
import Role.Role;
import Utils.*;
import com.app.main.Main;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.TreeMap;

public class UpdatePropertyController {
    @FXML
    private TextField name;
    @FXML
    private ChoiceBox<PropertyType> typeChoices;
    @FXML
    private ChoiceBox<Role> ownerChoices;
    @FXML
    private ChoiceBox<Role> agentChoices;
    @FXML
    private Spinner<Integer> roomNum;
    private SpinnerValueFactory<Integer> roomNumVF = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10);

    @FXML
    private Spinner<Integer> bathRoomNum;
    private SpinnerValueFactory<Integer> bathRoomNumVF = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10);

    @FXML
    private TextArea address;
    @FXML
    private ChoiceBox<String> stateChoices;
    @FXML
    private TextField postcode;
    @FXML
    private CheckBox wifi;
    @FXML
    private CheckBox swimmingPool;
    @FXML
    private Spinner<Integer> tvNum;
    private SpinnerValueFactory<Integer> tvNumVF = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10);
    @FXML
    private Spinner<Integer> fridgeNum;
    private SpinnerValueFactory<Integer> fridgeNumVF = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10);
    @FXML
    private Spinner<Integer> airConNum;
    private SpinnerValueFactory<Integer> airConNumVF = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10);
    @FXML
    private Spinner<Integer> waterHeaterNum;
    private SpinnerValueFactory<Integer> waterHeaterNumVF = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10);
    @FXML
    private TextField sizeTxt;
    @FXML
    private TextField rateTxt;
    @FXML
    private  CheckBox isPublished;

    AppHolder holder = AppHolder.getInstance();
    private TreeMap<Integer, Agent> agentList = AgentDatabase.getInstance().read();
    private Property selectedProperty = AppHolder.getInstance().getSelectedProperty();


    @FXML
    private void initialize() {
        // Setup Input
        populateData();
    }


    @FXML
    private void onUpdate(MouseEvent mouseEvent) {
        if (selectedProperty != null) {
            if (isValid()) {
                PropertyDatabase propertyDB = PropertyDatabase.getInstance();
                selectedProperty.setName(name.getText());
                selectedProperty.setType(typeChoices.getValue());
                selectedProperty.setOwner((Owner) ownerChoices.getValue());
                selectedProperty.setAgent((Agent) agentChoices.getValue());
                selectedProperty.setRoomNum(roomNum.getValue());
                selectedProperty.setBathRoomNum(bathRoomNum.getValue());
                selectedProperty.setAddress(new PropertyAddress(address.getText(), postcode.getText(), stateChoices.getValue()));
                selectedProperty.setFacilities(new PropertyFacilities(swimmingPool.isSelected(), wifi.isSelected(), tvNum.getValue(), fridgeNum.getValue(), airConNum.getValue(), waterHeaterNum.getValue()));
                int size = Integer.parseInt(sizeTxt.getText());
                double rate = Double.parseDouble(rateTxt.getText());
                selectedProperty.setSize(size);
                selectedProperty.setRate(rate);
                selectedProperty.setPublished(isPublished.isSelected());
                propertyDB.update(selectedProperty);

                if (propertyDB.searchByID(selectedProperty.getId()) != null) {
                    Utils.showAlert("Updated Successful!!", true);
                }
            } else {
                Utils.showAlert("All fields are required", false);
            }

        }
    }

    private void populateData() {
        if (selectedProperty != null) {
            RoleStringConverter roleStringConverter = new RoleStringConverter();
            PropertyTypeStringConverter propertyTypeStringConverter = new PropertyTypeStringConverter();
            IntegerFormatter integerFormatter1 = new IntegerFormatter();
            IntegerFormatter integerFormatter2 = new IntegerFormatter();
            DoubleFormatter doubleFormatter = new DoubleFormatter();

            // Owner role only able to create property owned by himself/herself, same concepts applied for agent role
            Role currentUser = holder.getUser();
            if (currentUser != null) {
                String role = currentUser.getRole();
                if (role.equals("Owner")) {
                    Owner ownerUser = OwnerDatabase.getInstance().searchByID(currentUser.getId()); //try get from OwnerDB
                    ownerChoices.getItems().addAll(ownerUser);
                    agentChoices.getItems().addAll(agentList.values());

                } else if (role.equals("Agent")) {
                    Agent agentUser = AgentDatabase.getInstance().searchByID(currentUser.getId()); //try get from AgentDB
                    ownerChoices.getItems().addAll(selectedProperty.getOwner());
                    agentChoices.getItems().addAll(agentUser);
                }
            }

            typeChoices.getItems().addAll(PropertyType.values());
            typeChoices.setConverter(propertyTypeStringConverter);
            ownerChoices.setConverter(roleStringConverter);
            agentChoices.setConverter(roleStringConverter);
            stateChoices.getItems().addAll(Utils.STATES);
            name.setText(selectedProperty.getName());
            typeChoices.setValue(selectedProperty.getType());
            ownerChoices.setValue(selectedProperty.getOwner());
            agentChoices.setValue(selectedProperty.getAgent());
            stateChoices.setValue(selectedProperty.getAddress().getState());
            address.setText(selectedProperty.getAddress().getDetailAddress());
            postcode.setTextFormatter(integerFormatter1.getInstance());
            postcode.setText(selectedProperty.getAddress().getPostalCode());
            wifi.setSelected(selectedProperty.getFacilities().isWifi());
            swimmingPool.setSelected(selectedProperty.getFacilities().isSwimmingPool());
            roomNumVF.setValue(selectedProperty.getRoomNum());
            roomNum.setValueFactory(roomNumVF);
            bathRoomNumVF.setValue(selectedProperty.getBathRoomNum());
            bathRoomNum.setValueFactory(bathRoomNumVF);
            tvNumVF.setValue(selectedProperty.getFacilities().getTv());
            tvNum.setValueFactory(tvNumVF);
            fridgeNumVF.setValue(selectedProperty.getFacilities().getFridge());
            fridgeNum.setValueFactory(fridgeNumVF);
            airConNumVF.setValue(selectedProperty.getFacilities().getAirCond());
            airConNum.setValueFactory(airConNumVF);
            waterHeaterNumVF.setValue(selectedProperty.getFacilities().getWaterHeater());
            waterHeaterNum.setValueFactory(waterHeaterNumVF);
            sizeTxt.setTextFormatter(integerFormatter2.getInstance());
            sizeTxt.setText(Integer.toString(selectedProperty.getSize()));
            rateTxt.setTextFormatter(doubleFormatter.getInstance());
            rateTxt.setText(Double.toString(selectedProperty.getRate()));
            isPublished.setSelected(selectedProperty.isPublished());
        }

    }

    private boolean isValid() {
        return !name.getText().isEmpty() && typeChoices.getValue() != null && ownerChoices.getValue() != null && agentChoices.getValue() != null
                && stateChoices.getValue() != null && !address.getText().isEmpty() && !postcode.getText().isEmpty();
    }

    @FXML
    private void onClickHomeBtn(MouseEvent mouseEvent) throws IOException {
        Main.goToViewBoardPage();
    }

    @FXML
    private void onClickAddBtn(MouseEvent mouseEvent) throws IOException {
        Main.goToAddPropertyPage();
    }

    @FXML
    private void onClickProfileBtn(MouseEvent mouseEvent) throws IOException {
        Main.goToEditProfilePage();
    }

    @FXML
    private void onLogout(MouseEvent mouseEvent) throws IOException {
        Main.goToLoginPage();
    }
}
