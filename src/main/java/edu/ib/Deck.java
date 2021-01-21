package edu.ib;

import java.util.ArrayList;

import java.util.Collections;

/**
 * Deck class definition
 */
public class Deck {
    private ArrayList<Card> listOfCards;
    private boolean visibility;

    /**
     * Constructs a Deck
     * First "for" loop assigns Suit, second assigns Rank
     * "If" statement allows to assign proper pointsValue
     */
    public Deck() {
        this.listOfCards = new ArrayList<Card>();

        for (int i = 0; i < 4; i++) {
            for (int j = 1; j < 14; j++) {
                if (j == 1) listOfCards.add(new Card(i, j, 1));
                else if (j == 11 || j == 12 || j == 13) listOfCards.add(new Card(i, j, 10));
                else listOfCards.add(new Card(i, j, j));
            }
        }
    }

    /**
     * @return visibility of the Deck
     */
    public boolean isVisibility() {
        return visibility;
    }

    /**
     * @param visibility new value of visibility of the Deck
     */
    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    /**
     * @return the last Card in the Deck
     */
    public Card getLastCard() {
        return listOfCards.get(listOfCards.size() - 1);
    }

    /**
     * @param i index of the requested Card
     * @return the Card with given index
     */
    public Card getCard(int i) {
        return listOfCards.get(i);
    }

    /**
     * @return boolean value after checking if listOfCards is empty
     */
    public boolean isEmpty() {
        return listOfCards.isEmpty();
    }

    /**
     * Shuffles the Deck
     */
    public void randomize() {
        Collections.shuffle(listOfCards);
    }

    /**
     * Clears listOfCards - sets all elements to null
     */
    public void clearDeck() {
        listOfCards.clear();
    }

    /**
     * @param i intex of the Card that is removed
     */
    public void removeCard(int i) {
        listOfCards.remove(i);
    }

    /**
     * @param card adds given Card to the Deck
     */
    public void addCard(Card card) {
        listOfCards.add(card);
    }

    /**
     * @return size of the Deck as int
     */
    public int size() {
        return listOfCards.size();
    }

    /**
     * Swaps two Cards in listOfCards
     *
     * @param i index of first Card
     * @param j index of second Card
     */
    public void swap(int i, int j) {
        Collections.swap(listOfCards, i, j);
    }

    /**
     * @param card chosen to find its index
     * @return index of given Card in the Deck
     */
    public int getIndex(Card card) {
        int index = 0;
        for (int i = 0; i < listOfCards.size(); i++) {
            if (card.getSuit() == listOfCards.get(i).getSuit() && card.getRank() == listOfCards.get(i).getRank())
                index = i;
        }
        return index;
    }

    /**
     * Displays all Cards in the Deck in pattern: "Rank of Suit"
     */
    public void display() {
        for (int i = 0; i < listOfCards.size(); i++) {
            System.out.println(listOfCards.get(i).toString());
        }
    }
}
