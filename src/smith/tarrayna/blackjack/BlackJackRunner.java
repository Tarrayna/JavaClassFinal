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
    GameHelper gameHelper;
    Double playerBuyIn;

    public void runGame(Scanner scanner, Double buyIn)  {
        //Max possible hand in 14 cards
        Player player = new Player(false, buyIn);
        Player dealer = new Player(true);

        GameHelper gameHelper = new GameHelper(buyIn);
        System.out.println("Let's Play Black Jack!!");

        int playerValue = gameHelper.playerTurn(player,scanner);
        int dealerValue = gameHelper.dealerTurn(dealer);

        switch(gameHelper.determineWinner(playerValue, dealerValue))
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
                break;
            case 0:
                System.out.println("The Game is a Tie!");
                break;
        }

    }

    public void dealStartingHand()
    {

    }

}
