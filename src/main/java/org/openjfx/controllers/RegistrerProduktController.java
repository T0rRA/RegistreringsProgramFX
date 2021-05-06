package org.openjfx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.openjfx.Produkt;
import org.openjfx.ProduktKategori;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class RegistrerProduktController implements  RegistreringsInterface, Initializable {
    @FXML
    TextField Navn;
    @FXML
    TextArea Beskrivelse;
    @FXML
    ChoiceBox<ProduktKategori> KategoriDropdown;
    @FXML
    Button CloseButton;
    @FXML
    private void Submit(ActionEvent event) {
        String navn = Navn.getText();
        String beskrivelse = Beskrivelse.getText();
        ProduktKategori kategori = KategoriDropdown.getValue();
        String kategoriNavn = kategori.getKategoriNavn();
        Produkt p = new Produkt(navn, beskrivelse, kategoriNavn);


        Stage stage = (Stage) KategoriDropdown.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void CloseModula(ActionEvent event){
        Stage stage = (Stage) CloseButton.getScene().getWindow();
        stage.close();
    }

   @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      //  strategiVelger strat = new KategoriReaderStrategi();

    }
}
