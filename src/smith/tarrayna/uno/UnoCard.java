package smith.tarrayna.uno;

import smith.tarrayna.cards.Card;
import smith.tarrayna.cards.CardTraits;
import smith.tarrayna.cards.Color;
import smith.tarrayna.cards.Suit;

public class UnoCard extends Card {

    UnoCard()
    {
        super();
    }



    UnoCard(Suit suit, CardTraits trait, Color color)
    {
        super();
        this.suit = suit;
        this.trait = trait;
        this.color = color;
    }



    public String getCard(){
        String cardString =  color.getColorCode() + trait.getCardArt() + Color.CLEAR.getColorCode();

        return cardString.toString();
    }

    public void fun(){

    }
}
