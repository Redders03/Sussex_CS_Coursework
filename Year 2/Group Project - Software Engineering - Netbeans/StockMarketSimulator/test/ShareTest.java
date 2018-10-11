/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import stockmarketsimulator.Company;
import stockmarketsimulator.Share;

/**
 *
 * @author Tobyrhodes
 */
public class ShareTest {
    
    public ShareTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    /**
     * Tests getters and setters for Share class
     */
    @Test
    public void testConstructor(){
        Company t = new Company("Patels Sauces","Food",100,55000);
        Share s = new Share(t,500,455);
        
        assertEquals(t,s.getCompany());
        assertEquals(500,s.getAmountOfShares());
        
        s.setAmountOfShares(100);
        assertEquals(100,s.getAmountOfShares());
        
        assertEquals(455,s.getPricePaid());
    } 
}
