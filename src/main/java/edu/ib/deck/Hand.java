package edu.ib.deck;

import edu.ib.Card;
import edu.ib.Game;

import java.util.Collections;


public class Hand extends Deck {

    public Hand(Game game) {
        super(game);
    }

    public void sort(){
        Collections.sort(listOfCards);
        display();
    }


    public void display(){
        int xLeft = 180;
        int xRight = 820;
        double step = (xRight - xLeft) / (double) listOfCards.size();
        if(game.getPlayers()[0].getHand().equals(this)) {
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
    public int getNumberOfRankCards(Card.Rank rank){
        int count=0;
        for(int i=0;i<listOfCards.size();i++){
            if(listOfCards.get(i).getRank().equals(rank)){
                count++;
            }
        }
        return count;
    }
    public int getNumberOfRankSuit(Card.Suit suit){
        int count=0;
        for(int i=0;i<listOfCards.size();i++){
            if(listOfCards.get(i).getSuit().equals(suit)){
                count++;
            }
        }
        return count;
    }
}