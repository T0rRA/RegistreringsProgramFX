package org.openjfx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.openjfx.Produkt;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    @FXML
    private TableView EntireTable;
    @FXML
    private TableColumn<Produkt, String> produktTable;
    @FXML
    private TableColumn<Produkt,String> kategoriTable;
    @FXML private TableColumn<Produkt, String> beskrivelseTable;
    @FXML private TableColumn<Produkt, String> prisTable;

    @FXML
    private void handleButtonAction(ActionEvent event) {

    }

    @FXML
    private void OpenRegistrerProdukt(ActionEvent event) throws MalformedURLException {
        try {
            helpOpenModula("RegistrerProdukt");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void OpenRegistrerKategori(ActionEvent event) throws MalformedURLException {
        try {
            helpOpenModula("RegistrerKategori");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @FXML private void OpenCloseModula(ActionEvent event) {
        try {
            helpOpenModula("CloseProgram");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    //Hjelpe metode som åpner en scene som en modula.
    private void helpOpenModula(String action) throws MalformedURLException {
        Parent root = null;
        //Path til resources, måtte gjøre på denne måten pga pakke strukturen.
        URL url = new File("src/main/resources/org/openjfx/"+action+".fxml").toURI().toURL();

        //Required try catch for load.
        try {
            FXMLLoader loader = new FXMLLoader();
            root = loader.load(url);
        } catch(IOException e) {
            e.printStackTrace();
            //errorMessage.setText("Can't open");
        }
        Scene eventViewSettingsScene = new Scene(root);
        Stage eventViewSettingsStage = new Stage();

        String title=null;
        switch(action) {
            case "RegistrerKategori":
                title="Kategori Registrering";
                break;
            case "RegistrerProdukt":
                title="Produkt Registrering";
                break;
            case "CloseProgram":
                title="Er du sikker du vil avslutte?";
                break;
        }
        eventViewSettingsStage.setTitle(title);
        eventViewSettingsStage.setScene(eventViewSettingsScene);

        //Fryser mainview'et.
        eventViewSettingsStage.initOwner(EntireTable.getScene().getWindow());
        eventViewSettingsStage.initModality(Modality.WINDOW_MODAL);
        eventViewSettingsStage.show();
    }

    public void initialize() {
        // TODO
    }

    @Override
    public void initialize(URL url, ResourceBundle resources) {

    }
}