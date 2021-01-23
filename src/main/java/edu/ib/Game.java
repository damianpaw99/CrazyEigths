package edu.ib;

public class Game {
    private final Controller controller;
    private boolean running=false;
    private final Deck mainDeck;
    private final Deck secondDeck;
    private int round=0;

    public Game(Controller controller){
        this.controller=controller;
        mainDeck=new Deck(this);
        secondDeck=new Deck(this);


    }
    public void newRound(){
        mainDeck.createFullDeck();
        mainDeck.randomize();
        secondDeck.emptyDeck();
        round++;
    }
}
