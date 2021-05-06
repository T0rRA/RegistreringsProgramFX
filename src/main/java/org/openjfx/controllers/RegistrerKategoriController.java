package org.openjfx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.openjfx.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

class kategoriStrategi extends strategiVelger {
    public kategoriStrategi() {
        super(new BinaryStrategy());
    }
}

public class RegistrerKategoriController implements Initializable, RegistreringsInterface {

    @FXML
    Button CloseButton;
    @FXML
    TextField Navn;
    @FXML
    TextArea Beskrivelse;
    @FXML
    Button RegistrerKategori;

    @FXML
    private void CloseModula(ActionEvent event){
        Stage stage = (Stage) CloseButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void Submit(ActionEvent event){
        String navn = Navn.getText();
        String beskrivelse = Beskrivelse.getText();

        ProduktKategori pk = new ProduktKategori(navn, beskrivelse);
        Produkt etTomtProdukt = new Produkt(null, null, null);

        strategiVelger strat = new kategoriStrategi();

        strat.leggTil(pk, etTomtProdukt);

        CloseModula(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //her fyller vi kategori
    }
}
