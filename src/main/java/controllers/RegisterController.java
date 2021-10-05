package controllers;

import AppHolder.AppHolder;
import Phone.Phone;
import Role.RoleDatabase;
import Tenant.*;
import Utils.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

import static com.app.main.Main.switchScene;

public class RegisterController {
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField phoneNoField;

    @FXML
    public void onRegister(MouseEvent mouseEvent) throws IOException{
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

    @FXML
    public void onBack(MouseEvent mouseEvent) throws IOException {
        switchScene("Login.fxml");
    }

    private boolean isValid() {
        return !usernameField.getText().isEmpty() && !passwordField.getText().isEmpty() && !phoneNoField.getText().isEmpty();
    }
}
