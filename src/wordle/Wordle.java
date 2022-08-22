package wordle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Wordle {
	public static List<String> wordList = new ArrayList<>(Arrays.asList("other", "there", "which", "their", "about", "write", "would", "these", "thing", "could", "sound", "water", "first", "place", "where", "after", "round", "every", "under", "great", "think", "cause", "right", "three", "small", "large", "spell", "light", "house", "again", "point", "world", "build", "earth", "stand", "found", "study", "still", "learn", "plant", "cover", "state", "never", "cross", "start", "might", "story", "while", "press", "close", "night", "north", "white", "begin", "paper", "group", "music", "those", "often", "until", "river", "carry", "began", "horse", "watch", "color", "plain", "usual", "young", "ready", "above", "leave", "black", "short", "class", "order", "south", "piece", "since", "whole", "space", "heard", "early", "reach", "table", "vowel", "money", "serve", "voice", "power", "field", "pound", "drive", "stood", "front", "teach", "final", "green", "quick", "ocean", "clear", "wheel", "force", "plane", "stead", "laugh", "check", "shape", "bring", "paint", "among", "grand", "heart", "heavy", "dance", "speak", "count", "store", "train", "sleep", "prove", "catch", "mount", "board", "glass", "grass", "visit", "month", "happy", "eight", "raise", "solve", "metal", "seven", "third", "shall", "floor", "coast", "value", "fight", "sense", "quite", "broke", "scale", "child", "speed", "organ", "dress", "cloud", "quiet", "stone", "climb", "stick", "smile", "trade", "mouth", "exact", "least", "shout", "wrote", "clean", "break", "blood", "touch", "brown", "equal", "woman", "whose", "radio", "spoke", "human", "party", "agree", "chair", "fruit", "thick", "guess", "sharp", "crowd", "sight", "hurry", "chief", "clock", "enter", "major", "fresh", "allow", "print", "block", "chart", "event", "quart", "truck", "noise", "level", "throw", "shine", "wrong", "broad", "anger", "claim", "sugar", "death", "skill", "women", "thank", "match", "steel", "guide", "score", "apple", "pitch", "dream", "total", "basic", "smell", "track", "shore", "sheet", "favor", "spend", "chord", "share", "bread", "offer", "slave", "chick", "enemy", "reply", "drink", "occur", "range", "steam", "meant", "teeth", "shell"));
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
    
    // History
    private static int wins = 0;
    private static int losses = 0;
    private static int gamesPlayed = 0;
    private static int inOne = 0;
    private static int inTwo = 0;
    private static int inThree = 0;
    private static int inFour = 0;
    private static int inFive = 0;
    private static int inSix = 0;
    
    // METHODS

    
	public static void setWins(int wins) {
		Wordle.wins = wins;
	}

	public static void setGamesPlayed(int gamesPlayed) {
		Wordle.gamesPlayed = gamesPlayed;
	}
	
	public static void setLosses(int losses) {
		Wordle.losses = losses;
	}
	
	


	public static void setInOne(int inOne) {
		Wordle.inOne = inOne;
	}

	public static void setInTwo(int inTwo) {
		Wordle.inTwo = inTwo;
	}

	public static void setInThree(int inThree) {
		Wordle.inThree = inThree;
	}

	public static void setInFour(int inFour) {
		Wordle.inFour = inFour;
	}

	public static void setInFive(int inFive) {
		Wordle.inFive = inFive;
	}

	public static void setInSix(int inSix) {
		Wordle.inSix = inSix;
	}

	public static String getStats() {
		double p = ((double) wins / (double) gamesPlayed);
		p *= 100;
		Integer percentage = (int) p;
		String percentagePrint;
		if (percentage>99) {
			percentagePrint = Integer.toString((int) p) + "% ";
		} else if (percentage<10){
			percentagePrint = Integer.toString((int) p) + "%   ";
		} else {
			percentagePrint = Integer.toString((int) p) + "%  ";
		}
		String gamesPrint;
		if (gamesPlayed>9) {
			gamesPrint = Integer.toString(gamesPlayed);
		} else {
			gamesPrint = Integer.toString(gamesPlayed) + " ";
		}
		String winsPrint;
		if (wins>9) {
			winsPrint = Integer.toString(wins);
		} else {
			winsPrint = Integer.toString(wins) + " ";
		}
		String lossesPrint;
		if (losses>9) {
			lossesPrint = Integer.toString(losses);
		} else {
			lossesPrint = Integer.toString(losses) + " ";
		}
		
		return "\033[44m" + " S T A T I S T I C S  \n" + " WINS: " + winsPrint + 
				"             \n LOSSES: " + lossesPrint + "           \n GAMES PLAYED: " + 
				gamesPrint + "     \n WIN PERCENTAGE: " + percentagePrint + RESET;
	}
	
	public static String getGraph() {
		String square = "⬛";
		String one = square.repeat(inOne);
		String two = square.repeat(inTwo);
		String three = square.repeat(inThree);
		String four = square.repeat(inFour);
		String five = square.repeat(inFive);
		String six = square.repeat(inSix);
		
		return " G U E S S  D I S T R I B U S T I O N : \n" + " 1 | " + one + "\n 2 | " + two + "\n 3 | " + three + "\n 4 | " + four + "\n 5 | " + five + "\n 6 | " + six;
	}


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
            	if (j == 5) {
            		setInSix(inSix + 1);
            	} else if (j == 4) {
            		setInFive(inFive + 1);
            	} else if (j == 3) {
            		setInFour(inFour + 1);
            	} else if (j == 2) {
            		setInThree(inThree + 1);
            	} else if (j == 1) {
            		setInTwo(inTwo + 1);
            	} else if (j == 0) {
            		setInOne(inOne + 1);
            	}
            		
            	setWins(wins + 1);
            	setGamesPlayed(gamesPlayed + 1);
                System.out.print((GREEN + " " + guess.toUpperCase().replace("", "  ").trim() + " " + RESET));
                System.out.print("     guesses: " + (j+1) + "/6");
                System.out.println("\n\nyou got it! congrats! 🥳");
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
            	setLosses(losses + 1);
            	setGamesPlayed(gamesPlayed + 1);
                System.out.println("\nyou ran out of guesses :(");
                System.out.println("\nthe word was: " + randomWord.toUpperCase());

            }
        }
    }
    
    public static void playAgain() {
    	System.out.println("\n" + getStats());
    	System.out.println("\n" + getGraph());
    	System.out.println("\ndo you want to play again?");
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
