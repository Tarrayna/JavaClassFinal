/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smith.tarrayna.blackjack;

import smith.tarrayna.cards.Card;
import smith.tarrayna.cards.CardValue;
import smith.tarrayna.cards.Suit;

import java.util.Scanner;

public class BlackJackRunner {
    GameHelper gameHelper = new GameHelper();

    public void runGame(Scanner scanner)  {
        //Max possible hand in 14 cards
        Card [] playerHand = new Card[14];
        Card [] dealerHand = new Card[14];

        int playerHandCount = 0;
        int dealerHandCount = 0;


        GameHelper gameHelper = new GameHelper();
        System.out.println("Let's Play Black Jack!!");

        int playerValue = playerTurn(playerHand, scanner);
        int dealerValue = dealerTurn(dealerHand);

        switch(determineWinner(playerValue, dealerValue))
        {
            case 1:
                if(playerValue == 21)
                {
                    System.out.print("Nice Blackjack! ");
                }

                System.out.println("You win!!");
                break;
            case -1:
                System.out.println("Dealer Wins. Nice try!");
            case 0:
                System.out.println("The Game is a Tie!");
        }

    }

    public int playerTurn(Card [] hand, Scanner scanner)
    {
        int playerHandValue  = 0;
        int playerCardCount = 0;

        do
        {
            System.out.println("Current hand:");
            hit(hand, playerHandValue, playerCardCount);
            printHand(hand);

        }while(promptUserHitOrBust(scanner));

        return playerHandValue;
    }

    public int dealerTurn(Card [] hand)
    {
        int dealerHandValue  = 0;
        int dealerHandCount = 0;

        do {
            printHand(hand);
            hit(hand, dealerHandValue, dealerHandCount);

        }while(calculateHandValue(hand) < 17);

        return dealerHandValue;
    }

    public int calculateHandValue(Card [] hand)
    {
        int value = 0;
        int aceCounter = 0;

        //Add up non ace cards. Keep track of aces
        for(int i = 0; i < hand.length; i++)
        {

            if(hand[i] == null)
            {
                continue;
            }
            if(hand[i].getCardValue() == CardValue.ACE)
            {
                aceCounter++;
                continue;
            }
            //TODO: Really should change the names of these
            value += hand[i].getCardValue().getCardValue();
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

    public boolean promptUserHitOrBust(Scanner scanner)
    {
        System.out.println("Hit(1) or Stand(0)?");
        String userInput = scanner.nextLine().toUpperCase();

        switch (userInput)
        {
            case "HIT":
            case "1":
                return true;
            case "STAND":
            case "0":
                return false;
            default:
                System.out.println(userInput + " is not a valid input. Try again.");
                return promptUserHitOrBust(scanner);
        }
    }

    //TODO: This is not incrementing values properly
    public void hit(Card [] hand, int value, int count)
    {
        hand [count++] = gameHelper.dealCard();
        value = calculateHandValue(hand);
    }

    public int determineWinner(int playerValue, int dealerValue)
    {
        //Check for ties. That would be points are even. Or both player busted
        if(dealerValue == playerValue || ((dealerValue < playerValue) && dealerValue > 21))
        {
            return 0;
        }

        //Check for player is winner
        if((playerValue > dealerValue) && playerValue <= 21)
        {
            return 1;
        }

        //The Dealer Won
        return -1;
    }

    public void printHand(Card [] hand)
    {
        for(Card card : hand)
        {
            if(card != null)
            {
                System.out.println(card.getCard());
            }

        }
    }

}
