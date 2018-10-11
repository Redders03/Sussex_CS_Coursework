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
import stockmarketsimulator.Client;
import stockmarketsimulator.Portfolio;

/**
 *
 * @author Tobyrhodes
 */
public class ClientTest {
    
    public ClientTest() {
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
     * Testing that when creating a new Client class that the data is correct
     */
    @Test
    public void clientTest(){
        Portfolio p = new Portfolio();
        Client c = new Client("Donald Shrek",p,10000);
        
        assertEquals(10000,c.getMoneyAvaliable());
        assertEquals("Donald Shrek",c.getClientName());
        assertEquals(p,c.getPortfolio());
    }
    /**
     * Testing the setters in Client class
     */
    @Test
    public void clientSetTest(){
        Portfolio p = new Portfolio();
        Portfolio f = new Portfolio();
        Client t = new Client("Donald Shrek",p,10000);
        
        t.setClientName("Trinity Investments");
        t.setMoneyAvaliable(78439);
        t.setPortfolio(f);

        assertEquals("Trinity Investments",t.getClientName());
        assertEquals(78439,t.getMoneyAvaliable());
        assertEquals(f,t.getPortfolio());
    }
}