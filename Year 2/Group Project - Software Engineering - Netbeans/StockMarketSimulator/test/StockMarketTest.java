/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.read.biff.BiffException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import stockmarketsimulator.SimulatorController;
import stockmarketsimulator.StockMarket;

/**
 *
 * @author Tobyrhodes
 */
public class StockMarketTest {

    /**
     *
     */
    public StockMarketTest() {
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    /**
     *
     */
    @Test
    public void testInputDataCompany() {
        SimulatorController c = new SimulatorController();
        StockMarket s = new StockMarket(c);
        try {
            s.importData();
        } catch (IOException ex) {
            Logger.getLogger(StockMarketTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BiffException ex) {
            Logger.getLogger(StockMarketTest.class.getName()).log(Level.SEVERE, null, ex);
        }
//        assertEquals("Pear Computing",s.getCompanyStorage().get(0).getCompanyName());
//        assertEquals(570,s.getCompanyStorage().get(9).getSharePrice());
//        assertEquals("Food",s.getCompanyStorage().get(15).getStockType());
//        assertEquals(6357000,s.getCompanyStorage().get(4).getNetworth());
//        assertEquals(9800,s.getCompanyStorage().get(18).getShareNb());
    }
    
    /**
     *
     */
//    @Test
//    
//    public void testInputDataClient(){
//        SimulatorController c = new SimulatorController();
//        StockMarket t = new StockMarket(c);
//        try {
//            t.importData();
//        } catch (IOException ex) {
//            Logger.getLogger(StockMarketTest.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (BiffException ex) {
//            Logger.getLogger(StockMarketTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        //assertEquals(t.clientStorage.get(0).getClientName(),t.clientStorage.get(0).clientPortfolio.getClientName());
//        //assertEquals(20,t.clientStorage.get(1).clientPortfolio.tradedCompanies.size());
//    }
}
