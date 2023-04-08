/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grizzplanner;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.List;
import java.util.ArrayList;
import grizzplanner.Traffic;
import grizzplanner.ParkingLot;


public class GrizzPlanner extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });

        Traffic traffic = new Traffic();

        VBox parkingLotBox = new VBox();

        for (ParkingLot lot : traffic.getParkingLots()) {
            Label nameLabel = new Label(lot.getName());
            Label capacityLabel = new Label("Capacity: " + lot.getCapacity());
            Label availabilityLabel = new Label("Availability: " + lot.getAvailability());

            parkingLotBox.getChildren().addAll(nameLabel, capacityLabel, availabilityLabel);
        }

        StackPane root = new StackPane();
        root.getChildren().addAll(btn, parkingLotBox);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("GrizzPlanner");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
