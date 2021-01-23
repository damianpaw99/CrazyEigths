package edu.ib;

public abstract class Player {
    private Hand hand;
    private final String name;
    private int points;
    private final Game game;

    /**
     * Player constructor
     * @param game Game
     * @param name Name of teh player
     */
    public Player(Game game,String name){
        this.game=game;
        this.name=name;
        points=0;
    }

    public abstract void playCard();

    public void drawCard(Card card,Deck deck){
        hand.moveCardToDeck(card, deck);
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
