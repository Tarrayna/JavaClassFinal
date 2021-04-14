package smith.tarrayna.cards;

public class Card {
    CardTraits trait;
    Suit suit;

    Card(Suit suit, CardTraits trait)
    {
        this.suit = suit;
        this.trait = trait;
    }
}
