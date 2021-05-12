package smith.tarrayna.cards;

/**
 * Suit. Defines the suit and color of a card.
 *
 * @author Tarrayna Smith
 * Final Project
 */
public enum Suit
    {
        SPADES(Color.BLACK), HEARTS(Color.RED), CLUBS(Color.BLACK), DIAMONDS(Color.RED);
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
