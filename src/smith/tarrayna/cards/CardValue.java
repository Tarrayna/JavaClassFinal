package smith.tarrayna.cards;

/**
 * CardValue. Contains the numerical value, card art and name of the card.
 *
 * @author Tarrayna Smith
 * Final Project
 */
public enum CardValue {
    TWO(2, CardArt.TWO, "TWO"), THREE(3, CardArt.THREE, "THREE"), FOUR(4, CardArt.FOUR, "FOUR"), FIVE(5, CardArt.FIVE,
            "FIVE"), SIX(6, CardArt.SIX, "SIX"), SEVEN(7, CardArt.SEVEN, "SEVEN"), EIGHT(8, CardArt.EIGHT,
            "EIGHT"), NINE(9, CardArt.NINE, "NINE"), TEN(10, CardArt.TEN, "TEN"), JACK(10, CardArt.JACK, "JACK"), QUEEN(
            10, CardArt.QUEEN, "QUEEN"), KING(10, CardArt.KING, "KING"), ACE(11, CardArt.ACE, "ACE");
    private final int value;
    private String cardArt;
    private final String name;

    CardValue(int value, String cardArt, String name) {
        this.value = value;
        this.cardArt = cardArt;
        this.name = name;
    }


    public int getCardValue() {
        return value;
    }

    public String getCardArt() {
        return cardArt;
    }

    public String getName() {
        return name;
    }


}
