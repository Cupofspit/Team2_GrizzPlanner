package grizzplanner;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    //travel time fields
    @FXML private Label displayTravelTime;

    //parking lot fields
    @FXML private Label lotOneCapacityLabel;
    @FXML private Label lotTwoCapacityLabel;
    @FXML private Label lotThreeCapacityLabel;
    @FXML private Label lotFourCapacityLabel;

    //feedback fields
    @FXML private Label feedbackReceivedLabel;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            //sets up table view for events
            pull();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
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


/*    //method to make start time localtime format
    public void convertStartTime(){
        try {
            int hour = Integer.parseInt(startTimeHour.getValue());
            int min = Integer.parseInt(startTimeMinute.getValue());
            selectedStartTime = LocalTime.of(hour, min);
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }*/

/*    //method to make end time localtime format
    public void convertEndTime(){
        try {
            int hour = Integer.parseInt(endTimeHour.getValue());
            int min = Integer.parseInt(endTimeMinute.getValue());
            selectedEndTime = LocalTime.of(hour, min);
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }*/

    // method to update the eventObservableList with the contents of eventArrayList
    private void updateEventObservableList() {
        eventObservableList.setAll(eventArrayList);
    }


    public void addEventButtonPressed(ActionEvent actionEvent) throws IOException {
        boolean inArr = false;
        ClassEvent update = null;

        for (ClassEvent temp: eventArrayList){
            if (Integer.parseInt(removeEventIDBox.getText()) == temp.getCL_ID() && inArr == false){
                inArr = true;
                update = temp;
                System.out.println("looped");
            }
        }

        if (inArr == true){
            update.setCL_Name(addEventNameBox.getText());
            update.setCL_Date(calendarBox.getValue());
            update.setCL_StartTime(LocalTime.of(Integer.parseInt((startTimeHour.getValue())), Integer.parseInt(startTimeMinute.getValue())));
            update.setCL_EndTime(LocalTime.of(Integer.parseInt((endTimeHour.getValue())), Integer.parseInt(endTimeMinute.getValue())));
            update.setCL_ID(Integer.parseInt(removeEventIDBox.getText()));
            updateEventObservableList();
        }else{

            ClassEvent addedEvent = new ClassEvent(addEventNameBox.getText(), calendarBox.getValue(), LocalTime.of(Integer.parseInt((startTimeHour.getValue())), Integer.parseInt(startTimeMinute.getValue())), LocalTime.of(Integer.parseInt((endTimeHour.getValue())), Integer.parseInt(endTimeMinute.getValue())), Integer.parseInt( removeEventIDBox.getText()));
            ObservableList<ClassEvent> eventObservableList = eventDisplayBox.getItems();
            eventObservableList.add(addedEvent);
            eventDisplayBox.setItems(eventObservableList);
            eventArrayList.add(addedEvent);
        }

        try {
            save();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

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
        try {
            save();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void feedbackYesButtonPressed(ActionEvent actionEvent){
        feedbackReceivedLabel.setText("Thank you for the feedback!");
    }

    public void feedbackNoButtonPressed(ActionEvent actionEvent){
        feedbackReceivedLabel.setText("Thank you for the feedback!");
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
    
        public void pull() throws FileNotFoundException, IOException{
        String input = "./save.txt";
        File file = new File(input);
        InputStream inputStream = new FileInputStream(file);
        InputStreamReader isReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(isReader);
        
        String str;
        
        String CL_Name = "";
        LocalDate CL_Date = null;
    //private String CL_Location;
        LocalTime CL_StartTime = null;
        LocalTime CL_EndTime = null;
        int CL_ID = 0;
        
        ClassEvent add;
        
        while((str = reader.readLine())!= null){
           String[] split1 = str.split(",");
            for(String temp: split1){
//                System.out.println(temp);
                String[] split2 = temp.split("=");   
                switch(split2[0].trim()){
                    case "CL_ID" :
                        CL_ID = Integer.parseInt(split2[1]);
                        break;
                    case "CL_Name" :
                        CL_Name = split2[1];
                        break;
                    case "CL_Date" :
                        CL_Date = LocalDate.parse(split2[1]);
                        break;
                    case "CL_StartTime" :
                         CL_StartTime = LocalTime.parse(split2[1]);
                        break;
                    case "CL_EndTime" :
                         CL_EndTime = LocalTime.parse(split2[1]);
                        break;
                }
            }
            
            add = new ClassEvent(CL_Name, CL_Date, CL_StartTime, CL_EndTime, CL_ID);
            eventArrayList.add(add);
            updateEventObservableList();
//               System.out.println(str);
        }
    }
        
        private void save() throws IOException{
        String output = "./save.txt";
        File file = new  File(output);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        for (ClassEvent temp: eventArrayList){
            writer.write(temp.toString() + "\n");
        }
        writer.close();
    }

}
