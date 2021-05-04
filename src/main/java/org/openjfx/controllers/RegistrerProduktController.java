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
import org.openjfx.Produkt;
import org.openjfx.ProduktKategori;

import java.io.File;
import java.io.IOException;

class produktStrategi extends strategiVelger {
    public produktStrategi() {
        super(new CSVStrategy());
    }
}

public class RegistrerProduktController implements  RegistreringsInterface{
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
        Produkt p = new Produkt(navn, beskrivelse, kategori);

        strategiVelger strat = new produktStrategi();
        File file = new File("Produkter");

        try {
            strat.skrivTilFil(file, p, kategori);
        } catch (IOException e) {
            e.printStackTrace();
            //Skriv feilmelding
            return;
        }

        Stage stage = (Stage) KategoriDropdown.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void CloseModula(ActionEvent event){
        Stage stage = (Stage) CloseButton.getScene().getWindow();
        stage.close();
    }

}
