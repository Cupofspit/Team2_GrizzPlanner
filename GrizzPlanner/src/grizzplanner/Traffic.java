
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



public class Traffic {
    private String key = "n5NcANxpS9cGU6R7PiANGgMeJWTw4625";
    
    private String start;
    private String dest;
    
    public Traffic(String location, String destination){
        start = location;
        dest = destination;
        
    }
    
    public String connect() throws MalformedURLException, IOException{
        String link = "https://api.tomtom.com/routing/1/calculateRoute/52.50931,13.42936:52.50274,13.43872/json?key=";
        link = link + key;
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
        
        return text;
        
        //can add arrive at header to get reccomendation on when to depart
        //will have to implement another api call to get location > coords
       
    }
     //https://www.baeldung.com/convert-input-stream-to-string for inputstream
    //https://developer.tomtom.com/routing-api/documentation/routing/calculate-route for api documentation
    //https://www.baeldung.com/java-http-request for http request in java 8
    //https://reqbin.com/ api call code
    
    public void getCoords() throws MalformedURLException, IOException{
        URL url2 = new URL("https://api.tomtom.com/search/2/geocode/14530,HopeDrive,SterlingHeights,Michigan,UnitedStates.json?key=n5NcANxpS9cGU6R7PiANGgMeJWTw4625");
        HttpURLConnection http = (HttpURLConnection)url2.openConnection();
                InputStream in;
        in = http.getInputStream();
        String text = new BufferedReader(
            new InputStreamReader(in, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
        System.out.println(text);   
        System.out.println("Test");
        http.disconnect();
    }
}
