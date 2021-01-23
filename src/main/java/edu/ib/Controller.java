package edu.ib;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class Controller {
    private Game game;
    Button[] cardsButtons = new Button[52];

        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private Text txtComputerScore;

        @FXML
        private Text txtPlayerScore;

        @FXML
        private Text txtRound;

        @FXML
        private ImageView cardToTake;

        @FXML
        private ImageView playedCard;

        @FXML
        private Button btnTakeCard;

        @FXML
        void initialize() {
            assert txtComputerScore != null : "fx:id=\"txtComputerScore\" was not injected: check your FXML file 'crazy_eights.fxml'.";
            assert txtPlayerScore != null : "fx:id=\"txtPlayerScore\" was not injected: check your FXML file 'crazy_eights.fxml'.";
            assert txtRound != null : "fx:id=\"txtRound\" was not injected: check your FXML file 'crazy_eights.fxml'.";
            assert cardToTake != null : "fx:id=\"cardToTake\" was not injected: check your FXML file 'crazy_eights.fxml'.";
            assert playedCard != null : "fx:id=\"playedCard\" was not injected: check your FXML file 'crazy_eights.fxml'.";
            assert btnTakeCard != null : "fx:id=\"btnTakeCard\" was not injected: check your FXML file 'crazy_eights.fxml'.";

        }

}
