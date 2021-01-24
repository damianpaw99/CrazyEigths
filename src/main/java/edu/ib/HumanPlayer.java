package edu.ib;

public class HumanPlayer extends Player {

    public HumanPlayer(Game game, String name) {
        super(game, name);
    }

    @Override
    public void drawCard(Card card) {
        super.drawCard(card);
        card.setDisplayedImage(card.FRONT_IMAGE);
        card.setDisable(false);
    }

    @Override
    public void playCard() {

    }
}
