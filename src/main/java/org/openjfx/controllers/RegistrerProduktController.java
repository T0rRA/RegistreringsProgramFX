package org.openjfx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.openjfx.ProduktKategori;

public class RegistrerProduktController {
    @FXML
    TextField ProduktNavn;
    @FXML
    TextArea ProduktBeskrivelse;
    @FXML
    ChoiceBox<ProduktKategori> KategoriDropdown;

    @FXML
    private void SubmitProdukt(ActionEvent event) {

    }
}
