/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smith.tarrayna.blackjack;

import smith.tarrayna.cards.Suit;

import java.util.Scanner;

public class BlackJackRunner {

    public static void main(String[] args)  {
        Scanner scanner = new Scanner(System.in);
        //Game Helper Will Take Care of Most Game Functionality
        //Deck of cards will only exist in this Helper Function
       // GameHelper gameHelper = new GameHelper(new DeckofCards());

        System.out.println("Poker Game!!");
        System.out.print("Enter number of players (1 - 9): ");

        System.out.print(Suit.DIAMONDS.getImage());
    }
}
