package smith.tarrayna.uno;

import smith.tarrayna.blackjack.Player;
import smith.tarrayna.cards.Card;
import smith.tarrayna.cards.DeckofCards;
import smith.tarrayna.cards.GameType;

class GameHelper  {
    private DeckofCards deckofCards;
    private Card[] communityHand;
    private Card [][] playerCards;
    Player player;
    private int numberOfPlayers;

    //Constructor for Game Helper
    GameHelper(int numberOfPlayers, int startingCash)
    {
        startNewGame();
    }

    public void startNewGame()
    {
        //Get a new deck of cards
        deckofCards = new DeckofCards(GameType.BASIC);

        //Update number of players
        this.numberOfPlayers = numberOfPlayers;

        //re deal the communnity hand
        communityHand = dealCommunityHand();

        //deal out the player hands
        playerCards = new Card[2][numberOfPlayers];
        dealPlayerCards();

        //Set up player array. Keeps track of what they are doing
        player = new Player();

    }

    //Generates the array that will store player hands
    public Card[][] generatePlayerHands (int numberOfPlayers)
    {
        return new Card[2][numberOfPlayers];
    }


    //Deals the first 5 cards to the community hand
    public Card[] dealCommunityHand()
    {
        //Create an array to store the community hand
        Card [] communityHand = new Card[5];

        //For each card take the corresponding card from the deck
        for(int i = 0; i < 5; i++)
        {
            communityHand[0] = deckofCards.getCard(0);
        }

        return communityHand;
    }

    public void dealPlayerCards()
    {
        //Place in deck counter. Start at 5 because community pot gets the first 5 (0-4)
        int deckPlace = 5;

        //Get the number of players
        int numberOfPlayers = playerCards[0].length;

        //Iterate through each player and give them 2 cards
        for(int pCounter = 0; pCounter < numberOfPlayers; pCounter++ )
        {
            //Give each player 2 cards
            for(int i = 0; i < 2; i++)
            {
                playerCards[i][pCounter] = deckofCards.getCard(deckPlace);
                deckPlace++;
            }
        }
    }


}
