
package smith.tarrayna.cards;

import java.util.Random;

public class DeckofCards {
    //Stores the values of the cube
    Card[] deckOfCards;
    Random random = new Random();

    //Consider making this abstract. And implementing the deck building based on a class that inherits from this
    //in other words overide the create Deck and then keep everything else the sameJ
    public DeckofCards(GameType gameType) {
        deckOfCards = new Card[gameType.getNumerbOfCards()];
        createDeck(gameType);
        shuffle(gameType);
    }

    //Creates the Deck to be used in the game
    private int createDeck(GameType gameType)
    {
        int cardsInDeck = 0;

        //Uno has 108 cards in Deck
        // One 0 of each color. Two 1-9 of each color. Two Reverse, Skip, Draw 2 Each color. 4 Wilds. 4 Wilds Draw Fours.
        if(gameType == GameType.UNO)
        {
            //Add each of the cards for each color
            for(Suit suit: Suit.values()){
                        //Add the appropriate type of card
                        for(CardTraits cardTrait : CardTraits.values())
                        {
                            switch (cardTrait)
                            {
                                case ZERO:
                                case WILD:
                                case WILD_DRAW_FOUR:
                                    deckOfCards[cardsInDeck] = new Card(suit, cardTrait);
                                    cardsInDeck++;
                                    break;
                                case ONE:
                                case TWO:
                                case THREE:
                                case FOUR:
                                case FIVE:
                                case SIX:
                                case SEVEN:
                                case EIGHT:
                                case NINE:
                                case REVERSE:
                                case SKIP:
                                case DRAW_TWO:
                                    deckOfCards[cardsInDeck] = new Card(suit, cardTrait);
                                    cardsInDeck++;
                                    deckOfCards[cardsInDeck] = new Card(suit, cardTrait);
                                    cardsInDeck++;
                                    break;
                                default:
                                    break;

                            }
                        }
                        cardsInDeck++;
                        break;
                }
        }
        else if (gameType == GameType.BASIC)
        {
            //A typical deck of cards will have 52 cards if not using Jokers
            for(Suit suit : Suit.values())
            {
                //Add a each type of card
                for(CardTraits cardTraits : CardTraits.values())
                {
                    if(cardTraits.getGameType() == GameType.BASIC || cardTraits.getGameType() == GameType.ANY)
                    {
                        deckOfCards[cardsInDeck] = new Card(suit,cardTraits);
                    }
                }
                cardsInDeck++;
            }
        }

        return cardsInDeck;
    }


    //Shuffle Deck 1000x
    private void shuffle(GameType gameType)
    {
        int count = 0;
        int swapOne;
        int swapTwo;

        do{
            //Figure out which cards to swap
            swapOne = random.nextInt(gameType.getNumerbOfCards() + 1);
            swapTwo = random.nextInt(gameType.getNumerbOfCards() + 1);

            //Hold card temp
            Card holder = deckOfCards[swapOne];

            //perform swap
            deckOfCards[swapOne] = deckOfCards[swapTwo];
            deckOfCards[swapTwo] = holder;

            count++;
        }while(count < 1000);
    }

    public Card getCard(int placeInDeck)
    {
        return deckOfCards[placeInDeck];
    }

}
