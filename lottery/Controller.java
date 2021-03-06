package main;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public static final String title = "Losowanie numerka";
    public static final String version = "1.05";


    @FXML // main.fxml
    Button buttonConfig;

    @FXML // main.fxml
    Button buttonRandom;

    @FXML // main.fxml
    Label labelNumber;


    // stage odpowiedzialny za widok config
    public static Stage configStage = new Stage();
    // interpetacja pliku fxml'owa dla widoku config.fxml
    public Parent configRoot;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    /*********************************************************************************************************************
    ****  WIDOK OKNA CONFIG.FXML / main.ConfigController
    * Ładowany jest widok dla widoku 'config'.
    * W następnym wywołaniach widoku musi nastąpić wywołanie
    * metody:  'configStage.show()'.
    * Widok jest chowany za pomocą 'closeConfigStage()' w
    * odwołaniu polimorfizycznym, przez 'javafx.scene.Stage.close()'.
    */
        try {
            configRoot = FXMLLoader.load(getClass().getResource("config.fxml"));
            configStage.setTitle(ConfigController.title);
            configStage.setScene(new Scene(configRoot, 350, 206));
            configStage.isAlwaysOnTop();
        } catch (IOException e) {
            runMessageError("ERROR: nie można załadować wyglądu. ");
            e.printStackTrace();
        }


    /*********************************************************************************************************************
    ****  WIDOK OKNA MAIN.FXML
    * Handlery dla pliku main.fxml / main.Controller
    * Podstawowe okno programu - zgodnie z modelem MVC 2.0
    */
        buttonConfig.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            // włącza okno config
            public void handle(MouseEvent event) {
                if (!configStage.isShowing()) configStage.show();
            }
        });

        buttonRandom.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            // losuje liczbę z podanego zakresu
            public void handle(MouseEvent event) {
                if (Lottery.checkRange()) {
                    int generatedNumber = Lottery.returnRandomNumber();
                    if (generatedNumber != -1) labelNumber.setText(new Integer(generatedNumber).toString());
                } else runMessageError("Wprowadzono zły przedział liczbowy. Zmień konfigurację.");
            }
        });
    }


    // zamyka okno config
    public static void closeConfigStage() {
        configStage.close();
    }


    // sprawdza poprawność wpisanej przez użytkownika liczby
    public static boolean checkNumberFromTextField(String numberInString) {
        try {
            int number = Integer.parseInt(numberInString);
            return number >= Lottery.minNumber && number <= Lottery.maxNumber ? true : false;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }


    // różne message
    public static void runMessageInfo(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Lottery numerka");
        alert.setHeaderText("");
        alert.setContentText(text);
        alert.showAndWait();
    }
    public static void runMessageWarn(String text) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Lottery numerka");
        alert.setHeaderText("");
        alert.setContentText(text);
        alert.showAndWait();
    }
    public static void runMessageError(String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Lottery numerka");
        alert.setHeaderText("");
        alert.setContentText(text);
        alert.showAndWait();
    }
}
