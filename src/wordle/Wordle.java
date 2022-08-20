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
        System.out.println("enter your first guess:");
    }
    
 // verify the validity of the user word by length and check against available options
    public static String obtainValidUserWord () {
        Scanner s = new Scanner(System.in);  // Create a Scanner object
        String userInput = s.nextLine();  // Read user input
        userInput = userInput.toLowerCase(); // covert to lowercase

        // check the length of the word and if it exists
        while (userInput.length() != 5) {
            if (userInput.length() > 5) {
                System.out.println("woah too many letters!");
            } else if (userInput.equals("no")) {
                System.out.println("wow, ok. i mean no one is making you play...");
            } else if (userInput.length() != 5) {
                System.out.println("i don't think " + userInput + " has 5 letters bud");
            }
            // Ask for a new word
            System.out.println("please enter a 5 letter word:");
            userInput = s.nextLine();
        }
        return userInput;
    }
    
    public void loopThroughSixGuesses() {

        for (int j = 0; j < 6; j++) {

            System.out.print("--> " + (j + 1) + ") ");

            String userWord = obtainValidUserWord();

            String chosenWordWithoutGreensAndYellows = chosenWordWithoutAccents;

            // check if the user won: the userWord is the same as chosenWord
            if (userWord.equals(chosenWordWithoutAccents)) {
                System.out.println((result + ANSI_GREEN_BACKGROUND + userWord.toUpperCase() + ANSI_RESET));
                System.out.println();
                System.out.println(youWonMessage);
                System.out.println();
                break;
            } else {

                System.out.print(result);

                // Loop checking every letter

                String userWordWithoutGreensAndYellows = userWord;
                String[] positionColors = new String[5];

                // check for green letters
                for (int i = 0; i < 5; i++) {
                    if (userWord.charAt(i) == randomWord.charAt(i)) {
                        userWordWithoutGreensAndYellows = replaceChar(userWordWithoutGreensAndYellows, '0', i);
                        chosenWordWithoutGreensAndYellows = replaceChar(chosenWordWithoutGreensAndYellows, '0', i);
                        // System.out.print(ANSI_GREEN_BACKGROUND + userWord.toUpperCase().charAt(i) + ANSI_RESET);
                        greenLetters.add(userWord.toUpperCase().charAt(i));
                        positionColors[i] = ANSI_GREEN_BACKGROUND;
                    }
                }

                // check for yellow letters
                for (int i = 0; i < 5; i++) {
                    if (userWordWithoutGreensAndYellows.charAt(i) == '0') {

                    } else if (chosenWordWithoutGreensAndYellows.indexOf(userWordWithoutGreensAndYellows.charAt(i)) != -1) {
                        chosenWordWithoutGreensAndYellows = replaceChar(chosenWordWithoutGreensAndYellows, '0', chosenWordWithoutGreensAndYellows.indexOf(userWordWithoutGreensAndYellows.charAt(i)));
                        userWordWithoutGreensAndYellows = replaceChar(userWordWithoutGreensAndYellows, '0', i);
                        yellowLetters.add(userWord.toUpperCase().charAt(i));
                        positionColors[i] = ANSI_YELLOW_BACKGROUND;
                    } else {
                        greyLetters.add(userWord.toUpperCase().charAt(i));
                        positionColors[i] = ANSI_GREY_BACKGROUND;
                    }
                }

                // print user word with colors
                for (int i = 0; i < 5; i++) {
                    System.out.print(positionColors[i] + userWord.toUpperCase().charAt(i) + ANSI_RESET);
                }
                System.out.println();


                // print alphabet
                printingColouredAlphabet(greenLetters, yellowLetters, greyLetters);

            }

            // Losing statement
            System.out.println();
            if (j == 5) {
                System.out.println();
                System.out.println("you ran out of guesses :(");
                System.out.println();
                System.out.println("the word was: " + randomWord.toUpperCase());

            }
        }
	
}
