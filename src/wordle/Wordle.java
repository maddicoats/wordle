package wordle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Wordle {
	public static List<String> wordList = new ArrayList<>(Arrays.asList("demon", "ghost", "wisps"));
	public static String randomWord = getRandomWord();
	
	// Console output colours:
	public static final String RESET = "\033[0m";
    public static final String BLACK = "\033[40m";
    public static final String GREEN = "\033[42m";
    public static final String YELLOW = "\033[43m";
    
    // Letter lists:
    protected static List<Character> greenLetters = new ArrayList<>();
    protected static List<Character> yellowLetters = new ArrayList<>();
    protected static List<Character> blackLetters = new ArrayList<>();

    
    // METHODS
	// get a random word from the array list
	public static String getRandomWord() {
		Random r = new Random();
		int range = wordList.size();
		int randomValue = r.nextInt(range);
		return wordList.get(randomValue);
	}
	
	
	// prompt user for guess
    public static void promtFirstGuess() {
        System.out.println();
        System.out.println("enter your first guess here:");
    }
    
 // verify the user word by length and check against available options
    public static String obtainValidUserWord () {
        Scanner s = new Scanner(System.in);
        String userInput = s.nextLine();
        userInput = userInput.toLowerCase();

        // check the length of the word and if it exists
        while (userInput.length() != 5) {
        	if (userInput.equals("just tell me")) {
        		System.out.println("\nthe word is " + randomWord + "... don't tell anyone");
        	} else if (userInput.length() > 5) {
                System.out.println("\nwoah too many letters!");
            } else if (userInput.equals("no")) {
                System.out.println("\nwow, ok. i mean no one is making you play...");
            } else if (userInput.length() != 5) {
                System.out.println("\ni don't think " + userInput + " has 5 letters bud");
            }
            // Ask for a new word
            System.out.println("please enter a 5 letter word:");
            userInput = s.nextLine();
        }
        return userInput;
    }

    
    // the game
    public static void loopThroughSixGuesses() {

        for (int j = 0; j < 6; j++) {

            String guess = obtainValidUserWord();
            String solution = randomWord;

            // check if the user won
            if (guess.equals(randomWord)) {
                System.out.print((GREEN + " " + guess.toUpperCase().replace("", "  ").trim() + " " + RESET));
                System.out.print("     guesses: " + (j+1) + "/6");
                System.out.println("\n\nyou got it! congrats! 🥳");
                System.out.println();
                break;
            } else {
                System.out.println();

                
                // loop checking every letter
                String[] positionColors = new String[5];

                for (int i = 0; i < 5; i++) {
                	char guessLetter = guess.charAt(i);
                    char solutionLetter = solution.charAt(i);
                    if (guessLetter == solutionLetter) {
                    	greenLetters.add(guess.toUpperCase().charAt(i));
                    	positionColors[i] = GREEN;
                    }
                    else if (solution.indexOf(guessLetter) != -1) {
                    	yellowLetters.add(guess.toUpperCase().charAt(i));
                    	positionColors[i] = YELLOW;
                    }
                    else {
                      blackLetters.add(guess.toUpperCase().charAt(i));
                      positionColors[i] = BLACK;
                    }	
                }

                // print user word with colours
                for (int i = 0; i < 5; i++) {
                    System.out.print(positionColors[i] + " " + guess.toUpperCase().charAt(i)+ " " + RESET);
                }
                System.out.print("     guesses: " + (j+1) + "/6");
                System.out.println();
            }

            // losing outcome
            System.out.println();
            if (j == 5) {
                System.out.println("\nyou ran out of guesses :(");
                System.out.println("\nthe word was: " + randomWord.toUpperCase());

            }
        }
    }
    
    public static void playAgain() {
    	System.out.println("do you want to play again?");
    	Scanner s = new Scanner(System.in);
        String userInput = s.nextLine();
        userInput = userInput.toLowerCase();
    	
        if (userInput.equals("y") || userInput.equals("yes") || userInput.equals("yeah") || userInput.equals("ok") || userInput.equals("sure") || userInput.equals("")) {
            randomWord = getRandomWord();
        	promtFirstGuess();
            loopThroughSixGuesses();
            playAgain();
        } else {
        	System.out.println("ok");
        }
    }
}
