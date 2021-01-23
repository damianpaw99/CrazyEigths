package edu.ib;

import java.util.Random;

public class Game {
    private final Controller controller;
    private boolean running=false;
    private final Deck mainDeck;
    private final Deck secondDeck;
    private int round=0;
    private final Player [] players=new Player[2];
    private int playerTurn;

    /**
     * Game constructor
     * @param controller Controller
     */
    public Game(Controller controller){
        this.controller=controller;
        mainDeck=new Deck(this);
        secondDeck=new Deck(this);
        players[0]=new HumanPlayer(this,"");
        players[1]=new AIPlayer(this,"Computer");
    }

    /**
     * Method creating new round
     */
    public void newRound(){
        mainDeck.createFullDeck();
        mainDeck.randomize();
        secondDeck.emptyDeck();
        round++;
        Random random=new Random();
        playerTurn= random.nextInt(2)+1;
        for(int i=0;i<7;i++){
            players[0].drawCard(mainDeck.getCard(0),secondDeck);
            players[1].drawCard(mainDeck.getCard(0),secondDeck);
        }
        players[0].getHand().sort();
        players[0].getHand().display();
        running=true;
        if(playerTurn==1)
            players[1].playCard();
    }

    /**
     *
     * @return Controller
     */
    public Controller getController() {
        return controller;
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
}
