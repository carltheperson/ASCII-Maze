import java.awt.Dimension;

public class Panel {
		
	public Panel() {
		ASCIIConverter.dimension = new Dimension(100, 40);
		ASCIIConverter.createLines();
		
		Runner runner = new Runner(1, 1, 'e');

		
		while (true) {
			
			if (!runner.done) {
				runner.update();
			}

			
			ASCIIConverter.displayMaze();

			
		}
		
	}

}
