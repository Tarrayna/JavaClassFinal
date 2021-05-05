
package smith.tarrayna.cards;

import java.util.Random;

//This should really be an interface. But whateves
public class DeckofCards {
    private Card[] deckOfCards;
    private Random random = new Random();

    //Consider making this abstract. And implementing the deck building based on a class that inherits from this
    //in other words override the create Deck and then keep everything else the sameJ
    public DeckofCards(GameType gameType) {
        deckOfCards = new Card[gameType.getNumberOfCards()];
        createDeck(gameType);
        shuffle();
    }

    //Creates the Deck to be used in the game
    private void createDeck(GameType gameType) {
       //Go through each card value
        int placeInDeck = 0;

        for(CardValue cardValue : CardValue.values())
        {
            //Check to see if card type is part of game
            if(gameType != cardValue.getGameType() && cardValue.getGameType() != GameType.ANY)
            {
                continue;
            }
            //Get each color
            for(Suit suit : Suit.values())
            {
                //Make sure suit is part of game
                if(gameType == suit.getGameType() || suit.getGameType() == GameType.ANY)
                {
                    deckOfCards[placeInDeck] = new Card(suit,cardValue);
                    placeInDeck++;
                }

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
