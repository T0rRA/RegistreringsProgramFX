package org.openjfx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.openjfx.ProduktKategori;

import java.io.IOException;

public class RegistrerProduktController {
    @FXML
    TextField ProduktNavn;
    @FXML
    TextArea ProduktBeskrivelse;
    @FXML
    ChoiceBox<ProduktKategori> KategoriDropdown;
    @FXML
    Button CloseButton;
    @FXML
    private void SubmitProdukt(ActionEvent event) {


        Stage stage = (Stage) KategoriDropdown.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void CloseModula(ActionEvent event){
        Stage stage = (Stage) CloseButton.getScene().getWindow();
        stage.close();
    }

}
