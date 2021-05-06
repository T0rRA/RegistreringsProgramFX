package org.openjfx.controllers;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.openjfx.Produkt;
import org.openjfx.ProduktKategori;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.MalformedInputException;
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
    Label errMessage1;

    @FXML
    private void Submit(ActionEvent event) {
        try {
            String navn = Navn.getText();
            String beskrivelse = Beskrivelse.getText();
            ProduktKategori kategori = KategoriDropdown.getValue();
            String kategoriNavn = kategori.getKategoriNavn();

            if(kategoriNavn.equals(null) || navn.equals(null) || beskrivelse.equals(null)){
                errMessage1.setText("Felt mangler");
                return;
            }
            Produkt p = new Produkt(navn, beskrivelse, kategoriNavn);

            CSVLesSkriv csvls = new CSVLesSkriv();
            List<Produkt> produkter = csvls.lesCSV();
            produkter.add(p);
            csvls.skrivTilCSV(produkter);

            Stage stage = (Stage) KategoriDropdown.getScene().getWindow();
            stage.close();
        }
        catch(Exception e){

        }
    }
    @FXML
    private void CloseModula(ActionEvent event){
        Stage stage = (Stage) CloseButton.getScene().getWindow();
        stage.close();
    }

   @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        KategoriChoicePopulator kcp = new KategoriChoicePopulator();
       try {
           List<ProduktKategori> kategorier = kcp.call();
           KategoriDropdown.getItems().addAll(kategorier);
       } catch (Exception e) {
           e.printStackTrace();
           return;
       }

    }
}

class KategoriChoicePopulator extends Task<List<ProduktKategori>> {

    @Override
    protected List<ProduktKategori> call() throws Exception {
        try{
            BinaryLesSkriv bls = new BinaryLesSkriv();
            bls.lastInn();
            Thread.sleep(5000);
            return bls;
        }
        catch (Exception ie){
            return null;
        }
    }
}
