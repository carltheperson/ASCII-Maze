
public class Runner {
	private int x;
	private int y;
	private char direction;
	
	public boolean done = false;
	
	public Runner(int x, int y, char direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}
	
	public void update() {
		int[] move = ASCIIConverter.locateLine(x, y, direction);
		ASCIIConverter.maze[move[0]][move[1]] = false;
	}

}
