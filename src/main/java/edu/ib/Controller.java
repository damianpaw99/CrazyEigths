package edu.ib;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class Controller {
    private Game game;
    Button[] cardsButtons = new Button[52];

        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private AnchorPane canvas;

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
            assert canvas != null : "fx:id=\"canvas\" was not injected: check your FXML file 'crazy_eights.fxml'.";
            assert txtComputerScore != null : "fx:id=\"txtComputerScore\" was not injected: check your FXML file 'crazy_eights.fxml'.";
            assert txtPlayerScore != null : "fx:id=\"txtPlayerScore\" was not injected: check your FXML file 'crazy_eights.fxml'.";
            assert txtRound != null : "fx:id=\"txtRound\" was not injected: check your FXML file 'crazy_eights.fxml'.";
            assert cardToTake != null : "fx:id=\"cardToTake\" was not injected: check your FXML file 'crazy_eights.fxml'.";
            assert playedCard != null : "fx:id=\"playedCard\" was not injected: check your FXML file 'crazy_eights.fxml'.";
            assert btnTakeCard != null : "fx:id=\"btnTakeCard\" was not injected: check your FXML file 'crazy_eights.fxml'.";
            /*
            Button test = new Button();
            test.setVisible(true);
            test.setText("Test");
            test.setLayoutX(50);
            test.setLayoutY(50);
            canvas.getChildren().add(test);
            */
            game=new Game(this);
            //game.newRound();
            for(int i =0;i<cardsButtons.length;i++){
                cardsButtons[i]=game.getMainDeck().getCard(i);
                canvas.getChildren().add(cardsButtons[i]);
            }
            game.newRound();
        }

}
