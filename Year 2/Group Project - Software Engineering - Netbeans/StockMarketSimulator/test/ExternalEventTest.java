/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import stockmarketsimulator.ExternalEvent;

/**
 *
 * @author Tobyrhodes
 */
public class ExternalEventTest {
    
    public ExternalEventTest() {
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
    public void testGetSet(){
        ExternalEvent e = new ExternalEvent();
        LocalDate date = LocalDate.parse("2017-01-01");
        e.setStartDate("2017-01-01");
       assertEquals(date,e.getStartDate());
       
       LocalTime time = LocalTime.parse("09:00:00");
        e.setStartTime("09:00:00");
        assertEquals(time,e.getStartTime());        
        
        e.setNature("Q1Q tech announce exciting developments..");
        assertEquals("Q1Q tech announce exciting developments..",e.getNature());
        
        e.setActionInput("Sell Shares");
        assertEquals("Sell Shares",e.getAction());
    }
}
