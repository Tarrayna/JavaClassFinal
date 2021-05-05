package smith.tarrayna.uno;

import smith.tarrayna.cards.*;

import java.util.Scanner;

public class UnoGameRunner {
    public void runGame(Scanner scanner)
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

        Card card = new Card(Suit.DIAMONDS, CardValue.REVERSE);
        Card card1 = new Card(Suit.DIAMONDS, CardValue.REVERSE);
        Card card2 = new Card(Suit.DIAMONDS, CardValue.REVERSE);
        Card card3 = new Card(Suit.DIAMONDS, CardValue.REVERSE);
        System.out.println(card.getCard());
        System.out.println(card1.getCard());
        System.out.println(card2.getCard());
        System.out.println(card3.getCard());


        //Lets Shuffle a Deck and Then Print EVERY CARD
        DeckofCards UnoDeck = new DeckofCards(GameType.UNO);

    }
}
