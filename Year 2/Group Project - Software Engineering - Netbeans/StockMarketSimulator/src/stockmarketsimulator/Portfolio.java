package stockmarketsimulator;

import java.math.BigDecimal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class is one to one with a client, it stores all of the companies in 
 * which the client has shares in. It also gives the total amount the portfolio
 * is worth.
 * @author Toby Jack Rhodes, Sam Wimshurst
 */
public class Portfolio {

    private Trader trader;
    private Client client;
    private ObservableList<Share> tradedCompanies;

    /**
     *
     */
    public Portfolio() {
        this.tradedCompanies = FXCollections.observableArrayList();
    }
    
    /**
     * Assigns a trader to a portfolio
     * @param trader Sets which type of trader the is involved in the portfolio
     */
    public void setTrader(Trader trader) {
        this.trader = trader;
    }

    /**
     * Gets trader dealing with portfolio
     * @return Trader of portfolio
     */
    public Trader getTrader() {
        return trader;
    }

    /**
     * Get a list of all shares in portfolio
     * @return List of shares
     */
    public ObservableList<Share> getShares() {
            return tradedCompanies;
  
    }

    /**
     * Sets the shares in portfolio
     * @param tradedCompanies List of shares
     */
    public void setShares(ObservableList<Share> tradedCompanies) {
        this.tradedCompanies = tradedCompanies;
    }
    
    /**
     * Add a new share to the portfolio
     * @param share New share
     */
    public void addShare(Share share) {
        this.tradedCompanies.add(share);
    }
    
    /**
     * Add a new share to the portfolio with the number of shares
     * the client owns.
     * @param company Company in which the client has shares in
     * @param nbOfShare number of shares the client has in the company
     */
    public void addShare(Company company, int nbOfShare) {
        Share share = new Share(company, nbOfShare);
        this.tradedCompanies.add(share);
    }
    
    /**
     * All of the companies that the client has shares in the total worth of 
     * theses have been added up (current share price of the company times the 
     * number of shares the client has)
     * @return total 
     */
    public int calculateTotalWorth() {
        int total = 0;
        for (int i = 0; tradedCompanies.size() > i; i++) {
            total += tradedCompanies.get(i).getAmountOfShares() * tradedCompanies.get(i).getCompany().getSharePrice();
        }
        return total; 
    }
    
    /**
     * 
     * @param company the company a trader wishes to buy in.
     * @return Amount it would cost to buy all of the clients shares in this company 
     */
    public int companyPriceShares(Company company){
        for(int i = 0; i<tradedCompanies.size();i++){
            if(tradedCompanies.get(i).getCompany().getCompanyName() == null ? company.getCompanyName() == null : tradedCompanies.get(i).getCompany().getCompanyName().equals(company.getCompanyName())){
               return (tradedCompanies.get(i).getCompany().getSharePrice()*tradedCompanies.get(i).getAmountOfShares());
            }
            
        }
        return 0;
    }
    
    /**
     * 
     * @param company The company it wishes to sell shares 
     * @param sellingShares The amount of shares the trader wants
     * @param trader What trader is making the transaction 
     * @param client The client that wants to buy the shares
     * @return boolean whether the transaction has be completed
     */
    public boolean sellShares(Company company, int sellingShares, Trader trader, Client client){
        int clientCash = client.getMoneyAvaliable();
        for(int i = 0;i>tradedCompanies.size();i++){
            if (tradedCompanies.get(i).getCompany().getCompanyName() == company.getCompanyName()){
                if(sellingShares > tradedCompanies.get(i).getAmountOfShares()){
                    System.out.println("Too many shares, client doesnt have that many ");
                    return false;
                }
                else{
                    int price = tradedCompanies.get(i).getCompany().getSharePrice()* sellingShares;
                    if (client.getMoneyAvaliable() < price){
                        System.out.println("Client dose not have enough money to buy shares");
                        return false; 
                    }
                    else{
                        client.setMoneyAvaliable(clientCash - price);
                        tradedCompanies.get(i).setAmountOfShares(tradedCompanies.get(i).getAmountOfShares()-sellingShares);
                        return true;
                    }
                }
                
            }
        }
        return false;
    }
}