package base;

import org.apache.log4j.Logger;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class LogManagerTest {
  private final static Logger logger = Logger.getRootLogger();
  private final static LogManager logManager = LogManager.LOG_MANAGER;

  /*
   * Test {@link LogManager#setStartTime(LocalDateTime)}. <p> Testing strategy:
   * <p> Partition the inputs as follows: <q>startTime: null, not null</q> <q>log
   * compare to startTime: <, >= </q>
   */

  /**
   * Covers:
   * <q>startTime: null, not null</q>
   * <q>log compare to startTime: <, >=</q>
   */
  @Test
  public void testSetStartTime() throws InterruptedException {
    String message1 = "Message for testSetStartTime#1";
    String message2 = "Message for testSetStartTime#2";
    logger.info(message1);
    Thread.sleep(100);
    LocalDateTime now = LocalDateTime.now();
    Thread.sleep(100);
    logger.info(message2);

    logManager.setEndTime(null);
    logManager.setMethod(null);
    logManager.setEntry(null);

    logManager.setStartTime(null);
    assertTrue(Arrays.stream(logManager.getFilteredLogs()).anyMatch(arr -> arr[3].equals(message1)));
    assertTrue(Arrays.stream(logManager.getFilteredLogs()).anyMatch(arr -> arr[3].equals(message2)));

    logManager.setStartTime(now);
    assertTrue(Arrays.stream(logManager.getFilteredLogs()).noneMatch(arr -> arr[3].equals(message1)));
    assertTrue(Arrays.stream(logManager.getFilteredLogs()).anyMatch(arr -> arr[3].equals(message2)));
  }

  /*
   * Test {@link LogManager#setEndTime(LocalDateTime)}. <p> Testing strategy: <p>
   * Partition the inputs as follows: <q>endTime: null, not null</q> <q>log
   * compare to endTime: <=, > </q>
   */

  /**
   * Covers:
   * <q>endTime: null, not null</q>
   * <q>log compare to endTime: <=, ></q>
   */
  @Test
  public void testSetEndTime() throws InterruptedException {
    String message1 = "Message for testSetEndTime#1";
    String message2 = "Message for testSetEndTime#2";
    logger.info(message1);
    Thread.sleep(100);
    LocalDateTime now = LocalDateTime.now();
    Thread.sleep(100);
    logger.info(message2);

    logManager.setStartTime(null);
    logManager.setMethod(null);
    logManager.setEntry(null);

    logManager.setEndTime(null);
    assertTrue(Arrays.stream(logManager.getFilteredLogs()).anyMatch(arr -> arr[3].equals(message1)));
    assertTrue(Arrays.stream(logManager.getFilteredLogs()).anyMatch(arr -> arr[3].equals(message2)));

    logManager.setEndTime(now);
    assertTrue(Arrays.stream(logManager.getFilteredLogs()).anyMatch(arr -> arr[3].equals(message1)));
    assertTrue(Arrays.stream(logManager.getFilteredLogs()).noneMatch(arr -> arr[3].equals(message2)));
  }

  /*
   * Test {@link LogManager#setMethod(String)}. <p> Testing strategy: <p>
   * Partition the inputs as follows: <q>method: null, not null</q> <q>equals
   * method: true, false</q>
   */

  /**
   * Covers:
   * <q>method: null, not null</q>
   * <q>equals method: true, false</q>
   */
  @Test
  public void testSetMethod() {
    String message = "Message for testSetMethod";
    logger.info(message);

    logManager.setStartTime(null);
    logManager.setEndTime(null);
    logManager.setEntry(null);

    logManager.setMethod(null);
    assertTrue(Arrays.stream(logManager.getFilteredLogs()).anyMatch(arr -> arr[3].equals(message)));

    logManager.setMethod("testSetMethod");
    assertTrue(Arrays.stream(logManager.getFilteredLogs()).anyMatch(arr -> arr[3].equals(message)));

    logManager.setMethod("main");
    assertTrue(Arrays.stream(logManager.getFilteredLogs()).noneMatch(
      arr -> arr[3].equals(message)));
  }

  /*
   * Test {@link LogManager#setEntry(String)}. <p> Testing strategy: <p> Partition
   * the inputs as follows: <q>entry: null, not null</q> <q>equals entry: true,
   * false</q>
   */

  /**
   * Covers:
   * <q>entry: null, not null</q>
   * <q>equals entry: true, false</q>
   */
  @Test
  public void testSetEntry() {
    String entry = "TestEntry[test]";
    String message = "Message for testEntry#" + entry;
    logger.info(message);

    logManager.setStartTime(null);
    logManager.setEndTime(null);
    logManager.setMethod(null);

    logManager.setEntry(null);
    assertTrue(Arrays.stream(logManager.getFilteredLogs()).anyMatch(arr -> arr[3].equals(message)));

    logManager.setEntry(entry);
    assertTrue(Arrays.stream(logManager.getFilteredLogs()).anyMatch(arr -> arr[3].equals(message)));

    logManager.setEntry("TestEntry[###############]");
    assertTrue(Arrays.stream(logManager.getFilteredLogs()).noneMatch(arr -> arr[3].equals(message)));
  }

  @Test
  public void testRequiresLayout() {
    assertTrue(logManager.requiresLayout());
  }

  @Test
  public void testClose() throws Exception {
    logManager.close();
  }
}
