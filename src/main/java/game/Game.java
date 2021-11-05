package game;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import org.apache.commons.lang3.math.NumberUtils;

public class Game {

	int choiseNum;
	int compStep;
	String[] choises;
	String key;
	String hMAC;

	public static String[] checkAgrs(String[] args) {

		while (!(check(args) & checkDistinct(args))) {
			System.out.println("Number of choises must be odd and >=3 and unique ");
			args = toStringArr(enterFromConsol());
		}

		return args;
	}

	public static boolean check(String[] args) {
		if (args.length % 2 == 0 | args.length < 3) {
			return false;
		}
		return true;
	}

	public static boolean checkDistinct(String[] args) {

		Collection<String> list = Arrays.asList(args);
		List<Object> distinctElements = list.stream().distinct().collect(Collectors.toList());
		if (distinctElements.size() == args.length) {
			return true;
		}
		return false;
	}

	public static String[] toStringArr(String str) {
		String[] stArr = null;
		stArr = str.split(" ");
		return stArr;
	}

	public static String enterFromConsol() {

		@SuppressWarnings("resource")
		Scanner scr = new Scanner(System.in);
		System.out.println(">> ");
		return scr.nextLine();

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
			System.out.println("Your choise " + a[f - 1] + " Comp choise " + a[compCh - 1]);
			if (Rules.whoWins(a, f, compCh) == 1) {
				System.out.println("You win!");
			} else if (Rules.whoWins(a, f, compCh) == -1) {
				System.out.println("You Lose!");
			} else if (Rules.whoWins(a, f, compCh) == 0) {
				System.out.println("Draw!");
			}

		}
	}

	public static void startGame(String[] arg) throws Exception {

		Game game = new Game();
		game.choiseNum = arg.length;
		game.key = Crypto.getKey();
		game.choises = arg;
		game.compStep = Crypto.getCompStep(game.choiseNum);
		game.hMAC = Crypto.hMAC(game.key, game.choises[game.compStep - 1]);
		gameplay(game.choises, game.hMAC, game.compStep);
		System.out.println("Game key: " + game.key);
	}

	public static void main(String[] args) throws Exception {
		String[] arg = checkAgrs(args);
		startGame(arg);
	}

}
