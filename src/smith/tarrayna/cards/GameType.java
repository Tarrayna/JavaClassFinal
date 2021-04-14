package smith.tarrayna.cards;

public enum GameType {
    BASIC(52), ANY(-1), UNO(108);
    private final int numberOfCards;

    private GameType(int numberOfCards)
    {
        this.numberOfCards = numberOfCards;
    }

    public int getNumerbOfCards()
    {
        return numberOfCards;
    }
}
