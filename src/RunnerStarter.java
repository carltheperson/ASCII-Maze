import java.util.ArrayList;
import java.util.Random;

public class RunnerStarter {

	static public ArrayList<int[]> runnerPoints = new ArrayList<int[]>();
	static public ArrayList<Character> runnerPointsDirections = new ArrayList<Character>();

	static private Random randomGenerator = new Random();

	static public void updateRunnerPoints() {
		runnerPoints = new ArrayList<int[]>();
		runnerPointsDirections = new ArrayList<Character>();

		ArrayList<Character> directions;
		int[] newXY;

		for (int[] touch : ASCIIConverter.touched) {
			directions = ASCIIConverter.possibleDirections(touch[0], touch[1]);
			for (char direction : directions) {
				newXY = ASCIIConverter.getNewXY(touch[0], touch[1], direction);

				int[] line = ASCIIConverter.locateLine(newXY[0], newXY[1], direction);

				if (!ASCIIConverter.isTouched(newXY) && !alreadyInRunnerPoints(newXY) && ASCIIConverter.legalMove(line)) {

					runnerPoints.add(newXY);
					runnerPointsDirections.add(direction);

				}
			}
		}
	}

	// Deletes runnerPoint, use after getRandomRunnerPoint()
	static public void useRunnerPoint(int index) {
		if (index > runnerPoints.size()) {
			runnerPoints.remove(index);
			runnerPointsDirections.remove(index);
		}
	}

	// return random index of runnerPoints, use for runnerPoints and
	// runnerPointsDirections
	static public int getRandomRunnerPoint() {
		return randomGenerator.nextInt(runnerPoints.size());
	}

	static public boolean alreadyInRunnerPoints(int[] xy) {

		for (int[] runnerPoint : runnerPoints) {
			if (runnerPoint[0] == xy[0] && runnerPoint[1] == xy[1]) {
				return true;
			}
		}

		return false;
	}
}
