package course;

import base.CommonLocation;
import base.Location;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_TIME;

/**
 * This class {@link Board} is used to display a collection of plan entries.
 *
 * @author cycleke
 */
public class Board implements Iterable<CoursePlanningEntry> {

  private final static Map<String, String> I18N_CHINESE_MAP = new HashMap<>();

  static {
    I18N_CHINESE_MAP.put("ALLOCATED STATE", "待上课");
    I18N_CHINESE_MAP.put("CANCELLED STATE", "已取消");
    I18N_CHINESE_MAP.put("ENDED STATE", "下课");
    I18N_CHINESE_MAP.put("RUNNING STATE", "上课中");
    I18N_CHINESE_MAP.put("WAITING STATE", "计划");
  }

  private final List<CoursePlanningEntry> entries;
  private boolean sorted;
  private Location sortKeyLocation;
  private List<CoursePlanningEntry> sortedEntries;

  // Abstraction function:
  // AF(s) = {entries}
  // Rep invariant:
  // All field are not null.
  // Safety from rep exposure:
  // The field are all private.

  /**
   * Constructor of {@link Board}.
   */
  public Board() {
    entries = new ArrayList<>();

    sorted = false;
    sortKeyLocation = CommonLocation.of("", CoursePlanningEntry.LOCATION_SHAREABLE);
    checkRep();
  }

  /**
   * Constructor of {@link Board}.
   *
   * @param entries the entries to initialize, not null
   */
  public Board(List<CoursePlanningEntry> entries) {
    this.entries = new ArrayList<>(entries);

    sorted = false;
    sortKeyLocation = CommonLocation.of("", CoursePlanningEntry.LOCATION_SHAREABLE);
    checkRep();
  }

  protected void checkRep() {
    assert entries != null;
    assert sortKeyLocation != null;
  }

  /**
   * Set the key location for iterator, default DEPARTURE.
   *
   * @param sortKeyLocation the sortKeyLocation to set, not null
   */
  public void setSortKeyLocation(Location sortKeyLocation) {
    if (this.sortKeyLocation.equals(sortKeyLocation)) {
      return;
    }
    this.sortKeyLocation = sortKeyLocation;
    sorted = false;
    checkRep();
  }

  /**
   * Return all the entries.
   *
   * @return the entries
   */
  public List<CoursePlanningEntry> getEntries() {
    return new ArrayList<>(entries);
  }

  /**
   * Add an entry to the board.
   *
   * @param entry the entry to add
   * @return true if there is no collision and added successfully
   */
  public boolean addEntry(CoursePlanningEntry entry) {
    for (CoursePlanningEntry entry2 : entries) {
      if (entry.conflictResourceWith(entry2) || entry.conflictLocationWith(entry2)) {
        return false;
      }
    }
    entries.add(entry);
    sorted = false;
    return true;
  }

  /**
   * Remove an entry from the board.
   *
   * @param entry the entry to remove
   * @return true if remove successfully
   */
  public boolean removeEntry(CoursePlanningEntry entry) {
    if (entries.remove(entry)) {
      sorted = false;
      return true;
    }
    return false;
  }

  @Override
  public Iterator<CoursePlanningEntry> iterator() {
    updateSortedEntries();
    return sortedEntries.iterator();
  }

  private void updateSortedEntries() {
    if (!sorted) {
      sorted = true;
      sortedEntries = new ArrayList<>();
      for (CoursePlanningEntry entry : entries) {
        if (entry.getLocation().equals(sortKeyLocation)) {
          sortedEntries.add(entry);
        }
      }
      sortedEntries.sort(Comparator.comparing(e -> e.getTimeslot().getStartTime()));
    }
  }

  /**
   * Return a {@link JTable} that contains entries from or to sortKeyLocation.
   *
   * @return a {@link JTable} contains entries from or to sortKeyLocation
   */
  public JTable generateTable() {
    updateSortedEntries();
    int subTableSize = sortedEntries.size();
    Object[][] data = new Object[subTableSize][];
    int count = 0;
    for (CoursePlanningEntry entry : sortedEntries) {
      data[count] = new Object[]{
        String.format("%s-%s", entry.getTimeslot().getStartTime().format(ISO_LOCAL_TIME),
          entry.getTimeslot().getEndTime().format(ISO_LOCAL_TIME)),
        entry.getName(), entry.getResource() == null ? "待分配" : entry.getResource().getName(),
        I18N_CHINESE_MAP.get(entry.getState().toString())};
      ++count;
    }

    return new BoardTable(data, sortKeyLocation);
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

}

