package smith.tarrayna.uno;

import smith.tarrayna.cards.*;

public class UnoGameRunner {
    public static void main(String[] args)
    {
        //TODO: Remove this test code
        System.out.println(CardArt.ZERO);
        System.out.println(CardArt.ONE);
        System.out.println(CardArt.TWO);
        System.out.println(CardArt.THREE);
        System.out.println(CardArt.FOUR);
        System.out.println(CardArt.FIVE);
        System.out.println(CardArt.SIX);
        System.out.println(CardArt.SEVEN);
        System.out.println(CardArt.EIGHT);
        System.out.println(CardArt.NINE);
        System.out.println(CardArt.TEN);
        System.out.println(CardArt.REVERSE);
        System.out.println(CardArt.SKIP);
        System.out.println(CardArt.DRAW_TWO);
        System.out.println(CardArt.WILD);
        System.out.println(CardArt.WILD_DRAW_FOUR);

        UnoCard card = new UnoCard(Suit.DIAMONDS, CardTraits.REVERSE, Color.GREEN);
        UnoCard card1 = new UnoCard(Suit.DIAMONDS, CardTraits.REVERSE, Color.RED);
        UnoCard card2 = new UnoCard(Suit.DIAMONDS, CardTraits.REVERSE, Color.YELLOW);
        UnoCard card3 = new UnoCard(Suit.DIAMONDS, CardTraits.REVERSE, Color.BLUE);
        System.out.println(card.getCard());
        System.out.println(card1.getCard());
        System.out.println(card2.getCard());
        System.out.println(card3.getCard());


        //Lets Shuffle a Deck and Then Print EVERY CARD
        DeckofCards UnoDeck = new DeckofCards(GameType.UNO);

    }
}
