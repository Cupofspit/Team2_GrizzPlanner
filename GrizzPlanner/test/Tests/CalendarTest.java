package Tests;

import grizzplanner.Calendar;
import grizzplanner.Event;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;
import org.junit.*;
import static org.junit.Assert.*;

//import static org.junit.jupiter.api.Assertions.*;

public class CalendarTest {
    private Calendar calendar;

    @Test
    public void addEvent() throws IOException {
        // create a mock input stream for user input
        calendar = new Calendar();
        InputStream inputStream = new ByteArrayInputStream("Event Name\n2023-03-30\nEvent Description\n".getBytes());
        System.setIn(inputStream);
        calendar = new Calendar();
        calendar.addEvent();


        List<Event> events = calendar.getEvents();
        assertEquals(1, events.size());

        Event event = events.get(0);
        assertEquals("Event Name", event.getName());
        assertEquals("2023-03-30", event.getDate());
        //assertEquals("Event Description", event.getDescription());
    }

    @Test
    public void viewEvent() throws IOException {
        calendar = new Calendar();
        InputStream inputStreamTest = new ByteArrayInputStream("Event Name\n2023-03-30\nEvent Description\n".getBytes());
        System.setIn(inputStreamTest);
        calendar = new Calendar();
        calendar.addEvent();

        // create a mock input stream for user input
        InputStream inputStream = new ByteArrayInputStream("1\n".getBytes());
        System.setIn(inputStream);

        // capture the output of the viewEvent method
        String expectedOutput = "Event ID: 1\nEvent Name: Event Name\nEvent Date: 2023-03-30\nEvent Description: Event Description\n";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        calendar.viewEvent(1);

        assertEquals(expectedOutput, calendar.viewEvent(1));
    }

    @Test
    public void updateEvent() throws IOException {
        InputStream inputStreamTest = new ByteArrayInputStream("Event Name\n2023-03-30\nEvent Description\n".getBytes());
        System.setIn(inputStreamTest);
        calendar = new Calendar();
        calendar.addEvent();

        // create a mock input stream for user input
        InputStream inputStream = new ByteArrayInputStream("Updated Event Name\n2023-03-28\nUpdated Event Description\n".getBytes());
        System.setIn(inputStream);

        //calendar.updateEvent(1, "Updated Event Name", "2023-03-28", "Updated Event Description");

        List<Event> events = calendar.getEvents();
        assertEquals(1, events.size());

        Event event = events.get(0);
        assertEquals("Updated Event Name", event.getName());
        assertEquals("2023-03-28", event.getDate());
        //assertEquals("Updated Event Description", event.getDescription());
    }

    @Test
    public void deleteEvent() throws IOException {
        InputStream inputStream = new ByteArrayInputStream("Event Name\n2023-03-30\nEvent Description\n".getBytes());
        System.setIn(inputStream);
        calendar = new Calendar();
        calendar.addEvent();


        calendar.deleteEvent(2);

        List<Event> events = calendar.getEvents();
        assertEquals(1, events.size());

        Event event = events.get(0);
        assertEquals(1, event.getId());
        assertEquals("Event Name", event.getName());
        assertEquals("2023-03-30", event.getDate());
        //assertEquals("Event Description", event.getDescription());
    }
}