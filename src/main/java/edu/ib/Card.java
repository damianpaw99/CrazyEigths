package edu.ib;


import java.util.Comparator;
import javafx.scene.control.Button;

/**
 * Card class definition
 */

public class Card {

    /**
     * RANK (value)
     * rank starts with 1 ends with 13
     * 1 - Ace
     * 2-10 - correspondingly
     * 11 - Jack
     * 12 - Queen
     * 13 - King
     */
    private final int rank;
    public static final String[] RANKS = {null, "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};

    /**
     * SUIT (color)
     * Values:
     * 0 - Hearts (kier)
     * 1 - Spades (pik)
     * 2 - Diamonds (karo)
     * 3 - Clubs (trefl)
     * color is int so we can sort in class Hand easier
     */
    private final int suit;
    public static final String[] SUITS = {"Hearts", "Spades", "Diamonds", "Clubs"};

    /**
     * Points value of:
     * Ace - 1 point
     * 2-10 - accordingly, 2-10 points
     * Jack, Queen, King - 10 points each
     */

    private final int pointsValue;

    /**
     * Button assigned to the Card
     */
    private Button button;

    public Card(int rank, int suit, int pointsValue) {
        this.rank = rank;
        this.suit = suit;
        this.pointsValue = pointsValue;
    }

    /**
     * @return int with rank/value of the Card
     */
    public int getRank() {
        return rank;
    }

    /**
     * @return String with color of the Card
     */
    public int getSuit() {
        return suit;
    }


    /**
     * @return int with points value of the Card
     */
    public int getPointsValue() {
        return pointsValue;
    }

    /**
     * @return Button assigned to the Card
     */
    public Button getButton() {
        return button;
    }


    public int findPlaceInDeck() {
        return getSuit() * 13 + getRank();
    }

    /**
     * @param secondCard - Card to which Suit is compared to
     * @return int -1, 1 or 0, depending on the comparison result
     */
    public int compareSuits(Card secondCard) {
        if (suit < secondCard.getSuit()) {
            return -1;
        } else if (suit > secondCard.getSuit()) {
            return 1;
        } else return 0;
    }

    /**
     * @param secondCard - Card to which Rank is compared to
     * @return int -1, 1 or 0, depending on the comparison result
     */
    public int compareRanks(Card secondCard) {
        if (rank < secondCard.getRank()) {
            return -1;
        } else if (rank > secondCard.getRank()) {
            return 1;
        } else return 0;
    }

    public String toString() {
        String stringRank;
        String stringSuit;

        if (rank == 1) stringRank = "Ace";
        else if (rank == 11) stringRank = "Jack";
        else if (rank == 12) stringRank = "Queen";
        else if (rank == 13) stringRank = "King";
        else stringRank = String.valueOf(rank);

        if (suit == 0) stringSuit = "Hearts";
        else if (suit == 1) stringSuit = "Spades";
        else if (suit == 2) stringSuit = "Diamonds";
        else stringSuit = "Clubs";

        return (stringRank + " of " + stringSuit);
    }
}
