package edu.ib.player;

import edu.ib.Card;
import edu.ib.Game;

import java.util.ArrayList;

/**
 * AIPlayer class definition
 */
public class AIPlayer extends Player {

    /**
     * AIPlayer constructor
     *
     * @param game Game to which the AIPlayer is assigned to
     * @param name Name of the AIPlayer
     */
    public AIPlayer(Game game, String name) {
        super(game, name);
    }

    /**
     * Method to draw a Card from the Deck
     *
     * @param card Card that is drawn from the Deck
     */
    @Override
    public void drawCard(Card card) {
        super.drawCard(card);
        //card.setDisplayedImage(Card.BACK_IMAGE);
        hand.getCard(hand.size() - 1).setDisplayedImage(hand.getCard(hand.size() - 1).FRONT_IMAGE);
    }

    /**
     * Method to play a Card
     *
     * @param c Card that is played
     */
    @Override
    public void playCard(Card c) {
        ArrayList<Card> list = new ArrayList<>();
        ArrayList<Integer> playValue = new ArrayList<>();

        Card topSecondDeck = getGame().getSecondDeck().getCard(0);

        Card.Rank rank = topSecondDeck.getRank();
        Card.Suit suit = topSecondDeck.getSuit();
        //szukanie prawidłowej karty i określenie jej wartości
        for (int i = 0; i < getHand().size(); i++) {
            Card handCard = hand.getCard(i);
            if (handCard.getRank().equals(Card.Rank.Eight)) { //jezeli 8
                list.add(getHand().getCard(i));
                if (getHand().size() == 2) {
                    playValue.add(10000);
                } else {
                    playValue.add(0);
                }
            } else if (((handCard.getRank().equals(rank) || handCard.getSuit().equals(suit))
                    && game.getCardColor().equals(Game.CardColor.Normal)) || handCard.getSuit().toString().equals(game.getCardColor().toString())) { //w przeciwnym wypadku
                list.add(handCard);
                try {
                    playValue.add(100 / hand.getNumberOfRankCards(rank) + 5 * hand.getNumberOfRankSuit(handCard.getSuit()));
                } catch (ArithmeticException e) {
                    playValue.add(100 + 5 * hand.getNumberOfRankSuit(handCard.getSuit()));
                }
                game.setCardColor(Game.CardColor.Normal);
            }
        }
        //brak prawidłowej karty
        if (list.isEmpty()) { //brak kart do zagrania
            boolean played = false;
            while (!played) {
                try {
                    drawCard(game.getMainDeck().getCard(0));
                } catch (NullPointerException e) {
                    game.restockMainDeck();
                    drawCard(game.getMainDeck().getCard(0));
                }
                if (hand.getCard(hand.size() - 1).getRank().equals(rank) || hand.getCard(hand.size() - 1).getSuit().equals(suit)) {
                    played = true;
                    game.getSecondDeck().setImage(getHand().getCard(hand.size() - 1).FRONT_IMAGE);
                    hand.moveCardToDeck(getHand().getCard(hand.size() - 1), game.getSecondDeck(), 0);
                    hand.display();
                }
            }
        } else {
            int maxValue = playValue.get(0);
            Card card = list.get(0);
            for (int i = 1; i < playValue.size(); i++) {
                if (playValue.get(i) > maxValue) {
                    maxValue = playValue.get(i);
                    card = list.get(i);
                }
            }
            hand.moveCardToDeck(card, game.getSecondDeck(), 0);
            card.setVisible(false);
            game.getSecondDeck().setImage(card.FRONT_IMAGE);
            hand.display();
            if (card.getRank().equals(Card.Rank.Eight)) {
                Card.Suit[] suits = Card.Suit.values();
                Card.Suit cardColor = Card.Suit.Hearts;
                int maxNumber = 0;
                for (Card.Suit value : suits) {
                    if (hand.getNumberOfRankSuit(value) > maxNumber) {
                        maxNumber = hand.getNumberOfRankSuit(value);
                        cardColor = value;
                    }
                }
                game.setCardColor(Game.CardColor.valueOf(cardColor.toString()));
            }
        }
        if (hand.isEmpty()) {
            game.finishRound(this);
        } else {
            game.setPlayerTurn(0);
        }
    }
}
