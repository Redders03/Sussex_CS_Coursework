package stockmarketsimulator;

import java.io.IOException;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * This class keeps all of the information needed about the company including, share price, number of shares, stock type and company name 
 * @author Toby Jack Rhodes
 */
public class Company {

    private StringProperty companyName;
    private StringProperty stockType;
    private IntegerProperty sharePrice; //Pence
    private IntegerProperty nbOfShares;
    
    /**
     *
     * @param companyName Name of company 
     * @param stockType Stock type of company   
     * @param sharePrice Current share price
     * @param nbOfShares Number of shares owned 
     */
    public Company() {
        
    }
    
    /**
     *
     * @param companyName
     * @param stockType
     * @param sharePrice
     * @param nbOfShares
     */
    public Company(String companyName, String stockType, int sharePrice, int nbOfShares) {
        this.companyName = new SimpleStringProperty(companyName);
        this.stockType = new SimpleStringProperty(stockType);
        this.sharePrice = new SimpleIntegerProperty(sharePrice);
        this.nbOfShares = new SimpleIntegerProperty(nbOfShares);
    }

    /**
     * Sets the company name
     * @param name is assigned to companyName
     */
    public void setCompanyName(String name) {
        companyName = new SimpleStringProperty(name);
    }

    /**
     * Sets the stock type of the company
     * @param type string is passed through, if it can passed through it will be
     * assigned to stockType
     * @exception IOException is thrown when the string passed through is not
     * recognised
     */    
    public void setStockType(String type) throws IOException {
        if ("Hitech".equals(type) || "Property".equals(type) || "Hard".equals(type) || "Food".equals(type)) {
            stockType = new SimpleStringProperty(type);
        } else {
            throw new IOException("Stock needs to be a recognised type");
        }
    }

    /**
     * Sets the share price
     * @param share sets sharePrice in pence 
     */   
    public void setSharePrice(int share) {
        sharePrice = new SimpleIntegerProperty(share);
    }

    /**
     * 
     * @param shares sets amount of shares company has 
     */
    public void setSharenb(int shares) {
        nbOfShares =  new SimpleIntegerProperty(shares);
    }

    /**
     * Gets the company name
     * @return companyName in a String
     */    
    public String getCompanyName() {
        return companyName.get();
    }

    /**
     * Gets the stock type
     * @return which stock the company deals in, in String format
     */    
    public String getStockType() {
        return stockType.get();
    }

    /**
     * Gets the share price
     * @return sharePrice (current company share price in pence)
     */    
    public int getSharePrice() {
        return sharePrice.get();
    }

    /**
     * Gets the number of shares
     * @return nbOdShares the amount of shares the company currently has
     */    
    public int getShareNb() {
        return nbOfShares.get();
    }

    /**
     * Gets the total net worth of the company
     * @return sharePrice times nbOfShares, this is the total network of the company
     */    
    public int getNetworth() {
        return (sharePrice.get() * nbOfShares.get());
    }
    
    /**
     * Get the company name property
     * @return company name property
     */
    public StringProperty companyNameProperty() {
        return companyName;
    }

    /**
     * Get the stock type property
     * @return stock type property
     */
    public StringProperty stockTypeProperty() {
        return stockType;
    }

    /**
     * Get the share price property
     * @return share price property
     */
    public IntegerProperty sharePriceProperty() {
        return sharePrice;
    }

    /**
     * Get the share number property
     * @return share number property
     */
    public IntegerProperty shareNbProperty() {
        return nbOfShares;
    }

    /**
     * Get the total net worth property
     * @return total net worth property
     */
    public IntegerProperty networthProperty() {
        return new SimpleIntegerProperty(((Integer)(sharePrice.get() * nbOfShares.get()/100)));
    }    

}