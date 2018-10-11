package stockmarketsimulator;

import java.math.BigDecimal;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * This class stores the client names and a reference to their portfolio
 * @author Alexander Reddington, Sam Wimshurst
 */
public class Client {

    private StringProperty clientName;
    private Portfolio portfolio;
    
    private int moneyAvaliable; 
    private float overallReturn;

    /**
     *
     * @param clientName
     */
    public Client (String clientName) {
        this.clientName = new SimpleStringProperty(clientName);
    }

    /**
     *
     * @param clientName Client name
     * @param portfolio Client Portfolio
     * @param moneyAvaliable Current money avaliable
     */
    public Client(String clientName, Portfolio portfolio, int moneyAvaliable) {
        this.clientName = new SimpleStringProperty(clientName);
        this.portfolio = portfolio;
        this.moneyAvaliable = moneyAvaliable;
        this.overallReturn = 0;
    }
    
    /**
     * Gets clients money avaliable
     * @return money avaliable
     */
    public int getMoneyAvaliable() {
        return moneyAvaliable;
    }

    /**
     * Sets the clients money avaliable
     * @param moneyAvaliable Amount of money avaliable
     */
    public void setMoneyAvaliable(int moneyAvaliable) {
        this.moneyAvaliable = moneyAvaliable;
    }

    /**
     * Get the total net worth of the client
     * @return total net worth of client
     */
    public int getTotalClientWorth() {
        return portfolio.calculateTotalWorth();
    }

    /**
     * Get the overall return of the client's portfolio
     * @return Overall return percentage
     */
    public float getOverallReturn() {
        return overallReturn;
    }

    /**
     * Set the overall return of the client's portfolio
     * @param overallReturn Overall return percentage
     */
    public void setOverallReturn(float overallReturn) {
        this.overallReturn = overallReturn;
    }

    /**
    * Gets the client portfolio
    * @return client portfolio
    */
    public Portfolio getPortfolio() {
        return portfolio;
    }

    /**
     * Sets the clients portfolio 
     * @param portfolio the one and only client portfolio 
     */    
    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    /**
    * Sets the client name
    * @param name Sets clients name 
    */
    public void setClientName(String name) {
        clientName = new SimpleStringProperty(name);
    }

    /**
    * Gets the client name
    * @return clientName gets the client name
    */
    public String getClientName() {
        return clientName.get();
    }
    
    /**
     * Gets the string property of the client name
     * @return String Property
     */
    public StringProperty clientNameProperty() {
        return clientName;
    }

}