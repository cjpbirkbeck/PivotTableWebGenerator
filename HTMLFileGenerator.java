import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Helps the HTML service by 
 * 
 * @author Christopher Birkbeck
 *
 */
public class HTMLFileGenerator {
	
	Path currentDir;
	String dirPath;
	HTMLGenerator htmlGenerator;
	PrintWriter html;

	public HTMLFileGenerator() {
		currentDir = Paths.get("");
		dirPath = currentDir.toAbsolutePath().toString();
		htmlGenerator = new HTMLGenerator();
	}
	
	/**
	 * Generates the actual file from the datasource and the file name.
	 * 
	 * @param data
	 * @param fileName
	 */
	public void generateHTML(String[][] data, String fileName) {
		Queue<String> elements = new LinkedList<String>();
		
		elements = htmlGenerator.generateHTML(data);
		
		try {
			html = new PrintWriter(new FileOutputStream(dirPath + "\\" + fileName + ".html"));
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find HTML file. Exiting.");
			System.exit(0);
		}
		
		System.out.println("Starting write.");
		
		while (!elements.isEmpty()) {
			String element = elements.remove();
			html.println(element);
		}
		
		html.flush();
		
		System.out.println("Ending write.");
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
