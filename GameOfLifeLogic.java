import java.util.Random ;

public class GameOfLifeLogic {
	//public final int SIZE;
	private final static int Dead  = 0;
	private final static int Alive = 1;
	public int[][] Universe;
	public int[][] CopyOfUniverse;

	public GameOfLifeLogic(int row, int col) {
		Universe = new int[row][col];
		CopyOfUniverse = new int[row][col];
	}

	public void FillBoard(int[][] board, int row, int col) {
		Random random = new Random();
		for (int i = 0; i < row ; i++) {
			for (int j = 0; j < col; j++) {
				board[i][j] = random.nextInt(2);
			}
		}
	}

	private Boolean isAlive(int[][] state, int i, int j, int row, int col) {
		int result = CalculateAliveNeighbors(state,i, j, row, col);
		if (result == 2 || result == 3) return true;
		return false;
	} 

	private Boolean isDead(int[][] state, int i, int j, int row, int col) {
		int result = CalculateAliveNeighbors(state, i, j,row, col);
		return result < 2;
	}

	private Boolean OverPopulation(int[][] state,int i,int j, int row, int col) {
		int result = CalculateAliveNeighbors(state, i, j, row, col);
		return result > 3;
	}

	public void NextGen(int[][] state, int[][] copy, int row, int col) {
		CopyUniverse(state, copy, row, col);
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (OverPopulation(copy, i, j, row, col)) state[i][j] = Dead;
				if (copy[i][j] == Dead) {
					if (ReProduction(copy, i, j, row, col)) state[i][j] = Alive;
				}
				if (isAlive(copy, i, j, row, col)) continue;
				if (isDead(copy, i, j, row, col)) state[i][j] = Dead;
			}
		}

	} 

	public int ScanPos(int[][] state, int i, int j) {
		return state[i][j];
	}

	private Boolean ReProduction(int[][] state, int i, int j, int row, int col) {
		int result = CalculateAliveNeighbors(state, i, j, row, col);
		if (result == 3) return true;
		return false;
	}

	private int CalculateAliveNeighbors(int[][] state, int i, int j, int row, int col) {
		int CountNeighbors = 0;
		int left = j - 1;
		int right = j + 1;
		int up = i - 1 ;
		int down = i + 1;

		if (right < col) {
			CountNeighbors += state[i][right];
			if (down < row) {
				CountNeighbors += state[down][right];
			}
			if (up >= 0) {
				CountNeighbors += state[up][right];
			}
		}
		if (left >= 0) {
			CountNeighbors += state[i][left];
			if (down < row) {
				CountNeighbors += state[down][left];
			} 
			if (up >= 0) {
				CountNeighbors += state[up][left];
			}
		}
		if (up >= 0) {
			CountNeighbors += state[up][j];
		}

		if (down < row) {
			CountNeighbors += state[down][j];
		}
		return CountNeighbors;

	}

	private void CopyUniverse(int[][] state, int[][] copy, int row, int col) {
		for (int i = 0;i < row -1 ; i++) {
			for (int j = 0; j < col - 1; j++) {
				copy[i][j] = state[i][j];
			}
		}
	}

}