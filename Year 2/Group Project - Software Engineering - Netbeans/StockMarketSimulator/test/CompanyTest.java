/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import stockmarketsimulator.Company;

/**
 *
 * @author Tobyrhodes
 */
public class CompanyTest {

    /**
     *
     */
    public CompanyTest() {
    }

    /**
     *
     */
    @BeforeClass
    public static void setUpClass() {
    }

    /**
     *
     */
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     *
     */
    @Before
    public void setUp() {
    }

    /**
     *
     */
    @After
    public void tearDown() {
    }

    /**
     * Tests the constructor taking the right inputs in Company
     *
     * @throws IOException when stock type input is wrong
     */
    @Test
    public void companyTest() throws IOException {
        Company s = new Company("Patels Sauces", "Food", 100, 55000);

        assertEquals("Patels Sauces", s.getCompanyName());
        assertEquals("Food", s.getStockType());
        assertEquals(100, s.getSharePrice());
        assertEquals(55000, s.getShareNb());
        assertEquals(5500000, s.getNetworth());
    }
    /**Tests the setters in Company class
     * 
     * @throws IOException When stock type input is wrong
     */
    @Test
    public void companySetTest() throws IOException {
        Company t = new Company("Patels Sauces", "Food", 100, 55000);
        t.setCompanyName("WazooIt");
        t.setStockType("Hitech");
        t.setSharePrice(45);
        t.setSharenb(9800);

        assertEquals("WazooIt", t.getCompanyName());
        assertEquals("Hitech", t.getStockType());
        assertEquals(45, t.getSharePrice());
        assertEquals(9800, t.getShareNb());
        assertEquals(441000, t.getNetworth());
    }

}
