package flight;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import base.CommonLocation;
import base.CommonPlanningEntry;
import base.Location;
import base.PairLocationsEntry;
import base.PairLocationsEntryImpl;
import base.PairLocationsEntryUnmodifiableDecorator;
import base.PlanningEntry;
import base.SingleResourceEntry;
import base.SingleResourceEntryImpl;
import base.SingleTimeslotEntry;
import base.SingleTimeslotEntryImpl;
import base.Timeslot;

/**
 * This class {@link FlightEntry} implies the basic functions of {@link FlightPlanningEntry}.
 *
 * @author cycleke
 */
public class FlightEntry extends CommonPlanningEntry<Plane> implements FlightPlanningEntry {
  public static final boolean LOCATION_SHAREABLE = true;
  public static final boolean RESOURCE_SHAREABLE = false;

  private PairLocationsEntry pairLocationsEntryImpl;
  private SingleResourceEntry<Plane> singleResourceEntryImpl;
  private SingleTimeslotEntry singleTimeslotEntryImpl;

  // Abstraction function:
  // AF(e) = {timeslot, startLocation, targetLocation, plane}
  // Rep invariant:
  // All field is not null, the locations are shareable.
  // Safety from rep exposure:
  // the field are all private.

  /**
   * Constructor of {@link FlightEntry}.
   *
   * @param name
   *          the name of the flight plan, not null
   * @param timeslot
   *          the time of the flight plan, not null
   * @param startLocation
   *          the start location of the plan, not null and is shareable
   * @param targetLocation
   *          the target location of the plan, not null and is shareable
   */
  public FlightEntry(String name, Timeslot timeslot, Location startLocation, Location targetLocation) {
    this.name = name;
    state = new WaitingState(this);

    pairLocationsEntryImpl =
      new PairLocationsEntryUnmodifiableDecorator(new PairLocationsEntryImpl(startLocation, targetLocation));
    singleTimeslotEntryImpl = new SingleTimeslotEntryImpl(timeslot);
    singleResourceEntryImpl = new SingleResourceEntryImpl<>();
    checkRep();
  }

  @Override
  protected void checkRep() {
    super.checkRep();
    assert pairLocationsEntryImpl != null;
    assert singleResourceEntryImpl != null;
    assert singleTimeslotEntryImpl != null;
    for (Location location : pairLocationsEntryImpl.getLocations())
      assert location.isShareable();
  }

  @Override
  public Location getStartLocation() {
    return pairLocationsEntryImpl.getStartLocation();
  }

  @Override
  public Location getTargetLocation() {
    return pairLocationsEntryImpl.getTargetLocation();
  }

  @Override
  public List<Location> getLocations() {
    return pairLocationsEntryImpl.getLocations();
  }

  @Override
  public void setStartLocation(Location startLocation) {
    pairLocationsEntryImpl.setStartLocation(startLocation);
    checkRep();
  }

  @Override
  public void setTargetLocation(Location targetLocation) {
    pairLocationsEntryImpl.setTargetLocation(targetLocation);
    checkRep();
  }

  @Override
  public void setLocations(List<Location> locations) {
    pairLocationsEntryImpl.setLocations(locations);
    checkRep();
  }

  @Override
  public Plane getResource() {
    return singleResourceEntryImpl.getResource();
  }

  @Override
  public void setResource(Plane resource) {
    singleResourceEntryImpl.setResource(resource);
    checkRep();
  }

  @Override
  public void setTimeslot(Timeslot timeslot) {
    singleTimeslotEntryImpl.setTimeslot(timeslot);
    checkRep();
  }

  @Override
  public Timeslot getTimeslot() {
    return singleTimeslotEntryImpl.getTimeslot();
  }

  @Override
  public Timeslot getCurrentTimeslot() {
    return singleTimeslotEntryImpl.getTimeslot();
  }

  @Override
  public Location getCurrentLocation() {
    return state.toString().contains("ENDED") ? getTargetLocation() : getStartLocation();
  }

  @Override
  public boolean conflictLocationWith(PlanningEntry<?> entry) {
    return false;
  }

  @Override
  public boolean conflictResourceWith(PlanningEntry<?> entry) {
    if (!(entry instanceof FlightPlanningEntry))
      return false;
    if (singleResourceEntryImpl.getResource() == null)
      return false;
    FlightPlanningEntry anotherEntry = (FlightPlanningEntry)entry;
    Timeslot anotherTimeslot = anotherEntry.getTimeslot();
    if (!getTimeslot().intersect(anotherTimeslot))
      return false;
    return getResource().equals(anotherEntry.getResource());
  }

  @Override
  public String toString() {
    return getName() + " " + getTimeslot().getStartTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + " - "
      + getTimeslot().getEndTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + " "
      + String.format("%s - %s", getStartLocation().getName(), getTargetLocation().getName());
  }

  /**
   * Parse a number of entries from a file.
   *
   * @param file
   *          the file to parse
   * @throws FileParseException
   *           if the file is illegal.
   * @throws FileNotFoundException
   */
  public static List<FlightEntry> parse(File file) throws FileParseException, FileNotFoundException {
    List<FlightEntry> entries = new ArrayList<>();

    try (Scanner scanner = new Scanner(file)) {
      final int[] line = {0};
      line[0] = 0;
      final Function<Void, String> getNonmptyLineFunction = x -> {
        while (scanner.hasNext()) {
          ++line[0];
          String lineString = scanner.nextLine().trim();
          if (lineString.isEmpty())
            continue;
          return lineString;
        }
        return null;
      };
      final Function<String, String> getValueFunction = x -> x.substring(x.indexOf(':') + 1).trim();

      final Pattern checkFlightPattern = Pattern.compile("^Flight:\\d{4}-\\d{2}-\\d{2},[A-Z]{2}\\d{2,4}$");
      final Pattern flightNamePattern = Pattern.compile("[A-Z]{2}\\d{2,4}");
      final Pattern checkDepartureAirportPattern = Pattern.compile("^DepartureAirport:\\w+$");
      final Pattern checkArrivalAirportPattern = Pattern.compile("^ArrivalAirport:\\w+$");
      final Pattern checkDepatureTimePattern = Pattern.compile("^DepatureTime:\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}$");
      final Pattern checkArrivalTimePattern = Pattern.compile("^ArrivalTime:\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}$");
      final Pattern checkPlanePattern = Pattern.compile("^Plane:[NB]\\d{4}$");
      final Pattern checkPlaneTypePattern = Pattern.compile("^Type:[a-zA-Z\\d]*$");
      final Pattern checkPlaneSeatsPattern = Pattern.compile("^Seats:\\w*$");
      final Pattern checkPlaneAgePattern = Pattern.compile("^Age:(0|0\\.0|[1-9]?\\d(.\\d)?)$");

      while (scanner.hasNext()) {
        // Flight
        String lineString = getNonmptyLineFunction.apply(null);
        if (lineString == null || !checkFlightPattern.matcher(lineString).find())
          throw new FileParseException(file, line[0]);
        Matcher flightMatcher = flightNamePattern.matcher(lineString);
        flightMatcher.find();
        String flight = flightMatcher.group();
        int neededZeroCount = 6 - flight.length();
        if (neededZeroCount > 0) {
          flight =
            flight.substring(0, 2) + new String(new char[neededZeroCount]).replace('\0', '0') + flight.substring(2);
        }
        // {
        lineString = getNonmptyLineFunction.apply(null);
        if (lineString == null || !lineString.equals("{"))
          throw new FileParseException(file, line[0]);

        // DepartureAirport
        lineString = getNonmptyLineFunction.apply(null);
        if (lineString == null || !checkDepartureAirportPattern.matcher(lineString).find())
          throw new FileParseException(file, line[0]);
        String departureAirport = getValueFunction.apply(lineString);

        // ArrivalAirport
        lineString = getNonmptyLineFunction.apply(null);
        if (lineString == null || !checkArrivalAirportPattern.matcher(lineString).find())
          throw new FileParseException(file, line[0]);
        String arrivalAirport = getValueFunction.apply(lineString);

        // DepatureTime 拼写错误
        lineString = getNonmptyLineFunction.apply(null);
        if (lineString == null || !checkDepatureTimePattern.matcher(lineString).find())
          throw new FileParseException(file, line[0]);
        String depatureTime = getValueFunction.apply(lineString);

        // ArrivalTime
        lineString = getNonmptyLineFunction.apply(null);
        if (lineString == null || !checkArrivalTimePattern.matcher(lineString).find())
          throw new FileParseException(file, line[0]);
        String arrivalTime = getValueFunction.apply(lineString);

        // Plane
        lineString = getNonmptyLineFunction.apply(null);
        if (lineString == null || !checkPlanePattern.matcher(lineString).find())
          throw new FileParseException(file, line[0]);
        String plane = getValueFunction.apply(lineString);

        // {
        lineString = getNonmptyLineFunction.apply(null);
        if (lineString == null || !lineString.equals("{"))
          throw new FileParseException(file, line[0]);

        // Plane Type
        lineString = getNonmptyLineFunction.apply(null);
        if (lineString == null || !checkPlaneTypePattern.matcher(lineString).find())
          throw new FileParseException(file, line[0]);
        String planeType = getValueFunction.apply(lineString);

        // Plane Seats
        lineString = getNonmptyLineFunction.apply(null);
        if (lineString == null || !checkPlaneSeatsPattern.matcher(lineString).find())
          throw new FileParseException(file, line[0]);
        String planeSeats = getValueFunction.apply(lineString);
        int seats = Integer.parseInt(planeSeats);
        if (seats < 50 || seats > 600)
          throw new FileParseException(file, line[0]);

        // Plane Age
        lineString = getNonmptyLineFunction.apply(null);
        if (lineString == null || !checkPlaneAgePattern.matcher(lineString).find())
          throw new FileParseException(file, line[0]);
        String planeAge = getValueFunction.apply(lineString);
        double age = Double.parseDouble(planeAge);
        if (age < 0 || age > 30)
          throw new FileParseException(file, line[0]);

        // }
        lineString = getNonmptyLineFunction.apply(null);
        if (lineString == null || !lineString.equals("}"))
          throw new FileParseException(file, line[0]);

        // }
        lineString = getNonmptyLineFunction.apply(null);
        if (lineString == null || !lineString.equals("}"))
          throw new FileParseException(file, line[0]);

        FlightEntry entry = new FlightEntry(flight, Timeslot.parse(depatureTime, arrivalTime),
          CommonLocation.of(departureAirport, LOCATION_SHAREABLE),
          CommonLocation.of(arrivalAirport, LOCATION_SHAREABLE));
        entry.setResource(new Plane(plane, planeType, seats, age));
        entries.add(entry);
      }
    }
    return entries;
  }
}
