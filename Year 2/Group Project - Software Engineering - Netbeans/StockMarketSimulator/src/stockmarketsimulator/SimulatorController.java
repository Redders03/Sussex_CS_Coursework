package stockmarketsimulator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 * Controller for Stock Market Simulator
 * 
 * @author Sam Wimshurst
 */
public class SimulatorController implements Initializable {

    // Top bar
    @FXML
    private Label statusLabel; 
    @FXML
    private Label indexLabel;

    // Bottom bar
    @FXML
    private Button playButton;
    @FXML
    private Button resetButton;
    @FXML
    private TextField speedInput;
    @FXML
    private Label dateLabel;
    @FXML
    private Label timeLabel;

    // Clients tab
    @FXML
    private TableView<Client> clientTable;
    @FXML
    private TableColumn<Client, String> clientTableName;

    @FXML
    private Label portfolioHelpLabel;
    @FXML
    private Label portfolioMoneyLabel;
    @FXML
    private Label portfolioNetWorthLabel;    
    @FXML
    private Label portfolioReturnLabel;
    
    @FXML
    private TableView<Share> portfolioTable;
    @FXML
    private TableColumn<Share, String> portfolioTableName;    
    @FXML
    private TableColumn<Share, Number> portfolioTableQty;
    @FXML
    private TableColumn<Share, Number> portfolioTablePaid;
    @FXML
    private TableColumn<Share, Number> portfolioTablePrice; 
    @FXML
    private TableColumn<Share, Number> portfolioTableValue;     
    
    // Companies table
    @FXML
    private TableView<Company> companyTable;
    @FXML
    private TableColumn<Company, String> companyTableName;
    @FXML
    private TableColumn<Company, String> companyTableType;
    @FXML
    private TableColumn<Company, Number> companyTableNumShares;
    @FXML
    private TableColumn<Company, Number> companyTableShareValue;
    @FXML
    private TableColumn<Company, Number> companyTableNetWorth;
    
    //Non-FXML variables
    boolean running;
    StockMarket model;
    
    //Tables
    ObservableList<Company> companyData = FXCollections.observableArrayList();
    
    /**
     *
     */
    public SimulatorController() {
        this.running = false;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
        //Client Table
        clientTableName.setCellValueFactory(cellData -> cellData.getValue().clientNameProperty());
        
        //Company Table
        companyTableName.setCellValueFactory(cellData -> cellData.getValue().companyNameProperty());
        companyTableType.setCellValueFactory(cellData -> cellData.getValue().stockTypeProperty());
        companyTableNumShares.setCellValueFactory(cellData -> cellData.getValue().shareNbProperty());
        companyTableShareValue.setCellValueFactory(cellData -> cellData.getValue().sharePriceProperty());
        companyTableNetWorth.setCellValueFactory(cellData -> cellData.getValue().networthProperty());
        
        //Portfolio Table
        portfolioTableName.setCellValueFactory(cellData -> cellData.getValue().companyNameProperty());
        portfolioTableQty.setCellValueFactory(cellData -> cellData.getValue().shareQuantityProperty());
        portfolioTablePaid.setCellValueFactory(cellData -> cellData.getValue().pricePaidProperty());
        portfolioTablePrice.setCellValueFactory(cellData -> cellData.getValue().marketPriceProperty());
        portfolioTableValue.setCellValueFactory(cellData -> cellData.getValue().marketValueProperty());
        
        clientTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> loadPortfolio(newValue));
    }
    
    /**
     * Assigns the model the the controller
     * 
     * @param model Simulation stock market model
     */
    public void initModel(StockMarket model) {
        this.model = model;
    }

    /**
     * Toggles between playing and pausing the model simulation.
     * 
     * @param event Click event
     */
    @FXML
    private void handlePlayButton(ActionEvent event) {
        if(!running) {
            try {
                running = true;
                model.start(getSpeed());
                System.out.println("Starting Simulation with speed "+ getSpeed() +"..");
                playButton.setText("Pause Simulation");
            } catch(Exception e) {
                running = false;                
                showAlert(AlertType.ERROR, "An error has occurred", 
                    "Oh no! Something has gone horribly wrong", e.getMessage());
            }
        } else {
             try {
                running = false;
                model.pause();
                System.out.println("Pausing Simulation..");
                playButton.setText("Play Simulation");
            } catch(Exception e) {
                running = true;
                showAlert(AlertType.ERROR, "An error has occurred", 
                    "Oh no! Something has gone horribly wrong", e.getMessage());
            }           
        }
    }
    
    /**
     * Resets the model simulation
     * 
     * @param event Click event
     */
    @FXML
    private void handleResetButton(ActionEvent event) {
        try {
            model.reset();
            System.out.println("Resetting Simulation..");
            playButton.setText("Play Simulation");
        } catch(Exception e) {         
            showAlert(AlertType.ERROR, "An error has occurred", 
                    "Oh no! Something has gone horribly wrong", e.getMessage()); 

        }
    }
    
    /**
     * Gets the speed of the simulation and checks that it's valid
     * 
     * @return Speed inputted by the user
     */
    public float getSpeed() {
        float speed = 1.0f;
        
        try {
            speed = Float.parseFloat(speedInput.getText());
            
            if(speed <= 0) {
                throw new Exception("Speed must be greater than zero");
            } 
        } catch(Exception e) {
            speedInput.setText("1.0");
            
            showAlert(AlertType.WARNING, "Invalid Value",
                    "Oops! You've entered an invalid number", e.getMessage());      
        }
        
        return speed;
    }

    /**
     * Updates the market index label with and formats it correctly
     * 
     * @param marketIndex Overall index of the market
     */
    public void updateMarketIndex(double marketIndex) {
        String formattedIndex = String.format("%d", Math.round(marketIndex)); // Comma Separate
        indexLabel.setText(formattedIndex);
    }

    /**
     * Updates the markets status depending on the change in the stock market's
     * total net worth
     * 
     * @param changeInNetWorth Percentage change in total net worth of stock market
     */
    public void updateMarketStatus(float changeInNetWorth) {
        if(changeInNetWorth > 0) {
            statusLabel.setText("Bull");
            statusLabel.setTextFill(Color.web("#1DB918"));
        } else if(changeInNetWorth < 0) {
            statusLabel.setText("Bear");
            statusLabel.setTextFill(Color.web("#D31C18"));
        } else {
            statusLabel.setText("Neutral");
            statusLabel.setTextFill(Color.web("#AEAEAE"));            
        }
    }

    /**
     * Updates the view with a given date. Formats date to user-friendly form
     * 
     * @param date Current date of model
     */
    public void updateSimulationDate(LocalDate date) {
        String monthName = date.getMonth().toString();
        String formattedMonth = monthName.substring(0,1) + monthName.substring(1).toLowerCase();
        
        dateLabel.setText(date.getDayOfMonth() +" "+formattedMonth+" "+date.getYear());
    }

    /**
     * Updates the view with a given time
     * 
     * @param time Current time of model
     */
    public void updateSimulationTime(LocalTime time) {
        String timeString = time.format(DateTimeFormatter.ofPattern("h:mm a"));
        String formattedTime = timeString.replace("AM", "am").replace("PM", "pm");
        
        timeLabel.setText(formattedTime);
    }
    
    /**
     * Create and show a GUI alert to the user
     * 
     * @param type Type of alert (e.g. AlertType.WARNING, AlertType.ERROR)
     * @param title Title of the alert dialog
     * @param header Header of the alert dialog
     * @param content Text content of the alert dialog
     */
    public void showAlert(AlertType type, String title, String header, String content) {
            Alert alert = new Alert(type);
            alert.setTitle(title);
            alert.setHeaderText(header);
            alert.setContentText(content);
            alert.showAndWait();         
    }
    
    /**
     * Displays companies in GUI table
     * 
     * @param companies An array of all companies in the simulation
     */
    public void loadCompanies(ObservableList<Company> companies) {
        companyTable.setItems(companies);
    }

    /**
     * Displays clients in GUI table
     * 
     * @param clients An array of all companies in the simulation
     */
    public void loadClients(ObservableList<Client> clients) {
        clientTable.setItems(clients);
    }    
    
    /**
     * Displays a portfolio for a particular client
     * 
     * @param client A client in the simulation
     */
    public void loadPortfolio(Client client) {
                
        // Hide help text from view
        portfolioHelpLabel.setVisible(false);
        
        // Update header labels
        BigDecimal formattedMoney = BigDecimal.valueOf(client.getMoneyAvaliable()).movePointLeft(2);
        formattedMoney.setScale(2, RoundingMode.HALF_EVEN);
        portfolioMoneyLabel.setText("£"+formattedMoney);
        
        int formattedNetWorth = client.getTotalClientWorth();
        portfolioNetWorthLabel.setText("£"+formattedNetWorth);        
        
        float overallReturn = client.getOverallReturn();
        
        if(overallReturn > 0) {
            portfolioReturnLabel.setText("+"+ String.format("%.1f", overallReturn) +"%");
            portfolioReturnLabel.setTextFill(Color.web("#1DB918"));
        } else {
            portfolioReturnLabel.setText(String.format("%.1f", overallReturn) +"%");
            portfolioReturnLabel.setTextFill(Color.web("#D31C18"));            
        }
        
        // List shares in portfolio
        portfolioTable.setItems(client.getPortfolio().getShares());
    }
    
}
