package smith.tarrayna.cards;

public enum Suit
    {
        YELLOW(Color.YELLOW, GameType.UNO),RED( Color.RED,GameType.UNO), GREEN(Color.GREEN,GameType.UNO),BLUE(Color.BLUE,GameType.UNO),SPADES(Color.BLACK,GameType.BASIC), HEARTS(Color.RED,GameType.BASIC), CLUBS(Color.BLACK,GameType.BASIC), DIAMONDS(Color.RED,GameType.BASIC), JOKER( Color.CLEAR,GameType.UNDEFINED);
        private final Color cardColor;
        private final GameType gameType;

         Suit(Color cardColor, GameType gameType)
        {
            this.cardColor = cardColor;
            this.gameType = gameType;
        }

        public Color getCardColor()
        {
            return cardColor;
        }
        public GameType getGameType()
        {
            return gameType;
        }
}
