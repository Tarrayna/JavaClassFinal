package smith.tarrayna.cards;

public enum CardValue {
    TWO(2, CardArt.TWO), THREE(3, CardArt.THREE), FOUR(4, CardArt.FOUR), FIVE(5, CardArt.FIVE), SIX(6, CardArt.SIX), SEVEN(7, CardArt.SEVEN), EIGHT(8, CardArt.EIGHT), NINE( 9, CardArt.NINE), TEN(10, CardArt.TEN), JACK(10, CardArt.JACK), QUEEN(10, CardArt.QUEEN), KING(10, CardArt.KING), ACE(11, CardArt.ACE);
    private final int value;
    private String cardArt;

    private CardValue(int value, String cardArt)
    {
        this.value = value;
        this.cardArt = cardArt;
    }


    public int getCardValue()
    {
        return value;
    }

    public String getCardArt()
    {
        return cardArt;
    }
}
