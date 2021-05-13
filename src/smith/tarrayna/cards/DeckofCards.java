
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

    /**
     * Constructor
     */
    public DeckofCards() {
        deckOfCards = new Card[DECK_SIZE];
        createDeck();
        shuffle();
    }

    /**
     * Creates deck that will be used in game.
     *
     * @return void
     * Project Requirement 2 For, while, do/while, and enhanced for loops should be used to keep the project running until the user wishes to end.
     * At least 3 of the loops mentioned should be used. (15 points) 2 of 3
     */
    private void createDeck() {
        //Go through each card value
        int placeInDeck = 0;

        //Add each value of card for each suit to deck
        for (CardValue cardValue : CardValue.values()) {
            //Get each color
            for (Suit suit : Suit.values()) {
                deckOfCards[placeInDeck] = new Card(suit, cardValue);
                placeInDeck++;
            }
        }


    }

    /**
     *Shuffles a deck 1000x
     *
     * @return void
     * Project Requirement 2 For, while, do/while, and enhanced for loops should be used to keep the project running until the user wishes to end.
     * At least 3 of the loops mentioned should be used. (15 points) 3 of 3
     */
    public void shuffle() {
        int maxCount = 1000;
        int count = 0;
        int swapOne;
        int swapTwo;
        int decrement = -1;


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

    /**
     * Returns card in position indicated
     *
     * @param placeInDeck where in deck to grab card to return
     *
     * @return card in that place in deck
     */
    public Card getCard(int placeInDeck) {
        return deckOfCards[placeInDeck];
    }

    /**
     * Gets the size of the deck
     *
     * @return size of the deck
     */
    public int getSizeOfDeck(){
        return deckOfCards.length;
    }

}
