package controllers;

import AppHolder.AppHolder;
import Role.Role;
import Tenant.TenantDatabase;
import com.app.main.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Label msg;

    @FXML
    private void initialize() {
        username.setFocusTraversable(true);
        password.setFocusTraversable(false);

    }

    @FXML
    private void onLogin(MouseEvent mouseEvent) throws IOException {

        Role tenantUser = TenantDatabase.getInstance().searchUser(username.getText()); //try get from TenantDB

        if (username.getText().isEmpty() || password.getText().isEmpty()) {
            msg.setText("Please enter your credentials.");
        } else if ((tenantUser != null) && tenantUser.getPassword().equals(password.getText())) {
            AppHolder holder = AppHolder.getInstance();
            holder.setUser(tenantUser);

            Main.switchScene("ViewBoard.fxml");
        } else {
            msg.setText("Invalid credentials!");
        }

    }
}
