package debug;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FlightClientTest {

  @Test
  public void planeAllocation() {
    List<Plane> planes = new ArrayList<>();
    List<Flight> flights = new ArrayList<>();
    for (int i = 0; i < 5; ++i) {
      Plane plane = new Plane();
      plane.setPlaneNo("P000" + i);
      planes.add(plane);
      Flight flight = new Flight();
      flight.setFlightNo("F000" + i);
      flights.add(flight);
    }

    List<Calendar> calendars = new ArrayList<>();
    for (int i = 0; i < 10; ++i) {
      Calendar calendar = new GregorianCalendar(2020, 6, 7, i, 0);
      calendars.add(calendar);
    }

    FlightClient client = new FlightClient();

    flights.get(0).setDepartTime(calendars.get(0));
    flights.get(0).setArrivalTime(calendars.get(3));
    flights.get(1).setDepartTime(calendars.get(1));
    flights.get(1).setArrivalTime(calendars.get(6));
    flights.get(2).setDepartTime(calendars.get(2));
    flights.get(2).setArrivalTime(calendars.get(6));
    flights.get(3).setDepartTime(calendars.get(4));
    flights.get(3).setArrivalTime(calendars.get(6));
    flights.get(4).setDepartTime(calendars.get(5));
    flights.get(4).setArrivalTime(calendars.get(7));
    assertTrue(client.planeAllocation(planes, flights));
    flights.forEach(f -> f.setPlane(null));
    assertTrue(client.planeAllocation(planes.subList(0, 4), flights));

    flights.get(0).setDepartTime(calendars.get(4));
    flights.get(0).setArrivalTime(calendars.get(5));
    flights.get(1).setDepartTime(calendars.get(2));
    flights.get(1).setArrivalTime(calendars.get(5));
    flights.get(2).setDepartTime(calendars.get(0));
    flights.get(2).setArrivalTime(calendars.get(5));
    flights.get(3).setDepartTime(calendars.get(1));
    flights.get(3).setArrivalTime(calendars.get(3));
    flights.forEach(f -> f.setPlane(null));
    assertTrue(client.planeAllocation(planes.subList(0, 4), flights.subList(0, 4)));
    flights.forEach(f -> f.setPlane(null));
    assertFalse(client.planeAllocation(planes.subList(0, 3), flights.subList(0, 4)));
  }
}
