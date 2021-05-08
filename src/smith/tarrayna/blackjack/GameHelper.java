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
    private int currentBet;
    private int biggestWin;
    private int biggestLoss;
    private int numberOfGamesPlayed;
    private int numberOfWinningHands;
    private int numberOfPushes;
    private int numberOfLosses;
    private int numberOfBlackJacks;
    private int bestSteak;
    private int currentStreak;
    private int worstStreak;
    private int currentWorstStreak;
    public static final int BLACKJACK = 21;
    private final int DEALER_LIMIT = 17;
    private boolean canDouble = true;

    public GameHelper() {
        //Get a new deck of cards
        deckofCards = new DeckofCards();
        dealer = new Player(true);
        player = new Player(false, buyIn);
        this.buyIn = 10000;
        biggestWin = 0;
        biggestLoss = 0;
        numberOfGamesPlayed = 0;
        numberOfWinningHands = 0;
        numberOfPushes = 0;
        numberOfLosses = 0;
        numberOfBlackJacks = 0;
        bestSteak = 0;
        currentStreak = 0;
        worstStreak = 0;
        currentWorstStreak = 0;
    }


    public Card dealCard() {
        return deckofCards.getCard(placeInDeck++);
    }

    public void playerTurn(Scanner scanner) {
        showDealCard();
        System.out.print("Player ");
        printHand(player);
        if (player.canSplit(currentBet)) {
            prompUserSplitHand(scanner);
            if (player.isSplit()) {

                printHand(player);
            }
        }


        //Show starting hand
        while (player.canKeepPlaying(Hand.HAND_ONE) && PromptUserHitStandOrDouble(scanner, Hand.HAND_ONE)) {

            player.getCard(Hand.HAND_ONE, dealCard());
            System.out.print("Player ");
            printHand(player);
        }
        if (player.isSplit()) {
            while (player.canKeepPlaying(Hand.HAND_TWO) && PromptUserHitStandOrDouble(scanner, Hand.HAND_TWO)) {

                player.getCard(Hand.HAND_TWO, dealCard());
                System.out.print("Player ");
                printHand(player);
            }
        }
    }

    public void dealerTurn() {
        while ((dealer.calculateHandValue(Hand.HAND_ONE) < DEALER_LIMIT)) {
            System.out.print("Dealer ");
            printHand(dealer);
            hit(dealer, Hand.HAND_ONE);
        }

        System.out.print("Dealer ");
        printHand(dealer);
    }


    public boolean PromptUserHitStandOrDouble(Scanner scanner, Hand handNumber) {
        //Check to make sure doubling bet wont make player go negative
        if (player.getCashOnHand() - (2 * currentBet) < 0) {
            canDouble = false;
        }
        System.out.printf("%s Hit(1) or Stand(0) %s?\n", (canDouble) ? "or Double Down(2)" : "", (handNumber == Hand.HAND_ONE)?"Primary Hand:":"Secondary Hand: ");
        String userInput = scanner.nextLine().toUpperCase();

        switch (userInput) {
            case "HIT":
            case "1":
                canDouble = false;
                return true;
            case "STAND":
            case "0":
                return false;
            case "DOUBLE":
            case "DOUBLEDOWN":
            case "DOUBLE DOWN":
            case "D":
            case "2":
            case "DD":
                if (canDouble) {
                    currentBet *= 2;
                    canDouble = false;
                } else {
                    System.out.println(userInput + " is not a valid input. Try again.");
                }
                return PromptUserHitStandOrDouble(scanner, handNumber);
            default:
                System.out.println(userInput + " is not a valid input. Try again.");
                return PromptUserHitStandOrDouble(scanner, handNumber);
        }
    }


    public void hit(Player player, Hand handNumber) {
        player.getCard(handNumber, dealCard());
    }

    //TODO: Do this at beginning of round
    public void setCurrentBet() {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("How much would you like to bet this round? (Current funds $%d): \n", player.getCashOnHand());

        try {
            int bet = scanner.nextInt();

            if (bet > player.getCashOnHand()) {
                System.out.println("That is more than you currently have on hand. Enter a new value. ");
                setCurrentBet();
            }

            currentBet = bet;

        } catch (InputMismatchException e) {
            System.out.println("Invalid Input Try Again");
            setCurrentBet();
        }


    }

    public void determineWinner(Player player, Player dealer, Hand handNumber) {
        //Check for ties. That would be points are even.
        int playerHandValue = player.calculateHandValue(handNumber);
        int dealerHandValue = dealer.calculateHandValue(Hand.HAND_ONE);

        boolean playerBlackJack = (playerHandValue == BLACKJACK);
        boolean dealerBlackJack = (dealerHandValue == BLACKJACK);

        if (playerHandValue == dealerHandValue) {
            System.out.printf("Player Hand: %d %s  " +
                    "Dealer Hand: %d %s\n" +
                    "Hand is a Push!\n", playerHandValue, (playerBlackJack) ? "BlackJack!!!" : "", dealerHandValue, (dealerBlackJack) ? "BlackJack!!!" : "");
            increaseNumberOfNumberOfPushes();
            if (playerBlackJack)
                increaseNumberOfNumberOfBlackJacks();
            return;
        }

        //Check for player is winner
        if (((playerHandValue > dealerHandValue) && playerHandValue <= BLACKJACK) || ((playerHandValue <= BLACKJACK) && dealerHandValue > BLACKJACK)) {
            int payout = ((playerHandValue == BLACKJACK) ? 2 * currentBet : currentBet);
            System.out.printf("Player Hand: %d %s  " +
                    "Dealer Hand: %d %s\n" +
                    "Player Wins! +$%d to player\n", playerHandValue, (playerBlackJack) ? "BlackJack!!!" : "", dealerHandValue, (dealerBlackJack) ? "BlackJack!!!" : "", Math.abs(payout));
            player.fullFillBet(payout);
            increaseNumberOfGamesWon(payout);
            if (playerBlackJack)
                increaseNumberOfNumberOfBlackJacks();
            return;
        }

        //Otherwise the dealer won
        int payout = -1 * currentBet;
        System.out.printf("Player Hand: %d %s  " +
                "Dealer Hand: %d %s\n" +
                "Dealer Wins! -$%d to player\n", playerHandValue, (playerBlackJack) ? "BlackJack!!!" : "", dealerHandValue, (dealerBlackJack) ? "BlackJack!!!" : "", Math.abs(payout));
        increaseNumberOfNumberOfLost(payout);
        if (playerBlackJack)
            increaseNumberOfNumberOfBlackJacks();
        player.fullFillBet(payout);
    }

    public void printHand(Player player) {
        System.out.println("Main Hand: ");
        for (Card card : player.getHand(Hand.HAND_ONE)) {
            if (card != null) {
                System.out.println(card.getCard());
            }
        }

        if (player.isSplit() && !player.isDealer()) {
            System.out.println("Secondary Hand: ");
            for (Card card : player.getHand(Hand.HAND_TWO)) {
                if (card != null) {
                    System.out.println(card.getCard());
                }
            }
        }
    }

    public void showDealCard() {
        System.out.println("Dealer Hand: ");
        Card card = dealer.getHand(Hand.HAND_ONE)[0];
        System.out.println(card.getCard());
    }

    public void newRound() {
        increaseNumberOfGamesPlayed();
        placeInDeck = 0;
        deckofCards.shuffle();
        player.resetPlayer();
        dealer.resetPlayer();
        canDouble = true;
        dealStartingHand();
    }

    public double getBuyIn() {
        return buyIn;
    }

    public void setBuyIn(int value) {
        this.buyIn = value;
        getPlayer().setStartCash(value);
    }

    public Player getPlayer() {
        return player;
    }

    public Player getDealer() {
        return dealer;
    }

    public void dealStartingHand() {
        player.getCard(Hand.HAND_ONE, dealCard());
        player.getCard(Hand.HAND_ONE, dealCard());
        dealer.getCard(Hand.HAND_ONE, dealCard());
        dealer.getCard(Hand.HAND_ONE, dealCard());
    }

    public void prompUserSplitHand(Scanner scanner) {
        System.out.println("Would you like to Split Hand? yes(1) or no(1)");

        switch (scanner.nextLine().toUpperCase()) {
            case "YES":
            case "1":
                canDouble = false;
                player.split();
                break;
            case "NO":
            case "0":
            default:
                System.out.println("Invalid Input. Try Again.");
                prompUserSplitHand(scanner);
        }
    }

    public int getNumberOfGamesPlayed() {
        return numberOfGamesPlayed;
    }

    public void increaseNumberOfGamesPlayed() {
        numberOfGamesPlayed++;
    }

    public int getNumberOfGamesWon() {
        return numberOfWinningHands;
    }

    public void increaseNumberOfGamesWon(int winnings) {
        numberOfWinningHands++;
        resetWorstStreak();
        setBiggestWin(winnings);
        increaseCurrentStreak();
    }

    public int getNumberOfGamesLost() {
        return numberOfLosses;
    }

    public void increaseNumberOfNumberOfLost(int losses) {
        numberOfLosses++;
        setBiggestLoss(losses);
        resetStreak();
        increaseWorstStreak();
    }

    public int getNumberOfNumberOfWashes() {
        return numberOfPushes;
    }

    public void increaseNumberOfNumberOfPushes() {
        numberOfPushes++;
    }

    public int getNumberOfNumberOfBlackJacks() {
        return numberOfBlackJacks;
    }

    public void increaseNumberOfNumberOfBlackJacks() {
        numberOfBlackJacks++;
    }

    public void increaseCurrentStreak() {
        if (currentStreak > bestSteak)
            bestSteak = currentStreak;
    }

    public void resetStreak() {
        currentStreak = 0;
    }

    public int getBestSteak() {
        return bestSteak;
    }

    public void setBiggestWin(int winnings) {
        if (this.biggestWin < winnings)
            this.biggestWin = winnings;
    }

    public int getBiggestWin() {
        return biggestWin;
    }

    public void setBiggestLoss(int loss) {
        if (this.biggestLoss > loss)
        {
            this.biggestLoss = loss;
        }

    }

    public int getBiggestLoss() {
        return biggestLoss;
    }

    public void increaseWorstStreak() {
        currentWorstStreak++;
        if (currentWorstStreak > worstStreak)
        {
            worstStreak = currentWorstStreak;
        }
    }

    public int getWorstStreak() {
        return worstStreak;
    }

    public void resetWorstStreak() {
        currentWorstStreak = 0;
    }
}
