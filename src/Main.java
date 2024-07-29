import java.util.Scanner;
import java.io.IOException;

public class Main {
    public static GameOfLifeLogic gameLogic;
    public static void main(String[] args) throws IOException{
        final int SIZE = 30; 

        //gameLogic = new GameOfLifeLogic(SIZE);
        LoadPatterns patterns = new LoadPatterns();
        Scanner scanner = new Scanner(System.in);

        String file = patterns.filesNames.get(0);
        System.out.println("Enter your choices: \n\t[0]- enter pattern file name. \n\t[1]- fill the board randomly");
        Integer result = (Integer) scanner.nextInt();
        if (result == 1) {
            gameLogic = new GameOfLifeLogic(SIZE, SIZE);
            gameLogic.FillBoard(gameLogic.Universe, SIZE, SIZE);
            runMainLoop(gameLogic.Universe);
        } else if (result == 0) {
            System.out.println("\nEnter file name: ");

            int[][] b = patterns.translateData();
            runMainLoop(b);
            }
        }
    private static void clearScreen() {

        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void runMainLoop(int[][] universe) throws IOException{
        gameLogic = new GameOfLifeLogic(universe.length, universe[0].length);
        for (;;) {
            try {
                clearScreen();
                printGrid(universe);
                Thread.sleep(300);

                gameLogic.NextGen(universe, gameLogic.CopyOfUniverse, universe.length, universe[0].length);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void printGrid(int[][] grid) {
        for (int[] row : grid) {
            for (int cell : row) {
                System.out.print(cell == 1 ? "▣ " : "  ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
