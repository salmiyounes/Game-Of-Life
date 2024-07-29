import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LoadPatterns {
	public static ArrayList<String> filesNames = new ArrayList<>();
	public static int SIZE, Lenght;
	public static int[][] universe;
	public static int[][] copyUniverse = new int[50][50]; 

	public LoadPatterns() {
		File[] files = new File("Patterns/").listFiles();
		if (files != null) {
			for (File file: files) {
				if (file.getName().endsWith("cells")) {
					filesNames.add(file.getName());
				}
			}

		}
	}

	public void fixSize(int i, int j) {
		universe = new int[i][j] ; 

	}

	public int[][] translateData() throws IOException {
		Scanner scanner = new Scanner(System.in);
		String filename = scanner.nextLine().strip(); 
		BufferedReader buffer = new BufferedReader(new FileReader(String.join("/", "Patterns", filename)));
		String[] result;
		try {
			StringBuilder string = new StringBuilder();
			String line = buffer.readLine();

			while (line != null) {
				string.append(line);
				string.append(System.lineSeparator());
				line = buffer.readLine();
		}
		result = string.toString().split("\n");
		int i = 0;
		for (String l : result) {
			if (l.startsWith("!")) {
				continue;
			} else {
				SIZE = l.length();
				i ++;
			}
		}
		Lenght = i;
		} 
		finally {
			buffer.close() ;
		}

		fixSize(Lenght+2, SIZE +2);
		int i =0;
		for (String l : result) {
			if (l.startsWith("!")) continue;
			else {
				for (int j = 0; j < l.length(); j++) {
					universe[i][j] = (l.charAt(j) == 'O') ? 1:0;
				}
				i ++;
			}
		}

		return universe;
		}


	private String getPatternname(int index) {
		return filesNames.get(index);
	}


}