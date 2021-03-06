package org.openjfx.controllers;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.openjfx.Produkt;
import org.openjfx.ProduktKategori;

import javax.security.auth.callback.Callback;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class KategoriController implements Initializable {
    @FXML
    private TableView EntireTable;
    @FXML private TableColumn<ProduktKategori,String> kategoriTable;
    @FXML private TableColumn<ProduktKategori,String> beskrivelseTable;
    @FXML private TableColumn<ProduktKategori, Boolean> FunctionColumn;
    @FXML private Button BackToProduct;
    @FXML private Label errMessage1;
    @FXML private Label Laster;

    @FXML
    private void OpenRegistrerKategori(){
        try {
            helpOpenModula("RegistrerKategori");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            errMessage1.setTextFill(Color.web("#e40d0d"));
            errMessage1.setText("Kan ikke åpne");
        }
    }
    @FXML
    private void OpenRegistrerProdukt(){
        try {
            helpOpenModula("RegistrerProdukt");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            errMessage1.setTextFill(Color.web("#e40d0d"));
            errMessage1.setText("Kan ikke åpne");
        }
    }
    @FXML
    private void EditKategori(TableColumn.CellEditEvent<?,?> event){
        try{
            Object oldVal = event.getOldValue();
            Object newVal = event.getNewValue();
            TablePosition<?,?> pos = event.getTablePosition();
            System.out.println("THE COLUMN IS "+pos+" and val is: " + newVal.toString());
        } catch (Exception e){
            errMessage1.setTextFill(Color.web("#e40d0d"));
            errMessage1.setText("Kan ikke redigere");
        }
    }
    @FXML private void OpenCloseModula(){
        try {
            helpOpenModula("CloseProgram");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            errMessage1.setTextFill(Color.web("#e40d0d"));
            errMessage1.setText("Kan ikke åpne");
        }
    }
    @FXML private void BackToProduct(){
        Stage stage = (Stage) EntireTable.getScene().getWindow();
        stage.close();
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
        Service<List<ProduktKategori>> service = new Service<List<ProduktKategori>>() {
            @Override
            protected Task<List<ProduktKategori>> createTask() {
                return new Task<List<ProduktKategori>>() {
                    @Override
                    protected List<ProduktKategori> call() throws Exception {
                        try{
                            beskrivelseTable.setCellValueFactory(new PropertyValueFactory<ProduktKategori, String>("omKategori"));
                            beskrivelseTable.setCellFactory(TextFieldTableCell.forTableColumn());
                            beskrivelseTable.setOnEditCommit(
                                    (TableColumn.CellEditEvent<ProduktKategori, String> pt) -> {
                                        ProduktKategori olDprod = ((ProduktKategori) pt.getTableView().getItems().get(
                                                pt.getTablePosition().getRow())).setOmKategori(pt.getOldValue());
                                        ProduktKategori produkt = new ProduktKategori(olDprod.getKategoriNavn(), pt.getNewValue());
                                        BinaryLesSkriv csv = new BinaryLesSkriv();
                                        csv.lastInn();
                                        try {
                                            csv.fjern(olDprod);
                                        } catch (Exception e){
                                            e.printStackTrace();
                                        }
                                        csv.leggTil(produkt);
                                    }
                            );
                            kategoriTable.setCellValueFactory(new PropertyValueFactory<ProduktKategori, String>("kategoriNavn"));
                            kategoriTable.setCellFactory(TextFieldTableCell.forTableColumn());
                            kategoriTable.setOnEditCommit(
                                    (TableColumn.CellEditEvent<ProduktKategori, String> pt) -> {
                                        ProduktKategori olDprod = ((ProduktKategori) pt.getTableView().getItems().get(
                                                pt.getTablePosition().getRow())).setKategoriNavn(pt.getOldValue());
                                        ProduktKategori produkt = new ProduktKategori(pt.getNewValue(), olDprod.getOmKategori());

                                        BinaryLesSkriv csv = new BinaryLesSkriv();
                                        csv.lastInn();
                                        try {
                                            csv.fjern(olDprod);
                                        } catch (Exception e){
                                            e.printStackTrace();
                                        }
                                        csv.leggTil(produkt);
                                    }
                            );
                            BinaryLesSkriv bls = new BinaryLesSkriv();
                            bls.lastInn();
                            Thread.sleep(5000);
                            EntireTable.getItems().setAll(bls);

                            return bls;
                        }
                        catch (Exception ie){
                            ie.printStackTrace();
                            errMessage1.setText("Klarer ikke lese kategorier");
                            return null;
                        }
                    }
                };
            }
        };
        Laster.setText("Henter kategorier...");
        service.start();
        service.setOnSucceeded((WorkerStateEvent event) -> {
            Laster.setText("");
            EntireTable.getColumns().set(2,FunctionColumn);
        });
        configs();
    }

    private void configs(){
        EntireTable.setEditable(true);
        EntireTable.getSelectionModel().cellSelectionEnabledProperty().set(true);
        FunctionColumn.setSortable(false);


        FunctionColumn.setCellValueFactory(new javafx.util.Callback<TableColumn.CellDataFeatures<ProduktKategori, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<ProduktKategori, Boolean> pkFeatures) {
                return new SimpleBooleanProperty(pkFeatures.getValue() != null);
            }
        });

        FunctionColumn.setCellFactory(new javafx.util.Callback<TableColumn<ProduktKategori, Boolean>, TableCell<ProduktKategori, Boolean>>() {
            @Override
            public TableCell<ProduktKategori,Boolean> call(TableColumn<ProduktKategori, Boolean> produktKategoriBooleanCellDataFeatures) {
                return new DeleteButton(EntireTable);
            }
        });



    }

    private class DeleteButton extends TableCell<ProduktKategori, Boolean>{
        final Button deleteButton = new Button("Delete");
        final StackPane paddedButton = new StackPane();

        DeleteButton(TableView t){
            paddedButton.setPadding(new Insets(3));
            paddedButton.getChildren().add(deleteButton);
            deleteButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    BinaryLesSkriv bls = new BinaryLesSkriv();
                    int rad = t.getSelectionModel().getSelectedIndex();
                    Object selected  = t.getItems().get(rad);
                    System.out.println(selected.toString());
                }
            });
        }
    }

}


