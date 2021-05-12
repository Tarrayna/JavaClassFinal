package smith.tarrayna.blackjack;

import smith.tarrayna.cards.Card;
import smith.tarrayna.cards.DeckofCards;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * GameHelper. Manages most of the game functionality
 *
 * @author Tarrayna Smith
 * Final Project
 * Requirement 5 Classes: There should be at least least 1 other class in your project with a minimum of 3 attributes,
 * 3 behaviors, and at least 1 overloaded constructor and the default constructor.
 * Objects of the class should be used in the application. (20 points)
 */


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
    private final int MAX_CARDS_BLACKJACK = 2;

    /**
     * Constructor. Generates the starting values for stats. Creates players and deck.
     */
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

    /**
     * Gets the next card in the deck. Returns that card. And increments the index tracker to the next card.
     *
     * @return "top" card of deck
     */
    public Card dealCard() {
        return deckofCards.getCard(placeInDeck++);
    }

    /**
     * Runs the player's turn
     *
     * @param scanner used for getting the user input
     * void
     */
    public void playerTurn(Scanner scanner) {

        //Player can keep taking their turn until they either can't (Blackjack or higher hand value). Or chooses not to
        while (player.canKeepPlaying(Hand.HAND_ONE) && PromptUserHitStandDoubleOrSplit(scanner, Hand.HAND_ONE)) {

            player.takeCard(Hand.HAND_ONE, dealCard());
        }

        //If player has a second hand.
        //The player can keep taking their turn until they either can't (Blackjack or higher hand value). Or chooses not to
        if (player.isSplit()) {
            while (player.canKeepPlaying(Hand.HAND_TWO) && PromptUserHitStandDoubleOrSplit(scanner, Hand.HAND_TWO)) {

                player.takeCard(Hand.HAND_TWO, dealCard());
            }
        }
    }

    /**
     * Runs the dealers turn
     *
     * void
     */
    public void dealerTurn() {
        //While the dealer can keep drawing cards (hand value less than 16), it will
        while ((dealer.calculateHandValue(Hand.HAND_ONE) < DEALER_LIMIT)) {
            System.out.print("Dealer ");
            printHand(dealer);
            hit(dealer, Hand.HAND_ONE);
        }

        System.out.print("Dealer ");
        printHand(dealer);
    }

    /**
     * Prompts the user for which action they would like to take on their turn. Prompt will change based on whether a player
     * can double the bet (only if funds exist). OR if the player can split (only on first cards and must have funds).
     * Doubling or Splitting hand will cause a recursive call back to this function. So player can act on that choice.
     *
     * @param scanner used for getting the user input
     * @param handNumber the hand to prompt user against
     *
     * returns true if the player chooses to hit. Returns false if the user chooses to stand
     * Requirement 7. Selections: If/else AND switch statements should be used somewhere in the application. (10 points)
     */
    public boolean PromptUserHitStandDoubleOrSplit(Scanner scanner, Hand handNumber) {
        int doubleValue = 2;
        int zeroValue = 0;

        //Print the table - enhancement idea. Make this a function
        showDealCard();
        System.out.print("Player ");
        printHand(player);

        //Check to make sure doubling bet wont make player go negative
        if (player.getCashOnHand() - (doubleValue * currentBet) < zeroValue) {
            canDouble = false;
        }

        //Print out options to player. This will change on where a bet can be doubled. Or a hand can be split
        System.out.printf("%s Hit(1), Stand(0)%s%s?\n",
                ((handNumber == Hand.HAND_ONE) ? "Primary Hand:" : "Secondary Hand: "),
                (canDouble) ? ", Double Down(2)" : "", (player.canSplit(currentBet)) ? ", Split(3)" : "");

        //Get user input
        String userInput = scanner.nextLine().toUpperCase();


        //Check the user input
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
                //Check if we can double the bet. Even though user options do not list this. We must check if user
                //tries to double bet anyway.
                if (canDouble) {
                    currentBet *= doubleValue;
                    canDouble = false;
                } else {
                    System.out.println(userInput + " is not a valid input. Try again.");
                }

                //Whether the input was valid or not. Prompt user again
                return PromptUserHitStandDoubleOrSplit(scanner, handNumber);
            case "3":
            case "SPLIT":
                //Check if we can splitting is  valid. Even though user options do not list this. We must check if user
                //tries to split anyway.
                if(player.canSplit(currentBet))
                {
                    canDouble = false;
                    player.split();
                }

                //Whether the input was valid or not. Prompt user again
                return PromptUserHitStandDoubleOrSplit(scanner, handNumber);
            //Input was invalid. Prompt user again.
            default:
                System.out.println(userInput + " is not a valid input. Try again.");
                return PromptUserHitStandDoubleOrSplit(scanner, handNumber);
        }
    }

    /**
     * Deals top card to player
     *
     * @param player player to deal card to
     * @param handNumber hand in which to deal card to
     * void
     */
    public void hit(Player player, Hand handNumber) {
        player.takeCard(handNumber, dealCard());
    }

    /**
     * Get a bet from the user. Bet should be less than what the user currently has. And a possitive value
     *
     * void
     */
    public void setCurrentBet() {
        Scanner scanner = new Scanner(System.in);
        int minBet = 0;

        System.out.printf("How much would you like to bet this round? (Current funds $%d): \n", player.getCashOnHand());

        try {
            int bet = scanner.nextInt();

            //Check that bet is a valid value
            if (bet > player.getCashOnHand() && player.getCashOnHand() >= minBet) {
                System.out.println("That is more than you currently have on hand and greater than 0. Enter a new value. ");

                //Set the bet for the game
                setCurrentBet();
            }
            currentBet = bet;

        //Try again if user input is not valid
        } catch (InputMismatchException e) {
            System.out.println("Invalid Input Try Again");
            setCurrentBet();
        }


    }

    /**
     * Determines the winner of the round for a specific hand
     *
     * @param player represents the player
     * @param dealer represents the dealer
     * @param handNumber the hand of the player. In which to compare with the hand of the dealer
     * void
     */
    public void determineWinner(Player player, Player dealer, Hand handNumber) {
        int doubleValue = 2;


        int playerHandValue = player.calculateHandValue(handNumber);
        int dealerHandValue = dealer.calculateHandValue(Hand.HAND_ONE);

        boolean playerBust = playerHandValue > BLACKJACK;

        boolean playerBlackJack = (playerHandValue == BLACKJACK && player
                .getNumberOfCardsInHand(handNumber) == MAX_CARDS_BLACKJACK);
        boolean dealerBlackJack = (dealerHandValue == BLACKJACK && dealer
                .getNumberOfCardsInHand(Hand.HAND_ONE) == MAX_CARDS_BLACKJACK);


        //Check for player being the winner.
        //The player has not busted AND the player has a hand greater than the dealers OR
        //The player has not busted AND the dealer has busted OR
        //The player has blackjack and the dealer does not have blackjack
        if (((!playerBust) && (playerHandValue > dealerHandValue)) || ((!playerBust) && dealerHandValue > BLACKJACK) || (playerBlackJack && !dealerBlackJack)) {
            //Determine if payout should be doubled
            int payout = ((playerHandValue == BLACKJACK) ? doubleValue * currentBet : currentBet);

            //Output the stats for that hand
            System.out.printf("Player Hand: %d %s  " +
                            "Dealer Hand: %d %s\n" +
                            "Player Wins! +$%d to player\n", playerHandValue, (playerBlackJack) ? "BlackJack!!!" : "",
                    dealerHandValue, (dealerBlackJack) ? "BlackJack!!!" : "", Math.abs(payout));

            //Pay the player
            player.fullFillBet(payout);

            increaseNumberOfGamesWon(payout);

            //If the player got blackjack. Increment number of blackjacks
            if (playerBlackJack)
                increaseNumberOfNumberOfBlackJacks();
            return;
        }

        //Check if round is a push
        //Neither the player nor the dealer have black jack AND the player has not busted AND player hand is equal to dealer hand OR
        //Both the dealer and the player have blackjack
        //No payout
        if (!dealerBlackJack && !playerBlackJack && !playerBust && playerHandValue == dealerHandValue || (playerBlackJack && dealerBlackJack)) {

            //Print round stats
            System.out.printf("Player Hand: %d %s  " +
                            "Dealer Hand: %d %s\n" +
                            "Hand is a Push!\n", playerHandValue, (playerBlackJack) ? "BlackJack!!!" : "", dealerHandValue,
                    (dealerBlackJack) ? "BlackJack!!!" : "");

            increaseNumberOfNumberOfPushes();

           //Check if player had blackjack and increment
            if (playerBlackJack)
                increaseNumberOfNumberOfBlackJacks();
            return;
        }

        //If we got this far. The dealer won.
        int payout = -1 * currentBet;

        //Print round stats
        System.out.printf("Player Hand: %d %s  " +
                        "Dealer Hand: %d %s\n" +
                        "Dealer Wins! -$%d to player\n", playerHandValue, (playerBlackJack) ? "BlackJack!!!" : "",
                dealerHandValue, (dealerBlackJack) ? "BlackJack!!!" : "", Math.abs(payout));

        increaseNumberOfNumberOfLost(payout);

        //Check if player had blackjack and increment
        if (playerBlackJack)
            increaseNumberOfNumberOfBlackJacks();

        //Player pays out bet
        player.fullFillBet(payout);
    }

    /**
     * Prints the current player's hand. This can be up to two hands for a player.
     * If the player is the dealer, they should only have one hand
     *
     * @param player player to print hand
     * void
     */
    public void printHand(Player player) {

        System.out.println("Main Hand: ");
        for (Card card : player.getHand(Hand.HAND_ONE)) {
            if (card != null) {
                System.out.println(card.getCard());
            }
        }

        //Check if player and if their hand is split then print out hand.
        if (player.isSplit() && !player.isDealer()) {
            System.out.println("Secondary Hand: ");
            for (Card card : player.getHand(Hand.HAND_TWO)) {
                if (card != null) {
                    System.out.println(card.getCard());
                }
            }
        }
    }

    /**
     * Prints the show card from the dealer
     *o
     * void
     */
    public void showDealCard() {
        int showCard = 0;

        System.out.println("Dealer Hand: ");
        Card card = dealer.getHand(Hand.HAND_ONE)[showCard];
        System.out.println(card.getCard());
    }

    /**
     * Resets the game for a new round
     *
     * void
     */
    public void newRound() {
        increaseNumberOfGamesPlayed();
        placeInDeck = 0;

        deckofCards.shuffle();

        player.resetPlayer();
        dealer.resetPlayer();

        canDouble = true;

        //Deal next hand to player and dealer
        dealStartingHand();
    }

    /**
     * Returns the buy-in from game setup
     *
     * void
     */
    public int getBuyIn() {
        return buyIn;
    }

    /**
     * Sets the buy-in for the game. Which in turn sets the players starting cash.
     *
     * @param value how much will be bet this game
     *
     *return void
     */
    public void setBuyIn(int value) {
        this.buyIn = value;
        getPlayer().setStartCash(value);
    }

    /**
     * Gets the player
     *
     *return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Gets the dealer
     *
     *return the dealer
     */

    public Player getDealer() {
        return dealer;
    }

    /**
     * Deals out first 2 cards to both the player and the dealer
     *
     *return void
     */
    public void dealStartingHand() {
        player.takeCard(Hand.HAND_ONE, dealCard());
        player.takeCard(Hand.HAND_ONE, dealCard());
        dealer.takeCard(Hand.HAND_ONE, dealCard());
        dealer.takeCard(Hand.HAND_ONE, dealCard());
    }

    /**
     * Gets the number of games played
     *
     *return number of games played
     */
    public int getNumberOfGamesPlayed() {
        return numberOfGamesPlayed;
    }

    /**
     * Increments number of games played
     *
     *return void
     */
    public void increaseNumberOfGamesPlayed() {
        numberOfGamesPlayed++;
    }

    /**
     * Gets the number of games won
     *
     *return number of games won
     */
    public int getNumberOfGamesWon() {
        return numberOfWinningHands;
    }

    /**
     * Increases the number of games won. Resets the worst streak to zero. Sets biggest winnings and increases the current
     * winning streak
     *
     *void
     */
    public void increaseNumberOfGamesWon(int winnings) {
        numberOfWinningHands++;

        resetWorstStreak();

        setBiggestWin(winnings);

        increaseCurrentStreak();
    }

    /**
     * Gets the number of games lost
     *
     *return number of games lost
     */
    public int getNumberOfGamesLost() {
        return numberOfLosses;
    }

    /**
     * Increases the number of games lost. Sets the biggest loss value. Sets the winning streak to zero. And increses
     * the current streak
     *
     *return void
     */
    public void increaseNumberOfNumberOfLost(int losses) {
        numberOfLosses++;
        setBiggestLoss(losses);
        ResetWinningStreak();
        increaseWorstStreak();
    }

    /**
     * Gets the number of hands pushed
     *
     *return number of hands pushed
     */
    public int getNumberOfNumberOfPushes() {
        return numberOfPushes;
    }

    /**
     * Increases the number of pushes
     *
     *void
     */
    public void increaseNumberOfNumberOfPushes() {
        numberOfPushes++;
    }

    /**
     * Gets the number of Blackjacks
     *
     *return number of Blackjacks
     */
    public int getNumberOfNumberOfBlackJacks() {
        return numberOfBlackJacks;
    }

    /**
     * Increases the number of BlackJacks
     *
     *void
     */
    public void increaseNumberOfNumberOfBlackJacks() {
        numberOfBlackJacks++;
    }

    /**
     * Increases current streak. And then checks to see if new streak is the new highest streak
     *
     *void
     */
    public void increaseCurrentStreak() {
        currentStreak++;

        if (currentStreak > bestSteak)
            bestSteak = currentStreak;
    }

    /**
     * Sets winning streak to zero
     *
     *void
     */
    public void ResetWinningStreak() {
        currentStreak = 0;
    }

    /**
     * Gets the best winning streak
     *
     *return number of the best winning streak
     */
    public int getBestSteak() {
        return bestSteak;
    }

    /**
     * Sets the biggest win.
     *
     *void
     */
    public void setBiggestWin(int winnings) {
        if (this.biggestWin < winnings)
            this.biggestWin = winnings;
    }

    /**
     * Gets the biggest win value
     *
     *return biggest win value
     */
    public int getBiggestWin() {
        return biggestWin;
    }

    /**
     * Sets biggest loss
     *
     *void
     */
    public void setBiggestLoss(int loss) {
        if (this.biggestLoss > loss) {
            this.biggestLoss = loss;
        }

    }

    /**
     * Gets the biggest lost value
     *
     *return biggest lost value
     */
    public int getBiggestLoss() {
        return biggestLoss;
    }

    /**
     * Increments losing streak. And checks to see if current streak is higher than best streak and updates
     *
     *void
     */
    public void increaseWorstStreak() {
        currentWorstStreak++;
        if (currentWorstStreak > worstStreak) {
            worstStreak = currentWorstStreak;
        }
    }

    /**
     * Gets the worst streak
     *
     *return the worst streak
     */
    public int getWorstStreak() {
        return worstStreak;
    }

    /**
     * Resets current streak to 0
     *
     *void
     */
    public void resetWorstStreak() {
        currentWorstStreak = 0;
    }
}
