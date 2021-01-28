package edu.ib;

import edu.ib.deck.Deck;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;

/**
 * Card class definition
 */
public class Card extends Button implements Comparable<Card> {

    private final Suit suit;
    private final Rank rank;

    /**
     * Deck in which the Card is
     */
    private Deck deck;

    public Image FRONT_IMAGE;
    public static final Image BACK_IMAGE = new Image("/graphics/back.png");

    /**
     * Enum class of possible Ranks of Card and with point value of each Rank
     */
    public enum Rank {
        Ace(1),
        Two(2),
        Three(3),
        Four(4),
        Five(5),
        Six(6),
        Seven(7),
        Eight(8),
        Nine(9),
        Ten(10),
        Jack(10),
        Queen(10),
        King(10);

        int pointsValue;

        Rank(int pointsValue) {
            this.pointsValue = pointsValue;
        }

        int getPointsValue() {
            return pointsValue;
        }
    }

    /**
     * Enum class of possible Suits of Card
     */
    public enum Suit {
        Hearts, //heart
        Spades, //pike
        Diamonds, //diamonds
        Clubs //clover
    }

    /**
     * Card constructor
     *
     * @param deck Deck in witch this card is
     * @param rank Rank of card
     * @param suit Suit of card
     */
    public Card(Deck deck, Rank rank, Suit suit) {
        try {
            FRONT_IMAGE = new Image("/graphics/" + rank.toString() + "_" + suit.toString() + ".png");
        } catch (Exception e) {
            FRONT_IMAGE = new Image("/graphics/blank.png");
            this.setText(rank.toString() + "\n" + suit.toString());
        }

        this.deck = deck;
        this.rank = rank;
        this.suit = suit;

        this.setBackground(new Background(new BackgroundImage(FRONT_IMAGE, null, null, null, null)));
        setLayoutX(0);
        setLayoutY(0);

        setVisible(false);
        setWidth(64);
        setHeight(87);
        setMinSize(64, 87);
        setMaxSize(64, 87);

        setOnAction(event -> {
            if (getDeck().getGame().getPlayerTurn() == 0 && getDeck().getGame().getPlayers()[0].getHand().haveCard(this)) {
                getDeck().getGame().getPlayers()[getDeck().getGame().getPlayerTurn()].playCard(this);
            }
        });
    }

    /**
     * Method finding position of Card in the Deck
     *
     * @return Card index in the Deck as int
     */
    public int findPlaceInDeck() {
        return deck.getIndex(this);
    }

    /**
     * Method to get the Deck in which the Card is
     *
     * @return Deck in which the Card is
     */
    public Deck getDeck() {
        return deck;
    }

    /**
     * Method setting a Deck in which the Card is
     *
     * @param deck new deck for the Card
     */
    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    /**
     * Method to get the Rank of the Card
     *
     * @return Rank (value) of the Card
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * Method to get the Suit of the Card
     *
     * @return Suit (colour) of the Card
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Method to get the PointsValue of the Card
     *
     * Points value of:
     * Ace - 1 point
     * 2-10 - accordingly, 2-10 points
     * Jack, Queen, King - 10 points each
     *
     * @return PointsValue of the Card as int
     */
    public int getPointsValue() {
        return rank.getPointsValue();
    }

    /**
     * Method to set an Image a background of the Card
     *
     * @param image Image that is set as Background of the Card
     */
    public void setDisplayedImage(Image image) {
        setBackground(new Background(new BackgroundImage(image, null, null, null, null)));
    }

    /**
     * Overridden toString() method from Object class
     *
     * @return Basic info about card in String
     */
    public String toString() {
        return suit.toString() + "\n" + rank.toString();
    }

    /**
     * Overridden method from Comparable interface
     *
     * @param card Another Card object to compare
     * @return -1 if Card is lower than another Card,
     * 0 if Card is equal to another Card,
     * 1 if Card is greater than another Card
     */
    @Override
    public int compareTo(Card card) {
        return (int) Math.signum(compareValueOfCard() - card.compareValueOfCard());
    }

    /**
     * Method counting comparing value of card
     *
     * @return Comparing value of card
     */
    private int compareValueOfCard() {
        int value = 0;
        switch (suit) {
            case Spades -> value += 400;
            case Hearts -> value += 300;
            case Diamonds -> value += 200;
            case Clubs -> value += 100;
        }
        switch (rank) {
            case Ace, Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten -> value += rank.pointsValue;
            case Jack -> value += 11;
            case Queen -> value += 12;
            case King -> value += 13;
        }
        return value;
    }
}
