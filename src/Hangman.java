import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.io.File;

public class Hangman {
    public static void main(String[] ars) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("C:\\Users\\Conner Vigil\\IdeaProjects\\Hangman\\src\\Words"));
        Scanner keyboard = new Scanner(System.in);

        List<String> words = new ArrayList<String>();
        while (scanner.hasNext()) {
            words.add(scanner.nextLine());
        }

        Random rand = new Random();
        String word = words.get(rand.nextInt(words.size()));

        List<Character> playerGuesses = new ArrayList<>();

        int wrongCount = 0;
        while (true) {
            printHangedMan(wrongCount);

            if (wrongCount >= 6) {
                System.out.println("You lose!");
                System.out.println("The answer was... "+word);
                break;
            }

            printWordState(word, playerGuesses);
            printGuessedLetters(playerGuesses);

            if (!getPlayerGuess(keyboard, word, playerGuesses)) {
                wrongCount++;
            }

            if (printWordState(word, playerGuesses)) {
                System.out.println("********** You win! **********");
                break;
            }
            System.out.println("Please enter your guess for the word: ");
            if (keyboard.nextLine().equals(word)) {
                System.out.println("********** You win! **********");
                break;
            }
            else {
                System.out.println("Nope! Try again.");
            }
            System.out.println("");
            System.out.println("");
        }
    }

    private static boolean printWordState(String word, List<Character> playerGuesses) {
        int correctCount = 0;
        for (int i = 0; i < word.length(); i ++) {
            if (playerGuesses.contains(word.charAt(i))) {
                System.out.print(word.charAt(i));
                correctCount++;
            }
            else {
                System.out.print("-");
            }
        }
        System.out.println();
        return (word.length() == correctCount);
    }

    private static boolean getPlayerGuess(Scanner keyboard, String word, List<Character> playerGuesses) {
        System.out.println("Please enter a letter: ");
        String letterGuess = keyboard.nextLine();
        playerGuesses.add(letterGuess.charAt(0));
        return word.contains(letterGuess);
    }

    private static void printHangedMan(Integer wrongCount) {
        System.out.println(" -------");
        System.out.println(" |      ");
        if (wrongCount >= 1) {
            System.out.println(" O      ");
        }
        if (wrongCount >= 2) {
            System.out.print("\\ ");
            if (wrongCount >= 3) {
                System.out.println("/");
            } else {
                System.out.println("");
            }
        }
        if (wrongCount >= 4) {
            System.out.println(" |");
        }
        if (wrongCount >= 5) {
            System.out.print("/  ");
            if (wrongCount >= 6) {
                System.out.println("\\");
            } else {
                System.out.println("");
            }
        }
    }

    private static void printGuessedLetters(List<Character> playerGuesses) {
        System.out.print("You have guessed: ");
        for (int i = 0; i < playerGuesses.size(); i++) {
            System.out.print(playerGuesses.get(i)+" ");
        }
        System.out.println("");
    }
}
