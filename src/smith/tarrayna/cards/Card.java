package smith.tarrayna.cards;

public class Card {
    private CardValue cardValue;
    private Suit suit;

    public Card()
    {
        this.suit = Suit.JOKER;
        this.cardValue = CardValue.WILD;
    }

    public Card(Suit suit, CardValue cardValue)
    {
        this.suit = suit;
        this.cardValue = cardValue;
    }

    public String getCard()
    {
        return (suit.getCardColor().getColorCode() + cardValue.getCardArt() + Color.CLEAR.getColorCode());
    }

    public Suit getSuit()
    {
        return suit;
    }

    public CardValue getCardValue()
    {
        return cardValue;
    }
}
