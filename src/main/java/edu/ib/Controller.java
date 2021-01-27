package edu.ib;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.text.Text;

public class Controller {
    private Game game;
    Button[] cardsButtons = new Button[52];
    Button[] colorButtons = new Button[4];

    public Button[] getColorButtons() {
        return colorButtons;
    }

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
    private Button btnDrawCard;

    @FXML
    private Button btnStart;

    @FXML
    private TextField editTxtFinishingPoints;

    public Game getGame() {
        return game;
    }

    public Button[] getCardsButtons() {
        return cardsButtons;
    }

    public ResourceBundle getResources() {
        return resources;
    }

    public URL getLocation() {
        return location;
    }

    public AnchorPane getCanvas() {
        return canvas;
    }

    public Text getTxtComputerScore() {
        return txtComputerScore;
    }

    public Text getTxtPlayerScore() {
        return txtPlayerScore;
    }

    public Text getTxtRound() {
        return txtRound;
    }

    public Button getBtnDrawCard() {
        return btnDrawCard;
    }

    public Button getBtnStart() {
        return btnStart;
    }

    public TextField getEditTxtFinishingPoints() {
        return editTxtFinishingPoints;
    }

    public Button getNewRoundButton() {
        return newRoundButton;
    }

    @FXML
    private Text txtPointsEndGame;

    @FXML
    private ImageView imageSuit;

    @FXML
    private Button newRoundButton;

    @FXML
    void newRound(ActionEvent event) {
        game.newRound();
        newRoundButton.setVisible(false);
    }
    @FXML
    void drawFromMainDeck(ActionEvent event) {
        if (game.isRunning() && game.getPlayerTurn() == 0) {
            try {
                game.getPlayers()[0].drawCard(game.getMainDeck().getCard(0));
            } catch(NullPointerException e){
                e.getStackTrace();
                game.restockMainDeck();
                game.getPlayers()[0].drawCard(game.getMainDeck().getCard(0));
            }
            game.getPlayers()[0].getHand().sort();
            game.getPlayers()[0].getHand().display();
        }
    }

    @FXML
    void start(ActionEvent event) {
        startNewGame();
    }

    public Text getTxtPointsEndGame() {
        return txtPointsEndGame;
    }

    public ImageView getImageSuit() {
        return imageSuit;
    }

    private void startNewGame() {
        try {
            game = new Game(this, Integer.parseInt(editTxtFinishingPoints.getText()));


            game.newRound();

            canvas.getChildren().add(game.getMainDeck());
            canvas.getChildren().add(game.getSecondDeck());

            game.getMainDeck().setLayoutX(431);
            game.getMainDeck().setLayoutY(298);
            game.getSecondDeck().setLayoutX(506);
            game.getSecondDeck().setLayoutY(298);

            game.getMainDeck().setVisibility(true);
            game.getSecondDeck().setVisibility(true);

            btnDrawCard.setVisible(true);
            btnDrawCard.setLayoutY(550);

            btnStart.setVisible(false);
            txtPointsEndGame.setVisible(false);
            editTxtFinishingPoints.setVisible(false);

            //jeszcze schować trzeba text
            for (int i = 0; i < colorButtons.length; i++) {
                colorButtons[i] = new Button();
            }
            for (int i = 0; i < colorButtons.length; i++) {
                Card.Suit[] suit = Card.Suit.values();
                Button button = new Button();
                button.setLayoutY(400);
                button.setLayoutX(400 + 50 * i);

                button.setMaxHeight(50);
                button.setMaxHeight(50);
                button.setMinWidth(50);
                button.setMinHeight(50);
                button.setVisible(false);
                try {
                    button.setBackground(new Background(new BackgroundImage(new Image("/graphics/" + suit[i].toString()+".png"), null, null, null, null)));
                } catch(Exception e) {
                    e.getStackTrace();
                    button.setBackground(new Background(new BackgroundImage(new Image("/graphics/blank.png"), null, null, null, null)));
                }
                button.setId(suit[i].toString());

                button.setOnAction((event) -> {
                    switch (button.getId()) {
                        case "Hearts" -> game.setCardColor(Game.CardColor.Hearts);
                        case "Spades" -> game.setCardColor(Game.CardColor.Spades);
                        case "Diamonds" -> game.setCardColor(Game.CardColor.Diamonds);
                        case "Clubs" -> game.setCardColor(Game.CardColor.Clubs);
                    }
                    game.setPlayerTurn(1);
                    game.getPlayers()[1].playCard(null);
                    hideColorButtons();
                });
                colorButtons[i] = button;
                canvas.getChildren().add(colorButtons[i]);

            }

        } catch (Exception e) {
            e.getStackTrace();
        }

    }
    void hideColorButtons(){
        for (Button colorButton : colorButtons) {
            colorButton.setVisible(false);
        }
    }
    void showColorButtons(){
        for (Button colorButton : colorButtons) {
            colorButton.setVisible(true);
        }
    }

    @FXML
    void initialize() {
        assert canvas != null : "fx:id=\"canvas\" was not injected: check your FXML file 'crazy_eights.fxml'.";
        assert txtComputerScore != null : "fx:id=\"txtComputerScore\" was not injected: check your FXML file 'crazy_eights.fxml'.";
        assert txtPlayerScore != null : "fx:id=\"txtPlayerScore\" was not injected: check your FXML file 'crazy_eights.fxml'.";
        assert txtRound != null : "fx:id=\"txtRound\" was not injected: check your FXML file 'crazy_eights.fxml'.";
        assert btnDrawCard != null : "fx:id=\"btnDrawCard\" was not injected: check your FXML file 'crazy_eights.fxml'.";
        assert btnStart != null : "fx:id=\"btnStart\" was not injected: check your FXML file 'crazy_eights.fxml'.";
        assert editTxtFinishingPoints != null : "fx:id=\"editTxtFinishingPoints\" was not injected: check your FXML file 'crazy_eights.fxml'.";
        assert txtPointsEndGame != null : "fx:id=\"txtPointsEndGame\" was not injected: check your FXML file 'crazy_eights.fxml'.";
        assert imageSuit != null : "fx:id=\"imageSuit\" was not injected: check your FXML file 'crazy_eights.fxml'.";
        assert newRoundButton != null : "fx:id=\"newRoundButton\" was not injected: check your FXML file 'crazy_eights.fxml'.";
    }

}
