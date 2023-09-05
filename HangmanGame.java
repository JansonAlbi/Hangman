import java.util.Scanner;

public class HangmanGame {
    private static final String[] WORDS = {"programming", "hangman", "java", "computer", "keyboard"};
    private static final int MAX_TRIES = 6;

    private String secretWord;
    private char[] guessedWord;
    private int triesLeft;

    public HangmanGame() {
        secretWord = getRandomWord();
        guessedWord = new char[secretWord.length()];
        triesLeft = MAX_TRIES;
        initializeGuessedWord();
    }

    private String getRandomWord() {
        int randomIndex = (int) (Math.random() * WORDS.length);
        return WORDS[randomIndex];
    }

    private void initializeGuessedWord() {
        for (int i = 0; i < secretWord.length(); i++) {
            guessedWord[i] = '_';
        }
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);

        while (triesLeft > 0) {
            displayGameStatus();
            displayGuessedWord();
            System.out.print("Enter a letter or guess the word: ");
            String guess = scanner.nextLine().toLowerCase();

            if (guess.equals(secretWord)) {
                System.out.println("Congratulations! You've guessed the word: " + secretWord);
                break;
            } else if (guess.length() == 1 && Character.isLetter(guess.charAt(0))) {
                char letterGuess = guess.charAt(0);
                if (isValidGuess(letterGuess)) {
                    updateGuessedWord(letterGuess);
                    if (isWordGuessed()) {
                        System.out.println("Congratulations! You've guessed the word: " + secretWord);
                        break;
                    }
                } else {
                    System.out.println("Invalid guess. Please guess a single letter.");
                }
            } else {
                System.out.println("Invalid input. Please enter a single letter or guess the whole word.");
            }
        }

        if (triesLeft == 0) {
            displayGameStatus();
            System.out.println("You're out of tries! The word was: " + secretWord);
        }

        scanner.close();
    }

    private boolean isValidGuess(char guess) {
        return !isLetterAlreadyGuessed(guess);
    }

    private boolean isLetterAlreadyGuessed(char guess) {
        for (char letter : guessedWord) {
            if (letter == guess) {
                System.out.println("You've already guessed that letter.");
                return true;
            }
        }
        return false;
    }

    private void updateGuessedWord(char guess) {
        boolean correctGuess = false;
        for (int i = 0; i < secretWord.length(); i++) {
            if (secretWord.charAt(i) == guess) {
                guessedWord[i] = guess;
                correctGuess = true;
            }
        }

        if (!correctGuess) {
            triesLeft--;
        }
    }

    private boolean isWordGuessed() {
        for (char letter : guessedWord) {
            if (letter == '_') {
                return false;
            }
        }
        return true;
    }

    private void displayGameStatus() {
        System.out.println("Tries left: " + triesLeft);
    }

    private void displayGuessedWord() {
        System.out.println("Current word: " + new String(guessedWord));
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Hangman!");
        HangmanGame hangman = new HangmanGame();
        hangman.play();
    }
}
