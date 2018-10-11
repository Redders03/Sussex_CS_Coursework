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
import stockmarketsimulator.Company;
import stockmarketsimulator.Portfolio;
import stockmarketsimulator.Share;
import stockmarketsimulator.SimulatorController;
import stockmarketsimulator.StockMarket;
import stockmarketsimulator.Trader;

/**
 *
 * @author Tobyrhodes
 */
public class PortfolioTest {
    
    public PortfolioTest() {
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

    @Test
    public void portfolioTest(){
        Portfolio p = new Portfolio();
        Company s = new Company("Patels Sauces", "Food", 100, 55000);
        Company k = new Company("The Lovely Pie Co", "Food", 24, 6000);
        Client c = new Client("Ivor Lott",p,10000);
        
        Share share = new Share(s,100);
        Share share2 = new Share(k,3000);
        p.addShare(share);
        p.addShare(share2);
        
        assertEquals(10000,p.companyPriceShares(s));
        assertEquals(82000,p.calculateTotalWorth());
    }
}
