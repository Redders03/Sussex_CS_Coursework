import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class LibraryTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class LibraryTest
{
    /**
     * Default constructor for test class LibraryTest
     */
    public LibraryTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void testIssueCard()
    {
        Library library1 = new Library(20);
        LibraryCard libraryC1 = library1.issueCard();
        assertNotNull(libraryC1);
    }

    @Test
    public void testBorrowBook()
    {
        Library library1 = new Library(20);
        LibraryCard libraryC1 = library1.issueCard();
        assertNotNull(libraryC1);
        TextBook textBook1 = library1.borrowBook(libraryC1);
        assertNotNull(textBook1);
    }
}

