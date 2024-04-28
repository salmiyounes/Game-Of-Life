import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final int SIZE = 30; 
        GameOfLifeLogic gameLogic = new GameOfLifeLogic(SIZE);
        Scanner scanner = new Scanner(System.in);
        gameLogic.FillBoard(SIZE, gameLogic.Universe);
        while (true) {
            try {
                clearScreen();
                printGrid(gameLogic.Universe);
                Thread.sleep(100);

                gameLogic.NextGen(gameLogic.Universe, gameLogic.CopyOfUniverse);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void clearScreen() {

        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void printGrid(int[][] grid) {
        for (int[] row : grid) {
            for (int cell : row) {
                System.out.print(cell == 1 ? "▣ " : "□ ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
