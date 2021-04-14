package smith.tarrayna.blackjack;

public class Player {
    //HandRank handRank = HandRank.HIGH_CARD;
    boolean in = true;
    int cash;
    int playerNumber;

    public Player(int startingCash, int playerNumber){
        this.cash = startingCash;
        this.playerNumber = playerNumber;
    }
}
