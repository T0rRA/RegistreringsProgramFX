package org.openjfx.controllers;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
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
    Label Laster;

    @FXML
    private void Submit(ActionEvent event) {
        try {
            String navn = Navn.getText();
            String beskrivelse = Beskrivelse.getText();
            ProduktKategori kategori = KategoriDropdown.getValue();
            String kategoriNavn = kategori.getKategoriNavn();

            if (kategoriNavn.equals(null) || navn.equals(null) || beskrivelse.equals(null)) {
                errMessage1.setTextFill(Color.web("#e40d0d"));
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
        } catch (Exception e) {

        }
    }

    @FXML
    private void CloseModula(ActionEvent event) {
        Stage stage = (Stage) CloseButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Laster.setText("Leser inn kategorier");
        System.out.println("Firste thread: "+Thread.currentThread());
        Service<Void> service = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        try{
                            BinaryLesSkriv bls = new BinaryLesSkriv();
                            bls.lastInn();
                            Thread.sleep(2000);
                            System.out.println("Sleepy thread: "+Thread.currentThread());
                            KategoriDropdown.getItems().addAll(bls);
                            Thread.sleep(1000);


                            return null;
                        }
                        catch (Exception ie){
                            ie.printStackTrace();
                            errMessage1.setText("Noe gikk galt...");
                            return null;
                        }
                    }
                };
            }
        };
        service.setOnSucceeded((WorkerStateEvent event) ->{
            Laster.setText("Ferdig");
        });
        service.start();
    }
}
