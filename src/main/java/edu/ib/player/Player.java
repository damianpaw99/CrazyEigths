package edu.ib.player;

import edu.ib.Card;
import edu.ib.Game;
import edu.ib.deck.Hand;

/**
 * Player class definition
 */
public abstract class Player {

    protected Hand hand;
    protected final String name;
    protected int points;
    protected final Game game;

    /**
     * Player constructor
     *
     * @param game Game to which the Player is assigned to
     * @param name Name of the Player
     */
    public Player(Game game, String name) {
        this.game = game;
        this.name = name;
        points = 0;
        hand = new Hand(game);
    }

    /**
     * Method to get the Hand of the Player
     *
     * @return Hand of the Player
     */
    public Hand getHand() {
        return hand;
    }

    /**
     * Method to set Hand of the Player
     *
     * @param hand Hand of the Player
     */
    public void setHand(Hand hand) {
        this.hand = hand;
    }

    /**
     * Method to get the Name of the Player
     *
     * @return Player's Name as a String
     */
    public String getName() {
        return name;
    }

    /**
     * Method to get the Points of the Player
     *
     * @return number of Points as int
     */
    public int getPoints() {
        return points;
    }

    /**
     * Method to set the Points of the Player
     *
     * @param points new value of Player's Points
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Method to get the Game to which Player is assigned to
     *
     * @return Game to which Player is assigned to
     */
    public Game getGame() {
        return game;
    }

    /**
     * Method to play a Card
     *
     * @param card Card that is played
     */
    public abstract void playCard(Card card);

    /**
     * Method to draw a Card from the Deck
     *
     * @param card Card that is drawn from the Deck
     */
    public void drawCard(Card card) {
        hand.moveCardToDeck(card, hand);
    }

    /**
     * Method to add points to the Player's Points score
     *
     * @param points Points that are added to the score
     */
    public void addPoints(int points) {
        this.points += points;
    }
}
