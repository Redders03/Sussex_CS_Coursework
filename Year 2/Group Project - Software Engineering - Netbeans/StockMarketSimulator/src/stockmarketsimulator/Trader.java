package stockmarketsimulator;

import java.math.BigDecimal;
import javafx.collections.ObservableList;

/**
 *
 * Trader class buys and sells shares on behalf on clients and companies
 *
 * @author Alexander Reddington
 */
public class Trader {
    
    StockMarket stockMarket;
    
    /**
     *
     * @param stockMarket
     */
    public Trader(StockMarket stockMarket) {
        this.stockMarket = stockMarket;
    }

    /**
     *
     * @param buyClient The client that wishes to sell shares
     * @param sellClient The client that wishes to buy shares
     * @param nbofShares Number of shares they wish to sell/buy
     * @param companyName Company name of shares they are dealing with
     * @return Boolean if the trade was made
     */
    public boolean makeTrade(int buyClient, int sellClient, int nbofShares, Company companyName) {
        ObservableList<Client> clientStorage = stockMarket.getClientStorage();
        ObservableList<Company> companyStorage = stockMarket.getCompanyStorage();
        
        boolean tradeMade = clientStorage.get(sellClient).getPortfolio().sellShares(companyName, nbofShares, this, clientStorage.get(buyClient));
        
        int tempCash = clientStorage.get(sellClient).getMoneyAvaliable();
       
        for (int k = 0; k > companyStorage.size(); k++) {
            if (companyStorage.get(k).getCompanyName() == companyName.getCompanyName()) {
                tempCash = tempCash - (nbofShares * companyStorage.get(k).getSharePrice());
            }
        }
        clientStorage.get(sellClient).getPortfolio().addShare(companyName, nbofShares);
        clientStorage.get(sellClient).setMoneyAvaliable(tempCash);
        return tradeMade;
    }
}
