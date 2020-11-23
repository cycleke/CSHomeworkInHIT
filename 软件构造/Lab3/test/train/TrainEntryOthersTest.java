package train;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import base.CommonLocation;
import base.IllegalStateTransitionException;
import base.Timeslot;
import train.TrainCabin.TrainCabinType;

/**
 * This class {@link TrainEntryOthersTest} tests other
 */
public class TrainEntryOthersTest {
  public TrainEntry singleton() {
    TrainEntry entry = new TrainEntry("G1020",
      Arrays.asList(Timeslot.parse("2020-02-24 07:01", "2020-02-24 08:00"),
        Timeslot.parse("2020-02-24 08:03", "2020-02-24 08:45")),
      Arrays.asList(CommonLocation.of("Mudanjiang", TrainEntry.LOCATION_SHAREABLE),
        CommonLocation.of("Harbin", TrainEntry.LOCATION_SHAREABLE),
        CommonLocation.of("Beijing", TrainEntry.LOCATION_SHAREABLE)));
    return entry;
  }

  /**
   * Tests {@link TrainEntry#TrainEntry(String, java.util.List, java.util.List)}
   */
  @Test
  public void testConstructor() {
    TrainPlanningEntry entry = singleton();
    assertNotNull(entry);
    assertTrue(entry.getState() instanceof WaitingState);
  }

  /**
   * Tests {@link TrainEntry#allocate()}
   *
   * @throws IllegalStateTransitionException
   */
  @Test
  public void testAllocate() throws IllegalStateTransitionException {
    TrainEntry entry = singleton();
    entry.setResources(Arrays.asList(new TrainCabin(1, TrainCabinType.BUSINESS, 10, 2020),
      new TrainCabin(2, TrainCabinType.FIRST_CLASS, 20, 2020)));
    entry.allocated();
    assertTrue(entry.getState() instanceof AllocatedState);
  }

  /**
   * Tests {@link TrainEntry#run()}
   *
   * @throws IllegalStateTransitionException
   */
  @Test
  public void testRun() throws IllegalStateTransitionException {
    TrainEntry entry = singleton();
    entry.setResources(Arrays.asList(new TrainCabin(1, TrainCabinType.BUSINESS, 10, 2020),
      new TrainCabin(2, TrainCabinType.FIRST_CLASS, 20, 2020)));
    entry.allocated();
    entry.run();
    assertTrue(entry.getState() instanceof RunningState);
  }

  /**
   * Tests {@link TrainEntry#block()}
   *
   * @throws IllegalStateTransitionException
   */
  @Test
  public void testBlock() throws IllegalStateTransitionException {
    TrainEntry entry = singleton();
    entry.setResources(Arrays.asList(new TrainCabin(1, TrainCabinType.BUSINESS, 10, 2020),
      new TrainCabin(2, TrainCabinType.FIRST_CLASS, 20, 2020)));
    entry.allocated();
    entry.run();
    entry.block();
    assertTrue(entry.getState() instanceof BlockedState);
  }

  /**
   * Tests {@link TrainEntry#cancel()}
   *
   * <p>
   * Testing strategy
   * </p>
   *
   * Partition the inputs as follows:
   * <q>path to cancelled state: WAITING -> CANCELLED, WAITING -> ALLOCATED -> CANCELLED, WAITING -> ALLOCATED ->
   * RUNNING -> BLOCKED -> CANCELLED <\q>
   */

  /**
   * Covers:
   * <q>path: WAITING -> ALLOCATED -> CANCELLED<\q>
   *
   * @throws IllegalStateTransitionException
   */
  @Test
  public void testCancel_1() throws IllegalStateTransitionException {
    TrainPlanningEntry entry = singleton();
    entry.cancel();
    assertTrue(entry.getState() instanceof CancelledState);
  }

  /**
   * Covers:
   * <q>path: WAITING -> ALLOCATED -> CANCELLED<\q>
   *
   * @throws IllegalStateTransitionException
   */
  @Test
  public void testCancel_2() throws IllegalStateTransitionException {
    TrainPlanningEntry entry = singleton();
    entry.setResources(Arrays.asList(new TrainCabin(1, TrainCabinType.BUSINESS, 10, 2020),
      new TrainCabin(2, TrainCabinType.FIRST_CLASS, 20, 2020)));
    entry.allocated();
    entry.cancel();
    assertTrue(entry.getState() instanceof CancelledState);
  }

  /**
   * Covers:
   * <q>path: WAITING -> ALLOCATED -> RUNNING -> BLOCKED -> CANCELLED<\q>
   *
   * @throws IllegalStateTransitionException
   */
  @Test
  public void testCancel_3() throws IllegalStateTransitionException {
    TrainPlanningEntry entry = singleton();
    entry.setResources(Arrays.asList(new TrainCabin(1, TrainCabinType.BUSINESS, 10, 2020),
      new TrainCabin(2, TrainCabinType.FIRST_CLASS, 20, 2020)));
    entry.allocated();
    entry.run();
    entry.block();
    entry.cancel();
    assertTrue(entry.getState() instanceof CancelledState);
  }

  /**
   * Tests {@link TrainEntry#end()}
   *
   * @throws IllegalStateTransitionException
   */
  @Test
  public void testEnd() throws IllegalStateTransitionException {
    TrainPlanningEntry entry = singleton();
    entry.setResources(Arrays.asList(new TrainCabin(1, TrainCabinType.BUSINESS, 10, 2020),
      new TrainCabin(2, TrainCabinType.FIRST_CLASS, 20, 2020)));
    entry.allocated();
    entry.run();
    entry.block();
    entry.run();
    entry.end();
    assertTrue(entry.getState() instanceof EndedState);
  }

}
