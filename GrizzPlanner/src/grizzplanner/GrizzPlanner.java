/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grizzplanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author 15866
 */
public class GrizzPlanner extends Application {
    
    @Override
    public void start(Stage primaryStage) throws MalformedURLException, IOException {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
        String link = "https://api.tomtom.com/routing/1/calculateRoute/52.50931,13.42936:52.50274,13.43872/json?key=n5NcANxpS9cGU6R7PiANGgMeJWTw4625";
        URL url = new URL(link);
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        //System.out.println("Content: " + url.getContent());
        //System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        //System.out.println(http.getContentLength() + "");
        //System.out.println("Test");
        //System.out.println(http.getContent());
        InputStream in;
        in = http.getInputStream();
        String text = new BufferedReader(
            new InputStreamReader(in, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
        System.out.println(text);
        //System.out.println("Length: " + http.getContentLength() + "");     
        System.out.println("Test");
        //URI uri = url.toURI();
        http.disconnect();
    }
    
    //https://www.baeldung.com/convert-input-stream-to-string for inputstream

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
