package smith.tarrayna.cards;

public enum Suit
    {
        SPADES("\u001B[44m", (char) 6, Color.BLUE), HEARTS("\u001B[41m", (char)3,Color.GREEN), CLUBS("\u001B[32m", (char)5, Color.RED), DIAMONDS("\u001B[43m", (char)4, Color.YELLOW), JOKER("color", (char) 6, Color.WILD);
        private final String color;
        private final char image;
        private final Color cardColor;

        private Suit(String color, char image, Color cardColor)
        {
            this.color = color;
            this.image = image;
            this.cardColor = cardColor;
        }

        public String getColor()
        {
            return color;
        }

        public char getImage()
        {
            return image;
        }

        public Color getCardColor()
        {
            return cardColor;
        }
}
