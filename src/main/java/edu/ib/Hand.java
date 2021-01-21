package edu.ib;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class Hand {

    private ArrayList<Card> cardsOnHand;

    public Hand() {
        this.cardsOnHand = new ArrayList<Card>();
    }

    public void sort(){
        Comparator <Card> byPlaceInDeck =
                (Card c1, Card c2) -> Integer.compare(c1.findPlaceInDeck(), c2.findPlaceInDeck());
        cardsOnHand.sort(byPlaceInDeck);
    }

    public static void main(String[] args) {
        Deck d = new Deck();
        Random r = new Random();

    }
    public void addCardToHand(Card c){
        cardsOnHand.add(c);
    }

    public ArrayList<Card> getCardsOnHand() {
        return cardsOnHand;
    }

    public void setCardsOnHand(ArrayList<Card> cardsOnHand) {
        this.cardsOnHand = cardsOnHand;
    }

    public void display(){

    }

}

