import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class StudentTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class StudentTest
{
    /**
     * Default constructor for test class StudentTest
     */
    public StudentTest()
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
    public void testFinishedStudiesFALSE()
    {
        Library library1 = new Library(20);
        Student student1 = new Student("student1", library1);
        assertEquals(false, student1.finishedStudies());
    }

    @Test
    public void testFinishedStudiesTRUE()
    {
        Library library1 = new Library(20);
        Student student1 = new Student("test", library1);
        student1.study();
        student1.study();
        student1.study();
        student1.study();
        student1.study();
        student1.study();
        student1.study();
        student1.study();
        student1.study();
        student1.study();
        student1.study();
        student1.study();
        student1.study();
        student1.study();
        student1.study();
        student1.study();
        student1.study();
        student1.study();
        student1.study();
        student1.study();
        student1.study();
        student1.study();
        student1.study();
        student1.study();
        student1.study();
        student1.study();
        student1.study();
        student1.study();
        student1.study();
        student1.study();
        student1.study();
        student1.study();
        student1.study();
        student1.study();
        student1.study();
        assertEquals(true, student1.finishedStudies());
    }

    @Test
    public void testStudyNULL()
    {
        Library library1 = new Library(20);
        Student student1 = new Student("test", library1);
        assertNull(student1.setTextbook);
    }

    @Test
    public void testStudyNOTNULL()
    {
        Library library1 = new Library(20);
        Student student1 = new Student("test", library1);
        student1.study();
        assertNotNull(student1.setTextbook);
    }

}

