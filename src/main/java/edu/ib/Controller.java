package edu.ib;

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

/**
 * Controller class definition
 */
public class Controller {
    private Game game;
    Button[] cardsButtons = new Button[52];
    Button[] colorButtons = new Button[4];

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

    @FXML
    private Text txtPointsEndGame;

    @FXML
    private ImageView imageSuit;

    @FXML
    private Button newRoundButton;

    @FXML
    void start(ActionEvent event) {
        startNewGame();
        txtComputerScore.setText("Computer's score: 0");
        txtPlayerScore.setText("Player's score: 0");
        txtRound.setText("Round: 1");
    }

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
            } catch (IndexOutOfBoundsException e) {
                e.getStackTrace();
                game.restockMainDeck();
                game.getPlayers()[0].drawCard(game.getMainDeck().getCard(0));
            }
            game.getPlayers()[0].getHand().sort();
            game.getPlayers()[0].getHand().display();
        }
    }

    /**
     * Method to start a new Game
     */
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

            for (int i = 0; i < colorButtons.length; i++) {
                colorButtons[i] = new Button();
            }
            for (int i = 0; i < colorButtons.length; i++) {
                Card.Suit[] suit = Card.Suit.values();
                Button button = new Button();
                button.setLayoutY(400);
                button.setLayoutX(400 + 51 * i);

                button.setMaxHeight(50);
                button.setMaxHeight(50);
                button.setMinWidth(50);
                button.setMinHeight(50);
                button.setVisible(false);
                try {
                    button.setBackground(new Background(new BackgroundImage(new Image("/graphics/" + suit[i].toString() + ".png"), null, null, null, null)));
                } catch (Exception e) {
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

    /**
     * Method to hide the Buttons with Colours
     */
    void hideColorButtons() {
        for (Button colorButton : colorButtons) {
            colorButton.setVisible(false);
        }
    }

    /**
     * Method to show the Buttons with Colours
     */
    void showColorButtons() {
        for (Button colorButton : colorButtons) {
            colorButton.setVisible(true);
        }
    }

    /**
     * Method to set the computers score
     */
    public void setComputerScore(int points){
        txtComputerScore.setText("Computer's score: "+points);
    }

    /**
     * Method to set the players score
     */
    public void setPlayerScore(int points){
        txtPlayerScore.setText("Player's score: "+points);
    }

    /**
     * Method to set the number of the round
     */
    public void setRounds(int number){
        txtRound.setText("Round: "+number);
    }

    /**
     * Method to get the color buttons
     */
    public Button[] getColorButtons() {
        return colorButtons;
    }

    /**
     * Method to get the points at the end of the game
     */
    public Text getTxtPointsEndGame() {
        return txtPointsEndGame;
    }

    public ImageView getImageSuit() {
        return imageSuit;
    }

    /**
     * Method to end the game
     */
    public void finishGame(){
        btnStart.setVisible(true);
        editTxtFinishingPoints.setVisible(true);
        txtPointsEndGame.setVisible(true);
        btnDrawCard.setVisible(false);
        newRoundButton.setVisible(false);
    }

    /**
     * Method to get the Game
     */
    public Game getGame() {
        return game;
    }

    /**
     * Method to get cardsButtons
     */
    public Button[] getCardsButtons() {
        return cardsButtons;
    }

    /**
     * Method to get resources
     */
    public ResourceBundle getResources() {
        return resources;
    }

    /**
     * Method to get location
     */
    public URL getLocation() {
        return location;
    }

    /**
     * Method to get canvas
     */
    public AnchorPane getCanvas() {
        return canvas;
    }

    /**
     * Method to get the computers score
     */
    public Text getTxtComputerScore() {
        return txtComputerScore;
    }

    /**
     * Method to get the players score
     */
    public Text getTxtPlayerScore() {
        return txtPlayerScore;
    }

    /**
     * Method to get the txtRound
     */
    public Text getTxtRound() {
        return txtRound;
    }

    /**
     * Method to get the btnDrawCard
     */
    public Button getBtnDrawCard() {
        return btnDrawCard;
    }

    /**
     * Method to get the btnStart
     */
    public Button getBtnStart() {
        return btnStart;
    }

    /**
     * Method to get the editTxtFinishingPoints
     */
    public TextField getEditTxtFinishingPoints() {
        return editTxtFinishingPoints;
    }

    /**
     * Method to get the newRoundButton
     */
    public Button getNewRoundButton() {
        return newRoundButton;
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
