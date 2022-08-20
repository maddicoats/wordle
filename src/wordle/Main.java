package wordle;

public class Main {

	public static void main(String[] args) {
		
		System.out.println(Wordle.randomWord);
		
		System.out.println(Wordle.GREEN + " T " + Wordle.YELLOW + " E " + Wordle.BLACK + " S  T " + Wordle.RESET);
	
		Wordle.promtFirstGuess();
		Wordle.obtainValidUserWord();
		
	}

}
