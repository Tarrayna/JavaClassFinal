package smith.tarrayna.blackjack;

import smith.tarrayna.cards.Card;
import smith.tarrayna.cards.CardValue;

import java.security.InvalidParameterException;


/**
 * Enum that defines hand one and hand two. Used when splitting a deck, to ensure only valid hands are modified and used
 *
 * @author Tarrayna Smith
 */
enum Hand {
    HAND_ONE(0), HAND_TWO(1);
    private final int value;

    Hand(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

/**
 * Defines a player. As well as what a player can do
 * TODO: Enhancement idea. Make this an interface and have player and dealer implement.
 *
 * @author Tarrayna Smith
 * Final Project
 */
public class Player {
    private int cashOnHand;
    private Card[][] playerHand;
    private int points;
    private int numberCardsInHandOne;
    private int numberCardsInHandTwo;
    private boolean isSplit;
    private boolean isDealer;

    private final int NUMBER_OF_CARDS = 14;
    private final int NUMBER_OF_HANDS = 2;
    private final int USING_HAND_ONE = 0;
    private final int USING_HAND_TWO = 1;
    private final int CARD_ONE = 0;
    private final int CARD_TWO = 1;

    /**
     * Player constructor. Builds the dealer.
     *
     * @param isDealer this constructor will only work if we are creating a dealer. Otherwise throw an exception
     *
     * @return void
     */
    public Player(boolean isDealer) {
        if (!isDealer) throw new IllegalArgumentException("This Constuctor Can Only be Used For Creating a Dealer");
        cashOnHand = Integer.MAX_VALUE;
        this.isDealer = isDealer;
        resetPlayer();
    }

    /**
     * Player constructor. Builds the player.
     *
     * @param isDealer this constructor will only work if we are creating a player. Otherwise throw an exception
     * @param startingCash sets the starting cash for the player
     *
     * @return void
     */
    public Player(boolean isDealer, int startingCash) {
        if (isDealer) throw new IllegalArgumentException("This Constuctor Can Only be Used For Creating a Player");
        this.isDealer = isDealer;
        this.cashOnHand = startingCash;
        resetPlayer();
    }

    /**
     * Returns the number of cards in a specific hand
     *
     * @param handNumber the hand in which to returnt he number of cards
     *
     * @return void
     */
    public int getNumberOfCardsInHand(Hand handNumber) {

        if (handNumber == Hand.HAND_ONE) {
            return numberCardsInHandOne;
        } else {
            return numberCardsInHandTwo;
        }
    }

    /**
     * Gets the cash the player currently has on hand
     *
     * @return the cash the player has one hand
     */
    public int getCashOnHand() {
        return cashOnHand;
    }

    /**
     * Sets the starting cash for the player
     *
     * @param cash the starting cash for the player
     *
     * @return void
     */
    public void setStartCash(int cash) {
        cashOnHand = cash;
    }

    /**
     * Either give or removes money from the player
     *
     * @param how much to give or take from player
     *
     * @return void
     */
    public void fullFillBet(int bet) {

        cashOnHand += bet;
    }

    /**
     * Resets the player for starting a new round. Creates an empty hand. And sets round values to zero
     *
     * @return void
     */
    public void resetPlayer() {
        playerHand = new Card[NUMBER_OF_HANDS][NUMBER_OF_CARDS];
        points = 0;
        numberCardsInHandOne = 0;
        numberCardsInHandTwo = 0;
        isSplit = false;
    }

    /**
     * Returns the hand given as a parameter
     *
     * @param handNumber hand to be returned
     *
     * @return the chosen hand
     * Requirement 2 For, while, do/while, and enhanced for loops should be used to keep the project running until the user wishes to end.
     * At least 3 of the loops mentioned should be used. (15 points) 3 of 3
     * 
     * Requirement 6 Arrays: You need to use at least a single dimensional array and a multi
     * dimensional array somewhere in the application. There must also be an array that stores a collection of one of the classes you create. (15 points)
     */
    public Card[] getHand(Hand handNumber) {
        int zero = 0;
        Card[] hand = new Card[playerHand[0].length];

        switch (handNumber) {
            case HAND_ONE:
                for (int i = zero; i < hand.length; i++) {
                    hand[i] = playerHand[USING_HAND_ONE][i];
                }
                break;
            case HAND_TWO:
                for (int i = zero; i < hand.length; i++) {
                    hand[i] = playerHand[USING_HAND_TWO][i];
                }
                break;
            default:
                throw new InvalidParameterException("Invalid Player Hand Selection in getHand()");
        }

        return hand;
    }

    /**
     * Checks to see if player can keep playing. (Hand value does not exceed 20)
     *
     * @param handNumber the hand in which to check
     *
     * @return whether that hand can still be played on
     */
    public boolean canKeepPlaying(Hand handNumber) {

        if (calculateHandValue(handNumber) >= GameHelper.BLACKJACK) {
            //TODO: consider making printHand a static method.
            new GameHelper().printHand(this);
            return false;
        }
        return true;
    }

    /**
     * Calculates the value of a hand. Takes into consideration that Aces can have a value of 1 or 11
     *
     * @param handNumber the hand to calculate value
     *
     * @return value of hand
     */
    public int calculateHandValue(Hand handNumber) {
        int zero = 0;
        int value = 0;
        int aceCounter = 0;

        //Add up non ace cards. Keep track of aces
        for (int i = zero; i < playerHand[zero].length; i++) {
            //Hand is nothing. Do nothing. Points is 0
            if (playerHand[handNumber.getValue()][i] == null) {
                continue;
            }

            //Wait until the end to count Aces
            if (playerHand[handNumber.getValue()][i].getCardValue() == CardValue.ACE) {
                aceCounter++;
                continue;
            }
            //TODO: Really should change the names of these
            value += playerHand[handNumber.getValue()][i].getCardValue().getCardValue();
        }

        //Add in Aces. 2 Aces cannot = 11. So if more than 1 ace. Only verify value of last Ace
        int highAce = 11;
        for (int i = zero; i < aceCounter; i++) {
            //Only check last ace for 11
            if (i == aceCounter - 1) {
                //Check to see if an 11 will take you over. And since last card. Return
                if (value + highAce <= GameHelper.BLACKJACK) {
                    return value + highAce;
                }
            }

            //All other aces add 1
            value++;
        }

        return value;
    }

    /**
     * Check to see if player is dealer
     *
     * @return true if player is the dealer. False otherwise
     */
    public boolean isDealer() {
        return isDealer;
    }

    /**
     * Checks to see if hand was split
     *
     * @return true if hand was split. False otherwise
     */
    public boolean isSplit() {
        return isSplit;
    }

    /**
     * Checks to see if hand can be split. This means that the same type of card is drawn for the first two cards.
     * (i.e 2 Aces or 2 ones). And the player has enough cash on hand to double their bet. And the hand has not
     * already been split.
     *
     * @param currentBet the current bet on the table. This will be compared to cash on hand
     *
     * @return true if the hand can be split. False otherwise
     */
    public boolean canSplit(int currentBet) {
        int doubleBet = 2;

        if (getCashOnHand() < (currentBet * doubleBet) || isSplit) {
            return false;
        }
        try {
            if (playerHand[USING_HAND_ONE][CARD_ONE].getCardValue().getName()
                    .equals(playerHand[USING_HAND_ONE][CARD_TWO].getCardValue().getName()))
                return true;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
        return false;
    }

    /**
     * Splits the players hand. Sets both hands to 1 card. Sets the split boolean to true.
     *
     * @return void
     */
    public void split() {

        Card temp = playerHand[USING_HAND_ONE][CARD_TWO];
        playerHand[USING_HAND_ONE][CARD_TWO] = null;
        playerHand[USING_HAND_TWO][CARD_ONE] = temp;
        isSplit = true;

        updateCardsCountsForSplit();
    }

    /**
     * Takes a card and puts it into the desired hand
     *
     * @param handNumber
     *
     * @return void
     */
    public void takeCard(Hand handNumber, Card card) {
        //Get the current place in hand and then increment it
        int placeInHand = (handNumber.getValue() != USING_HAND_ONE) ? numberCardsInHandTwo++ : numberCardsInHandOne++;

        //Put card into new place in hand
        playerHand[handNumber.getValue()][placeInHand] = card;
    }

    /**
     * Updates the number of cards in hands after splitting
     *
     * @return void
     */
    private void updateCardsCountsForSplit() {
        numberCardsInHandTwo = 1;
        numberCardsInHandTwo = 1;
    }
}