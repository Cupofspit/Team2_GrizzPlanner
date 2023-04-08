package Tests;

import grizzplanner.Calendar;
import grizzplanner.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CalendarTest {
    private Calendar calendar;

    @BeforeEach
    void setUp() {
        calendar = new Calendar();
    }

    @Test
    void addEvent() {
        // create a mock input stream for user input
        InputStream inputStream = new ByteArrayInputStream("Event Name\n2023-03-30\nEvent Description\n".getBytes());
        System.setIn(inputStream);
        calendar = new Calendar();
        calendar.addEvent();


        List<Event> events = calendar.getEvents();
        assertEquals(1, events.size());

        Event event = events.get(0);
        assertEquals("Event Name", event.getName());
        assertEquals("2023-03-30", event.getDate());
        assertEquals("Event Description", event.getDescription());
    }

    @Test
    void viewEvent() {
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
    void updateEvent() {
        InputStream inputStreamTest = new ByteArrayInputStream("Event Name\n2023-03-30\nEvent Description\n".getBytes());
        System.setIn(inputStreamTest);
        calendar = new Calendar();
        calendar.addEvent();

        // create a mock input stream for user input
        InputStream inputStream = new ByteArrayInputStream("Updated Event Name\n2023-03-28\nUpdated Event Description\n".getBytes());
        System.setIn(inputStream);

        calendar.updateEvent(1, "Updated Event Name", "2023-03-28", "Updated Event Description");

        List<Event> events = calendar.getEvents();
        assertEquals(1, events.size());

        Event event = events.get(0);
        assertEquals("Updated Event Name", event.getName());
        assertEquals("2023-03-28", event.getDate());
        assertEquals("Updated Event Description", event.getDescription());
    }

    @Test
    void deleteEvent() {
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
        assertEquals("Event Description", event.getDescription());
    }
}