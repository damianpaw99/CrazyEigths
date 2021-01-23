package edu.ib;

import javafx.scene.control.Button;

/**
 * Card class definition
 */

public class Card implements Comparable<Card>{



    /**
     * Enum class of possible ranks of Card and with value of each rank
     */
    public enum Rank{
        Ace(1),
        Two(2),
        Three(3),
        Four(4),
        Five(5),
        Six(6),
        Seven(7),
        Eight(8),
        Nine(9),
        Ten(10),
        Jack(10),
        Queen(10),
        King(10);

        int pointsValue;

        Rank(int pointsValue){
            this.pointsValue=pointsValue;
        }
        int getPointsValue(){
            return pointsValue;
        }
    }

    /**
     * Enum class of possible suits of card
     */
    public enum Suit{
        Hearts,
        Spades,
        Diamonds,
        Clubs
    }


    private final Suit suit;
    private final Rank rank;

    /**
     * Button assigned to the Card
     */
    private final Button button;
    /**
     * Deck in which this card is
     */
    private Deck deck;

    /**
     * @return Deck in which card is
     */
    public Deck getDeck() {
        return deck;
    }

    /**
     * Method setting in which deck card is
     * @param deck New deck for card
     */
    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    /**
     * Card constructor
     * @param deck Deck in witch this card is
     * @param rank Rank of card
     * @param suit Suit of card
     * @param button Button of card
     */
    public Card(Deck deck, Rank rank, Suit suit, Button button) {
        this.button=button;
        this.deck=deck;
        this.rank=rank;
        this.suit=suit;
    }

    /**
     * @return int with rank/value of the Card
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * @return String with color of the Card
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Points value of:
     * Ace - 1 point
     * 2-10 - accordingly, 2-10 points
     * Jack, Queen, King - 10 points each
     * @return int with points value of the Card
     */
    public int getPointsValue() {
        return rank.getPointsValue();
    }

    /**
     * @return Button assigned to the Card
     */
    public Button getButton() {
        return button;
    }

    /**
     * Method finding position of card in deck
     * @return Card index in deck
     */
    public int findPlaceInDeck() {
        return deck.getIndex(this);
    }

    /**
     * Overridden to String method from Object class
     * @return Basic info about card in String
     */
    public String toString() {
        return suit.toString()+" "+rank.toString();
    }

    /**
     * Overridden method from Comparable interface
     * @param card Another Card object to compare
     * @return -1 if card is lower than another card,
     * 0 if card is equal another card,
     * 1 if card is grater then another card
     */
    @Override
    public int compareTo(Card card) {
        return (int) Math.signum(compareValueOfCard()-card.compareValueOfCard());
    }

    /**
     * Method counting comparing value of card
     * @return Compering value of card
     */
    private int compareValueOfCard(){
        int value=0;
        switch (suit) {
            case Spades -> value += 400;
            case Hearts -> value += 300;
            case Diamonds -> value += 200;
            case Clubs -> value += 100;
        }
        switch (rank) {
            case Ace,Two,Three,Four,Five,Six,Seven, Eight,Nine,Ten-> value+=rank.pointsValue;
            case Jack-> value+=11;
            case Queen-> value+=12;
            case King -> value+=13;
        }
        return value;
    }
}
