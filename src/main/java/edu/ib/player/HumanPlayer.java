package edu.ib.player;

import edu.ib.Card;
import edu.ib.Game;

public class HumanPlayer extends Player {

    public HumanPlayer(Game game, String name) {
        super(game, name);
    }

    @Override
    public void drawCard(Card card) {
        super.drawCard(card);
        card.setDisplayedImage(card.FRONT_IMAGE);
    }

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
        } else if(suit.toString().equals(game.getCardColor().toString())) { //jezeli po wyborze koloru
            hand.moveCardToDeck(card,game.getSecondDeck(),0);
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
