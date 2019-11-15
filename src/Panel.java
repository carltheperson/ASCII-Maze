import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Panel {

	public Panel(String[] args) {
		
		boolean printToFile = false;
		String fileName = "maze.txt";
		int width = 80;
		int height = 40;
		
		// Args
		for (int i = 0; i < args.length; i ++) {
			if (args[i].equals("-file")) {
				printToFile = true;
				fileName = args[i + 1];
			} if (args[i].equals("-w")) {
				width = Integer.parseInt(args[i + 1])/ 2 * 2;
			} if (args[i].equals("-h")) {
				height = Integer.parseInt(args[i + 1])/ 2 * 2;
			}
			
		}
		
		ASCIIConverter.dimension = new Dimension(width, height);
		ASCIIConverter.createLines();

		Runner runner = new Runner(1, 1, 'e');

		int runnerPointIndex = 0;

		while (true) {

			if (!runner.done) {
				runner.update();
			} else {
				// make new runner

				RunnerStarter.useRunnerPoint(runnerPointIndex);
				RunnerStarter.updateRunnerPoints();

				if (RunnerStarter.runnerPoints.size() == 0) {
					
					if (printToFile == true) {
						try (PrintWriter out = new PrintWriter(fileName)) {
						    out.println(ASCIIConverter.displayMaze());
						} catch (FileNotFoundException e) {}
							System.exit(0);
					}
						
					
					System.out.println(ASCIIConverter.displayMaze());
					System.exit(0);
				}

				runnerPointIndex = RunnerStarter.getRandomRunnerPoint();
				int[] newRunnerPoint = RunnerStarter.runnerPoints.get(runnerPointIndex);
				char newRunnerPointDirection = RunnerStarter.runnerPointsDirections.get(runnerPointIndex);

				runner = new Runner(newRunnerPoint[0], newRunnerPoint[1], newRunnerPointDirection);

				int[] line = ASCIIConverter.locateLine(newRunnerPoint[0], newRunnerPoint[1],
						ASCIIConverter.getOppositeDirection(newRunnerPointDirection));
				ASCIIConverter.maze[line[0]][line[1]] = false;

			}

		}

	}

}
