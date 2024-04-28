import java.util.Random ;

public class GameOfLifeLogic {
	public final int SIZE;
	private final static int Dead  = 0;
	private final static int Alive = 1;
	public int[][] Universe;
	public int[][] CopyOfUniverse;

	public GameOfLifeLogic(int size) {
		SIZE = size;
		Universe = new int[SIZE][SIZE];
		CopyOfUniverse = new int[SIZE][SIZE];
	}

	public void FillBoard(int size, int[][] board) {
		Random random = new Random();
		for (int i = 0; i < size ; i++) {
			for (int j = 0; j < size; j++) {
				board[i][j] = random.nextInt(2);
			}
		}
	}

	private Boolean isAlive(int[][] state, int i, int j) {
		int result = CalculateAliveNeighbors(state,i, j);
		if (result == 2 || result == 3) return true;
		return false;
	} 

	private Boolean isDead(int[][] state, int i, int j) {
		int result = CalculateAliveNeighbors(state, i, j);
		return result < 2;
	}

	private Boolean OverPopulation(int[][] state,int i,int j) {
		int result = CalculateAliveNeighbors(state, i, j);
		return result > 3;
	}

	public void NextGen(int[][] state, int[][] copy) {
		CopyUniverse(state, copy);
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (OverPopulation(copy, i, j)) state[i][j] = Dead;
				if (copy[i][j] == Dead) {
					if (ReProduction(copy, i, j)) state[i][j] = Alive;
				}
				if (isAlive(copy, i, j)) continue;
				if (isDead(copy, i, j)) state[i][j] = Dead;
			}
		}

	} 

	public int ScanPos(int[][] state, int i, int j) {
		return state[i][j];
	}

	private Boolean ReProduction(int[][] state, int i, int j) {
		int result = CalculateAliveNeighbors(state, i, j);
		if (result == 3) return true;
		return false;
	}

	private int CalculateAliveNeighbors(int[][] state, int i, int j) {
		int CountNeighbors = 0;
		int left = j - 1;
		int right = j + 1;
		int up = i - 1 ;
		int down = i + 1;

		if (right < SIZE) {
			CountNeighbors += state[i][right];
			if (down < SIZE) {
				CountNeighbors += state[down][right];
			}
			if (up >= 0) {
				CountNeighbors += state[up][right];
			}
		}
		if (left >= 0) {
			CountNeighbors += state[i][left];
			if (down < SIZE) {
				CountNeighbors += state[down][left];
			} 
			if (up >= 0) {
				CountNeighbors += state[up][left];
			}
		}
		if (up >= 0) {
			CountNeighbors += state[up][j];
		}

		if (down < SIZE) {
			CountNeighbors += state[down][j];
		}
		return CountNeighbors;

	}

	private void CopyUniverse(int[][] state, int[][] copy) {
		for (int i = 0;i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				copy[i][j] = state[i][j];
			}
		}
	}

}