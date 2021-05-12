package smith.tarrayna;

import smith.tarrayna.blackjack.BlackJackRunner;
import smith.tarrayna.blackjack.GameHelper;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Main function. Entry point for application. Gets the game started.
 *
 * @author Tarrayna Smith
 * Final Project
 * Requirement 4 Methods: In addition to the main method there should be at least
 * 3 other methods in the main class. These methods need to receive AND return values. (15 points)
 */
class main {
    public static void main(String[] args) {
        //Create a new gamehelper. This will run most of the basic game tasks
        Scanner scanner = new Scanner(System.in);
        GameHelper gameHelper = new GameHelper();
        while (true) {
            try {
                System.out.println("Welcome to Blackjack!");

                //Initial starting cash
                gameHelper.setBuyIn(getBuyIn(scanner));

                //Basic Game Menu.
                gameMenu(gameHelper);
                //Custom Exception thrown when game is over
            } catch (DonePlayingException e) {
                System.out.println(e.getMessage());
                System.out.println(printStats(gameHelper));
                break;
            }
        }
    }

    /**
     * Displays the main menue for the game
     *
     * @param gameHelper this is the gameHelper created in the main function
     * @return void
     * requirements met: 2. For, while, do/while, and enhanced for loops should be used to keep the project running until the user wishes to end.
     * At least 3 of the loops mentioned should be used. (15 points) 1 of 3
     */
    public static void gameMenu(GameHelper gameHelper) throws DonePlayingException {
        BlackJackRunner blackJackRunner = new BlackJackRunner();

        Scanner scanner = new Scanner(System.in);
        System.out.printf("Are you ready to play?(Enter Y\\N)(Enter \"H\" for help)\n");

        //This will run at the end of every round. This will end once user quits or loses
        while (true) {
            String userInput = scanner.nextLine().toUpperCase().trim();
            switch (userInput) {
                case "BLACK JACK":
                case "BLACKJACK":
                case "YES":
                case "Y":
                case "READY":
                    //Reset game for new round
                    gameHelper.newRound();

                    //Start round
                    blackJackRunner.runRound(scanner, gameHelper);

                    //Once round is complete check to see if player can still play
                    if (gameHelper.getPlayer().getCashOnHand() <= 0) {
                        throw new DonePlayingException("You are out of cash. Thank you for playing!");
                    }
                    System.out.println(
                            "Would you like to play another hand(Y\\N)? Remaining cash: " + gameHelper.getPlayer()
                                                                                                      .getCashOnHand());
                    break;
                case "QUIT":
                case "NO":
                case "N":
                    throw new DonePlayingException("Thank you for playing!");
                case "H":
                case "HELP":
                    System.out.println(getHelp(gameHelper));
                    gameMenu(gameHelper);
                default:
                    System.out.println(userInput + " is not a valid input.");
                    gameMenu(gameHelper);
                    break;
            }
        }
    }

    /**
     * Prompts user for buy in amount. Validates that the value is within 10-10000000. Only accepts integer values
     * will recursively ask user for input. Until valid
     *
     * @param scanner used for getting the user input
     * @return returns the user's input
     */
    public static int getBuyIn(Scanner scanner) {
        int buyIn = 0;

        System.out.println("Enter your buy-in. Max $1,000,000. Minimum $10 (no change please)");

        //Try to get a valid value from user. Recursively prompt again if input is invalid
        //Requirement 11 Exception handling should be used to prevent your computer from crashing.(10 points)
        try {
            buyIn = scanner.nextInt();
            if (buyIn < 10 || buyIn > 1000000) {
                System.out.println("Buy-in must be in valid range.");
                scanner.nextLine();
                return getBuyIn(scanner);
            }
        } catch (InputMismatchException e) {
            System.out.println("Input was Invalid. Try Again");
            scanner.nextLine();
            return getBuyIn(scanner);
        }

        return buyIn;
    }

    /**
     * Generates string with game stats
     *
     * @param gameHelper declared in main. Kept track of stats during gameplay
     * @return the generated string with game stats
     */
    public static String printStats(GameHelper gameHelper) {
        StringBuilder stats = new StringBuilder();
        stats.append("Final Game Stats: " +
                "\nStarting Cash: $" + gameHelper.getBuyIn() +
                "\nEnding Cash: $" + gameHelper.getPlayer().getCashOnHand() +
                "\nBiggest Win: $" + gameHelper.getBiggestWin() +
                "\nBiggest Loss:$" + gameHelper.getBiggestLoss() +
                "\nNumber of Games Played: " + gameHelper.getNumberOfGamesPlayed() +
                "\nNumber of Games Won: " + gameHelper.getNumberOfGamesWon() +
                "\nLongest Winning Streak: " + gameHelper.getBestSteak() +
                "\nNumber of Games Lost: " + gameHelper.getNumberOfGamesLost() +
                "\nLongest Losing Streak: " + gameHelper.getWorstStreak() +
                "\nNumber of Pushes: " + gameHelper.getNumberOfNumberOfPushes() +
                "\nTotal Blackjacks: " + gameHelper.getNumberOfNumberOfBlackJacks());

        stats.append("\n");
        return stats.toString();
    }

    /**
     * Prints the menu for the game
     *
     * @param gameHelper this will be used to grab the value of a blackjack
     * @return a string containing the menu text
     */
    public static String getHelp(GameHelper gameHelper) {
        String gameHelp = "This is a fairly basic game of blackjack \n" +
                "The following rules apply: \n" +
                "1. A blackjack is when the first 2 cards dealt = " + GameHelper.BLACKJACK + "\n" +
                "2. Splits are allowed. But only on the first 2 cards drawn. And only 1 split per game \n" +
                "3. Doubling is allowed. But only on the first 2 cards. And not in conjunction with a split. \n" +
                "4. No Insurance\n" +
                "5. If both the player and dealer bust. Dealer wins\n" +
                "6. Player automatically wins on Blackjack, unless dealer also gets Blackjack \n" +
                "7. Normal win is paid out 1:1. Blackjack will be paid out 4:2 (very generous I know)\n";

        return gameHelp;
    }
}
