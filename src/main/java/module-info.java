module RegistreringsProgramFX {
    requires javafx.controls;
    requires javafx.fxml;
    requires opencsv;
    requires java.sql;

    opens org.openjfx to javafx.fxml;
    exports org.openjfx;
    exports org.openjfx.controllers;
    opens org.openjfx.controllers to javafx.fxml;
}