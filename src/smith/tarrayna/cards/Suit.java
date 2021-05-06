package smith.tarrayna.cards;

public enum Suit
    {
        PADES(Color.BLACK), HEARTS(Color.RED), CLUBS(Color.BLACK), DIAMONDS(Color.RED), JOKER( Color.CLEAR);
        private final Color cardColor;
        
         Suit(Color cardColor)
        {
            this.cardColor = cardColor;

        }

        public Color getCardColor()
        {
            return cardColor;
        }
}
