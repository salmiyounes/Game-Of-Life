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

	public void fixSize(int size) {
		universe = new int[size][size] ; 

	}

	public int[][] translateData() throws IOException {
		Scanner scanner = new Scanner(System.in);
		String filename = scanner.nextLine(); 
		BufferedReader buffer = new BufferedReader(new FileReader(String.join("/", "Patterns", filename)));
		String[] result;
		int i = 0;
		int j = 0;
		try {
			StringBuilder string = new StringBuilder();
			String line = buffer.readLine();

			while (line != null) {
				string.append(line);
				string.append(System.lineSeparator());
				line = buffer.readLine();
		} 
		result = string.toString().split("\n");
		for (String l : result) {
			if (l.startsWith("!")) {
				continue;
			} else {
				for (j = 0; j < l.length(); j++) {
					copyUniverse[i][j] = (l.charAt(j) == 'O') ? 1:0;
				}
				i ++;
			}
		}
		} 
		finally {
			buffer.close() ;
		}
		return copyUniverse;
		}


	private String getPatternname(int index) {
		return filesNames.get(index);
	}


}