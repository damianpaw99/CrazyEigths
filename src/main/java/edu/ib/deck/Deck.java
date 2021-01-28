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
     * Deck constructor
     *
     * @param game to which Deck is assigned to
     */
    public Deck(Game game) {
        this.game = game;
        this.listOfCards = new ArrayList<>();
        visibility = false;
    }

    public Deck(Game game, int x, int y) {
        this.game = game;
        this.listOfCards = new ArrayList<>();
        visibility = true;
        this.setLayoutX(x);
        this.setLayoutY(y);
    }

    /**
     * Constructs a full Deck
     * First "for" loop assigns Suit, second assigns Rank
     */
    public void createFullDeck() {
        Card.Rank[] ranks = Card.Rank.values();
        Card.Suit[] suits = Card.Suit.values();
        for (int i = 0; i < suits.length; i++) {
            for (int j = 0; j < ranks.length; j++) {
                listOfCards.add(new Card(this, ranks[j], suits[i]));
                game.getController().getCanvas().getChildren().add(listOfCards.get(listOfCards.size() - 1));
            }
        }
    }

    /**
     * Method to empty existing Deck
     */
    public void emptyDeck() {
        for (int i = 0; i < listOfCards.size(); i++) {
            game.getController().getCanvas().getChildren().remove(listOfCards.get(i));
        }
        listOfCards.clear();
    }

    /**
     * @return visibility of the Deck
     */
    public boolean isVisibility() {
        return visibility;
    }

    /**
     * @return boolean value after checking if listOfCards is empty
     */
    public boolean isEmpty() {
        return listOfCards.isEmpty();
    }

    /**
     * Method to shuffle the Deck
     */
    public void randomize() {
        Collections.shuffle(listOfCards);
    }

    /**
     * Method to clear the listOfCards - sets all elements to null
     */
    public void clearDeck() {
        listOfCards.clear();
    }

    /**
     * Method removing card from the Deck at index i
     *
     * @param i index of the Card that is removed from the listOfCards
     */
    public void removeCard(int i) {
        listOfCards.remove(i);
    }

    /**
     * Method removing card from the Deck
     *
     * @param card Card that is removed from the listOfCards
     */
    public void removeCard(Card card) {
        listOfCards.remove(card);
    }

    /**
     * @param card Card that is added to the listOfCards
     */
    public void addCard(Card card) {
        listOfCards.add(card);
    }

    /**
     * Method moving Card to another Deck
     *
     * @param card Card that is moved
     * @param deck new Deck for the moved Card
     */
    public void moveCardToDeck(Card card, Deck deck) {
        card.getDeck().removeCard(card);
        card.setDeck(deck);
        deck.addCard(card);
    }

    /**
     * Method moving Card to another Deck
     *
     * @param card  Card that is moved
     * @param deck  new Deck for the moved Card
     * @param index index in the Deck, where Card is moved
     */
    public void moveCardToDeck(Card card, Deck deck, int index) {
        card.getDeck().removeCard(card);
        card.setDeck(deck);
        deck.addCard(index, card);
    }

    /**
     * Method to add Card to the Deck
     *
     * @param index index in the Deck, where Card is added
     * @param card  Card that is added to the Deck
     */
    public void addCard(int index, Card card) {
        listOfCards.add(index, card);
    }

    /**
     * @return size of the Deck as int
     */
    public int size() {
        return listOfCards.size();
    }

    /**
     * Method to swap two Cards in the Deck
     *
     * @param i index of first Card
     * @param j index of second Card
     */
    public void swap(int i, int j) {
        Collections.swap(listOfCards, i, j);
    }

    /**
     * Method checking if card is in deck
     * @param card Card
     * @return Logical value
     */
    public boolean haveCard(Card card){
        return listOfCards.contains(card);
    }
    /**
     * @param card chosen to find its index
     * @return index of given Card in the Deck
     */
    public int getIndex(Card card) {
        return listOfCards.indexOf(card);
    }

    /**
     * @return Game in which the Deck is
     */
    public Game getGame() {
        return game;
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
}
