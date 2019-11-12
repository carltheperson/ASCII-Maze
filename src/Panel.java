import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Panel {

	public Panel(String[] args) {
		ASCIIConverter.dimension = new Dimension(300, 150);
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

				System.out.println(RunnerStarter.runnerPoints.size());
				if (RunnerStarter.runnerPoints.size() == 0) {
					
					// Args
					for (int i = 0; i < args.length; i ++) {
						if (args[i].equals("-file")) {
							try (PrintWriter out = new PrintWriter("maze.txt")) {
							    out.println(ASCIIConverter.displayMaze());
							} catch (FileNotFoundException e) {}
							System.exit(0);
						}
						if (args[i].equals("plane")) {
							System.out.println(ASCIIConverter.displayMaze());
						}
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
