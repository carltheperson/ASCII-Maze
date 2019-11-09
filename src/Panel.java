import java.awt.Dimension;

public class Panel {
		
	public Panel() {
		ASCIIConverter.dimension = new Dimension(50, 20);
		ASCIIConverter.createLines();
		ASCIIConverter.displayMaze();
		
		Runner runner = new Runner(1, 1, 'e');
		
		while (!runner.done) {
			runner.update();
			ASCIIConverter.displayMaze();
		}
		
	}

}
