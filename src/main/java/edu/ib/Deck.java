package edu.ib;

import java.util.ArrayList;

public class Deck {

    private ArrayList<Card> listOfCards;

    public Deck() {

        this.listOfCards = new ArrayList<Card>();

        for (int i = 0; i<4; i++){
            for (int j = 0; j<13; j++){
                listOfCards.add(new Card(i,j));
            }
        }
    }
}
