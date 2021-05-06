package org.openjfx.controllers;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.openjfx.Produkt;
import org.openjfx.ProduktKategori;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    @FXML private TableView EntireTable;
    @FXML private TableColumn<Produkt, String> produktTable;
    @FXML private TableColumn<Produkt, ProduktKategori> kategoriTable;
    @FXML private TableColumn<Produkt, String> beskrivelseTable;
    @FXML private Button RegistrerProdukt;
    @FXML private Label errMessage1;
    @FXML private Label Laster;


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
            errMessage1.setText("Kan ikke åpne");
        }
    }

    @FXML private void OpenKategoriVisning(ActionEvent event){
        try {
            helpOpenModula("KategoriView");
        }catch (MalformedURLException e){
            e.printStackTrace();
            errMessage1.setText("Kan ikke åpne");
        }
    }

    @FXML private void OpenCloseModula(ActionEvent event) {
        try {
            helpOpenModula("CloseProgram");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            errMessage1.setText("Kan ikke åpne");
        }
    }

    @FXML private void EditProdukt(TableColumn.CellEditEvent<?,?> event){
        //this is hard
        try {
            Object newVal = event.getNewValue();

            TablePosition<?, ?> pos = event.getTablePosition();
            int row = pos.getRow();
            int col = pos.getColumn();
        } catch (Exception e){
            errMessage1.setText("Klarer ikke redigere");
        }
    }

    public void initialize() {
        // TODO
    }

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        produktTable.setCellValueFactory(new PropertyValueFactory<Produkt, String>("produktNavn"));
        beskrivelseTable.setCellValueFactory(new PropertyValueFactory<Produkt, String>("omProdukt"));
        kategoriTable.setCellValueFactory(new PropertyValueFactory<Produkt, ProduktKategori>("produktKategori"));

        ProduktPopulator pp = new ProduktPopulator();
        try {
            Laster.setText("Laster...");
            List<Produkt> produkter = pp.call();
            EntireTable.getItems().setAll(produkter);
        } catch (Exception e) {
            e.printStackTrace();
            Laster.setText("");
            errMessage1.setText("Klarte ikke finne produkter");
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

        Scene modulaScene = new Scene(root);
        Stage modulaStage = new Stage();

        //valg av tittel basert på hvilken knapp man trykker på.
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
            case "KategoriView":
                title="Registrerings programmet";
                break;
        }
        modulaStage.setTitle(title);

        modulaStage.setScene(modulaScene);

        //Fryser mainview'et.
        modulaStage.initOwner(RegistrerProdukt.getScene().getWindow());
        modulaStage.initModality(Modality.WINDOW_MODAL);
        //Scene switch here
        modulaStage.show();
    }
}

class ProduktPopulator extends Task<List<Produkt>> {

    @Override
    protected List<Produkt> call() throws Exception {
        try{
            CSVLesSkriv csvls = new CSVLesSkriv();
            List<Produkt> produkts = csvls.lesCSV();
            Thread.sleep(500);
            return produkts;
        }
        catch (Exception ie){
                    return null;
        }
    }
}

