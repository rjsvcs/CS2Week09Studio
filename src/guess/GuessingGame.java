package guess;

public interface GuessingGame {
    GuessResult guess(int number);

    void restart();
}
