package org.openjfx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistrerKategoriController implements Initializable {

    @FXML
    Button CloseButton;
    @FXML
    TextField KategoriNavn;
    @FXML
    TextArea KategoriBeskrivelse;
    @FXML
    Button RegistrerKategori;

    @FXML
    private void CloseModula(ActionEvent event){
        Stage stage = (Stage) CloseButton.getScene().getWindow();
        stage.close();
    }

    @FXML private void SubmitKategori(ActionEvent event){

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
