package guess;

import java.util.Random;

public class GuessingGameImpl implements GuessingGame {

    private static final Random RNG = new Random();

    private int numberOfGuesses;
    private int numberToGuess;

    @Override
    public GuessResult guess(int number) {
        GuessResult result;
        if(numberOfGuesses == 0) {
            result = GuessResult.OUT_OF_GUESSES;
        } else if(number == numberToGuess) {
                result = GuessResult.CORRECT;
        } else if(number < numberToGuess) {
            result = GuessResult.TOO_LOW;
            numberOfGuesses--;
        } else {
            result = GuessResult.TOO_HIGH;
            numberOfGuesses--;
        }
        return result;
    }

    @Override
    public void restart() {
        numberOfGuesses = 6;
        numberToGuess = RNG.nextInt(100) + 1;
    }
}
