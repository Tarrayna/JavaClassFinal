package smith.tarrayna.blackjack;

import smith.tarrayna.cards.Card;
import smith.tarrayna.cards.DeckofCards;
import smith.tarrayna.cards.GameType;

class GameHelper {
    private DeckofCards deckofCards;
    private Card[] dealerHand;
    private Card[] playerHand;
    private int placeInDeck;

    public GameHelper()
    {
        //Get a new deck of cards
        deckofCards = new DeckofCards(GameType.BASIC);
        placeInDeck = 0;
    }


    //Deals the first 5 cards to the community hand
    public Card dealCard()
    {
        return deckofCards.getCard(placeInDeck++);
    }

}
