package game;

public class Rules {

	public static int whoWins (String choises[], int choise1, int choise2) {

		int half;

		half = (choises.length - 1) / 2;

		if (choise1 == choise2) {
			return 0;
		}
		if (choise1 > half) {
			if (choise2 >= choise1 - half && choise2 < choise1) {
				return -1;
			} else {
				return 1;
			}
		} else if (choise1 <= half) {
			if (choise2 <= choise1 + half && choise2 > choise1) {
				return 1;
			} else {
				return -1;
			}
		}

		return 0;
	

	}

}
