package game;

import java.util.Scanner;
import org.apache.commons.lang3.math.NumberUtils;

public class Game {

	int choiseNum;
	int compStep;
	String[] choises;
	String key;
	String hMAC;

	public static String[] createChoiseList(int choiseNum) {

		String[] choisesNames = new String[] { "Sissors", "Lizzard", "Paper", "Spok", "Rock", "Fire", "Air" };
		if (choiseNum == 3) {
			String choises[] = new String[] { choisesNames[0], choisesNames[2], choisesNames[4] };
			return choises;
		}

		String choises[] = new String[choiseNum];

		for (int i = 0; i < choises.length; i++) {
			if (i < 7) {
				choises[i] = choisesNames[i];
			} else {
				choises[i] = "var" + (i + 1);
			}
		}

		return choises;
	}

	public static String enterFromConsol() {

		@SuppressWarnings("resource")
		Scanner scr = new Scanner(System.in);
		System.out.println(">> ");
		return scr.nextLine();

	}

	public static int enterNum() {
		int num;
		String val = enterFromConsol();

		while (!NumberUtils.isDigits(val)) {
			System.out.println("Print number!");
			val = enterFromConsol();
		}

		num = Integer.parseInt(val);
		return num;
	}

	public static int checkGameNum() {
		int num = enterNum();
		while (num < 3 || num % 2 == 0) {
			System.out.println("Choises must be >= 3 and odd!");
			num = enterNum();
		}
		;

		return num;
	}

	public static String checkChoise() {

		String val = enterFromConsol();

		while (!(NumberUtils.isDigits(val) || (val.equals("?")))) {
			System.out.println("Enter number or ? ");
			val = enterFromConsol();
		}

		return val;
	}

	public static int playerChoise(String val, int n) {

		if (val.equals("?"))
			return n + 1;

		else
			while (!(Integer.parseInt(val) <= n)) {
				System.out.println("Enter num <= " + n);
				val = checkChoise();
			}

		return Integer.parseInt(val);
	}

	public static void gameplay(String a[], String hMAC, int compCh) {
		System.out.println("HMAC: " + hMAC);
		System.out.println("Available moves: ");

		for (int i = 0; i < a.length; i++) {
			System.out.println((i + 1) + " - " + a[i]);
		}
		System.out.println("?" + " - " + "help");
		int f = playerChoise(checkChoise(), a.length);
		if (f == a.length + 1) {
			Table.printTab(a);
			gameplay(a, hMAC, compCh);
		} else {
			System.out.println("Your choise " + a[f-1] + " Comp choise " + a[compCh-1]);
			if (Rules.whoWins(a, f, compCh)==1){
				System.out.println("You win!");
			} else if(Rules.whoWins(a, f, compCh)== -1) {
				System.out.println("You Lose!");
			}else if (Rules.whoWins(a, f, compCh)== 0) {
				System.out.println("Draw!");
			}
			
		}
	}

	public static void startGame() throws Exception {

		System.out.println("Enter number of choises. Choises must be >= 3 and odd ");

		Game game = new Game();
		game.choiseNum = checkGameNum();
		game.key = Crypto.getKey();
		game.choises = createChoiseList(game.choiseNum);
		game.compStep = Crypto.getCompStep(game.choiseNum);
		game.hMAC = Crypto.hMAC(game.key, game.choises[game.compStep - 1]);
		gameplay(game.choises, game.hMAC, game.compStep);
		System.out.println("Game key: " + game.key);
	}

	public static void main(String[] args) throws Exception {
		startGame();
	}

}
