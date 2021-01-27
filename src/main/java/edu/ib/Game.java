package edu.ib;

import edu.ib.deck.Deck;
import edu.ib.player.AIPlayer;
import edu.ib.player.HumanPlayer;
import edu.ib.player.Player;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import java.util.Random;

public class Game {
    private final Controller controller;
    private boolean running=false;
    private final Deck mainDeck;
    private final Deck secondDeck;
    private int round=0;
    private final Player[] players=new Player[2];
    private int playerTurn;
    private int valueToEnd;

    public boolean isGameFinished() {
        return gameFinished;
    }

    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

    public CardColor getCardColor() {
        return cardColor;
    }

    public void setCardColor(CardColor cardColor) {
        this.cardColor = cardColor;
    }

    private boolean gameFinished=false;
    private CardColor cardColor=CardColor.Normal;
    public enum CardColor{
        Hearts,
        Clubs,
        Diamonds,
        Spades,
        Normal
    }

    /**
     * Game constructor
     * @param controller Controller
     */
    public Game(Controller controller, int valueToEnd){
        this.valueToEnd=valueToEnd;
        this.controller=controller;
        mainDeck=new Deck(this);
        //mainDeck.createFullDeck();

        secondDeck=new Deck(this);
        mainDeck.setImage(new Image("/graphics/back.png"));
        players[0]=new HumanPlayer(this,"Player");
        players[1]=new AIPlayer(this,"Computer");
    }

    /**
     * Method creating new round
     */
    public void newRound(){
        secondDeck.emptyDeck();
        mainDeck.emptyDeck();
        players[0].getHand().emptyDeck();
        players[1].getHand().emptyDeck();
        mainDeck.createFullDeck();
        mainDeck.randomize();
        round++;
        Random random=new Random();
        //playerTurn= random.nextInt(2)+1;
        playerTurn=0;
        for(int i=0;i<7;i++){
            players[0].drawCard(mainDeck.getCard(0));
            players[1].drawCard(mainDeck.getCard(0));
        }
        players[0].getHand().sort();
        players[0].getHand().display();
        players[1].getHand().display();

        mainDeck.setImage(Card.BACK_IMAGE);
        mainDeck.moveCardToDeck(mainDeck.getCard(0),secondDeck,0);
        secondDeck.setImage(secondDeck.getCard(0).FRONT_IMAGE);
        running=true;
        if(playerTurn==1) players[1].playCard(null);
    }

    /**
     *
     * @return Controller
     */
    public Controller getController() {
        return controller;
    }


    public void restockMainDeck(){
        for(int i=1;i<secondDeck.size();i++){
            secondDeck.moveCardToDeck(secondDeck.getCard(i),mainDeck);
        }
        mainDeck.randomize();
    }

    public void finishRound(Player player){
        Player pl;
        Player pw;
        if(players[0].equals(player)){
            pw=players[0];
            pl=players[1];
        } else {
            pw=players[1];
            pl=players[0];
        }
        int p=0;
        for(int i=0;i<pl.getHand().size();i++){
            player.addPoints(pl.getHand().getCard(i).getPointsValue());
        }
        if(pw.getPoints()>valueToEnd){
            finishGame();
        }
        mainDeck.emptyDeck();
        controller.getNewRoundButton().setVisible(true);
    }
    public void finishGame(){

    }

    /**
     *
     * @return Game running state
     */
    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public Deck getMainDeck() {
        return mainDeck;
    }

    public Deck getSecondDeck() {
        return secondDeck;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public Player[] getPlayers() {
        return players;
    }

    public int getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(int playerTurn) {
        this.playerTurn = playerTurn;
    }
    public void displayColorButtons(){
        Button [] b=getController().getColorButtons();
        for(int i=0;i<b.length;i++){
            b[i].setVisible(true);
        }
    }
}
