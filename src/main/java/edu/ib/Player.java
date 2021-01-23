package edu.ib;

public abstract class Player {
    private Hand hand;
    private final String name;
    private int points;
    private final Game game;

    public Player(Game game,String name){
        this.game=game;
        this.name=name;
        points=0;
    }

    public abstract void playCard();

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
