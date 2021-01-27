package edu.ib.player;

import edu.ib.Card;
import edu.ib.Game;
import edu.ib.deck.Hand;

public abstract class Player {
    protected Hand hand;
    protected final String name;
    protected int points;
    protected final Game game;

    /**
     * Player constructor
     * @param game Game
     * @param name Name of teh player
     */
    public Player(Game game,String name){
        this.game=game;
        this.name=name;
        points=0;
        hand=new Hand(game);
    }

    public abstract void playCard(Card card);

    public void drawCard(Card card){
        hand.moveCardToDeck(card, hand);
    }
    public void addPoints(int points){
        this.points+=points;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Game getGame() {
        return game;
    }
}
