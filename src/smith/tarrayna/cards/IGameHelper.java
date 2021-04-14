package smith.tarrayna.cards;

import smith.tarrayna.blackjack.Player;

public interface IGameHelper {
    void startNewGame(int numberOfPlayers, int startingCash);
    Card[][] generatePlayerHands (int numberOfPlayers);
    Player[] generatePlayers(int startingCash);
    void dealPlayerCards();
}
