import java.util.ArrayList;
import java.util.Random;

public class Runner {
	public int x;
	public int y;

	public int oldx;
	public int oldy;

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

		ASCIIConverter.touch(new int[] { x, y });
		move = ASCIIConverter.locateLine(x, y, direction);

		// Do move

		if (ASCIIConverter.legalMove(move)) {
			ASCIIConverter.maze[move[0]][move[1]] = false;
		}

		// Set new x,y from move
		int[] newXY = ASCIIConverter.getNewXY(x, y, direction);

		oldx = x;
		oldy = y;

		x = newXY[0];
		y = newXY[1];

		directions = ASCIIConverter.possibleDirections(x, y);
		ASCIIConverter.touch(new int[] { x, y });
		if (directions.size() == 0) { // Check if dead
			done = true;
		} else {
			//                                                             V--- Change this to change maze
			if (!directions.contains(direction) || randomGenerator.nextInt(11) > 7) {
				direction = directions.get(randomGenerator.nextInt(directions.size()));
			}

		}

	}

}
