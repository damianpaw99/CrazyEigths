package edu.ib;

import java.util.Comparator;

public class Card {


    /**
     * RANK (value)
     * rank starts with 1 ends with 13
     * 1 -  2 (dwojka)
     * 3, 4, ..., 10
     * 10 - Walet
     * 11 - Dama
     * 12 - Krol
     * 13 - As
     */
    private int rank;

    /**
     * SUIT (color)
     * Values:
     * 0 - serce Heart
     * 1 - wino
     * 2 - romb
     * 3 - trefl
     * color is int so we can sort in class Hand easier
     */

    private int suit;

    public Card(int suit, int rank) {
        this.suit = suit;
        this.rank = rank;

    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getSuit() {
        return suit;
    }

    public void setSuit(int suit) {
        this.suit = suit;
    }

    public int findPlaceInDeck(){
        return getSuit() * 13 + getRank();
    }
}
