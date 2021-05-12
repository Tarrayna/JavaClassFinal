
package smith.tarrayna.cards;

import java.util.Random;
/**
 * DeckofCards. Deck of cards in which the game will be played from.
 *
 * @author Tarrayna Smith
 * Final Project
 */
public class DeckofCards {
    private final int DECK_SIZE = 52;
    private Card[] deckOfCards;
    private Random random = new Random();

    //Consider making this abstract. And implementing the deck building based on a class that inherits from this
    //in other words override the create Deck and then keep everything else the sameJ
    public DeckofCards() {
        deckOfCards = new Card[DECK_SIZE];
        createDeck();
        shuffle();
    }

    //Creates the Deck to be used in the game
    private void createDeck() {
        //Go through each card value
        int placeInDeck = 0;

        //2. For, while, do/while, and enhanced for loops should be used to keep the project running until the user wishes to end.
        // At least 3 of the loops mentioned should be used. (15 points) 2 of 3
        for (CardValue cardValue : CardValue.values()) {
            //Get each color
            for (Suit suit : Suit.values()) {
                deckOfCards[placeInDeck] = new Card(suit, cardValue);
                placeInDeck++;
            }
        }


    }

    //Shuffle Deck 1000x
    public void shuffle() {
        int maxCount = 1000;
        int count = 0;
        int swapOne;
        int swapTwo;
        int decrement = -1;

        //2. For, while, do/while, and enhanced for loops should be used to keep the project running until the user wishes to end.
        // At least 3 of the loops mentioned should be used. (15 points) 3 of 3
        do {
            //Figure out which cards to swap
            swapOne = random.nextInt(getSizeOfDeck() + decrement);
            swapTwo = random.nextInt(getSizeOfDeck() + decrement);

            //Hold card temp
            Card holder = deckOfCards[swapOne];

            //perform swap
            deckOfCards[swapOne] = deckOfCards[swapTwo];
            deckOfCards[swapTwo] = holder;

            count++;
        } while (count < maxCount);
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
    public int getSizeOfDeck(){
        return deckOfCards.length;
    }

}
