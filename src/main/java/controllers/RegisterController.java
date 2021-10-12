package controllers;

import AppHolder.AppHolder;
import Phone.Phone;
import Role.RoleDatabase;
import Tenant.*;
import Utils.PhoneFormatter;
import Utils.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

import static com.app.main.Main.switchScene;

/**
 * <h1>RegisterController Class</h1>
 * The RegisterController class is a controller class that registers
 * a new Tenant role and logs in for the user
 *
 * @author Boe Chang Horn
 * @version 1.0
 * @since 2021-10-12
 */
public class RegisterController {
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField phoneNoField;

    /**
     * A private method that will be triggered when
     * the scene initializes and set text format
     */
    @FXML
    private void initialize() {
        phoneNoField.setTextFormatter(new PhoneFormatter().getInstance());
    }

    /**
     * A private method that creates a new Tenant role
     * and logs in with input validation
     *
     * @param mouseEvent the mouse event
     */
    @FXML
    private void onRegister(MouseEvent mouseEvent) throws IOException{
        if (isValid()) {

            String newUsername = usernameField.getText();
            if (RoleDatabase.isUserExist(newUsername))  {
                Utils.showAlert("User name has been taken", false, mouseEvent);
                return;
            }

            TenantDatabase tenantDB = TenantDatabase.getInstance();
            int id = tenantDB.getNewID();
            Phone phone = new Phone(phoneNoField.getText());
            Tenant tenant = new Tenant(id, newUsername, passwordField.getText(), phone);
            tenantDB.create(tenant);

            Utils.showAlert("Create Successful!!", true, mouseEvent);

            AppHolder holder = AppHolder.getInstance();
            holder.setUser(tenant);

            switchScene("ViewBoard.fxml");
        } else {
            Utils.showAlert("All fields are required", false, mouseEvent);
        }
    }

    /**
     * A private method that brings the user back to the Login page
     *
     * @param mouseEvent the mouse event
     */
    @FXML
    private void onBack(MouseEvent mouseEvent) throws IOException {
        switchScene("Login.fxml");
    }

    /**
     * A private method that validates inputs
     *
     * @return boolean value that determine whether inputs value are valid
     */
    private boolean isValid() {
        return !usernameField.getText().isEmpty() && !passwordField.getText().isEmpty() && !phoneNoField.getText().isEmpty();
    }
}
