package smith.tarrayna.cards;

/**
 * Card. Defines a card by it's suit and value
 *
 * @author Tarrayna Smith
 * Final Project
*/
public class Card {
    private CardValue cardValue;
    private Suit suit;

    /**
     * Constructor
     */
    public Card() {
        this.suit = Suit.SPADES;
        this.cardValue = CardValue.ACE;
    }

    /**
     * Overloaded Constrctor. Allows for setting suit and card value
     */
    public Card(Suit suit, CardValue cardValue) {
        this.suit = suit;
        this.cardValue = cardValue;
    }

    /**
     * Gets the string that will be used for printing a card
     *
     * @return string of the card art
     */
    public String getCard() {
        return (suit.getCardColor().getColorCode() + cardValue.getCardArt() + Color.CLEAR.getColorCode());
    }

    /**
     *Gets the value of the card
     *
     * @return the value of the card
     */
    public CardValue getCardValue() {
        return cardValue;
    }
}
