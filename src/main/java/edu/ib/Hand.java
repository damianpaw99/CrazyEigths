package edu.ib;


import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;


public class Hand extends Deck{

    public Hand(Game game) {
        super(game);
    }

    public void sort(){
        Collections.sort(listOfCards);
    }

    public void addCardToHand(Card c){
        listOfCards.add(c);
    }


    public void display(){

    }
}