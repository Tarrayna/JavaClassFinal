package smith.tarrayna;

import smith.tarrayna.blackjack.BlackJackRunner;
import smith.tarrayna.uno.UnoGameRunner;

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
        System.out.printf("Pick a game to play: UNO(1), BLACK JACK(2) or QUIT(0)\n");

        String userInput = scanner.nextLine().toUpperCase().trim();

        switch(userInput)
        {
            case "UNO":
            case "1":
                new UnoGameRunner().runGame(scanner);
                break;
            case "BLACK JACK":
            case "BLACKJACK":
            case "2":
                new BlackJackRunner().runGame(scanner);
                break;
            case "QUIT":
            case "0":
                throw new DonePlayingException("Thank you for playing!");
            default:
                System.out.println(userInput + " is not a valid input.");
                gameMenu();
                break;
        }
    }
}
