package base;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class {@link Timeslot} is an immutable class to represent a entry containing to time stamp : start time and end
 * time.
 *
 * @author cycleke
 * @implSpec This class is immutable.
 */
public class Timeslot {
  private static final int TIMESLOT_FORMATTER_LENGTH = 16;
  private static final DateTimeFormatter TIMESLOT_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
  private final LocalDateTime startTime, endTime;

  // Abstraction function:
  // AF(r) = {startTime, endTime}
  // Rep invariant:
  // startTime < endTime
  // Safety from rep exposure:
  // The field are all private and final. There are only getters, no setters.

  /**
   * Constructor of {@link Timeslot}. The startTime is less than endTime.
   *
   * @param startTime the start time of the timeslot
   * @param endTime   the end time of the timeslot
   */
  public Timeslot(LocalDateTime startTime, LocalDateTime endTime) {
    this.startTime = startTime;
    this.endTime = endTime;
    checkRep();
  }

  /**
   * Obtains an instance of {@link Timeslot} from two {@link LocalDateTime}s. The startTime shouldn't be greater than
   * endTime.
   *
   * @param startTime the start time of the timeslot, not null
   * @param endTime   the end time of the timeslot, not null
   */
  public static Timeslot of(LocalDateTime startTime, LocalDateTime endTime) {
    return new Timeslot(startTime, endTime);
  }

  /**
   * Obtains an instance of {@link Timeslot} from two {@link CharSequence}s. The format of the {@link CharSequence} must
   * be "yyyy-MM-dd HH:mm", such as "2020-03-01 14:00".The startTime shouldn't be greater than endTime.
   *
   * @param startText the text for the start time of the timeslot to parse such as "2020-03-01 14:00", not null
   * @param endText   the text for the end time of the timeslot to parse such as "2020-03-01 14:00", not null
   * @throws DateTimeParseException if the text cannot be parsed
   */
  public static Timeslot parse(String startText, String endText) throws DateTimeParseException {
    return new Timeslot(LocalDateTime.parse(startText.replace(' ', 'T'), TIMESLOT_FORMATTER),
      LocalDateTime.parse(endText.replace(' ', 'T'), TIMESLOT_FORMATTER));
  }

  private void checkRep() {
    assert endTime != null;
    assert startTime.compareTo(endTime) < 0;
  }

  /**
   * Return the start time of the timeslot.
   *
   * @return the start time of the timeslot
   */
  public LocalDateTime getStartTime() {
    return startTime;
  }

  /**
   * Return the end time of the timeslot.
   *
   * @return the end time of the timeslot
   */
  public LocalDateTime getEndTime() {
    return endTime;
  }

  /**
   * Check whether the timeslot intersect with another timeslot.
   *
   * @return true if there is intersection
   */
  public boolean intersect(Timeslot anotherTimeslot) {
    if (equals(anotherTimeslot)) {
      return true;
    }

    LocalDateTime anotherStartTime = anotherTimeslot.getStartTime();
    LocalDateTime anotherEndTime = anotherTimeslot.getEndTime();
    if (startTime.compareTo(anotherStartTime) >= 0 && startTime.compareTo(anotherEndTime) < 0) {
      return true;
    }
    if (endTime.compareTo(anotherStartTime) > 0 && endTime.compareTo(anotherEndTime) <= 0) {
      return true;
    }
    if (startTime.compareTo(anotherEndTime) >= 0) {
      return false;
    }
    return endTime.compareTo(anotherStartTime) > 0;
  }

  @Override
  public String toString() {
    return String.format("(%s, %s)",
      startTime.format(TIMESLOT_FORMATTER).replace('T', ' ').substring(0, TIMESLOT_FORMATTER_LENGTH),
      endTime.format(TIMESLOT_FORMATTER).replace('T', ' ').substring(0, TIMESLOT_FORMATTER_LENGTH));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + startTime.hashCode();
    result = prime * result + endTime.hashCode();
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Timeslot other = (Timeslot) obj;
    if (!startTime.equals(other.startTime)) {
      return false;
    } else {
      return endTime.equals(other.endTime);
    }
  }

}
