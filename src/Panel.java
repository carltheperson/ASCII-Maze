import java.awt.Dimension;

public class Panel {

	public Panel() {
		ASCIIConverter.dimension = new Dimension(80, 40);
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
					ASCIIConverter.displayMaze();
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
