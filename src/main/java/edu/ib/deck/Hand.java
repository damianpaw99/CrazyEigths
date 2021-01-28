package edu.ib.deck;

import edu.ib.Card;
import edu.ib.Game;

import java.util.Collections;

/**
 * Hand class definition
 */
public class Hand extends Deck {

    /**
     * Hand constructor
     *
     * @param game to which the Hand is assigned to
     */
    public Hand(Game game) {
        super(game);
    }

    /**
     * Method to sort and dispay the Hand
     */
    public void sort() {
        Collections.sort(listOfCards);
        display();
    }

    /**
     * Method to sort Cards in the Hand
     */
    public void display() {
        int xLeft = 130;
        int xRight = 870;
        double step = (xRight - xLeft) / (double) listOfCards.size();
        if (game.getPlayers()[0].getHand().equals(this)) {
            int h = 610;
            for (int i = 0; i < listOfCards.size(); i++) {
                listOfCards.get(i).setLayoutX(xLeft + step * i);
                listOfCards.get(i).setLayoutY(h);
                listOfCards.get(i).setVisible(true);
            }
        } else {
            int h = 3;
            for (int i = 0; i < listOfCards.size(); i++) {
                listOfCards.get(i).setLayoutX(xLeft + step * i);
                listOfCards.get(i).setLayoutY(h);
                listOfCards.get(i).setVisible(true);
            }
        }
    }

    /**
     * Method to get the number of Cards with given Rank
     *
     * @param rank Rank that is counted
     * @return number of Cards with given Rank as int
     */
    public int getNumberOfRankCards(Card.Rank rank) {
        int count = 0;
        for (Card listOfCard : listOfCards) {
            if (listOfCard.getRank().equals(rank)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Method to get the number of Cards with given Suit
     *
     * @param suit Suit that is counted
     * @return number of Cards with given Rank as int
     */
    public int getNumberOfSuitCards(Card.Suit suit) {
        int count = 0;
        for (Card listOfCard : listOfCards) {
            if (listOfCard.getSuit().equals(suit)) {
                count++;
            }
        }
        return count;
    }
}