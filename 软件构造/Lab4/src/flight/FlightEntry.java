package flight;

import base.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * This class {@link FlightEntry} implies the basic functions of
 * {@link FlightPlanningEntry}.
 *
 * @author cycleke
 */
public class FlightEntry extends AbstractCommonPlanningEntry<Plane> implements FlightPlanningEntry {
  public static final boolean LOCATION_SHAREABLE = true;
  public static final boolean RESOURCE_SHAREABLE = false;

  private static final Pattern DATE_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
  private static final Pattern FLIGHT_NUMBER_PATTERN = Pattern.compile("^[A-Z]{2}\\d{2,4}$");
  private static final Pattern FLIGHT_NUMBER_CASE_INSENSITIVE_PATTERN = Pattern.compile("^[A-Za-z]\\d{2,4}$");
  private static final Pattern DATE_TIME_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}$");
  private static final Pattern PLANE_PATTERN = Pattern.compile("[NB]\\d{4}");
  private static final Pattern PLANE_CASE_INSENSITIVE_PATTERN = Pattern.compile("[nd]\\d{4}");
  private final static Pattern PLANE_TYPE_PATTERN = Pattern.compile("\\w+");
  private static final Pattern PLANE_AGE_PATTERN = Pattern.compile("^(0|0\\.0|[1-9]?\\d(.\\d)?)$");
  private static final double PLANE_AGE_LOWER_BOUND = 0;
  private static final double PLANE_AGE_UPPER_BOUND = 30;
  private static final int PLANE_SEATS_LOWER_BOUND = 50;
  private static final int PLANE_SEATS_UPPER_BOUND = 600;
  private static final int SECONDS_PER_DAY = 24 * 60 * 60;

  // Abstraction function:
  // AF(e) = {timeslot, startLocation, targetLocation, plane}
  // Rep invariant:
  // All field is not null, the locations are shareable, the start location and
  // target location are different.
  // Safety from rep exposure:
  // the field are all private.

  private final PairLocationsEntry pairLocationsEntryImpl;
  private final SingleResourceEntry<Plane> singleResourceEntryImpl;
  private final SingleTimeslotEntry singleTimeslotEntryImpl;

  /**
   * Constructor of {@link FlightEntry}.
   *
   * @param name           the name of the flight plan, not empty
   * @param timeslot       the time of the flight plan, not null
   * @param startLocation  the start location of the plan, not null and is
   *                       shareable
   * @param targetLocation the target location of the plan, not null and is
   *                       shareable
   */
  public FlightEntry(String name, Timeslot timeslot, Location startLocation, Location targetLocation) {
    this.name = name;
    state = new WaitingState(this);

    pairLocationsEntryImpl = new PairLocationsEntryUnmodifiableDecorator(
      new PairLocationsEntryImpl(startLocation, targetLocation));
    singleTimeslotEntryImpl = new SingleTimeslotEntryImpl(timeslot);
    singleResourceEntryImpl = new SingleResourceEntryImpl<>();
    checkRep();
  }

  /**
   * Parse a number of entries from a file.
   *
   * @param file the file to parse
   * @throws FileParseException    if the file is illegal.
   * @throws FileNotFoundException if the file not exist.
   */
  public static List<FlightEntry> parse(File file) throws FileParseException, FileNotFoundException {
    List<FlightEntry> entries = new ArrayList<>();
    Map<String, Integer> dateFlightNumberLineMap = new HashMap<>();
    Map<String, Plane> planeMap = new HashMap<>();
    Map<String, Integer> planeLineMap = new HashMap<>();

    try (Scanner scanner = new Scanner(file)) {
      int[] line = {0};
      while (scanner.hasNext()) {
        // Flight
        String lineString = getNonemptyLine(scanner, file, line);
        int flightLine = line[0];
        List<String> flightValues = parseFlight(lineString, file, line[0]);
        String date = flightValues.get(0), number = flightValues.get(1);

        String dateFlightNumberPair = date + "," + number;
        if (dateFlightNumberLineMap.containsKey(dateFlightNumberPair)) {
          throw new SameElementException(file, dateFlightNumberLineMap.get(dateFlightNumberPair), flightLine);
        }
        dateFlightNumberLineMap.put(dateFlightNumberPair, flightLine);

        // {
        lineString = getNonemptyLine(scanner, file, line);
        parseBracket(lineString, file, line[0], '{');

        // DepartureAirport
        lineString = getNonemptyLine(scanner, file, line);
        String departureAirport = parseAirport(lineString, file, line[0], "Departure");

        // ArrivalAirport
        lineString = getNonemptyLine(scanner, file, line);
        String arrivalAirport = parseAirport(lineString, file, line[0], "Arrival");

        // DepartureTime
        lineString = getNonemptyLine(scanner, file, line);
        String departureTime;
        try {
          departureTime = parseDateTime(lineString, file, line[0], "Departure");
        } catch (MismatchedKeyException e) {
          // 为了兼容Lab3的拼写错误
          departureTime = parseDateTime(lineString, file, line[0], "Depature");
        }

        if (!date.equals(departureTime.substring(0, 10))) {
          // 第一行出现的航班日期与内部出现的起飞时间中的日期不一致
          throw new MismatchedDateException(file, line[0]);
        }

        // ArrivalTime
        lineString = getNonemptyLine(scanner, file, line);
        String arrivalTime = parseDateTime(lineString, file, line[0], "Arrival");

        Timeslot timeslot;
        try {
          timeslot = Timeslot.parse(departureTime, arrivalTime);
        } catch (AssertionError e) {
          // 出发时间 >= 降落时间，利用 {@link Timeslot#checkRep()}
          throw new DepartureTimeIsNotSmallerThanArrivalTimeException(file, line[0]);
        }
        Duration duration = Duration.between(timeslot.getStartTime(), timeslot.getEndTime());
        if (duration.getSeconds() > SECONDS_PER_DAY) {
          // 降落时间中的日期与航班日期差距大于1天
          throw new TimeGapTooLargeException(file, line[0]);
        }

        // Plane
        lineString = getNonemptyLine(scanner, file, line);
        int planeLine = line[0];
        String planeNumber = parsePlaneNumber(lineString, file, line[0]);

        // {
        lineString = getNonemptyLine(scanner, file, line);
        parseBracket(lineString, file, line[0], '{');

        // Plane Type
        lineString = getNonemptyLine(scanner, file, line);
        String planeType = parsePlaneType(lineString, file, line[0]);

        // Plane Seats
        lineString = getNonemptyLine(scanner, file, line);
        int seats = parsePlaneSeats(lineString, file, line[0]);

        // Plane Age
        lineString = getNonemptyLine(scanner, file, line);
        double age = parsePlaneAge(lineString, file, line[0]);

        // }
        lineString = getNonemptyLine(scanner, file, line);
        parseBracket(lineString, file, line[0], '}');

        // }
        lineString = getNonemptyLine(scanner, file, line);
        parseBracket(lineString, file, line[0], '}');

        Plane plane = new Plane(planeNumber, planeType, seats, age);
        if (planeMap.containsKey(planeNumber)) {
          Plane anotherPlane = planeMap.get(planeNumber);
          if (!plane.equals(anotherPlane)) {
            int anotherPlaneLine = planeLineMap.get(planeNumber);
            throw new PlaneAttributeConflictException(file, planeLine, anotherPlaneLine);
          }
        } else {
          planeMap.put(planeNumber, plane);
          planeLineMap.put(planeNumber, planeLine);
        }

        FlightEntry entry = new FlightEntry(number, timeslot, CommonLocation.of(departureAirport, LOCATION_SHAREABLE),
          CommonLocation.of(arrivalAirport, LOCATION_SHAREABLE));
        entry.setResource(plane);
        entries.add(entry);
      }
    }
    return entries;
  }

  private static double parsePlaneAge(String lineString, File file, int line) throws FileParseException {
    List<String> valueTokens = parseValueTokens(lineString, file, line, "Age");
    if (valueTokens.size() != 1) {
      throw new WrongNumberOfValuesException(file, line, 1, valueTokens.size());
    }
    String token = valueTokens.get(0);
    if (!PLANE_AGE_PATTERN.matcher(token).find()) {
      throw new ValueFormatException(file, line, "Reg: " + PLANE_AGE_PATTERN.pattern());
    }
    double age = Double.parseDouble(token);
    if (age < PLANE_AGE_LOWER_BOUND || age > PLANE_AGE_UPPER_BOUND) {
      throw new NumberOutOfRangeException(file, line, PLANE_AGE_LOWER_BOUND, PLANE_AGE_UPPER_BOUND);
    }
    return age;
  }

  private static int parsePlaneSeats(String lineString, File file, int line) throws FileParseException {
    List<String> valueTokens = parseValueTokens(lineString, file, line, "Seats");
    if (valueTokens.size() != 1) {
      throw new WrongNumberOfValuesException(file, line, 1, valueTokens.size());
    }
    String token = valueTokens.get(0);
    int seats;
    try {
      seats = Integer.parseInt(token);
    } catch (NumberFormatException e) {
      throw new ValueFormatException(file, line, "Positive Integer");
    }
    if (seats < PLANE_SEATS_LOWER_BOUND || seats > PLANE_SEATS_UPPER_BOUND) {
      throw new NumberOutOfRangeException(file, line, PLANE_SEATS_LOWER_BOUND, PLANE_SEATS_UPPER_BOUND);
    }
    return seats;
  }

  private static String parsePlaneType(String lineString, File file, int line) throws FileParseException {
    List<String> valueTokens = parseValueTokens(lineString, file, line, "Type");
    if (valueTokens.size() != 1) {
      throw new WrongNumberOfValuesException(file, line, 1, valueTokens.size());
    }
    String token = valueTokens.get(0);
    if (PLANE_TYPE_PATTERN.matcher(token).find()) {
      return token;
    }
    throw new ValueFormatException(file, line, "Reg: " + PLANE_TYPE_PATTERN.pattern());
  }

  private static String parsePlaneNumber(String lineString, File file, int line) throws FileParseException {
    List<String> valueTokens = parseValueTokens(lineString, file, line, "Plane");
    if (valueTokens.size() != 1) {
      throw new WrongNumberOfValuesException(file, line, 1, valueTokens.size());
    }
    String token = valueTokens.get(0);
    if (PLANE_PATTERN.matcher(token).find()) {
      return token;
    }
    throw PLANE_CASE_INSENSITIVE_PATTERN.matcher(token).find()
      ? new CaseNotMatchException(file, line, CaseNotMatchException.UPPER_CASE)
      : new ValueFormatException(file, line, "Reg: " + PLANE_PATTERN.pattern());
  }

  private static String parseDateTime(String lineString, File file, int line, String key) throws FileParseException {
    List<String> valueTokens = parseValueTokens(lineString, file, line, key + "Time");
    if (valueTokens.size() != 1) {
      throw new WrongNumberOfValuesException(file, line, 1, valueTokens.size());
    }
    String token = valueTokens.get(0);
    if (DATE_TIME_PATTERN.matcher(token).find()) {
      return token;
    } else {
      throw new DateTimeFormatException(file, line);
    }
  }

  private static String parseAirport(String lineString, File file, int line, String key) throws FileParseException {
    List<String> valueTokens = parseValueTokens(lineString, file, line, key + "Airport");
    if (valueTokens.size() != 1) {
      throw new WrongNumberOfValuesException(file, line, 1, valueTokens.size());
    }
    String token = valueTokens.get(0);
    if (token.isEmpty()) {
      throw new ValueNotFoundException(file, line, key + "Airport");
    }
    return token;
  }

  private static String getNonemptyLine(Scanner scanner, File file, int[] line) throws FileParseException {
    while (scanner.hasNext()) {
      ++line[0];
      String trimmedString = scanner.nextLine().trim();
      if (!trimmedString.isEmpty())
        return trimmedString;
    }
    throw new FileParseException(file, line[0]);
  }

  private static List<String> parseValueTokens(String lineString, File file, int line, String key)
    throws FileParseException {
    String[] tokens = lineString.split(":", 2);

    if (tokens.length != 2) {
      throw new FileParseException(file, line);
    }
    tokens[0] = tokens[0].trim();
    if (tokens[0].equals(key) && tokens[1].isEmpty()) {
      throw new ValueNotFoundException(file, line, key);
    } else {
      if (!tokens[0].equals(key)) {
        if (tokens[0].toLowerCase().equals(key.toLowerCase())) {
          throw new CaseNotMatchException(file, line, CaseNotMatchException.CAPITALIZE);
        }
        throw new MismatchedKeyException(file, line, key);
      }
      List<String> valueTokens = Arrays.stream(tokens[1].split(",")).map(String::trim).collect(Collectors.toList());
      if (valueTokens.isEmpty()) {
        throw new ValueNotFoundException(file, line, key);
      }
      return valueTokens;
    }

  }

  private static List<String> parseFlight(String lineString, File file, int line) throws FileParseException {
    List<String> valueTokens = parseValueTokens(lineString, file, line, "Flight");
    if (valueTokens.size() != 2) {
      throw new WrongNumberOfValuesException(file, line, 2, valueTokens.size());
    }

    String token0 = valueTokens.get(0), token1 = valueTokens.get(1);
    if (DATE_PATTERN.matcher(token0).find()) {
      if (FLIGHT_NUMBER_PATTERN.matcher(token1).find()) {
        int neededZeroCount = 6 - token1.length();
        if (neededZeroCount > 0) {
          valueTokens.set(1,
            token1.substring(0, 2) + new String(new char[neededZeroCount]).replace('\0', '0') + token1.substring(2));
        }
        return valueTokens;
      } else {
        throw FLIGHT_NUMBER_CASE_INSENSITIVE_PATTERN.matcher(token1).find()
          ? new CaseNotMatchException(file, line, CaseNotMatchException.UPPER_CASE)
          : new ValueFormatException(file, line, "Reg: " + FLIGHT_NUMBER_PATTERN.pattern());
      }
    } else if (FLIGHT_NUMBER_PATTERN.matcher(token0).find() && DATE_PATTERN.matcher(token1).find()) {
      throw new ValuesOutOfOrderException(file, line);
    }

    throw new DateFormatException(file, line);
  }

  private static void parseBracket(String lineString, File file, int line, char c)
    throws LeftBracketNotFoundException, RightBracketNotFoundException {
    switch (c) {
      case '{':
        if ("{".equals(lineString)) {
          return;
        } else {
          throw new LeftBracketNotFoundException(file, line);
        }
      case '}':
        if ("}".equals(lineString)) {
          return;
        } else {
          throw new RightBracketNotFoundException(file, line);
        }
      default:
        assert false;
    }
  }

  @Override
  protected void checkRep() {
    super.checkRep();
    assert pairLocationsEntryImpl != null;
    assert singleResourceEntryImpl != null;
    assert singleTimeslotEntryImpl != null;
    assert !getStartLocation().equals(getTargetLocation());
    for (Location location : pairLocationsEntryImpl.getLocations()) {
      assert location.isShareable();
    }
  }

  @Override
  public Location getStartLocation() {
    return pairLocationsEntryImpl.getStartLocation();
  }

  @Override
  public void setStartLocation(Location startLocation) {
    pairLocationsEntryImpl.setStartLocation(startLocation);
    checkRep();
  }

  @Override
  public Location getTargetLocation() {
    return pairLocationsEntryImpl.getTargetLocation();
  }

  @Override
  public void setTargetLocation(Location targetLocation) {
    pairLocationsEntryImpl.setTargetLocation(targetLocation);
    checkRep();
  }

  @Override
  public List<Location> getLocations() {
    return pairLocationsEntryImpl.getLocations();
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
  public Timeslot getTimeslot() {
    return singleTimeslotEntryImpl.getTimeslot();
  }

  @Override
  public void setTimeslot(Timeslot timeslot) {
    singleTimeslotEntryImpl.setTimeslot(timeslot);
    checkRep();
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
    if (!(entry instanceof FlightPlanningEntry)) {
      return false;
    }
    if (singleResourceEntryImpl.getResource() == null) {
      return false;
    }
    FlightPlanningEntry anotherEntry = (FlightPlanningEntry) entry;
    Timeslot anotherTimeslot = anotherEntry.getTimeslot();
    if (!getTimeslot().intersect(anotherTimeslot)) {
      return false;
    }
    return getResource().equals(anotherEntry.getResource());
  }

  @Override
  public String toString() {
    return getName() + " " + getTimeslot().getStartTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + " - "
      + getTimeslot().getEndTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + " "
      + String.format("%s - %s", getStartLocation().getName(), getTargetLocation().getName());
  }
}
