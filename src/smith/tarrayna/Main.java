package smith.tarrayna;

import smith.tarrayna.blackjack.BlackJackRunner;
import smith.tarrayna.blackjack.GameHelper;

import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

class main {
    public static void main(String [] args)
    {
        GameHelper gameHelper = new GameHelper();
        while(true)
        {
            try {
                System.out.println("Welcome to Blackjack!");
                gameHelper.setBuyIn(getBuyIn());
                gameMenu(gameHelper);
            } catch (DonePlayingException e) {
                printStats(gameHelper);
                break;
            }
        }
    }

    public static void gameMenu(GameHelper gameHelper) throws DonePlayingException{
        BlackJackRunner blackJackRunner = new BlackJackRunner();

        Scanner scanner = new Scanner(System.in);
        System.out.printf("Are you ready to play?(enter \"H\" for help)\n");

        while(true)
        {
            String userInput = scanner.nextLine().toUpperCase().trim();
                switch(userInput)
                {
                    case "BLACK JACK":
                    case "BLACKJACK":
                    case "YES":
                        blackJackRunner.runGame(scanner, gameHelper);
                        System.out.println("Would you like to player another hand? Remaining cash: " + gameHelper.getPlayer().getCashOnHand());
                        break;
                    case "QUIT":
                    case "NO":
                        throw new DonePlayingException("Thank you for playing!");
                    case "H":
                    case "HELP":
                        getHelp();;
                        gameMenu(gameHelper);
                    default:
                        System.out.println(userInput + " is not a valid input.");
                        gameMenu(gameHelper);
                        break;
                }

        }

    }

    public static int getBuyIn()
    {
        DecimalFormat df = new DecimalFormat("###.##");
        int buyIn = 0;

        System.out.println("Enter your buy in. Max $1,000,000. Minimum $10 (no change please)");
        try {
                buyIn = new Scanner(System.in).nextInt();
                if(buyIn < 10 || buyIn > 1000000)
                {
                    System.out.println("Buy in must be in valid range.");
                }
            }
        catch (InputMismatchException e )
        {
            System.out.println("Input was Invalid. Try Again");
            getBuyIn();
        }

        return buyIn;
    }

    //TODO: Use this to print all the stats after the game is over
    public static void printStats(GameHelper gameHelper)
    {

    }

    public static void getHelp()
    {
        System.out.printf("This is a fairly basic game of blackjack \n" +
                "The following rules apply: \n" +
                "1. Splits are allowed. But only on the first 2 cards drawn. And only 1 split per game \n" +
                "2. Doubling is allowed. But only on the first 2 cards. And not in conjunction with a split. \n" +
                "3. No Insurance\n" +
                "4. If both the player and dealer bust. Dealer wins\n" +
                "5. Player automatically wins on Blackjack, unless dealer also gets Blackjack \n" +
                "6. Normal win is paid out 1:1. Blackjack will be paid out 3:2\n");
    }
}
