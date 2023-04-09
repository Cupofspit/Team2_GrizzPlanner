package grizzplanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

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

    //event display fields
    @FXML private TableView<ClassEvent> eventDisplayBox;
    @FXML private TableColumn<ClassEvent, String> displayEventNameColumn;
    @FXML private TableColumn<ClassEvent, LocalDate> displayEventDateColumn;
    @FXML private TableColumn<ClassEvent, LocalTime> displayStartTimeColumn;
    @FXML private TableColumn<ClassEvent, LocalTime> displayEndTimeColumn;
    @FXML private TableColumn<ClassEvent, Integer> displayEventIDColumn;

    //event display variables
    private ObservableList<ClassEvent> eventObservableList = FXCollections.observableArrayList();

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
        //sets up table view for events
        displayEventNameColumn.setCellValueFactory(new PropertyValueFactory<ClassEvent, String>("CL_Name"));
        displayEventDateColumn.setCellValueFactory(new PropertyValueFactory<ClassEvent, LocalDate>("CL_Date"));
        displayStartTimeColumn.setCellValueFactory(new PropertyValueFactory<ClassEvent, LocalTime>("CL_StartTime"));
        displayEndTimeColumn.setCellValueFactory(new PropertyValueFactory<ClassEvent, LocalTime>("CL_EndTime"));
        displayEventIDColumn.setCellValueFactory(new PropertyValueFactory<ClassEvent, Integer>("CL_ID"));

        eventDisplayBox.setItems(eventObservableList);

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

        //example data for parking lot meter
        int lotOneSpots = (r.nextInt(40)+90);
        int lotOneSpace = 200;
        lotOneCapacityLabel.setText(lotOneSpots+"/"+lotOneSpace+", "+(((100*((double)lotOneSpots/(double)lotOneSpace)))+"%"));
        int lotTwoSpots = (r.nextInt(50)+120);
        int lotTwoSpace = 180;
        lotTwoCapacityLabel.setText(lotTwoSpots+"/"+lotTwoSpace+", "+(((100*((double)lotTwoSpots/(double)lotTwoSpace)))+"%"));
        int lotThreeSpots = (r.nextInt(70)+150);
        int lotThreeSpace = 400;
        lotThreeCapacityLabel.setText(lotThreeSpots+"/"+lotThreeSpace+", "+(((100*((double)lotThreeSpots/(double)lotThreeSpace)))+"%"));
        int lotFourSpots = (r.nextInt(20)+20);
        int lotFourSpace = 40;
        lotFourCapacityLabel.setText(lotFourSpots+"/"+lotFourSpace+", "+(((100*((double)lotFourSpots/(double)lotFourSpace)))+"%"));



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

    // method to update the eventObservableList with the contents of eventArrayList
    private void updateEventObservableList() {
        eventObservableList.setAll(eventArrayList);
    }


    public void addEventButtonPressed(ActionEvent actionEvent) throws IOException {
        ClassEvent addedEvent = new ClassEvent(addEventNameBox.getText(), calendarBox.getValue(), LocalTime.of(Integer.parseInt((startTimeHour.getValue())), Integer.parseInt(startTimeMinute.getValue())), LocalTime.of(Integer.parseInt((endTimeHour.getValue())), Integer.parseInt(endTimeMinute.getValue())), r.nextInt(100));
        //ClassEvent addedEvent = new ClassEvent("Name", LocalDate.now(), LocalTime.now(), LocalTime.now(), 123);
        ObservableList<ClassEvent> eventObservableList = eventDisplayBox.getItems();
        eventObservableList.add(addedEvent);
        eventDisplayBox.setItems(eventObservableList);
        eventArrayList.add(addedEvent);

        // update the TableView
        updateEventObservableList();

        //convertStartTime();
        //convertEndTime();
        //selectedDate = calendarBox.getValue();
        //selectedEventName = addEventNameBox.getText();
        //ID = r.nextInt(10000);
        //ClassEvent toBeAdded = new ClassEvent(selectedEventName,  selectedDate, selectedStartTime, selectedEndTime,ID);
        //eventArrayList.add(toBeAdded);
        //ClassEvent testEvent = new ClassEvent("Name", LocalTime.now(), LocalTime.now(), LocalDate.now(), 1);
        //eventObservableList.add(toBeAdded);
        //eventObservableList.add(testEvent);
        //System.out.println(toBeAdded.toString());
        //System.out.println(eventObservableList);
       // eventDisplayBox.getItems().addAll(eventObservableList);

        //debug
/*
        System.out.println("getters from classevent class");
        System.out.println(toBeAdded.getClassEventName());
        System.out.println(toBeAdded.getClassEventDate());
        System.out.println(toBeAdded.getClassEventStartTime());
        System.out.println(toBeAdded.getClassEventEndTime());
        System.out.println(toBeAdded.getClassEventID());

        System.out.println("local variables");
        System.out.println(selectedEventName);
        System.out.println(selectedStartTime);
        System.out.println(selectedEndTime);
        System.out.println(selectedDate);
        System.out.println(ID);

        System.out.println("items in array list");
        for(int i = 0; i<eventArrayList.size(); i++){
            System.out.println(eventArrayList.get(i).getClassEventName());
            System.out.println(eventArrayList.get(i).getClassEventDate());
            System.out.println(eventArrayList.get(i).getClassEventStartTime());
            System.out.println(eventArrayList.get(i).getClassEventEndTime());
            System.out.println(eventArrayList.get(i).getClassEventID());
        }

        System.out.println("items in observable list");
        for(int i = 0; i<eventObservableList.size(); i++){
            System.out.println(eventObservableList.get(i).getClassEventName());
            System.out.println(eventObservableList.get(i).getClassEventDate());
            System.out.println(eventObservableList.get(i).getClassEventStartTime());
            System.out.println(eventObservableList.get(i).getClassEventEndTime());
            System.out.println(eventObservableList.get(i).getClassEventID());
        }


        System.out.println("displaybox get items:");
        System.out.println(eventDisplayBox.getItems());
*/

    }

    public void removeEventButtonPressed(ActionEvent actionEvent){
       int removalID = Integer.parseInt(removeEventIDBox.getText());
        for(int i=0; i<eventArrayList.size(); i++){
            if(eventArrayList.get(i).getCL_ID() == removalID){
                eventArrayList.remove(i);
                System.out.println("Removed event with ID "+removalID+"!");
            }
            else continue;
        }
int selectedId = eventDisplayBox.getSelectionModel().getSelectedIndex();
eventDisplayBox.getItems().remove(selectedId);

    }

    public void feedbackYesButtonPressed(ActionEvent actionEvent){

    }

    public void feedbackNoButtonPressed(ActionEvent actionEvent){

    }

    public void trafficSubmitButtonPressed(ActionEvent actionEvent) throws IOException {
        Traffic toBeAdded = new Traffic(Integer.parseInt(addressBox.getText()), streetNameBox.getText(), cityNameBox.getText(), stateChoiceBox.getValue());
        displayTravelTime.setText(String.valueOf(toBeAdded.findDriveTime())+" minutes");

    }

    public void boxClicked(MouseEvent mouseEvent){
        ClassEvent clickedEvent = eventDisplayBox.getSelectionModel().getSelectedItem();
        addEventNameBox.setText(clickedEvent.getCL_Name());
        startTimeHour.setValue(String.valueOf(clickedEvent.getCL_StartTime().getHour()));
        startTimeMinute.setValue(String.valueOf(clickedEvent.getCL_StartTime().getMinute()));
        endTimeHour.setValue(String.valueOf(clickedEvent.getCL_EndTime().getHour()));
        endTimeMinute.setValue(String.valueOf(clickedEvent.getCL_EndTime().getMinute()));
        calendarBox.setValue(clickedEvent.getCL_Date());
        removeEventIDBox.setText(String.valueOf(clickedEvent.getCL_ID()));
    }
}
