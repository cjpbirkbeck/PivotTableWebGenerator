import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Creates a HTML file with a table from a given set of 2d array of Strings.
 * 
 * @author c_birkbe
 *
 */
public class HTMLGenerator {
	
	/* Variables */
	
	// Title shown in the window.
	private String pageTitle;
	// Title shown on the page.
	private String textTitle;
	// Language of the page.
	private String language = "EN";
	// Character encoding of the page.
	private String charset = "UTF-8";

	/**
	 * Defaults to having same page title.
	 */
	public HTMLGenerator() {
		pageTitle = "New Table";
		textTitle = "New Table";
	}
	
	/**
	 * Sets the title of the page.
	 * 
	 * @param title
	 */
	public HTMLGenerator(String title) {
		pageTitle = title;
		textTitle = title;
	}
	
	/**
	 * @return the pageTitle
	 */
	public String getPageTitle() {
		return pageTitle;
	}

	/**
	 * @param pageTitle the pageTitle to set
	 */
	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	/**
	 * @param textTitle the textTitle to set
	 */
	public void setTextTitle(String textTitle) {
		this.textTitle = textTitle;
	}

	/**
	 * @return the textTitle
	 */
	public String getTextTitle() {
		return textTitle;
	}

	/**
	 * Take a String a data and generates an entire string of web page.
	 * @param data
	 * @return
	 */
	public Queue<String> generateHTML(String[][] data) {
		Queue<String> elements = new LinkedList<String>();
		generateStart(elements);
		generateTable(elements, data);
		generateEnd(elements);
		return elements;
	}
	
	/**
	 * Make the first elements of the HTML page.
	 * 
	 * @param elements
	 */
	private void generateStart(Queue<String> elements) {
		elements.add("<!DOCTYPE html>");
		elements.add("<html lang=" + language + ">");
		elements.add("\t<head>");
		elements.add("\t\t<title>" + pageTitle + "</title>");
		elements.add("\t\t<meta charset=" + charset + ">");
		elements.add("\t</head>");
		elements.add("\t<body>");
		elements.add("\t\t<h1>" + textTitle + "</h1>");
	}
	
	/**
	 * Take the base data and put into table with the proper HTML tags.
	 * 
	 * @param html
	 * @param source
	 */
	private void generateTable(Queue<String> html, String[][] source) {
		html.add("\t\t\t<table>");
		for (int i = 0; i < source.length; i++) {
			html.add("\t\t\t\t<tr>");
			for (int j = 0; j < source[i].length; j++) {
				// Makes the first row the header with the HTML tags <th>... </th>
				// Otherwise, uses the regular <tr>...</tr>
				if (i == 0) {
					html.add("\t\t\t\t\t<th>" + source[i][j] + "</th>");
				} else {
					html.add("\t\t\t\t\t<td>" + source[i][j] + "</td>");
				}
			}
			html.add("\t\t\t\t</tr>");
		}
		html.add("\t\t\t</table>");
	}
	
	/**
	 * Generates the final elements of the HTML page.
	 * 
	 * @param elements
	 */
	private void generateEnd(Queue<String> elements) {
		elements.add("\t\t<p>Table generated on " + LocalDate.now() + "</p>");
		elements.add("\t</body>");
		elements.add("</html>");
	}
	
}
