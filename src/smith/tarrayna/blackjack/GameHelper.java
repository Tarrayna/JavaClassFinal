package smith.tarrayna.blackjack;

import smith.tarrayna.cards.Card;
import smith.tarrayna.cards.DeckofCards;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GameHelper {
    private DeckofCards deckofCards;
    private Player player;
    private Player dealer;
    private int placeInDeck;
    private int buyIn;
    private int  currentBet;
    private int numberOfHandsPlayed;
    private int numberOfGamesPlayed = 0;
    private int numberOfWinningHands = 0;
    private int numberOfWashes = 0;
    private int numberOfLosses = 0;
    private int numberOfJackBlacks = 0;
    private int bestSteak = 0;
    private final int BLACKJACK = 21;
    private final int DEALER_LIMIT = 17;

    public GameHelper()
    {
        //Get a new deck of cards
        deckofCards = new DeckofCards();
        dealer = new Player(true);
        player = new Player(false, buyIn);
        this.buyIn = 10000;
        newRound();
    }


    public Card dealCard()
    {
        return deckofCards.getCard(placeInDeck++);
    }

    public void playerTurn( Scanner scanner)
    {
        //TODO: This is showing both cards after first run
        showDealCard();
        System.out.print("Player ");
        printHand(player);
        if(player.canSplit())
        {
            //TODO: Add option to double bet
            System.out.println("Would you like to Split Hand? yes(1) or no(1)");
            //TODO: Possible user input error. Make this a function so we can loop.
            switch(scanner.nextLine().toUpperCase())
            {
                case "YES":
                case "1":
                    player.split();
                    break;
                case "NO":
                case "0":
                default:
                    System.out.println("DAAAAAANG");
            }
        }

        //Show starting hand
        while(player.canKeepPlaying() && promptUserHitOrStand(scanner) )
        {

            player.getCard(Hand.HAND_ONE,dealCard());
            System.out.print("Player ");
            printHand(player);
        }
    }

    public void dealerTurn()
    {
        while((dealer.calculateHandValue(Hand.HAND_ONE) < DEALER_LIMIT)){
            System.out.print("Dealer ");
            printHand(dealer);
            hit(dealer, Hand.HAND_ONE);
        }

        System.out.print("Dealer ");
        printHand(dealer);
    }



    public boolean promptUserHitOrStand(Scanner scanner)
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
                return promptUserHitOrStand(scanner);
        }
    }


    public void hit(Player player, Hand handNumber)
    {
            player.getCard(handNumber,dealCard());
    }

    //TODO: Do this at beginning of round
    public void setCurrentBet()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("How much would you like to bet this round? (Current funds $%d): \n", player.getCashOnHand());

        try {
            int bet = scanner.nextInt();

            if(bet > player.getCashOnHand())
            {
                System.out.println("That is more than you currently have on hand. Enter a new value. ");
            }

            currentBet = bet;

        }catch(InputMismatchException e)
        {
            System.out.println("Invalid Input Try Again");
            setCurrentBet();
        }


    }

    public void determineWinner(Player player, Player dealer, Hand handNumber)
    {
        //Check for ties. That would be points are even. Or both player busted
        int playerHandValue = player.calculateHandValue(handNumber);
        int dealerHandValue = dealer.calculateHandValue(Hand.HAND_ONE);

        if(playerHandValue == dealerHandValue)
        {
            System.out.printf("Player Hand: %d %s  " +
                    "Dealer Hand: %d %s\n" +
                    "Hand is a Bust!\n", playerHandValue, (playerHandValue == BLACKJACK)?"BlackJack!!!":"",  dealerHandValue,(dealerHandValue == BLACKJACK)?"BlackJack!!!":"");
            return;
        }

        if(dealerHandValue == playerHandValue || ((dealerHandValue > playerHandValue) && dealerHandValue <= BLACKJACK))
        {
            System.out.printf("Player Hand: %d %s  " +
                    "Dealer Hand: %d %s\n" +
                    "Dealer Wins!\n", playerHandValue, (playerHandValue == BLACKJACK)?"BlackJack!!!":"",  dealerHandValue,(dealerHandValue == BLACKJACK)?"BlackJack!!!":"");
            player.fullFillBet(-1 * currentBet);
            return;
        }

        //Check for player is winner
        if(((playerHandValue > dealerHandValue) && playerHandValue <= BLACKJACK) || ((playerHandValue <= BLACKJACK) && dealerHandValue > 21))
        {
            System.out.printf("Player Hand: %d %s  " +
                    "Dealer Hand: %d %s\n" +
                    "Player Wins!\n", playerHandValue, (playerHandValue == BLACKJACK)?"BlackJack!!!":"",  dealerHandValue,(dealerHandValue == BLACKJACK)?"BlackJack!!!":"");
            player.fullFillBet(-1 * currentBet);
            return;
        }

        //The Dealer Won
        System.out.printf("Player Hand: %d %s  " +
                "Dealer Hand: %d %s\n" +
                "Dealer Wins!\n", playerHandValue, (playerHandValue == BLACKJACK)?"BlackJack!!!":"",  dealerHandValue,(dealerHandValue == BLACKJACK)?"BlackJack!!!":"");
        player.fullFillBet(-1 * currentBet);
    }

    public void printHand(Player player)
    {
        System.out.println("Main Hand: ");
        for(Card card : player.getHand(Hand.HAND_ONE))
        {
            if(card != null)
            {
                System.out.println(card.getCard());
            }
        }

        if(player.isSplit() && !player.isDealer())
        {
           System.out.println("Secondary Hand: ");
            for(Card card : player.getHand(Hand.HAND_TWO))
            {
                if(card != null)
                {
                    System.out.println(card.getCard());
                }
            }
        }
    }

    public void showDealCard()
    {
        System.out.println("Dealer Hand: ");
        Card card = dealer.getHand(Hand.HAND_ONE)[0];
        System.out.println(card.getCard());
    }

    public void newRound()
    {
        placeInDeck = 0;
        deckofCards = new DeckofCards();
        player.resetPlayer();
        dealer.resetPlayer();
        dealStartingHand();
    }

    public double getBuyIn()
    {
        return buyIn;
    }

    public void setBuyIn(int value)
    {
        this.buyIn = value ;
        getPlayer().setStartCash(value);
    }
    public Player getPlayer()
    {
        return player;
    }
    public Player getDealer()
    {
        return dealer;
    }

    public void dealStartingHand()
    {
        player.getCard(Hand.HAND_ONE,dealCard());
        player.getCard(Hand.HAND_ONE,dealCard());
        dealer.getCard(Hand.HAND_ONE,dealCard());
        dealer.getCard(Hand.HAND_ONE,dealCard());
    }
}
