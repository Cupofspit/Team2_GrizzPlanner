
package grizzplanner;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;



public class Traffic {
    private String key = "n5NcANxpS9cGU6R7PiANGgMeJWTw4625";
    
    public static void main(String[] args) throws MalformedURLException, IOException{
        URL url = new URL("https://api.tomtom.com/routing/1/calculateRoute/52.50931,13.42936:52.50274,13.43872/json?key=n5NcANxpS9cGU6R7PiANGgMeJWTw4625");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        //System.out.println("Content: " + url.getContent());
        //System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        //System.out.println(http.getContentLength() + "");
        //System.out.println("Test");
        
        http.getInputStream();
        http.disconnect();
        
        //System.out.println("Length: " + http.getContentLength() + "");     
        System.out.println("Test");
        //URI uri = url.toURI();
       
        
        
    }
}
