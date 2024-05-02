import java.util.Scanner;
import java.io.IOException;

public class Main {
    public static void main(String[] args)  throws IOException{
        final int SIZE = 30; 

        GameOfLifeLogic gameLogic = new GameOfLifeLogic(SIZE);
        LoadPatterns patterns = new LoadPatterns();
        Scanner scanner = new Scanner(System.in);

        String file = patterns.filesNames.get(0);
        System.out.println("Enter your choices: \n\t[0]- enter pattern file name. \n\t[1]- fill the board randomly");
        Integer result = (Integer) scanner.nextInt();
        if (result == 1) {
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
        } else if (result == 0) {
            System.out.println("\nEnter file name: ");
            int[][] b = patterns.translateData();
            while (true) {
                try {
                    clearScreen();
                    printGrid(b);
                    Thread.sleep(200);

                    gameLogic.NextGen(b, gameLogic.CopyOfUniverse);
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
                System.out.print(cell == 1 ? "▣ " : "  ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
