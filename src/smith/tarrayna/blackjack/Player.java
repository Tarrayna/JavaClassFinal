package smith.tarrayna.blackjack;

import smith.tarrayna.cards.Card;
import smith.tarrayna.cards.CardValue;
import smith.tarrayna.cards.Suit;

import java.security.InvalidParameterException;

public class Player {
    private int cashOnHand;
    private Card [][] playerHand;
    private int points;
    private int numberCardsInHandOne ;
    private int numberCardsInHandTwo ;
    private boolean isSplit ;
    private boolean standHandOne ;
    private boolean standHandTwo;
    private boolean doubleDown;
    private boolean isDealer;

    private final int NUMBER_OF_CARDS = 14;
    private final int NUMBER_OF_HANDS = 2;
    private final int USING_HAND_ONE = 0;
    private final int USING_HAND_TWO = 1;
    private final int CARD_ONE = 0;
    private final int CARD_TWO = 1;

    public Player(boolean isDealer){
        if(!isDealer) throw new IllegalArgumentException("This Constuctor Can Only be Used For Creating a Dealer");
        cashOnHand =  Integer.MAX_VALUE;
        this.isDealer = isDealer;
       resetPlayer();
    }

    public Player(boolean isDealer, int startingCash){
        if(isDealer) throw new IllegalArgumentException("This Constuctor Can Only be Used For Creating a Player");
        this.isDealer = isDealer;
        this.cashOnHand = startingCash;
        resetPlayer();
    }

    public int getCashOnHand()
    {
        return cashOnHand;
    }

    public void setStartCash(int cash)
    {
        cashOnHand = cash;
    }

    public void fullFillBet(int bet)
    {

        cashOnHand += bet;
    }

    public void resetPlayer()
    {
        playerHand = new Card[NUMBER_OF_HANDS][NUMBER_OF_CARDS];
        points = 0;
        numberCardsInHandOne = 0;
        numberCardsInHandTwo = 0;
        isSplit = false;
        standHandOne = false;
        standHandTwo = false;
        doubleDown = false;
    }

    public Card[] getHand(Hand handNumber)
    {
        Card [] hand = new Card [playerHand[0].length];

        switch(handNumber)
         {
             case HAND_ONE:
                 for(int i = 0; i < hand.length; i++)
                 {
                     hand[i] = playerHand[USING_HAND_ONE][i];
                 }
                 break;
             case HAND_TWO:
                 for(int i = 0; i < hand.length; i++)
                 {
                     hand[i] = playerHand[USING_HAND_TWO][i];
                 }
                 break;
             default:
                 throw new InvalidParameterException("Invalid Player Hand Selection in getHand()");
         }

         return hand;
    }

    public boolean canKeepPlaying(Hand handNumber)
    {
        //Check to see if player is using 2 hands
        if(calculateHandValue(handNumber) >= GameHelper.BLACKJACK)
        {
            return false;
        }
        return true;
    }

    public int calculateHandValue(Hand handNumber)
    {
        int value = 0;
        int aceCounter = 0;

        //Add up non ace cards. Keep track of aces
        for(int i = 0; i < playerHand[0].length; i++)
        {
            //Hand is nothing. Do nothing. Points is 0
            if(playerHand[handNumber.getValue()][i] == null)
            {
                continue;
            }

            //Wait until the end to count Aces
            if(playerHand[handNumber.getValue()][i].getCardValue() == CardValue.ACE)
            {
                aceCounter++;
                continue;
            }
            //TODO: Really should change the names of these
            value += playerHand[handNumber.getValue()][i].getCardValue().getCardValue();
        }

        //Add in Aces. 2 Aces cannot = 11. So if more than 1 ace. Only verify value of last Ace
        int highAce = 11;
        for(int i = 0; i < aceCounter; i++)
        {
            //Only check last ace for 11
            if(i == aceCounter -1)
            {
                //Check to see if an 11 will take you over. And since last card. Return
                if(value + highAce <= GameHelper.BLACKJACK)
                {
                    return value+ highAce;
                }
            }

            //All other aces add 1
            value++;
        }

        return value;
    }

    public boolean isDealer()
    {
        return isDealer;
    }
    public boolean isSplit()
    {
        return isSplit;
    }


    public boolean canSplit(int currentBet)
    {
        if(getCashOnHand() < (currentBet * 2))
        {
            return false;
        }
        try
        {
            if(playerHand[USING_HAND_ONE][CARD_ONE].getCardValue()== playerHand[USING_HAND_ONE][CARD_TWO].getCardValue())
                return true;
        }catch(IndexOutOfBoundsException e)
        {
            return false;
        }

        return false;
    }

    public void split()
    {

            Card temp = playerHand[USING_HAND_ONE][CARD_TWO];
            playerHand[USING_HAND_ONE][CARD_TWO] = null;
            playerHand[USING_HAND_TWO][CARD_ONE]  = temp;
            isSplit = true;
            numberCardsInHandOne = 1;
            numberCardsInHandTwo = 1;
    }

    public void getCard(Hand handNumber, Card card)
    {
       int placeInHand = (handNumber.getValue() != USING_HAND_ONE) ? numberCardsInHandTwo++ : numberCardsInHandOne++;
        playerHand[handNumber.getValue()][placeInHand] = card;
    }

    public boolean playerCanHit(Hand handNumber)
    {
        if(handNumber == Hand.HAND_TWO)
        {
            return  (isSplit() == false || calculateHandValue(handNumber) > GameHelper.BLACKJACK)? false : true;
        }

        return  (calculateHandValue(handNumber) > GameHelper.BLACKJACK)? false : true;
    }

    //TODO: Delete test code
    public void testPlayer()
    {
        playerHand[0][0] = new Card(Suit.HEARTS, CardValue.ACE);
        playerHand[0][1] = new Card(Suit.SPADES, CardValue.ACE);
    }
}

enum Hand{
    HAND_ONE(0), HAND_TWO(1);
    private final int value;

    private Hand(int value)
    {
        this.value = value;
    }

    public int getValue()
    {
        return value;
    }
}