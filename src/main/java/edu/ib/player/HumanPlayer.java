package edu.ib.player;

import edu.ib.Card;
import edu.ib.Game;

/**
 * HumanPlayer class definition
 */
public class HumanPlayer extends Player {

    /**
     * HumanPlayer constructor
     *
     * @param game Game to which the HumanPlayer is assigned to
     * @param name Name of the HumanPlayer
     */
    public HumanPlayer(Game game, String name) {
        super(game, name);
    }

    /**
     * Method to draw a Card from a deck
     *
     * @param card Card that is drawn from the Deck
     */
    @Override
    public void drawCard(Card card) {
        super.drawCard(card);
        card.setDisplayedImage(card.FRONT_IMAGE);
    }

    /**
     * Method to play chosen Card
     *
     * @param card Card that is played
     */
    @Override
    public void playCard(Card card) {
        Card.Rank rank = card.getRank();
        Card.Suit suit = card.getSuit();
        if (rank.equals(Card.Rank.Eight)) { //jezeli 8
            hand.moveCardToDeck(card, game.getSecondDeck(), 0);
            card.setVisible(false);
            hand.display();
            if (hand.isEmpty()) {
                game.setRunning(false);
                game.finishRound(this);
            }
            game.getSecondDeck().setImage(card.FRONT_IMAGE);
            game.displayColorButtons();

        } else if (game.getCardColor().equals(Game.CardColor.Normal)) { //jezeli normalny tryb

            Card.Rank secondTopRank = game.getSecondDeck().getCard(0).getRank();
            Card.Suit secondTopSuit = game.getSecondDeck().getCard(0).getSuit();

            if (rank.equals(secondTopRank) || suit.equals(secondTopSuit)) {
                hand.moveCardToDeck(card, game.getSecondDeck(), 0);
                card.setVisible(false);
                hand.display();

                if (hand.isEmpty()) {
                    game.setRunning(false);
                    game.finishRound(this);
                }
                game.setPlayerTurn(1);
                game.getSecondDeck().setImage(card.FRONT_IMAGE);
                game.getPlayers()[1].playCard(null);

            }
        } else if (suit.toString().equals(game.getCardColor().toString())) { //jezeli po wyborze koloru
            hand.moveCardToDeck(card, game.getSecondDeck(), 0);
            hand.display();
            card.setVisible(false);
            if (hand.isEmpty()) {
                game.setRunning(false);
                game.finishRound(this);
            }

            game.setPlayerTurn(1);
            game.getPlayers()[1].playCard(null);
            game.getSecondDeck().setImage(card.FRONT_IMAGE);
            game.setCardColor(Game.CardColor.Normal);
        }

    }
}
