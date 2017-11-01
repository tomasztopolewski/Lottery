package main;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class ConfigController implements Initializable {

    public static final String title = "Konfiguracja losowania"; // ujednolicenie kodowanie wielostagowego


    @FXML // config.fxml
    Button buttonSave;

    @FXML // config.fxml
    Button buttonCancel;

    @FXML // config.fxml
    TextField textFieldFirstNumber;

    @FXML // config.fxml
    TextField textFieldEndNumber;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*********************************************************************************************************************
         *****  CONFIG.FXML
         * Handlery dla pliku config.fxml
         * Dodatkowe okno programu - konfiguracyjne
         */
        buttonSave.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int firstNumber = -1, endNumber = -1;

                if (Controller.checkNumberFromTextField(textFieldFirstNumber.getText()) && Controller.checkNumberFromTextField(textFieldEndNumber.getText())) {
                    firstNumber = Integer.parseInt(textFieldFirstNumber.getText());
                    endNumber = Integer.parseInt(textFieldEndNumber.getText());

                    if (Lottery.checkRange(firstNumber, endNumber)) {
                        Lottery.setFirstNumber(firstNumber);
                        Lottery.setEndNumber(endNumber);

                        if (Lottery.getFirstNumber() == firstNumber && Lottery.getEndNumber() == endNumber) {
                            Controller.runMessageInfo("Przedział liczbowy został poprawnie zapisany.");
                            Controller.closeConfigStage();
                        }

                    } else {
                        clearTextFields();
                        Controller.runMessageWarn("Wprowadzony przedział liczbowy został źle zapisany.");
                    }
                } else {
                    //System.out.println("Jestem tu.");
                    clearTextFields();
                    Controller.runMessageWarn("Wprowadzony przedział liczbowy został źle zapisany.");
                }
            }
        });

        buttonCancel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Controller.closeConfigStage();
            }
        });
    }


    // czyści pola, w które użytkownik wpisuje przedział
    public void clearTextFields() {
        textFieldFirstNumber.setText("");
        textFieldEndNumber.setText("");
    }


}

// Tomasz Topolewski 2017
