package edu.ib;

public class AIPlayer extends Player {

    public AIPlayer(Game game, String name){
        super(game, name);
    }

    @Override
    public void drawCard(Card card) {
        super.drawCard(card);
        card.setDisplayedImage(card.BACK_IMAGE);
        card.setDisable(true);
    }

    @Override
    public void playCard() {

    }
}
