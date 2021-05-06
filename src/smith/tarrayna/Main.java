package smith.tarrayna;

import smith.tarrayna.blackjack.BlackJackRunner;

import java.util.InputMismatchException;
import java.util.Scanner;

class main {
    public static void main(String [] args)
    {
        while(true)
        {
            try {
                gameMenu();
            } catch (DonePlayingException e) {
                continue;
            }
        }
    }

    public static void gameMenu() throws DonePlayingException{
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Would you like to play BLACKJACK(yes)?  or QUIT(no)\n");

        String userInput = scanner.nextLine().toUpperCase().trim();

        switch(userInput)
        {
            case "BLACK JACK":
            case "BLACKJACK":
            case "YES":
                new BlackJackRunner().runGame(scanner, getBuyIn());
                break;
            case "QUIT":
            case "NO":
                throw new DonePlayingException("Thank you for playing!");
            default:
                System.out.println(userInput + " is not a valid input.");
                gameMenu();
                break;
        }
    }

    public static double getBuyIn()
    {
        double buyIn = 0;

        System.out.println("Enter your buy in. Max $10,000. Minimum $100");
        try {
                buyIn = new Scanner(System.in).nextDouble();
            }
        catch (InputMismatchException e )
        {
            System.out.println("Input was Invalid. Try Again");
            getBuyIn();
        }

        return buyIn;
    }

    //TODO: Use this to print all the stats after the game is over
    public void printStats()
    {

    }
}
