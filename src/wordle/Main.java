package wordle;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("\n" + Wordle.GREEN + " H " + Wordle.YELLOW + " E " + Wordle.BLACK + " L  L  O " + Wordle.RESET);
		
		System.out.println("\nHere's how to play!\nYou need to figure out the secret 5 letter word by typing in your guesses.");
		System.out.println("  > Green means you have the letter in correct position\n  > Yellow means the letter is in the word but not in the correct position\n  > You will have 6 chances to guess the correct word");
	
		Wordle.promtFirstGuess();
		Wordle.loopThroughSixGuesses();
		
		Wordle.playAgain();
	}

}
