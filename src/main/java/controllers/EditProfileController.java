package controllers;

import Agent.Agent;
import Agent.AgentDatabase;
import AppHolder.AppHolder;
import Owner.Owner;
import Owner.OwnerDatabase;
import Phone.Phone;
import Role.Role;
import Utils.Utils;
import com.app.main.Main;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class EditProfileController {
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField phoneNoField;

    private Role currentUser = AppHolder.getInstance().getUser();

    @FXML
    private void initialize() {
        populateData();
    }

    @FXML
    private void onUpdate(MouseEvent mouseEvent) throws IOException {
        if (currentUser != null) {
            if (isValid()) {
                String role = currentUser.getRole();
                String currentUsername = currentUser.getUserName();
                String newUsername = usernameField.getText();
                String newPassword = passwordField.getText();
                String newPhoneNo = phoneNoField.getText();

                if (role.equals("Owner")) {
                    OwnerDatabase ownerDB = OwnerDatabase.getInstance();
                    Owner ownerUser = ownerDB.searchUser(currentUsername);
                    ownerUser.setUserName(newUsername);
                    ownerUser.setPassword(newPassword);
                    ownerUser.setPhone(new Phone(newPhoneNo));
                    ownerDB.update(ownerUser);
                } else if (role.equals("Agent")) {
                    AgentDatabase agentDB = AgentDatabase.getInstance();
                    Agent agentUser = agentDB.searchUser(currentUsername);
                    agentUser.setUserName(newUsername);
                    agentUser.setPassword(newPassword);
                    agentUser.setPhone(new Phone(newPhoneNo));
                    agentDB.update(agentUser);
                }

                Utils.showAlert("Updated Successful!!", true);
            } else {
                Utils.showAlert("All fields are required", false);
            }

        }
    }

    private boolean isValid() {
        return !usernameField.getText().isEmpty() && !passwordField.getText().isEmpty() && !phoneNoField.getText().isEmpty();
    }

    private void populateData() {
        if (currentUser != null) {
            usernameField.setText(currentUser.getUserName());
            passwordField.setText(currentUser.getPassword());
            phoneNoField.setText(currentUser.getPhone().getNumber());
        }
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
