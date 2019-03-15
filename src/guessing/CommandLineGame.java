package guessing;

public class CommandLineGame {
    public static void main(String[] args) {
        GuessingGame game = new GuessingGameImpl();
        GamePlayer player = new GamePlayer(game);
        player.playTheGame();
    }
}
