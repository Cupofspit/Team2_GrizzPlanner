
package grizzplanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;



public class Traffic {
    private String key = "n5NcANxpS9cGU6R7PiANGgMeJWTw4625";
    
    private String start;
    public int address;
    public String street;
    public String city;
    public String state;
    public String xCoord;
    public String yCoord;
    private List<ParkingLot> parkingLots;
    
    public Traffic(int address, String street, String city, String state) throws IOException{
        start = address + "," + street + "," + city + "," + state;
        start = start.replaceAll("\\s", "");
        this.street = street;
        this.address = address;
        this.city = city;
        this.state = state;
        xCoord = locateXCoords(start);
        yCoord = locateYCoords(start);
        
        //Ashton
        ParkingLot lot1 = new ParkingLot("Lot 1", 100, 80);
        ParkingLot lot2 = new ParkingLot("Lot 2", 150, 120);
        ParkingLot lot3 = new ParkingLot("Lot 3", 200, 180);
        ParkingLot lot4 = new ParkingLot("Lot 4", 250, 200);

        parkingLots = new ArrayList<>();
        parkingLots.add(lot1);
        parkingLots.add(lot2);
        parkingLots.add(lot3);
        parkingLots.add(lot4);
        
    }
     
    public int findDriveTime() throws MalformedURLException, IOException{
        String link = "";
        link = "https://api.tomtom.com/routing/1/calculateRoute/" + xCoord + "," + yCoord + ":42.6679,-83.2082/json?key=" + key;
        System.out.println(link);
        URL url = new URL(link);
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        InputStream in;
        in = http.getInputStream();
        String text = new BufferedReader(
            new InputStreamReader(in, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
        //System.out.println(text);    
        http.disconnect();
        
        int intIndex = text.indexOf("travelTimeInSeconds");
        int endIndex = text.indexOf(",\"trafficDelayInSeconds\"");
        
        //System.out.println(intIndex);
        
        text = (String) text.subSequence(intIndex, endIndex);
        
        //System.out.println(text);
        
        String[] splits = text.split(":");
        String time = splits[1];
        System.out.println(time + " In Seconds");
        int min = Integer.parseInt(time);
        min = (int) Math.ceil(min/60);
        System.out.println(min + " In Minutes");
        return min;
        
        //can add arrive at header to get reccomendation on when to depart
        //will have to implement another api call to get location > coords
       
    }
     //https://www.baeldung.com/convert-input-stream-to-string for inputstream
    //https://developer.tomtom.com/routing-api/documentation/routing/calculate-route for api documentation
    //https://www.baeldung.com/java-http-request for http request in java 8
    //https://reqbin.com/ api call code
    //https://www.tutorialspoint.com/javaexamples/string_search.htm for parsing string
    //https://www.geeksforgeeks.org/how-to-remove-all-white-spaces-from-a-string-in-java/ for the replace
    
    public String locateXCoords(String addr) throws MalformedURLException, IOException{
        String link = "";
        link = "https://api.tomtom.com/search/2/geocode/" + addr + ",UnitedStates.json?key=" + key;
        URL url2 = new URL(link);
        HttpURLConnection http = (HttpURLConnection)url2.openConnection();
                InputStream in;
        in = http.getInputStream();
        String text = new BufferedReader(
            new InputStreamReader(in, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
        //System.out.println(text);   
        http.disconnect();
        
        int intIndex = text.indexOf("\"position\":{\"lat\"");
        int endIndex = text.indexOf(",\"lon\":");
        
        text = (String) text.subSequence(intIndex, endIndex);
        
        String[] splits = text.split(":");
        String x = splits[2];
        System.out.println(x + " lat");
  
        
        return x;
    }
    
    public String locateYCoords(String addr) throws MalformedURLException, IOException{
        String link = "";
        link = "https://api.tomtom.com/search/2/geocode/" + addr + ",UnitedStates.json?key=" + key;
        URL url2 = new URL(link);
        HttpURLConnection http = (HttpURLConnection)url2.openConnection();
                InputStream in;
        in = http.getInputStream();
        String text = new BufferedReader(
            new InputStreamReader(in, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
        //System.out.println(text);   
        http.disconnect();
        
        int intIndex = text.indexOf("\"lon\":");
        int endIndex = text.indexOf("},\"viewport\"");
        
        text = (String) text.subSequence(intIndex, endIndex);
        
        String[] splits = text.split(":");
        String y = splits[1];
        System.out.println(y + " lon");        
        
        return y;
    }
        public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
        setStart();
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
        setStart();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
        setStart();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
        setStart();
    }

    public String getxCoord() {
        return xCoord;
    }

    public String getyCoord() {
        return yCoord;
    }
    
    private void setStart(){
        start = address + "," + street + "," + city + "," + state;
    }
        
}

//need to split out urls, make them customizable
//update constructor for address, use get coords to get coordinates
//add gets and sets
//pass through methods
