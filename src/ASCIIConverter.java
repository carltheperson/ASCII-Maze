import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Random;

public class ASCIIConverter {

	static public Dimension dimension;

	static public boolean[][] maze;

	static public ArrayList<int[]> touched = new ArrayList<int[]>();

	static Random randomGenerator = new Random();

	public ASCIIConverter(Dimension dimension) {
	}

	static public void createLines() {
		maze = new boolean[dimension.height][dimension.width];

		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[i].length; j++) {
				maze[i][j] = true;
			}
		}

	}

	static public int[] locateLine(int x, int y, char direction) {

		if (direction == 'n') {
			return new int[] { y - 1, x * 2 - 1 };
		}
		if (direction == 's') {
			return new int[] { y, x * 2 - 1 };
		}
		if (direction == 'w') {
			return new int[] { y, x * 2 - 2 };
		}
		if (direction == 'e') {
			return new int[] { y, x * 2 };
		}

		return null;
	}

	static public boolean legalMove(int[] xy) {

		if (xy[1] <= 0 || xy[1] >= maze[0].length - 2) {
			return false;
		}

		if (xy[1] % 2 == 0 && xy[0] == maze.length - 1) {
			return true;
		}

		if (xy[0] <= 0 || xy[0] >= maze.length - 1) {
			return false;
		}

		return true;
	}

	static public ArrayList<Character> possibleDirections(int x, int y) {
		char[] directions = new char[] { 'n', 's', 'w', 'e' };

		ArrayList<Character> possible = new ArrayList<Character>();

		for (int i = 0; i < directions.length; i++) {

			if (legalMove(locateLine(x, y, directions[i])) && !isTouched(getNewXY(x, y, directions[i]))) {
				possible.add(directions[i]);
			}
		}

		return possible;
	}

	static public void touch(int[] xy) {
		touched.add(xy);
	}

	static public boolean isTouched(int[] xy) {
		boolean isItTouched = false;

		for (int[] thouch : touched) {
			if (thouch[0] == xy[0] && thouch[1] == xy[1]) {
				isItTouched = true;
			}
		}

		return isItTouched;
	}

	static public int[] getNewXY(int x, int y, char direction) {
		if (direction == 'n') {
			return new int[] { x, y - 1 };
		}
		if (direction == 's') {
			return new int[] { x, y + 1 };
		}
		if (direction == 'w') {
			return new int[] { x - 1, y };
		}
		if (direction == 'e') {
			return new int[] { x + 1, y };
		}

		return null;

	}

	static public char getOppositeDirection(char direction) {
		char[] directions = new char[] { 'n', 's', 'w', 'e' };
		char[] Odirections = new char[] { 's', 'n', 'e', 'w' };

		for (int i = 0; i < directions.length; i++) {
			if (directions[i] == direction) {
				return Odirections[i];
			}
		}
		return 'x';
	}

	static public void displayMaze() {

		ArrayList<String> finalMaze = new ArrayList<String>();

		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[i].length; j++) {

				if (maze[i][j] == false) {

					finalMaze.add(" ");
				} else {
					if (j % 2 == 0 && i != 0) {
						finalMaze.add("|");
					} else if (j != maze[i].length - 1) {
						finalMaze.add("_");
					}
				}

			}
			finalMaze.add("\n");
		}

		System.out.println(fix(finalMaze));
	}

	static public String fix(ArrayList<String> finalMaze) {
		// Sorry for this unreadable code

		int firstArrow = 0;
		int firstEntrance = 0;

		int n = 0;
		int lastN = 0;
		for (int i = 0; i < finalMaze.size(); i++) {

			if (finalMaze.get(i) == "\n" && n == 0) {
				n = i + 1;
			} else if (finalMaze.get(i) == "\n") {
				lastN = i;
			}
		}

		// Weird corner bug
		finalMaze.set(lastN - n + 3, "_");
		finalMaze.set(n * 2 - 4, " ");

		for (int i = 0; i < finalMaze.size(); i++) {
			if (i - 2 > 0) {
				if (finalMaze.get(i) == "_" && finalMaze.get(i - 2) == "_" && finalMaze.get(i - 1) == " ") {
					finalMaze.set(i - 1, "_");
				}

				try {
					// Hole fix
					if (finalMaze.get(i) == "_") {
						if (finalMaze.get(i - 1) == " " && finalMaze.get(i + 1) == " " && finalMaze.get(i - 2) == " "
								&& finalMaze.get(i + 2) == " " && finalMaze.get(i - 1 + n) == " "
								&& finalMaze.get(i + 1 + n) == " ") {
							int[] sides = new int[] { i - 1, i + 1, i - 1 + n, i + 1 + n };
							finalMaze.set(sides[randomGenerator.nextInt(sides.length)], "|");
						}
					}

					if (finalMaze.get(i) == "|") {
						if (finalMaze.get(i - 1) == " " && finalMaze.get(i + 1) == " "
								&& finalMaze.get(i - 1 - n) == " " && finalMaze.get(i + 1 - n) == " "
								&& finalMaze.get(i - n) == " " && finalMaze.get(i + n) == " ") {
							int[] sides = new int[] { i - 1, i + 1, i - 1 - n, i + 1 - n };
							finalMaze.set(sides[randomGenerator.nextInt(sides.length)], "_");
						}
					}

				} catch (IndexOutOfBoundsException e) {
				}
			}

			// Arrow stuff

			if (finalMaze.get(i) == "\n" || i == 0) {
				if (firstArrow == 1) {
					finalMaze.set(i, "\n -->  ");
				} else {
					finalMaze.set(i, "\n      ");
				}
				firstArrow += 1;
			}

			if (finalMaze.get(i) == "|") {
				firstEntrance += 1;
				if (firstEntrance == 1) {
					finalMaze.set(i, " ");
				}
			}
		}
		// Arrow stuff
		finalMaze.set(finalMaze.size() - 2, "  -->");

		return String.join("", finalMaze);
	}

}
