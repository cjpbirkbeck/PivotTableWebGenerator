import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class TestGenerator {
	
	// Note this is a very ad-hoc program just to demonstrate the generator's capablities.
	// In the final interation it will be replaced with something more robust.
	
	String[][] data = new String[6][4];
	int i = 0;
	int j = 0;

	public String[][] getData() {
		return data;
	}

	public TestGenerator() {
		// TODO Auto-generated constructor stub
	}
	
	public void addData(String next) {
		data[i][j] = next;
		j++;
		
		if (j == data[i].length) {
			i++;
			j = 0;
		} else {
			
		}
	}

	public static void main(String[] args) {
		TestGenerator test = new TestGenerator();
		
		Scanner csv = null;
		PrintWriter html = null;
		Queue<String> elements = new LinkedList<String>();
		HTMLGenerator getHTML = new HTMLGenerator("Here is my table");
		
		String csvLine = null;
		
		Path currentDir = Paths.get("");
		String dirPath = currentDir.toAbsolutePath().toString();
		System.out.println(dirPath);
		
		try {
			csv = new Scanner(new FileInputStream(dirPath + "\\example.csv"));
			System.out.println(dirPath + "\\example.csv");
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find CSV file. Exiting.");
			System.exit(0);
		}
		
		try {
			html = new PrintWriter(new FileOutputStream(dirPath + "\\new_table.html"));
			System.out.println(dirPath + "\\new_table.html");
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find HTML file. Exiting.");
			System.exit(0);
		}
		
		System.out.println("Starting write.");
		
		while (csv.hasNext()) {
			csvLine = csv.nextLine();
			int entryStart = 0;
			int entryEnd = 0;
			
			// Loop will find each
			while (entryEnd < csvLine.length()) {
				if (csvLine.charAt(entryEnd) == ',') {
					String entry = csvLine.substring(entryStart, entryEnd);
					System.out.println(entry);
					test.addData(entry);
					entryStart = entryEnd + 1;
				}
				entryEnd++;
			}
			
			if (entryStart != entryEnd) {
				String entry = csvLine.substring(entryStart, csvLine.length());
				test.addData(entry);
				entryStart = entryEnd + 1;
			}
		}
		
		elements = getHTML.generateHTML(test.getData());
		
		while (!elements.isEmpty()) {
			String element = elements.remove();
			
			System.out.println(element);
			
			html.println(element);
		}
		
		html.flush();
		
		System.out.println("Ending write.");

	}

}
