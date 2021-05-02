package org.openjfx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.openjfx.Produkt;

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
    private void OpenRegistrerProdukt(ActionEvent event) {

    }

    @FXML
    private void OpenRegistrerKategori(ActionEvent event) {

    }

    @FXML private void OpenCloseModula(ActionEvent event) {

    }

    public void initialize() {
        // TODO
    }

    @Override
    public void initialize(URL url, ResourceBundle resources) {

    }
}
