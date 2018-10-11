package stockmarketsimulator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert.AlertType;
import jxl.*;
import jxl.read.biff.BiffException;

/**
 *
 * @author Alexander Reddington, Toby Rhodes, Sam Wimshurst
 * @version April 2017
 */
public class StockMarket {
    
    SimulatorController controller;

    // Storage for key classes
    private ObservableList<Client> clientStorage;
    private ObservableList<Company> companyStorage;
    private ArrayList<Trader> traderStorage;
    private ArrayList<ExternalEvent> externalEvents;
    boolean empty;

    //Class Dependant Variable
    LocalDate startDate;
    LocalDate endDate;
    LocalTime startTime;
    LocalTime endTime;

    /**
     *
     * @param controller
     */
    public StockMarket(SimulatorController controller) {
        
        this.controller = controller;
        
        this.clientStorage = FXCollections.observableArrayList();
        this.companyStorage = FXCollections.observableArrayList();
        this.traderStorage = new ArrayList<>();
        this.externalEvents = new ArrayList<>();
        
        this.startDate = LocalDate.parse("2017-01-01");
        this.endDate = LocalDate.parse("2017-12-31");
        this.startTime = LocalTime.parse("09:00");
        this.endTime = LocalTime.parse("16:00");
        
        try {
            importData();
            controller.loadCompanies(companyStorage);
            controller.loadClients(clientStorage);
        } catch(Exception e) {
            controller.showAlert(AlertType.ERROR, "Error importing data!",
                    "There was an error importing data from file", e.getMessage());
        }
    }

    /**
     * Get the client list
     * @return client list
     */
    public ObservableList<Client> getClientStorage() {
        return clientStorage;
    }
    
    /**
     * Get the company list
     * @return company list
     */
    public ObservableList<Company> getCompanyStorage() {
        return companyStorage;
    }
    
    /**
     * Starts the simulation
     * @param speed Speed of the simulation (1.0 is normal)
     */
    public void start(float speed) {
        // TODO
    }
    
    /**
     * Pauses the simulation
     */
    public void pause() {
        // Pause the simulation
    }
    
    /**
     * Reset the simulation
     */
    public void reset() {
        // Reset the simulation
    }

    /**
     * Imports all of the data from (ExternalEventsData.xls and InitialData.xls) 
     * sorts out what information is needed and inputs them into the simulator
     * 
     * @throws FileNotFoundException When the file in the location DataInputs is not found
     * @throws IOException When the data in the expected file is not found 
     * @throws BiffException when a new workbook cannot be created
     */    
    public void importData() throws FileNotFoundException, IOException, BiffException {
        //Import data from ExternalEventsData
        String filePath = "src/stockmarketsimulator/DataInputs/ExternalEventsData.xls";
        Workbook wrk1 = Workbook.getWorkbook(new File(filePath));
        Sheet sheet1 = wrk1.getSheet(0);
        int k = 6;

        while (!empty) {

            Cell cellContent = sheet1.getCell(0, k);
            if ("".equals(cellContent.getContents())) {
                empty = true;
            } else {
                ExternalEvent i = new ExternalEvent();
                //i.setStartDate((cellContent.getContents()));

                String cellContentTime = sheet1.getCell(2, k).getContents();
                //i.setStartTime(cellContentTimegetContents());

                String cellContentNafEvent = sheet1.getCell(4, k).getContents();
                i.setNature(cellContentNafEvent);

                String cellContentAction = sheet1.getCell(15, k).getContents();
                i.setActionInput(cellContentAction);

                externalEvents.add(i);
                k++;
            }
        }

        //Import data from InitalData
        String filePathInitalData = "src/stockmarketsimulator/DataInputs/InitialData.xls";

        int t = 11;
        Workbook wrk2 = Workbook.getWorkbook(new File(filePathInitalData));
        Sheet sheet2 = wrk2.getSheet(0);
        empty = false;
        while (!empty) {

            Cell cellContent = sheet2.getCell(0, t);

            if ("".equals(cellContent.getContents())) {

                empty = true;
            } else {
                Company companyInstance = new Company();
                companyInstance.setCompanyName(cellContent.getContents());

                String cellContentStockType = sheet2.getCell(2, t).getContents();
                companyInstance.setStockType(cellContentStockType);

                String cellContentPrice = sheet2.getCell(3, t).getContents();
                companyInstance.setSharePrice(Integer.parseInt(cellContentPrice));

                String cellContentShare = sheet2.getCell(18, t).getContents();
                companyInstance.setSharenb(Integer.parseInt(cellContentShare));

                companyStorage.add(companyInstance);
                t++;
            }
        }

        int o = 7;
        int j = 7;

        empty = false;
        while (!empty) {
            Cell cellContent = sheet2.getCell(o, 8);
            if ("".equals(cellContent.getContents())) {
                empty = true;
            } else {
                Client clientInstance = new Client(cellContent.getContents());

                Portfolio port = new Portfolio();
                boolean empty2 = false;
                int p = 11;
                while (empty2 == false) {
                    Cell cellContentCompanyName = sheet2.getCell(0, p);
                    if ("".equals(cellContentCompanyName.getContents())) {
                        

                        empty2 = true;
                    } else {
                        Cell cellContentStockName = sheet2.getCell(o, p);
                        //System.out.println(sheet2.getColumns());

                    
                        port.addShare(companyStorage.get(p - 11), Integer.parseInt(cellContentStockName.getContents()));
                        p++;
                    }
                } 
                if(o <12){
                    Cell clientWorth = sheet2.getCell(o, 32);
                    Integer clientCash = Integer.parseInt(clientWorth.getContents());
                    clientInstance.setMoneyAvaliable(clientCash);
                }
                                        
                clientInstance.setPortfolio(port);
                clientStorage.add(clientInstance);
                o++;

            }
        }
    }
}