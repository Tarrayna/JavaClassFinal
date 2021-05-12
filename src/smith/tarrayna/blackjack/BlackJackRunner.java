package smith.tarrayna.blackjack;

import java.util.Scanner;
/**
 * BlackJackRunner. An instance of this class will run the game, based on an instance of GameHelper
 *
 * @author Tarrayna Smith
 * Final Project
 */
public class BlackJackRunner {

    /**
     * Runs a round of the game. Giving the user a chance to play out thier turn. And the the computer. Once the round is
     * completes game and determines winner
     *
     * @param scanner used for getting the user input
     * @param gameHelper used for tracking game data. And running basic functionality
     *
     * @return void
     */
    public void runRound(Scanner scanner, GameHelper gameHelper) {

        System.out.println("Let's Play Black Jack!!");

        gameHelper.setCurrentBet();

        playerBorder(); //Readability border
        gameHelper.playerTurn(scanner);

        dealerBorder(); //Readability border
        gameHelper.dealerTurn();

        handCompleteRunner(gameHelper);
        endGameBorder(); //Readability border

    }

    /**
     * Detemines whether the player has 2 hands. And will determine winner of each hand.
     *
     * @param gameHelper used for tracking game data. And running basic functionality
     *
     * @return void
     */
    public void handCompleteRunner(GameHelper gameHelper) {
        System.out.println("Player Main Hand Results");
        gameHelper.determineWinner(gameHelper.getPlayer(), gameHelper.getDealer(), Hand.HAND_ONE);

        //Check to see if hand is split and return winner of hand
        if (gameHelper.getPlayer().isSplit()) {
            System.out.println("Player Split Hand Results");
            gameHelper.determineWinner(gameHelper.getPlayer(), gameHelper.getDealer(), Hand.HAND_TWO);

        }
    }

    /**
     * Just a border to help with finding place in terminal
     *
     * @return void
     */
    public void endGameBorder() {
        System.out.printf("************************************************\n" +
                "************************************************\n");
    }

    /**
     * Just a border to help with finding place in terminal (p for player)
     *
     * @return void
     */
    public void playerBorder() {
        System.out.printf("pppppppppppppppppppppppppppppppppppppppppp\n" +
                "pppppppppppppppppppppppppppppppppppppppppp\n");
    }

    /**
     * Just a border to help with finding place in terminal (d for dealer)
     *
     * @return void
     */
    public void dealerBorder() {
        System.out.printf("ddddddddddddddddddddddddddddddddddddddd\n" +
                "ddddddddddddddddddddddddddddddddddddddd\n");
    }


}
