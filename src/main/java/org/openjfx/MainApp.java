package org.openjfx;

import javafx.application.Application;
import static javafx.application.Application.launch;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.openjfx.controllers.MainViewController;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MainView.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setTitle("Registrerings Programmet");

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                try {
                    Parent root = null;
                    //Path til resources, måtte gjøre på denne måten pga pakke strukturen.
                    URL url = new File("src/main/resources/org/openjfx/CloseProgram.fxml").toURI().toURL();

                    //Required try catch for load.
                    try {
                        FXMLLoader loader = new FXMLLoader();
                        root = loader.load(url);
                    } catch(IOException e) {
                        e.printStackTrace();
                        //errorMessage.setText("Can't open");
                    }

                    Scene exitScene = new Scene(root);
                    Stage exitModal = new Stage();

                    //valg av tittel basert på hvilken knapp man trykker på.
                    exitModal.setTitle("Er du sikker på at du vil avslutte?");

                    exitModal.setScene(exitScene);

                    //Fryser mainview'et.
                    exitModal.initOwner(scene.getWindow());
                    exitModal.initModality(Modality.WINDOW_MODAL);
                    //Scene switch here
                    exitModal.showAndWait();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        });

        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
