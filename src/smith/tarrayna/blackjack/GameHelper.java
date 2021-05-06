package smith.tarrayna.blackjack;

import smith.tarrayna.cards.Card;
import smith.tarrayna.cards.DeckofCards;

import java.util.Scanner;

class GameHelper {
    private DeckofCards deckofCards;
    private Player player;
    private Player dealer;
    private int placeInDeck;
    private double buyIn;
    private double roundBet;
    private int numberOfHandsPlayed;
    private int numberOfGamesPlayed = 0;
    private int numberOfWinningHands = 0;
    private int numberOfWashes = 0;
    private int numberOfLosses = 0;
    private int numberOfJackBlacks = 0;
    private int bestSteak = 0;

    public GameHelper(double buyIn)
    {
        //Get a new deck of cards
        deckofCards = new DeckofCards();
        player = new Player(true);
        dealer = new Player(false, buyIn);
        this.buyIn = buyIn;
    }


    public Card dealCard()
    {
        return deckofCards.getCard(placeInDeck++);
    }

    public int playerTurn(Player player, Scanner scanner)
    {
        printHand(player);
        if(player.canSplit())
        {
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

        while(promptUserHitOrBust(scanner) && player.canKeepPlaying());
        {
            player.getCard(Hand.HAND_ONE,dealCard());
        }
:while ()
        if()
        return player.getNumberOfPoints();
    }

    public int dealerTurn(Player dealer)
    {
        int dealerHandValue  = 0;
        int dealerHandCount = 0;

        do {
            printHand(dealer);
            hit(dealer, false);

        }while(player.calculateHandValue(Hand.HAND_ONE) < 17);

        printHand(hand);
        return dealerHandValue;
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


    publi void hit(Player player, Hand handNumber)
    {
            player.getCard(handNumber,dealCard());
    }

    public void setCurrentBet()
    {
        System.out.println("Set Bet For This Round: ");
        double bet =
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
        player.getCard(Hand.HAND_ONE,dealCard());
        player.getCard(Hand.HAND_ONE,dealCard());
        dealer.getCard(Hand.HAND_ONE,dealCard());
        dealer.getCard(Hand.HAND_ONE,dealCard());
    }

}
