package smith.tarrayna.cards;

public class Card {
    protected CardTraits trait;
    protected Suit suit;
    protected Color color;

    public Card()
    {
        this.suit = Suit.JOKER;
        this.trait = CardTraits.WILD;
    }

    public Card(Suit suit, CardTraits trait, Color color)
    {
        this.suit = suit;
        this.trait = trait;
        this.color = color; 
    }

}
