package stockmarketsimulator;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *This class scans through the ExternalEventData for key pieces of information and assigns them to the correct variable 
 * @author Alexander Reddington
 */
public class ExternalEvent {

    private LocalDate startDate;
    private LocalTime startTime;
    private String nature;
    private String traderType;
    private String actionInput;
    private Boolean force;
    private String action;
    private String stockType;
    private String behaviour;
    private LocalDate endDate;
    private LocalTime endTime;
    /**
     * 
     * @param date sets the start date 
     */
    public void setStartDate(String date) {
        startDate = LocalDate.parse(date);
    }
    /**
     * 
     * @param time sets the start time 
     */
    public void setStartTime(String time) {
        startTime = LocalTime.parse(time);
    }
    /**
     * 
     * @param natureInput sets what type of nature
     */
    public void setNature(String natureInput) {
        nature = natureInput;
    }
    /**
     * 
     * @param Input sets what the action input is 
     */
    public void setActionInput(String Input) {
        actionInput = Input;
        action = Input;
    }
    /**
     * 
     * @return startDate gets start date
     */
    public LocalDate getStartDate() {
        return startDate;
    }
    /**
     * 
     * @return traderType gets what trader type 
     */
    public String getTraderType() {
        return traderType;
    }
    /**
     * 
     * @return startTime gets the start time
     */
    public LocalTime getStartTime() {
        return startTime;
    }
    /**
     * 
     * @return nature gets what nature of the event it 
     */
    public String getNature() {
        return nature;
    }

    /**
     *
     * @return
     */
    public Boolean getForce() {
        return force;
    }
    /**
     * 
     * @return action gets the action that has an impact on the event
     */
    public String getAction() {
        return action;
    }
    /**
     * 
     * @return stockType gets stock type
     */
    public String getStock() {
        return stockType;
    }
    /**
     * 
     * @return behaviour gets behaviour of the event 
     */
    public String getBehaviour() {
        return behaviour;
    }
    /**
     * 
     * @return endData from event 
     */
    public LocalDate getEndDate() {
        return endDate;
    }
    /**
     * 
     * @return endTime from event 
     */
    public LocalTime getEndTime() {
        return endTime;
    }
    
    /**
     * Sorts through the necessary information from the ExternalEventsData.xls 
     * assigns them through variable throughout this class
     * @throws IOException when imported data is wrong from the xls document 
     */
    public void setFields() throws IOException {
        if (actionInput != null) {
            //Set Type of trader to be influenced by event.
            if (actionInput.matches(".*\\Random\\b.*")) {
                traderType = "Random";
            } else if (actionInput.matches(".*\\Intelligent\\b.*")) {
                traderType = "Intelligent";
            } else {
                throw new java.io.IOException("Non Appropriate Trader Type");
            }
            //Set Force value of event
            if (!actionInput.matches(".*\\may\\b.*")) {
                force = true;
            }
            //Set Action of Event
            if (actionInput.matches(".*\\buy\\b.*")) {
                action = "Buy";
            } else if (actionInput.matches(".*\\sell\\b.*")) {
                action = "Sell";
            } else {
                throw new java.io.IOException("Non Appropriate Action Found");
            }
            // Set Stock Type
            if (actionInput.matches(".*\\any\\b.*")) {
                stockType = "Any";
            } else if (actionInput.matches(".*\\food\\b.*")) {
                stockType = "Food";
            } else if (actionInput.matches(".*\\hard\\b.*")) {
                stockType = "Hard";
            } else if (actionInput.matches(".*\\hi tech\\b.*")) {
                stockType = "Hitech";
            } else if (actionInput.matches(".*\\property\\b.*")) {
                stockType = "Property";
            } else {
                throw new java.io.IOException("No Appropriate Stock type has been found");
            }
            //Set Event Duration
            if (actionInput.matches(".*\\over\\b.*")) {
                int days = Integer.parseInt(actionInput.replaceAll("[^0-9]+", " "));
                endDate = startDate.plusDays(days);
                endTime = startTime;
            } else if (actionInput.matches(".*\\for\\b.*")) {
                endDate = startDate;
                endTime = LocalTime.parse("16:00");
            } else {
                throw new java.io.IOException("No Appropriate Duration Found");
            }
        } else {
            throw new java.io.IOException("No Action has been assigned to this External Event");
        }
    }
}