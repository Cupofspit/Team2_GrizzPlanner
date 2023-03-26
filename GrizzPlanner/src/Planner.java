/*package studentplanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StudentPlanner extends Application {
    
    private Calendar calendar;
    private Notification notification;
    private ParkingLot parkingLot;
    
    @Override
    public void start(Stage primaryStage) {
        // Initialize the calendar, notification, and parking lot objects
        calendar = new Calendar();
        notification = new Notification();
        parkingLot = new ParkingLot();
        
        // Create the user interface components
        Button addEventButton = new Button("Add Event");
        addEventButton.setOnAction(e -> {
            // Display a dialog to add a new event
            EventDialog eventDialog = new EventDialog();
            Event event = eventDialog.showAndWait();
            if (event != null) {
                calendar.addEvent(event);
            }
        });
        Button filterEventsButton = new Button("Filter Events");
        filterEventsButton.setOnAction(e -> {
            // Display a dialog to filter events
            FilterDialog filterDialog = new FilterDialog();
            EventFilter filter = filterDialog.showAndWait();
            if (filter != null) {
                calendar.setFilter(filter);
            }
        });
        Button showParkingLotButton = new Button("Show Parking Lot");
        showParkingLotButton.setOnAction(e -> {
            // Display a dialog to show parking lot information
            ParkingLotDialog parkingLotDialog = new ParkingLotDialog();
            String parkingLotInfo = parkingLotDialog.showAndWait();
            if (parkingLotInfo != null) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Parking Lot Info");
                alert.setHeaderText(null);
                alert.setContentText(parkingLotInfo);
                alert.showAndWait();
            }
        });
        Label upcomingEventsLabel = new Label("Upcoming Events:");
        EventListView upcomingEventsListView = new EventListView();
        upcomingEventsListView.setEvents(calendar.getUpcomingEvents());
        // Schedule notifications for upcoming events
        notification.scheduleNotifications(calendar.getUpcomingEvents());
        
        // Create the main layout
        VBox buttonsBox = new VBox(10, addEventButton, filterEventsButton, showParkingLotButton);
        BorderPane mainLayout = new BorderPane();
        mainLayout.setLeft(buttonsBox);
        mainLayout.setCenter(upcomingEventsLabel);
        mainLayout.setRight(upcomingEventsListView);
        
        // Create the main scene and show the stage
        Scene scene = new Scene(mainLayout, 800, 600);
        primaryStage.setTitle("Student Planner");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
*/