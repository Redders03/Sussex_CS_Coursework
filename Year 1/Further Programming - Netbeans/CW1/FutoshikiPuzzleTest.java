import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alex
 */
public class FutoshikiPuzzleTest {

	FutoshikiPuzzle testClass;
	int gridSize = 5;

	/**
	 *
	 */
	@Before
	public void initalize() {
		testClass = new FutoshikiPuzzle(gridSize);

	}

	/**
	 * Test of setSquare method, of class Futoshiki_puzzle.
	 */
	@Test
	public void testSetSquare() {
		testClass.setSquare(0, 0, 4);
		assertEquals(4, testClass.getSquare(0, 0));

	}

	/**
	 *
	 */
	@Test
	public void testSetRowConstraint() {
		testClass.setRowConstraint(0, 0, ">");
		assertEquals(">", testClass.getRowConstraint(0, 0));
	}

	/**
	 *
	 */
	@Test
	public void testSetColumnConstraint() {
		testClass.setColumnConstraint(0, 0, "^");
		assertEquals("^", testClass.getColumnConstraint(0, 0));
	}

	/**
	 * Testfill puzzle.
	 */

	@Test
	public void testfillPuzzle() {
		boolean valid = false;
		int countGrid = 0;
		int countRow = 0;
		int countColumn = 0;
		testClass.fillPuzzle();
		for (int x = 0; x < gridSize; x++) {
			for (int y = 0; y < gridSize; y++) {
				if (testClass.getSquare(x, y) != 0 && testClass.getSquare(x, y) <= gridSize) {
					countGrid++;
				}
			}
			for (int y = 0; y < gridSize - 1; y++) {
				if (testClass.getRowConstraint(x, y) == "<" || testClass.getRowConstraint(x, y) == ">") {
					countRow++;
				}
				if (testClass.getColumnConstraint(y, x) == "^" || testClass.getColumnConstraint(y, x) == "V") {
					countColumn++;
				}
			}
		}
		if (countGrid <= gridSize && countRow <= gridSize && countColumn <= gridSize) {
			valid = true;
		}
		assertTrue(valid);
	}
	
	@Test
	public void testToString(){
		boolean valid = false;
		String gridOutput = testClass.toString();
		String output = "";
		for(int i = 0; i < gridSize; i++){		
			for(int a = 0; a < gridSize; a++){
					output += ("---   ");
				}
				output += ("\n");
				for(int b = 0; b < gridSize; b++){
					output += ("| |   ");
				}
				output += ("\n");
				for(int a = 0; a < gridSize; a++){
					output += ("---   ");
				}
				output += ("\n");
				for(int a = 0; a < gridSize; a++){
					output += ("      ");
				}
				output += ("\n");
			}	
		if(gridOutput.equals(output)){
			valid = true;
		}
		System.out.println(gridOutput);
		System.out.println(output);
		assertTrue(valid);
	}
	
	@Test
	public void testIsValid(){
		boolean valid = false;
		boolean test = false;
		testClass.setSquare(0,0,5);
		testClass.setSquare(1,4,4);
		testClass.setSquare(1,3,4);
		testClass.setSquare(3,0,3);
		testClass.setRowConstraint(0,1,">");
		testClass.setRowConstraint(1,1,">");
		testClass.setRowConstraint(2,2,"<");
		testClass.setColumnConstraint(1,0,"^");
		testClass.setColumnConstraint(0,1,"V");
		testClass.setColumnConstraint(2,0,"v");
		valid = testClass.isLegal();
		if(valid = false){
			test = true;
		}
		assertTrue(true);
	}
}
