
package smith.tarrayna.cards;

import java.util.Random;

//This should really be an interface. But whateves
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

        for(CardValue cardValue : CardValue.values())
        {
            //Get each color
            for(Suit suit : Suit.values())
            {
                    deckOfCards[placeInDeck] = new Card(suit,cardValue);
                    placeInDeck++;
            }
        }


    }

    //Shuffle Deck 1000x
    public void shuffle() {
        int count = 0;
        int swapOne;
        int swapTwo;

        do {
            //Figure out which cards to swap
            swapOne = random.nextInt(getSizeOfDeck() - 1);
            swapTwo = random.nextInt(getSizeOfDeck() -1);

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
    public int getSizeOfDeck(){
        return deckOfCards.length;
    }

}
