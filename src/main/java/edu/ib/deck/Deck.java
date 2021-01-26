package edu.ib.deck;

import edu.ib.Card;
import edu.ib.Game;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Deck class definition
 */
public class Deck extends ImageView {
    protected final ArrayList<Card> listOfCards;
    protected boolean visibility;
    protected final Game game;
    /**
     * Constructs a Deck
     * First "for" loop assigns Suit, second assigns Rank
     * "If" statement allows to assign proper pointsValue
     */
    public Deck(Game game) {
        this.game=game;
        this.listOfCards = new ArrayList<>();
        visibility=false;
    }
    public Deck(Game game, int x, int y) {
        this.game=game;
        this.listOfCards = new ArrayList<>();
        visibility=true;
        this.setLayoutX(x);
        this.setLayoutY(y);
    }
    public void createFullDeck(){
        Card.Rank[] ranks = Card.Rank.values();
        Card.Suit[] suits = Card.Suit.values();
        for (Card.Suit suit : suits) {
            for (Card.Rank rank : ranks) {
                listOfCards.add(new Card(this, rank, suit));
            }
        }
    }
    public void emptyDeck(){
        listOfCards.clear();
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
     * Method removing card from deck at index i
     * @param i index of the Card that is removed
     */
    public void removeCard(int i) {
        listOfCards.remove(i);
    }

    /**
     * Method removing card from deck
     * @param card Card to remove
     */
    public void removeCard(Card card){
        listOfCards.remove(card);
    }

    /**
     * @param card adds given Card to the Deck
     */
    public void addCard(Card card) {
        listOfCards.add(card);
    }

    /**
     * Method moving card to another deck
     * @param card Card to move
     * @param deck New deck for card
     */
    public void moveCardToDeck(Card card,Deck deck){
        card.getDeck().removeCard(card);
        card.setDeck(deck);
        deck.addCard(card);
    }
    public void moveCardToDeck(Card card,Deck deck,int index){
        listOfCards.remove(card);
        card.setDeck(deck);
        deck.addCard(index, card);
    }
    public void addCard(int index, Card card){
        listOfCards.add(index, card);
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
            return listOfCards.indexOf(card);
    }

    /**
     * @return Game in which Deck is
     */
    public Game getGame(){
        return game;
    }
    /**
     *
     */ //do poprawy
    public void display() {

    }
}
