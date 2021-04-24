
package smith.tarrayna.cards;

import java.util.Random;

//This should really be an interface. But whateves
public class DeckofCards {
    //Stores the values of the cube
    Card[] deckOfCards;
    Random random = new Random();

    //Consider making this abstract. And implementing the deck building based on a class that inherits from this
    //in other words override the create Deck and then keep everything else the sameJ
    public DeckofCards(GameType gameType) {
        deckOfCards = new Card[gameType.getNumerbOfCards()];
        createDeck(gameType);
        shuffle(gameType);
    }

    //Creates the Deck to be used in the game
    private void createDeck(GameType gameType) {
        int cardsInDeck = 0;

            //A typical deck of cards will have 52 cards if not using Jokers
            for (Suit suit : Suit.values()) {
                //Add a each type of card
                for (CardTraits cardTraits : CardTraits.values()) {
                    if (cardTraits.getGameType() == GameType.BASIC || cardTraits.getGameType() == GameType.ANY) {
                        deckOfCards[cardsInDeck] = new Card(suit, cardTraits, Color.BLACK);
                    }
                }
                cardsInDeck++;

    }
    }

    //Shuffle Deck 1000x
    private void shuffle(GameType gameType) {
        int count = 0;
        int swapOne;
        int swapTwo;

        do {
            //Figure out which cards to swap
            swapOne = random.nextInt(gameType.getNumerbOfCards() + 1);
            swapTwo = random.nextInt(gameType.getNumerbOfCards() + 1);

            //Hold card temp
            Card holder = deckOfCards[swapOne];

            //perform swap
            deckOfCards[swapOne] = deckOfCards[swapTwo];
            deckOfCards[swapTwo] = holder;

            count++;
        } while (count < 1000);
    }

    public Card getCard(int placeInDeck) {
        return deckOfCards[placeInDeck];
    }

    public Card[] getDeck() {
        return deckOfCards;
    }

    public void printDeck(){
        for(Card card: deckOfCards)
        {
        }
    }

}
