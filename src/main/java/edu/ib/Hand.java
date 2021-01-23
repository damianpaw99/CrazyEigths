package edu.ib;

import java.util.Collections;


public class Hand extends Deck{

    public Hand(Game game) {
        super(game);
    }

    public void sort(){
        Collections.sort(listOfCards);
    }


    public void display(){

    }
}