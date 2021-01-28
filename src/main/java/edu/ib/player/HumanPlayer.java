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

            game.getSecondDeck().setImage(card.FRONT_IMAGE);
            game.displayColorButtons();
            game.setPlayerTurn(1);
            game.getController().getImageSuit().setVisible(false);
        } else if (game.getCardColor().equals(Game.CardColor.Normal)) { //jezeli normalny tryb

            Card.Rank secondTopRank = game.getSecondDeck().getCard(0).getRank();
            Card.Suit secondTopSuit = game.getSecondDeck().getCard(0).getSuit();

            if (rank.equals(secondTopRank) || suit.equals(secondTopSuit)) {
                hand.moveCardToDeck(card, game.getSecondDeck(), 0);
                card.setVisible(false);
                hand.display();

                game.setCardColor(Game.CardColor.Normal);
                game.setPlayerTurn(1);
                game.getSecondDeck().setImage(card.FRONT_IMAGE);
                game.getController().getImageSuit().setVisible(false);
                game.getPlayers()[1].playCard(null);

            }
        } else if (suit.toString().equals(game.getCardColor().toString())) { //jezeli po wyborze koloru
            hand.moveCardToDeck(card, game.getSecondDeck(), 0);
            game.setCardColor(Game.CardColor.Normal);
            game.getController().getImageSuit().setVisible(false);
            hand.display();
            card.setVisible(false);

            game.getSecondDeck().setImage(card.FRONT_IMAGE);
            game.setPlayerTurn(1);
            game.getPlayers()[1].playCard(null);
        }

        //sprawdzenie wygranej rundy
        if (hand.isEmpty() && game.isRunning()) {
            game.finishRound(this);
        }
    }
}
