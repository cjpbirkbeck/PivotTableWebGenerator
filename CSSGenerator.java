import java.util.LinkedList;
import java.util.Queue;

public class CSSGenerator {
	
	/**
	 * Holds the data for a CSS selector.
	 * @author c_birkbe
	 *
	 */
	public class CSSSelector {
		private String selector;
		private LinkedList<CSSDeclaration> declarations;
		
		public CSSSelector(String selector) {
			this.selector = selector;
		}

		public String getSelector() {
			return selector;
		}

		public LinkedList<CSSDeclaration> getDeclarations() {
			return declarations;
		}
		
		public void addDeclaration(String dec, String val) {
			declarations.add(new CSSDeclaration(dec, val));
		}
		
		public void removeDeclaration(String dec) {
			CSSDeclaration del = new CSSDeclaration(dec, "");
			
			// Find item in list and break out of loop. Else it will return.
			for (CSSDeclaration item: declarations) {
				if (item.getProperty().equals(del.getProperty())) {
					del = item;
					break;
				}
				return;
			}
			
			declarations.remove(del);
		}
	}
	
	/**
	 * Hold data for each CSS declaration.
	 * @author c_birkbe
	 *
	 */
	public class CSSDeclaration {
		private String property;
		private String value;
		
		public CSSDeclaration(String property, String value) {
			this.property = property;
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public String getProperty() {
			return property;
		}
	}
	
	private LinkedList<CSSSelector> settings; // list for the CSS file to be generated. 

	public CSSGenerator() {
		settings = new LinkedList<CSSSelector>();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
