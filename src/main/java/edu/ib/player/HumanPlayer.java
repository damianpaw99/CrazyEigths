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
                game.setPlayerTurn(1);
                if (hand.isEmpty()) {
                    game.setRunning(false);
                }
                game.getPlayers()[1].playCard(null);
        } else if (game.getCardColor().equals(Game.CardColor.Normal)) { //jezeli normalny tryb

            Card.Rank secondTopRank = game.getSecondDeck().getCard(0).getRank();
            Card.Suit secondTopSuit = game.getSecondDeck().getCard(0).getSuit();

            if (rank.equals(secondTopRank) || suit.equals(secondTopSuit)) {
                hand.moveCardToDeck(card, game.getSecondDeck(), 0);
                game.setPlayerTurn(1);
                if (hand.isEmpty()) {
                    game.setRunning(false);
                }
                game.getPlayers()[1].playCard(null);
            }
        } else if(suit.toString().equals(game.getCardColor().toString())) { //jezeli po wyborze koloru
            hand.moveCardToDeck(card,game.getSecondDeck(),0);
            game.setPlayerTurn(1);
            if (hand.isEmpty()) {
                game.setRunning(false);
                game.finishRound(this);
            }
            game.getPlayers()[1].playCard(null);
        }

    }
}
