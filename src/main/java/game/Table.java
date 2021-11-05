package game;

import com.github.freva.asciitable.AsciiTable;

public class Table {

	public static String[] headerTab(String[] choises) {
		String[] header = new String[choises.length + 1];
		header[0] = "";
		for (int i = 1; i < header.length; i++) {
			header[i] = choises[i - 1];
		}
		return header;
	}

	public static String[][] dataTab(String[] choises) {

		String[][] data = new String[choises.length][choises.length + 1];

		for (int i = 0; i < choises.length; i++) {
			data[i][0] = choises[i];
		}

		for (int i = 0; i < choises.length; i++) {
			for (int j = 1; j < data[i].length; j++) {
				data[i][j] = Integer.toString(Rules.whoWins(choises, i + 1, j));

				if (Rules.whoWins(choises, i + 1, j) == 0) {
					data[i][j] = "Draw";
				} else if (Rules.whoWins(choises, i + 1, j) == 1) {
					data[i][j] = "Win";
				} else if (Rules.whoWins(choises, i + 1, j) == -1) {
					data[i][j] = "Lose";
				}
			}
		}

		return data;
	}

	public static void printTab(String[] choises) {
		System.out.println(AsciiTable.getTable(headerTab(choises), dataTab(choises)));
	}

}
