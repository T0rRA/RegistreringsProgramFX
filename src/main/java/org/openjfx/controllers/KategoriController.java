package org.openjfx.controllers;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

public class KategoriController implements Initializable {
    @FXML
    private TableView EntireTable;
    @FXML
    private TableColumn<Produkt, ProduktKategori> kategoriTable;
    @FXML private TableColumn<Produkt, String> beskrivelseTable;
    @FXML
    private Button BackToProduct;
    @FXML private Label errMessage1;


    @FXML
    private void OpenRegistrerKategori(){
        try {
            helpOpenModula("RegistrerKategori");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            errMessage1.setText("Kan ikke åpne");
        }
    }
    @FXML
    private void OpenRegistrerProdukt(){
        try {
            helpOpenModula("RegistrerProdukt");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            errMessage1.setText("Kan ikke åpne");
        }
    }
    @FXML
    private void EditKategori(){
        try{

        } catch (Exception e){
            errMessage1.setText("Kan ikke redigere");
        }
    }
    @FXML private void OpenCloseModula(){
        try {
            helpOpenModula("CloseProgram");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            errMessage1.setText("Kan ikke åpne");
        }
    }
    @FXML private void BackToProduct(){
        try {
            helpOpenModula("MainView");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


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
            case "MainView":
                title="Registrerings programmet";
                break;
        }
        modulaStage.setTitle(title);

        modulaStage.setScene(modulaScene);

        //Fryser mainview'et.
        modulaStage.initOwner(EntireTable.getScene().getWindow());
        modulaStage.initModality(Modality.WINDOW_MODAL);
        //Scene switch here
        modulaStage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    class KategoriPopulator extends Service<List<Produkt>> {

        @Override
        protected Task<List<Produkt>> createTask() {
            return new Task<List<Produkt>>(){

                @Override
                protected List<Produkt> call() throws Exception {
                    try{
                        BinaryStrategy bs = new BinaryStrategy();
                        bs.lastInn();

                    }
                    catch (Exception ie){

                    }
                    return null;
                }
            };
        }
    }
}
