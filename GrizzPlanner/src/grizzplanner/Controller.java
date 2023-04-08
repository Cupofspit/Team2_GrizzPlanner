package grizzplanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    //class ID counter
    Random r = new Random();
    int ID = r.nextInt(10000);

    //remove event fields
    @FXML private TextField removeEventIDBox;
    @FXML private Button removeEventButton;

    //add event fields
    @FXML private TextField addEventNameBox;
    @FXML private Button addEventButton;
    @FXML private ChoiceBox<String> startTimeHour;
    @FXML private ChoiceBox<String> startTimeMinute;
    @FXML private ChoiceBox<String> endTimeHour;
    @FXML private ChoiceBox<String> endTimeMinute;
    @FXML private ChoiceBox<String> startTimeAMPM;
    @FXML private ChoiceBox<String> endTimeAMPM;
    @FXML private DatePicker calendarBox;

    //add event variables
    private ArrayList<ClassEvent> eventArrayList = new ArrayList<>();
    private String selectedEventName;
    private LocalTime selectedStartTime;
    private LocalTime selectedEndTime;
    private LocalDate selectedDate;


    //traffic fields
    @FXML private TextField addressBox;
    @FXML private TextField streetNameBox;
    @FXML private TextField cityNameBox;
    @FXML private ChoiceBox<String> stateChoiceBox;
    @FXML private Button startPointSubmitButton;

    //traffic variables
    private String selectedAddress;
    private String selectedStreetName;
    private String selectedCityName;
    private String selectedState;

    //travel time fields
    @FXML private Label displayTravelTime;

    //parking lot fields
    @FXML private Label lotOneCapacityLabel;
    @FXML private Label lotTwoCapacityLabel;
    @FXML private Label lotThreeCapacityLabel;
    @FXML private Label lotFourCapacityLabel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //add event fields for choice boxes
        String[] hourTimes = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        String[] minuteTimes = new String[60];
        String[] ampm = {"AM", "PM"};
        //loop to add minutes to an array, formats properly
        for(int i = 0; i<60; i++){
            if(i<10){
                minuteTimes[i] = "0"+i;
            }
            else {
                minuteTimes[i] = Integer.toString(i);
            }
        }
        startTimeHour.getItems().addAll(hourTimes);
        endTimeHour.getItems().addAll(hourTimes);
        startTimeMinute.getItems().addAll(minuteTimes);
        endTimeMinute.getItems().addAll(minuteTimes);
        startTimeAMPM.getItems().addAll(ampm);
        endTimeAMPM.getItems().addAll(ampm);




        //traffic fields
        String [] stateAbbr = {"AK", "AL", "AR", "AZ", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "IA", "ID", "IL", "IN", "KS", "KY", "LA", "MA", "MD", "ME", "MI", "MN", "MO", "MS", "MT", "NC", "ND", "NE", "NH", "NJ", "NM", "NV", "NY", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VA", "VT", "WA", "WI", "WV", "WY"};

        stateChoiceBox.getItems().addAll(stateAbbr);

    }


    //method to make start time localtime format
    public void convertStartTime(){
        try {
            int hour = Integer.parseInt(startTimeHour.getValue());
            int min = Integer.parseInt(startTimeMinute.getValue());
            selectedStartTime = LocalTime.of(hour, min);
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    //method to make end time localtime format
    public void convertEndTime(){
        try {
            int hour = Integer.parseInt(endTimeHour.getValue());
            int min = Integer.parseInt(endTimeMinute.getValue());
            selectedEndTime = LocalTime.of(hour, min);
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    public void addEventButtonPressed(ActionEvent actionEvent) {
        convertStartTime();
        convertEndTime();
        selectedDate = calendarBox.getValue();
        selectedEventName = addEventNameBox.getText();
        ID = r.nextInt(10000);
        ClassEvent toBeAdded = new ClassEvent(selectedEventName, selectedStartTime, selectedEndTime, selectedDate, ID);
        eventArrayList.add(toBeAdded);
        System.out.println(toBeAdded.toString());
    }

    public void removeEventButtonPressed(ActionEvent actionEvent){
        int removalID = Integer.parseInt(removeEventIDBox.getText());
        for(int i=0; i<eventArrayList.size(); i++){
            if(eventArrayList.get(i).getClassEventID() == removalID){
                eventArrayList.remove(i);
                System.out.println("Removed event with ID "+removalID+"!");
            }
            else continue;
        }

    }

    public void feedbackYesButtonPressed(ActionEvent actionEvent){

    }

    public void feedbackNoButtonPressed(ActionEvent actionEvent){

    }

    public void trafficSubmitButtonPressed(ActionEvent actionEvent) throws IOException {
        Traffic toBeAdded = new Traffic(Integer.parseInt(addressBox.getText()), streetNameBox.getText(), cityNameBox.getText(), stateChoiceBox.getValue());
        displayTravelTime.setText(String.valueOf(toBeAdded.findDriveTime())+" minutes");
    }
}
