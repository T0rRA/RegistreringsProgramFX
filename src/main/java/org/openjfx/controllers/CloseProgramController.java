package org.openjfx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.openjfx.ProduktKategori;

import java.net.URL;
import java.util.ResourceBundle;

public class CloseProgramController implements Initializable {
    @FXML
    TextField ProduktNavn;
    @FXML
    TextArea ProduktBeskrivelse;
    @FXML
    ChoiceBox<ProduktKategori> KategoriDropdown;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void SubmitProdukt(ActionEvent event) {

    }
}
