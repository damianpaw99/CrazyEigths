package edu.ib.player;

import edu.ib.Card;
import edu.ib.Game;
import javafx.scene.image.Image;

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
        card.setDisplayedImage(Card.BACK_IMAGE);
        //hand.getCard(hand.size() - 1).setDisplayedImage(hand.getCard(hand.size() - 1).FRONT_IMAGE);
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

            boolean case1=(handCard.getRank().equals(rank) || handCard.getSuit().equals(suit)) && game.getCardColor().equals(Game.CardColor.Normal);
            boolean case2=handCard.getSuit().toString().equals(game.getCardColor().toString());

            if (handCard.getRank().equals(Card.Rank.Eight)) { //jezeli 8
                list.add(getHand().getCard(i));
                if (getHand().size() == 2) {
                    playValue.add(10000);
                } else {
                    playValue.add(0);
                }

            } else if (case1||case2) { //w przeciwnym wypadku
                list.add(handCard);
                try {
                    playValue.add(100 / (hand.size()-hand.getNumberOfRankCards(rank)) + 5 * hand.getNumberOfSuitCards(handCard.getSuit()));
                } catch (ArithmeticException e) {
                    playValue.add(100 + 5 * hand.getNumberOfSuitCards(handCard.getSuit()));
                }

            }
        }
        //brak prawidłowej karty
        if (list.isEmpty()) { //brak kart do zagrania
            boolean played = false;
            while (!played) {
                try {
                    drawCard(game.getMainDeck().getCard(0));
                } catch (IndexOutOfBoundsException e) {
                    game.restockMainDeck();
                    drawCard(game.getMainDeck().getCard(0));
                }

                Card drawCard = hand.getCard(hand.size()-1);

                boolean case1=(drawCard.getRank().equals(rank) || drawCard.getSuit().equals(suit)) && game.getCardColor().equals(Game.CardColor.Normal);
                boolean case2=(game.getCardColor().toString().equals(hand.getCard(hand.size() - 1).getSuit().toString()));

                if (drawCard.getRank().equals(Card.Rank.Eight)) { //jeżeli ósemka
                    playEight(drawCard);
                    played=true;
                    hand.display();
                } else if (case1||case2){ //jeśli nie ósemka
                    played = true;
                    game.setCardColor(Game.CardColor.Normal);
                    game.getSecondDeck().setImage(getHand().getCard(hand.size() - 1).FRONT_IMAGE);
                    hand.moveCardToDeck(getHand().getCard(hand.size() - 1), game.getSecondDeck(), 0);
                    hand.display();
                }
            }
        } else { //lista nie pusta
            int maxValue = playValue.get(0);
            Card card = list.get(0);
            for (int i = 1; i < playValue.size(); i++) {
                if (playValue.get(i) > maxValue) {
                    maxValue = playValue.get(i);
                    card = list.get(i);
                }
            }
            if (card.getRank().equals(Card.Rank.Eight)) { //jeżeli zagrana ósemka
                playEight(card);
            } else {
                hand.moveCardToDeck(card, game.getSecondDeck(), 0);
                card.setVisible(false);
                game.getSecondDeck().setImage(card.FRONT_IMAGE);
                game.setCardColor(Game.CardColor.Normal);
            }
            hand.display();
        }

        if (hand.isEmpty() && game.isRunning()) {
            game.finishRound(this);
        } else {
            game.setPlayerTurn(0);
        }
    }

    private void playEight(Card card){
        Card.Suit[] suits = Card.Suit.values();
        Card.Suit cardColor = Card.Suit.Hearts;
        int maxNumber = hand.getNumberOfSuitCards(Card.Suit.Hearts);
        for (int i = 1; i < suits.length; i++) {
            Card.Suit value = suits[i];
            if (hand.getNumberOfSuitCards(value) > maxNumber) {
                maxNumber = hand.getNumberOfSuitCards(value);
                cardColor = value;
            }
        }
        hand.moveCardToDeck(card,game.getSecondDeck(),0);
        card.setVisible(false);
        game.getSecondDeck().setImage(card.FRONT_IMAGE);
        game.setCardColor(Game.CardColor.valueOf(cardColor.toString()));
        game.getController().getImageSuit().setVisible(true);
        game.getController().getImageSuit().setImage(new Image("/graphics/"+game.getCardColor().toString()+".png"));
    }
}
