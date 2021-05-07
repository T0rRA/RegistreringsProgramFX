package org.openjfx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.openjfx.ProduktKategori;

import java.net.URL;
import java.util.ResourceBundle;

public class CloseProgramController {
    @FXML
    Button AvsluttProgramKnapp;
    @FXML
    Button AvbrytKnapp;

    @FXML
    private void ExitApplication(ActionEvent event){
        System.exit(0);
    }

    @FXML
    private void CloseModula(ActionEvent event){
        Stage stage = (Stage) AvbrytKnapp.getScene().getWindow();
        stage.close();

    }
}
