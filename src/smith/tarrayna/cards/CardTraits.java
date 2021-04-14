package smith.tarrayna.cards;

public enum CardTraits {
    //TODO: Missing ACE
    ZERO(GameType.UNO, 0, CardArt.ZERO),ONE(GameType.ANY, 1, CardArt.ONE), TWO(GameType.ANY, 2, CardArt.TWO), THREE(GameType.ANY, 3, CardArt.THREE), FOUR(GameType.ANY, 4, CardArt.FOUR), FIVE(GameType.ANY, 5, CardArt.FIVE), SIX(GameType.ANY, 6, CardArt.SIX), SEVEN(GameType.ANY,7, CardArt.SEVEN), EIGHT(GameType.ANY,8, CardArt.EIGHT), NINE(GameType.ANY, 9, CardArt.NINE), TEN(GameType.ANY, 10, CardArt.TEN), JACK(GameType.BASIC, 11, CardArt.NOT_COMPLTE), QUEEN(GameType.BASIC, 12, CardArt.NOT_COMPLTE), KING(GameType.BASIC, 13, CardArt.NOT_COMPLTE), SKIP(GameType.UNO, 14, CardArt.SKIP), REVERSE(GameType.UNO, 15, CardArt.REVERSE), DRAW_TWO(GameType.UNO, 16, CardArt.DRAW_TWO), WILD(GameType.UNO, 17, CardArt.WILD), WILD_DRAW_FOUR(GameType.UNO, 18, CardArt.WILD_DRAW_FOUR);
    private final GameType gameType;
    private final int value;
    private String cardArt;

    private CardTraits(GameType gameType, int value, String cardArt)
    {
        this.gameType = gameType;
        this.value = value;
        this.cardArt = cardArt;
    }

    public GameType getGameType()
    {
        return gameType;
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
