
package grizzplanner;



import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import java.util.List;
import java.util.ArrayList;


public class GrizzPlanner extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Calendar cal = new Calendar();
//       cal.addEvent();
//        cal.addEvent();
//        for(Event temp: cal.getEvents()){
//            System.out.println(temp.toString());
//        }
//        cal.deleteEvent(1);
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        VBox root = FXMLLoader.load(getClass().getResource("PlannerView.fxml"));
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Hello World!");

        Traffic test = new Traffic(316, "Paragon","Troy", "Michigan");
        test.findDriveTime();

        VBox parkingLotBox = new VBox();

        for (ParkingLot lot : test.getParkingLots()) {
            Label nameLabel = new Label(lot.getName());
            Label capacityLabel = new Label("Capacity: " + lot.getCapacity());
            Label availabilityLabel = new Label("Availability: " + lot.getAvailability());

            parkingLotBox.getChildren().addAll(nameLabel, capacityLabel, availabilityLabel);
        }

        //StackPane root = new StackPane();
        root.getChildren().addAll(parkingLotBox);

        primaryStage.setTitle("GrizzPlanner");
        primaryStage.setScene(scene);
        primaryStage.show();
        

    }
    

    public static void main(String[] args) {
        launch(args);
    }

}
