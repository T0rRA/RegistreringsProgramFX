package org.openjfx.controllers;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.paint.Color;
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
    @FXML private TableColumn<Produkt, String> kategoriTable;
    @FXML private TableColumn<Produkt, String> beskrivelseTable;
    @FXML private Button RegistrerProdukt;
    @FXML private Label errMessage1;
    @FXML private Label Laster;


    @FXML
    private void OpenRegistrerProdukt(ActionEvent event) throws MalformedURLException {
        try {
            helpOpenModula("RegistrerProdukt");
        } catch (MalformedURLException e) {
            errMessage1.setTextFill(Color.web("#e40d0d"));
            errMessage1.setText("Klarte ikke åpne");
            e.printStackTrace();
        }
    }

    @FXML
    private void OpenRegistrerKategori(ActionEvent event) throws MalformedURLException {
        try {
            helpOpenModula("RegistrerKategori");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            errMessage1.setTextFill(Color.web("#e40d0d"));
            errMessage1.setText("Kan ikke åpne");
        }
    }

    @FXML private void OpenKategoriVisning(ActionEvent event){
        try {
            helpOpenModula("KategoriView");
        }catch (MalformedURLException e){
            e.printStackTrace();
            errMessage1.setTextFill(Color.web("#e40d0d"));
            errMessage1.setText("Kan ikke åpne");
        }
    }

    @FXML private void OpenCloseModula(ActionEvent event) {
        try {
            helpOpenModula("CloseProgram");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            errMessage1.setTextFill(Color.web("#e40d0d"));
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
            errMessage1.setTextFill(Color.web("#e40d0d"));
            errMessage1.setText("Klarer ikke redigere");
        }
    }

    public void initialize() {
        // TODO
    }

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        Service<List<Produkt>> service = new Service<List<Produkt>>() {
            @Override
            protected Task<List<Produkt>> createTask() {
                return new Task<List<Produkt>>() {
                    @Override
                    protected List<Produkt> call() throws Exception {
                        try{
                            produktTable.setCellValueFactory(new PropertyValueFactory<Produkt, String>("produktNavn"));
                            produktTable.setCellFactory(TextFieldTableCell.forTableColumn());
                            beskrivelseTable.setCellValueFactory(new PropertyValueFactory<Produkt, String>("omProdukt"));
                            beskrivelseTable.setCellFactory(TextFieldTableCell.forTableColumn());
                            kategoriTable.setCellValueFactory(new PropertyValueFactory<Produkt, String>("produktKategori"));
                            kategoriTable.setCellFactory(TextFieldTableCell.forTableColumn());
                            CSVLesSkriv csvls = new CSVLesSkriv();
                            csvls.lesCSV();

                            Thread.sleep(5000);
                            EntireTable.getItems().addAll(csvls);
                            Thread.sleep(1000);
                            return csvls;

                        }
                        catch (Exception ie){
                            ie.printStackTrace();
                            errMessage1.setTextFill(Color.web("#e40d0d"));
                            errMessage1.setText("Klarte ikke laste inn produkter");
                            return null;
                        }
                    }
                };
            }
        };
        Laster.setText("Laster nå inn...");
        service.setOnSucceeded((WorkerStateEvent event) -> {
            Laster.setText("");
        });
        configs();
        service.start();
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
    private void configs(){
        EntireTable.setEditable(true);
        EntireTable.getSelectionModel().cellSelectionEnabledProperty().set(true);
    }
}


