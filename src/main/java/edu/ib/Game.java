package edu.ib;

import edu.ib.deck.Deck;
import edu.ib.player.AIPlayer;
import edu.ib.player.HumanPlayer;
import edu.ib.player.Player;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Random;

/**
 * Game class definition
 */
public class Game {
    private final Controller controller;
    private boolean running = false;
    private final Deck mainDeck;
    private final Deck secondDeck;
    private int round = 0;
    private final Player[] players = new Player[2];
    private int playerTurn;
    private int valueToEnd;
    private boolean gameFinished = false;
    private CardColor cardColor = CardColor.Normal;

    /**
     * Class describing color state of game
     */
    public enum CardColor {
        Hearts,
        Spades,
        Diamonds,
        Clubs,
        Normal
    }

    /**
     * Game constructor
     *
     * @param controller Controller
     * @param valueToEnd value of Points to end the Game
     */
    public Game(Controller controller, int valueToEnd) {
        this.valueToEnd = valueToEnd;
        this.controller = controller;
        mainDeck = new Deck(this);
        //mainDeck.createFullDeck();

        secondDeck = new Deck(this);
        mainDeck.setImage(new Image("/graphics/back.png"));
        players[0] = new HumanPlayer(this, "Player");
        players[1] = new AIPlayer(this, "Computer");
    }

    /**
     * Method to check if Game if finished
     *
     * @return gameFinished value as boolean
     */
    public boolean isGameFinished() {
        return gameFinished;
    }

    /**
     * Method to check if the Game is running
     *
     * @return value of Running as boolean
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Method to create new round
     */
    public void newRound() {
        secondDeck.emptyDeck();
        mainDeck.emptyDeck();
        players[0].getHand().emptyDeck();
        players[1].getHand().emptyDeck();
        mainDeck.createFullDeck();
        mainDeck.randomize();
        round++;
        Random random = new Random();
        playerTurn = random.nextInt(2);
        //playerTurn = 0;
        for (int i = 0; i < 7; i++) {
            players[0].drawCard(mainDeck.getCard(0));
            players[1].drawCard(mainDeck.getCard(0));
        }
        players[0].getHand().sort();
        players[0].getHand().display();
        players[1].getHand().display();

        mainDeck.setImage(Card.BACK_IMAGE);
        mainDeck.moveCardToDeck(mainDeck.getCard(0), secondDeck, 0);
        secondDeck.setImage(secondDeck.getCard(0).frontImage);
        running = true;
        if (playerTurn == 1) players[1].playCard(null);
        controller.getBtnDrawCard().setVisible(true);
        controller.setRounds(round);
    }

    /**
     * Method to restock the mainDeck of the Game
     */
    public void restockMainDeck() {
        for (int i = 1; i < secondDeck.size(); i++) {
            secondDeck.moveCardToDeck(secondDeck.getCard(1), mainDeck);
        }
        mainDeck.randomize();
    }

    /**
     * Method to finish the round
     *
     * @param player Player who won the round
     */
    public void finishRound(Player player) {
        running=false;
        cardColor = CardColor.Normal;
        controller.getImageSuit().setVisible(false);
        ArrayList<Player> pl=new ArrayList<>();

        for(int i=0;i<players.length;i++){
            if (!players[i].equals(player)){
                pl.add(players[i]);
            }
        }
        for(int j=0;j<pl.size();j++) {
            for (int i = 0; i < pl.get(j).getHand().size(); i++) {
                player.addPoints(pl.get(j).getHand().getCard(i).getPointsValue());
            }
        }

        controller.setComputerScore(players[1].getPoints());
        controller.setPlayerScore(players[0].getPoints());

        mainDeck.emptyDeck();
        secondDeck.emptyDeck();
        for (Player value : players) {
            value.getHand().emptyDeck();
            value.getHand().display();
        }
        if (player.getPoints() >= valueToEnd) {
            finishGame();
        } else {
            controller.getBtnDrawCard().setVisible(false);
            controller.getNewRoundButton().setVisible(true);
        }
    }

    /**
     * Method to finish the Game
     */
    public void finishGame() {
        controller.finishGame();
    }

    /**
     * Method to display the Buttons with Colours
     */
    public void displayColorButtons() {
        controller.showColorButtons();
    }


    /**
     * Method to set value of gameFinished
     *
     * @param gameFinished new value of gameFinished
     */
    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

    /**
     * Method to set the CardColor
     *
     * @param cardColor new value of CardColor
     */
    public void setCardColor(CardColor cardColor) {
        this.cardColor = cardColor;
    }

    /**
     * Method to set Running of the Game
     *
     * @param running new value of Running of the Game
     */
    public void setRunning(boolean running) {
        this.running = running;
    }

    /**
     * Method to set the Round of the Game
     *
     * @param round new value of the Round
     */
    public void setRound(int round) {
        this.round = round;
    }

    /**
     * Method to set the playerTurn
     *
     * @param playerTurn new playerTurn value
     */
    public void setPlayerTurn(int playerTurn) {
        this.playerTurn = playerTurn;
    }

    /**
     * Method to get the mainDeck of the Game
     *
     * @return mainDeck of the Game
     */
    public Deck getMainDeck() {
        return mainDeck;
    }

    /**
     * Method to get the secondDeck of the game
     *
     * @return second Deck of the Game
     */
    public Deck getSecondDeck() {
        return secondDeck;
    }

    /**
     * Method to get the ROund of the Game
     *
     * @return round of the Game as int
     */
    public int getRound() {
        return round;
    }

    /**
     * Method to get Players taking part in the game
     *
     * @return Players taking part in the Game as Array of Players
     */
    public Player[] getPlayers() {
        return players;
    }

    /**
     * Method to get the playerTurn
     *
     * @return playerTurn as int
     */
    public int getPlayerTurn() {
        return playerTurn;
    }

    /**
     * Method to get the Controller of the Game
     *
     * @return Controller of the Game
     */
    public Controller getController() {
        return controller;
    }

    /**
     * Method to get the CardColor
     *
     * @return CardColor
     */
    public CardColor getCardColor() {
        return cardColor;
    }
}
