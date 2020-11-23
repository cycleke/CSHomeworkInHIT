package flight;

import base.CommonLocation;
import base.Location;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.*;

/**
 * This class {@link Board} is used to display a collection of plan entries.
 *
 * @author cycleke
 */
public class Board implements Iterable<FlightPlanningEntry> {

  private final static Map<String, String> I18N_CHINESE_MAP = new HashMap<>();

  static {
    I18N_CHINESE_MAP.put("ALLOCATED STATE", "即将起飞");
    I18N_CHINESE_MAP.put("CANCELLED STATE", "已取消");
    I18N_CHINESE_MAP.put("ENDED STATE", "已降落");
    I18N_CHINESE_MAP.put("RUNNING STATE", "即将降落");
    I18N_CHINESE_MAP.put("WAITING STATE", "等待分配");
  }

  private final List<FlightPlanningEntry> entries;
  private IteratorType iteratorType;
  private boolean sorted;
  private Location sortKeyLocation;
  private List<FlightPlanningEntry> sortedEntries;

  /**
   * Constructor of {@link Board}.
   */
  public Board() {
    iteratorType = IteratorType.DEPARTURE;
    entries = new ArrayList<>();

    sorted = true;
    sortKeyLocation = CommonLocation.of("", FlightPlanningEntry.LOCATION_SHAREABLE);
    checkRep();
  }

  // Abstraction function:
  // AF(s) = {entries}
  // Rep invariant:
  // All field are not null.
  // Safety from rep exposure:
  // The field are all private.

  /**
   * Constructor of {@link Board}.
   *
   * @param entries the entries to initialize, not null
   */
  public Board(List<FlightPlanningEntry> entries) {
    iteratorType = IteratorType.DEPARTURE;
    this.entries = new ArrayList<>(entries);

    sorted = false;
    sortKeyLocation = CommonLocation.of("", FlightPlanningEntry.LOCATION_SHAREABLE);
    checkRep();
  }

  protected void checkRep() {
    assert iteratorType != null;
    assert entries != null;
    assert sortKeyLocation != null;
  }

  /**
   * Change the iteratorType of the board.
   *
   * @param iteratorType the iteratorType to set, not null
   */
  public void setIteratorType(IteratorType iteratorType) {
    if (iteratorType != this.iteratorType) {
      this.iteratorType = iteratorType;
      sorted = false;
      checkRep();
    }
  }

  /**
   * Set the key location for iterator, default DEPARTURE.
   *
   * @param sortKeyLocation the sortKeyLocation to set, not null
   */
  public void setSortKeyLocation(Location sortKeyLocation) {
    this.sortKeyLocation = sortKeyLocation;
    sorted = false;
    checkRep();
  }

  /**
   * Return all the entries.
   *
   * @return the entries
   */
  public List<FlightPlanningEntry> getEntries() {
    return new ArrayList<>(entries);
  }

  /**
   * Add an entry to the board.
   *
   * @param entry the entry to add
   * @return true if there is no collision and added successfully
   */
  public boolean addEntry(FlightPlanningEntry entry) {
    for (FlightPlanningEntry entry2 : entries) {
      if (entry.conflictResourceWith(entry2) || entry.conflictLocationWith(entry2)) {
        return false;
      }
    }
    return entries.add(entry);
  }

  /**
   * Remove an entry from the board.
   *
   * @param entry the entry to remove
   * @return true if remove successfully
   */
  public boolean removeEntry(FlightPlanningEntry entry) {
    return entries.remove(entry);
  }

  @Override
  public Iterator<FlightPlanningEntry> iterator() {
    updateSortedEntries();
    return sortedEntries.iterator();
  }

  private void updateSortedEntries() {
    if (!sorted) {
      sorted = true;
      sortedEntries = new ArrayList<>();
      for (FlightPlanningEntry entry : entries) {
        switch (iteratorType) {
          case DEPARTURE:
            if (entry.getStartLocation().equals(sortKeyLocation)) {
              sortedEntries.add(entry);
            }
            break;
          case ARRIVAL:
            if (entry.getTargetLocation().equals(sortKeyLocation)) {
              sortedEntries.add(entry);
            }
            break;
          default:
            assert false;
        }
      }
      sortedEntries.sort((e1, e2) -> {
        switch (iteratorType) {
          case DEPARTURE:
          case ARRIVAL:
            return e1.getTimeslot().getStartTime().compareTo(e2.getTimeslot().getStartTime());
          default:
            assert false;
        }
        return 0;
      });
    }
  }

  /**
   * Return a {@link JTable} that contains entries from or to sortKeyLocation.
   *
   * @return a {@link JTable} contains entries from or to sortKeyLocation
   */
  public JTable generateTable() {
    IteratorType currentIteratorType = iteratorType;

    setIteratorType(IteratorType.ARRIVAL);
    updateSortedEntries();
    int subTable1Size = sortedEntries.size();
    Object[][] data1 = new Object[subTable1Size][];
    int count = 0;
    for (FlightPlanningEntry entry : sortedEntries) {
      data1[count] =
        new Object[]{entry.getTimeslot().getEndTime().format(DateTimeFormatter.ISO_LOCAL_TIME), entry.getName(),
          String.format("%s-%s", entry.getStartLocation().getName(), entry.getTargetLocation().getName()),
          I18N_CHINESE_MAP.get(entry.getState().toString())};
      ++count;
    }

    setIteratorType(IteratorType.DEPARTURE);
    updateSortedEntries();
    int subTable2Size = sortedEntries.size();
    Object[][] data2 = new Object[subTable2Size][];
    count = 0;
    for (FlightPlanningEntry entry : sortedEntries) {
      data2[count] =
        new Object[]{entry.getTimeslot().getStartTime().format(DateTimeFormatter.ISO_LOCAL_TIME), entry.getName(),
          String.format("%s-%s", entry.getStartLocation().getName(), entry.getTargetLocation().getName()),
          I18N_CHINESE_MAP.get(entry.getState().toString())};
      ++count;
    }

    setIteratorType(currentIteratorType);
    return new BoardTable(data1, data2, sortKeyLocation);
  }

  /**
   * Display the board in GUI.
   */
  public void visualize() {
    JFrame frame = new JFrame("Board Visualize");
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    JScrollPane scrollPanel = new JScrollPane(generateTable());
    frame.getContentPane().add(scrollPanel, BorderLayout.CENTER);
    frame.pack();
    frame.setVisible(true);
  }

  public enum IteratorType {
    /**
     * Sort by the time to depart
     */
    DEPARTURE,
    /**
     * Sort by the time to arrive
     */
    ARRIVAL
  }

}

