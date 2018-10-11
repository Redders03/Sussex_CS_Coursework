package stockmarketsimulator;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;

/**
 * This class keeps the amount of shares that a client has and what company it has shares in.
 * @author Toby Jack Rhodes
 */
public class Share {

    private Company comany;
    private IntegerProperty amountOfShares;
    private IntegerProperty pricePaid; //Pence

    /**
     *
     * @param company Company that shares are from
     * @param amountOfShares Number of shares brought
     */
    public Share(Company company, int amountOfShares) {
        this.comany = company;
        this.amountOfShares = new SimpleIntegerProperty(amountOfShares);
        this.pricePaid = new SimpleIntegerProperty(company.getSharePrice());
    }
    
    /**
     *
     * @param comany Company that shares are from
     * @param amountOfShares Number of shares brought
     * @param pricePaid Price paid for current shares 
     */
    public Share(Company comany, int amountOfShares, int pricePaid) {
        this.comany = comany;
        this.amountOfShares = new SimpleIntegerProperty(amountOfShares);
        this.pricePaid = new SimpleIntegerProperty(pricePaid);
    }
    
    /**
     * Get the company of the shares
     * @return company of the shares
     */
    public Company getCompany() {
        return comany;
    }
    
    /**
     * Get the company name property
     * @return company name property
     */
    public StringProperty companyNameProperty() {
        return comany.companyNameProperty();
    }

    /**
     * Sets the amount of shares
     * @param shareAmount Amount of shares
     */
    public void setAmountOfShares(int shareAmount) {
        amountOfShares = new SimpleIntegerProperty(shareAmount);
    }

    /**
     * @return amountOfShares the number of shares the client has in this one company
     */    
    public int getAmountOfShares() {
        return amountOfShares.get();
    }
    
    /**
     * Get the amount of shares property
     * @return amount of shares property
     */
    public IntegerProperty shareQuantityProperty() {
        return amountOfShares;
    }

    /**
     * Get the price paid for the shares (in pence)
     * @return Get price paid for shares
     */
    public int getPricePaid() {
        return pricePaid.get();
    }

    /**
     * Set price paid for the shares
     * @param pricePaid price paid (in pence)
     */
    public void setPricePaid(int pricePaid) {
        this.pricePaid = new SimpleIntegerProperty(pricePaid);
    }

    /**
     * Get the price paid property
     * @return price paid property
     */
    public IntegerProperty pricePaidProperty() {
        return pricePaid;
    }
    
    /**
     * Get the market price property
     * @return market price property
     */
    public IntegerProperty marketPriceProperty() {
        return comany.sharePriceProperty();
    }
    
    /**
     * Get the market value property
     * @return market value property
     */
    public IntegerProperty marketValueProperty() {
        return new SimpleIntegerProperty(comany.getSharePrice() * amountOfShares.get());
    }    
}