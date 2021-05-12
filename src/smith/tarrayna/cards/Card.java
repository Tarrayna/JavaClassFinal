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

    public Card() {
        this.suit = Suit.SPADES;
        this.cardValue = CardValue.ACE;
    }

    public Card(Suit suit, CardValue cardValue) {
        this.suit = suit;
        this.cardValue = cardValue;
    }

    public String getCard() {
        return (suit.getCardColor().getColorCode() + cardValue.getCardArt() + Color.CLEAR.getColorCode());
    }

    public Suit getSuit() {
        return suit;
    }

    public CardValue getCardValue() {
        return cardValue;
    }
}
