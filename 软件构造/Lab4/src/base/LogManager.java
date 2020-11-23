package base;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class {@link LogManager} is used to manager the logs in client.
 *
 * @author cycleke
 */
public class LogManager extends AppenderSkeleton {
  public final static LogManager LOG_MANAGER = new LogManager();
  private final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("(yyyy-MM-dd HH:mm:ss");

  private static final List<LogRecord> LOG_RECORDS = new LinkedList<>();
  private LocalDateTime startTime = null, endTime = null;
  private String method = null, entry = null;

  // Abstraction function:
  // AF(mgr) = {logs, filters}
  // Rep invariant:
  // true
  // Safety from rep exposure:
  // The field are all private.

  public LogManager() {
  }

  @Override
  protected void append(LoggingEvent event) {
    LogRecord record = new LogRecord(event.getTimeStamp(), layout.format(event));
    synchronized (LOG_RECORDS) {
      LOG_RECORDS.add(record);
    }
  }

  private boolean matchFilter(LogRecord log) {
    if (startTime != null && log.getTime().compareTo(startTime) < 0) {
      return false;
    }
    if (endTime != null && log.getTime().compareTo(endTime) > 0) {
      return false;
    }
    if (method != null && !log.getMethod().equals(method)) {
      return false;
    }
    return entry == null || log.getMessage().contains(entry);
  }

  /**
   * Set the start time filter param.
   *
   * @param startTime the start time filter param, no limit if it's null
   */
  public void setStartTime(LocalDateTime startTime) {
    this.startTime = startTime;
  }

  /**
   * Set the end time filter param.
   *
   * @param endTime the end time filter param, no limit if it's null
   */
  public void setEndTime(LocalDateTime endTime) {
    this.endTime = endTime;
  }

  /**
   * Set the method filter param.
   *
   * @param method the method param, no limit if it's null
   */
  public void setMethod(String method) {
    this.method = method;
  }

  /**
   * Set the entry filter param.
   *
   * @param entry the entry param, no limit if it's null
   */
  public void setEntry(String entry) {
    this.entry = entry;
  }

  /**
   * Get the filtered logs
   *
   * @return the filtered logs
   */
  public Object[][] getFilteredLogs() {
    synchronized (LOG_RECORDS) {
      return LOG_RECORDS.stream().filter(this::matchFilter).map(r -> {
        Object time = r.getTime().format(DATE_TIME_FORMATTER);
        return new Object[]{time, r.getLevel(), r.getPath(), r.getMessage()};
      }).toArray(Object[][]::new);
    }
  }

  @Override
  public void close() {
  }

  @Override
  public boolean requiresLayout() {
    return true;
  }
}

class LogRecord {
  private final static Pattern LEVEL_PATTERN = Pattern.compile("\\[ Level:\\w* ]");
  private final static Pattern METHOD_PATTERN = Pattern.compile("\\[ Method:[\\w<>$]* ]");
  private final static Pattern PATH_PATTERN = Pattern.compile("\\[ Path:[\\d\\w()<>$.:]* ]");
  private final static Pattern MESSAGE_PATTERN = Pattern.compile("\\[ Message:.* ]");

  private final LocalDateTime time;
  private final String level, method, path, message;

  public LogRecord(long timestamp, String log) {
    time = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), TimeZone.getDefault().toZoneId());

    Matcher levelMatcher = LEVEL_PATTERN.matcher(log);
    boolean found = levelMatcher.find();
    assert found;
    level = levelMatcher.group().replaceFirst("\\[ Level:", "").replaceFirst(" ]$", "");

    Matcher methodMatcher = METHOD_PATTERN.matcher(log);
    found = methodMatcher.find();
    assert found;
    method = methodMatcher.group().replaceFirst("\\[ Method:", "").replaceFirst(" ]$", "");

    Matcher pathMatcher = PATH_PATTERN.matcher(log);
    found = pathMatcher.find();
    assert found;
    path = pathMatcher.group().replaceFirst("\\[ Path:", "").replaceFirst(" ]$", "");

    Matcher messageMatcher = MESSAGE_PATTERN.matcher(log);
    found = messageMatcher.find();
    assert found;
    message = messageMatcher.group().replaceFirst("\\[ Message:", "").replaceFirst(" ]$", "");
  }

  public LocalDateTime getTime() {
    return time;
  }

  public String getMethod() {
    return method;
  }

  public String getMessage() {
    return message;
  }

  public String getLevel() {
    return level;
  }

  public String getPath() {
    return path;
  }
}
