package controllers;

import Agent.AgentDatabase;
import AppHolder.AppHolder;
import Owner.OwnerDatabase;
import Role.Role;
import com.app.main.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;

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

        Role agentUser = AgentDatabase.getInstance().searchUser(username.getText()); //try get from AgentDB
        Role ownerUser = OwnerDatabase.getInstance().searchUser(username.getText()); //try get from OwnerDB

        if (username.getText().isEmpty() || password.getText().isEmpty()) {
            msg.setText("Please enter your credentials.");
        } else if ((agentUser != null) && agentUser.getPassword().equals(password.getText()) || (ownerUser != null) && ownerUser.getPassword().equals(password.getText())) {
            AppHolder holder = AppHolder.getInstance();
            if (agentUser != null) {
                holder.setUser(agentUser);
            } else {
                holder.setUser(ownerUser);
            }

            Main.switchScene("ViewBoard.fxml");
        } else {
            msg.setText("Invalid credentials!");
        }

    }
}
