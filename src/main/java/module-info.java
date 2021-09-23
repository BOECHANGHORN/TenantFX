module com.app.owneragentfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.app.main to javafx.fxml;
    exports com.app.main;
    opens controllers to javafx.fxml;
    exports controllers;

}