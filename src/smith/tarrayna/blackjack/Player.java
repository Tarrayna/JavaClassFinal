package smith.tarrayna.blackjack;

import smith.tarrayna.cards.Card;
import smith.tarrayna.cards.CardValue;

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

    public int getNumberCardsInHandOne()
    {
        return numberCardsInHandOne;
    }
    public int getNumberCardsInHandTwo()
    {
        return numberCardsInHandTwo;
    }

    public int getNumberOfPoints()
    {
        return points;
    }

    public boolean canKeepPlaying()
    {
        //Check to see if player is using 2 hands
        if(isSplit)
        {
            return (calculateHandValue(Hand.HAND_ONE) <= 21 && calculateHandValue(Hand.HAND_TWO) <= 21);
        }

        return (calculateHandValue(Hand.HAND_ONE) <= 21);
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
        for(int i = 0; i < aceCounter; i++)
        {
            //Only check last ace for 11
            if(i == aceCounter -1)
            {
                //Check to see if an 11 will take you over. And since last card. Return
                if(value + 11 < 21)
                {
                    return value+ 11;
                }
            }

            //All other aces add 1
            value++;
        }

        return value;
    }

    public void setDoubleDown()
    {
        doubleDown = true;
    }
    public boolean isDealer()
    {
        return isDealer;
    }
    public boolean isSplit()
    {
        return isSplit;
    }

    public boolean canPlayerPlaySecondHand()
    {
        if(!isSplit() || calculateHandValue(Hand.HAND_TWO) > 21)
            return false;

        return true;
    }

    public boolean canSplit()
    {
        //Check that there are cards there
        if(playerHand[0][0] == null || playerHand[1][0] == null)
            return false;

        if(playerHand[0][0].getCardValue().getCardValue() == playerHand[1][0].getCardValue().getCardValue())
            return true;
        return false;
    }

    public void split()
    {
        if(canSplit())
        {
            Card temp = playerHand[0][1];
            playerHand[0][1] = null;
            playerHand[1][0]  = temp;
            isSplit = true;
        }
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
            return  (isSplit() == false || calculateHandValue(handNumber) > 21)? false : true;
        }

        return  (calculateHandValue(handNumber) > 21)? false : true;
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