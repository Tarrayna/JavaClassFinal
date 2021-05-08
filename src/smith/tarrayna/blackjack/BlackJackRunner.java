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

    public void runGame(Scanner scanner,GameHelper gameHelper)  {

        System.out.println("Let's Play Black Jack!!");
        gameHelper.setCurrentBet();

        playerBorder();
        gameHelper.playerTurn(scanner);

        dealerBorder();
        gameHelper.dealerTurn();

        handCompleteRunner(gameHelper);
        endGameBorder();

    }
    public void handCompleteRunner(GameHelper gameHelper)
    {
        System.out.println("Player Main Hand Results");
        gameHelper.determineWinner(gameHelper.getPlayer(), gameHelper.getDealer(), Hand.HAND_ONE);

        if(gameHelper.getPlayer().isSplit())
        {
            System.out.println("Player Split Hand Results");
            gameHelper.determineWinner(gameHelper.getPlayer(), gameHelper.getDealer(), Hand.HAND_TWO);

        }
    }

    //These are intentionally left in. For quickly searching in terminal
    public void endGameBorder()
    {
        System.out.printf("************************************************\n" +
                          "************************************************\n");
    }
    public void playerBorder(){
        System.out.printf("pppppppppppppppppppppppppppppppppppppppppp\n" +
                          "pppppppppppppppppppppppppppppppppppppppppp\n");
    }

    public void dealerBorder()
    {
        System.out.printf("ddddddddddddddddddddddddddddddddddddddd\n" +
                          "ddddddddddddddddddddddddddddddddddddddd\n");
    }



}
