package stockmarketsimulator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Sam Wimshurst
 * @version April 2017
 */
public class Simulator extends Application {
  
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SimulatorView.fxml"));
        Parent root = loader.load();
        
        SimulatorController controller = loader.getController();
        StockMarket model = new StockMarket(controller);
        
        controller.initModel(model);
        
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}