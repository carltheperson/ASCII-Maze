import java.util.ArrayList;
import java.util.Random;

public class Runner {
	private int x;
	private int y;
	private char direction;

	public boolean done = false;

	private Random randomGenerator = new Random();

	private ArrayList<Character> directions = ASCIIConverter.possibleDirections(x, y);
	private int[] move;

	public Runner(int x, int y, char direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}

	public void update() {

		move = ASCIIConverter.locateLine(x, y, direction);

		// Do move
		ASCIIConverter.maze[move[0]][move[1]] = false;
		ASCIIConverter.touch(new int[] { x, y });

		// Set new x,y from move
		int[] newXY = ASCIIConverter.getNewXY(x, y, direction);
		x = newXY[0];
		y = newXY[1];

		// Check if dead
		directions = ASCIIConverter.possibleDirections(x, y);
		if (directions.size() == 0) {
			ASCIIConverter.displayMaze();
			done = true;
		} else {
			// Pick new direction
			direction = directions.get(randomGenerator.nextInt(directions.size()));
		}

	}

}
