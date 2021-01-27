package edu.ib.player;

import edu.ib.Card;
import edu.ib.Game;

import java.util.ArrayList;

public class AIPlayer extends Player {

    public AIPlayer(Game game, String name) {
        super(game, name);
    }

    @Override
    public void drawCard(Card card) {
        super.drawCard(card);
        //card.setDisplayedImage(Card.BACK_IMAGE);
        card.setDisplayedImage(card.FRONT_IMAGE);

    }

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
            } else if (handCard.getRank().equals(rank) || handCard.getSuit().equals(suit)) { //w przeciwnym wypadku
                list.add(handCard);
                try {
                    playValue.add(100 / hand.getNumberOfRankCards(rank) + 5 * hand.getNumberOfRankSuit(handCard.getSuit()));
                } catch(ArithmeticException e){
                    playValue.add(100+5*hand.getNumberOfRankSuit(handCard.getSuit()));
                }
            }
        }
        //brak prawidłowej karty
        if (list.isEmpty()) {
            boolean played = false;
            while (!played) {
                drawCard(getGame().getMainDeck().getCard(0));
                if (hand.getCard(hand.size() - 1).getRank().equals(rank) || hand.getCard(hand.size() - 1).getSuit().equals(suit)) {
                    played = true;
                    hand.moveCardToDeck(getHand().getCard(hand.size() - 1), game.getSecondDeck(), 0);
                }
            }
            game.setPlayerTurn(0);
        } else { //brak kart do zagrania
            int maxValue = 0;
            int card = 0;
            for (int i = 0; i < playValue.size(); i++) {
                if (playValue.get(i) > maxValue) {
                    maxValue = playValue.get(i);
                    card = i;
                }
            }
            hand.moveCardToDeck(hand.getCard(card), game.getSecondDeck(), 0);
            hand.display();
        }
    }
}
