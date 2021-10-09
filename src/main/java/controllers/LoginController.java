package controllers;

import AppHolder.AppHolder;
import Role.Role;
import Role.RoleDatabase;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

import static com.app.main.Main.switchScene;

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
    private void onRegister(MouseEvent mouseEvent) throws IOException {
        switchScene("Register.fxml");
    }

    @FXML
    private void onLogin(MouseEvent mouseEvent) throws IOException {
        if (username.getText().isEmpty() || password.getText().isEmpty()) {
            msg.setText("Please enter your credentials.");
            return;
        }

        String userNameEntered = username.getText();
        String passwordEntered = password.getText();

        Role getUser = RoleDatabase.searchUser(userNameEntered);

        if (getUser == null) {
            msg.setText("User does not exist");
            return;
        }

        String role = getUser.getRole();
        String password = getUser.getPassword();

        if (!role.equals("Tenant")) {
            msg.setText("Wrong software. For Tenant only");
            return;
        }

        if (!password.equals(passwordEntered)) {
            msg.setText("Invalid credentials!");
            return;
        }

        AppHolder holder = AppHolder.getInstance();
        holder.setUser(getUser);

        switchScene("ViewBoard.fxml");
    }
}