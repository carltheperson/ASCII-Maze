import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;

public class ASCIIConverter {
	
	static public Dimension dimension;
	
	static public boolean[][] maze;
	
	public ASCIIConverter (Dimension dimension) {
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
			return new int[]{y - 1, x * 2 - 1};
		} if (direction == 's') {
			return new int[]{y, x * 2 - 1};
		} if (direction == 'w') {
			return new int[]{y, x * 2 - 2};
		} if (direction == 'e') {
			return new int[]{y, x * 2};
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
		char[] directions = new char[]{'n', 's', 'w', 'e'};
	
		ArrayList<Character> possible = new ArrayList<Character>();
		
		for (int i = 0; i < directions.length; i++) {
			if (legalMove(locateLine(x, y, directions[i]))) {
				possible.add(directions[i]);
			}
		}
		
		return possible;
	}
	
	static public void displayMaze() {
		
		try {
			TimeUnit.MILLISECONDS.sleep(6);
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String finalMaze = "";
		
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[i].length; j++) {
				
				if (maze[i][j] == false) {
					finalMaze += " ";
				} else {
					if (j % 2 == 0 && i != 0) {
						finalMaze += "|";
					} else if (j != maze[i].length - 1) {
						finalMaze += "_";
					}
				}
				
			}
			finalMaze += "\n";
		}
		
		System.out.println(finalMaze);
	}
	
	
	
}
