package train;

import java.awt.BorderLayout;
import java.awt.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import base.CommonLocation;
import base.Location;

/**
 * This class {@link Board} is used to display a collection of plan entries.
 *
 * @author cycleke
 */
public class Board implements Iterable<TrainPlanningEntry> {

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

  private IteratorType iteratorType;
  private List<TrainPlanningEntry> entries;

  private boolean sorted;
  private Location sortKeyLocation;
  private List<TrainPlanningEntry> sortedEntries;
  private final static Map<String, String> i18nChineseMap = new HashMap<>();
  static {
    i18nChineseMap.put("ALLOCATED STATE", "即将出发");
    i18nChineseMap.put("CANCELLED STATE", "已取消");
    i18nChineseMap.put("ENDED STATE", "到达终点");
    i18nChineseMap.put("RUNNING STATE", "正在行驶");
    i18nChineseMap.put("WAITING STATE", "等待分配");
    i18nChineseMap.put("BLOCKED STATE", "中停");
  }

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
    iteratorType = IteratorType.DEPARTURE;
    entries = new ArrayList<>();

    sorted = true;
    sortKeyLocation = CommonLocation.of("", TrainPlanningEntry.LOCATION_SHAREABLE);
    checkRep();
  }

  /**
   * Constructor of {@link Board}.
   *
   * @param entries
   *          the entries to initialize, not null
   */
  public Board(List<TrainPlanningEntry> entries) {
    iteratorType = IteratorType.DEPARTURE;
    this.entries = new ArrayList<>(entries);

    sorted = false;
    sortKeyLocation = CommonLocation.of("", TrainPlanningEntry.LOCATION_SHAREABLE);
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
   * @param iteratorType
   *          the iteratorType to set, not null
   */
  public void setIteratorType(IteratorType iteratorType) {
    if (iteratorType == this.iteratorType)
      return;
    this.iteratorType = iteratorType;
    sorted = false;
    checkRep();
  }

  /**
   * Set the key location for iterator, default DEPARTURE.
   *
   * @param sortKeyLocation
   *          the sortKeyLocation to set, not null
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
  public List<TrainPlanningEntry> getEntries() {
    return new ArrayList<>(entries);
  }

  /**
   * Add an entry to the board.
   *
   * @param entry
   *          the entry to add
   * @return true if there is no collision and added successfully
   */
  public boolean addEntry(TrainPlanningEntry entry) {
    for (TrainPlanningEntry entry2 : entries)
      if (entry.conflictResourceWith(entry2) || entry.conflictLocationWith(entry2))
        return false;
    return entries.add(entry);
  }

  /**
   * Remove an entry from the board.
   *
   * @param entry
   *          the entry to remove
   * @return true if remove successfully
   */
  public boolean removeEntry(TrainPlanningEntry entry) {
    return entries.remove(entry);
  }

  @Override
  public Iterator<TrainPlanningEntry> iterator() {
    updateSortedEntries();
    return sortedEntries.iterator();
  }

  private void updateSortedEntries() {
    if (!sorted) {
      sorted = true;
      sortedEntries = new ArrayList<>();
      for (TrainPlanningEntry entry : entries) {
        Location currentLocation = entry.getCurrentLocation();
        switch (iteratorType) {
          case DEPARTURE:
            if (currentLocation.equals(sortKeyLocation)) {
              if (entry.getState() instanceof RunningState) {
                sortedEntries.add(entry);
              }
            } else {
              boolean foundKeyLocation = false;
              for (Location location : entry.getLocations()) {
                if (location.equals(sortKeyLocation)) {
                  foundKeyLocation = true;
                } else if (location.equals(currentLocation)) {
                  if (foundKeyLocation) {
                    sortedEntries.add(entry);
                  }
                  break;
                }
              }
            }
            break;
          case ARRIVAL:
            if (currentLocation.equals(sortKeyLocation)) {
              if (entry.getState() instanceof BlockedState) {
                sortedEntries.add(entry);
              }
            } else {
              boolean foundCurrentLocation = false;
              for (Location location : entry.getLocations()) {
                if (location.equals(currentLocation)) {
                  foundCurrentLocation = true;
                } else if (location.equals(sortKeyLocation)) {
                  if (foundCurrentLocation) {
                    sortedEntries.add(entry);
                  }
                  break;
                }
              }
            }
            break;
          default:
            assert false;
        }
      }
      Collections.sort(sortedEntries, (e1, e2) -> {
        switch (iteratorType) {
          case DEPARTURE:
            return e1.getCurrentTimeslot().getStartTime().compareTo(e2.getCurrentTimeslot().getStartTime());
          case ARRIVAL:
            return e1.getCurrentTimeslot().getEndTime().compareTo(e2.getCurrentTimeslot().getEndTime());
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
  public JTable generateJTable() {
    IteratorType currentIteratorType = iteratorType;

    setIteratorType(IteratorType.ARRIVAL);
    updateSortedEntries();
    int subTable1Size = sortedEntries.size();
    Object[][] data1 = new Object[subTable1Size][];
    int count = 0;
    for (TrainPlanningEntry entry : sortedEntries) {
      List<Location> locations = entry.getLocations();
      data1[count] =
        new Object[] {entry.getCurrentTimeslot().getEndTime().format(DateTimeFormatter.ISO_LOCAL_TIME), entry.getName(),
          String.format("%s-%s", locations.get(0).getName(), locations.get(locations.size() - 1).getName()),
          i18nChineseMap.get(entry.getState().toString())};
      ++count;
    }

    setIteratorType(IteratorType.DEPARTURE);
    updateSortedEntries();
    int subTable2Size = sortedEntries.size();
    Object[][] data2 = new Object[subTable2Size][];
    count = 0;
    for (TrainPlanningEntry entry : sortedEntries) {
      List<Location> locations = entry.getLocations();
      data2[count] = new Object[] {entry.getCurrentTimeslot().getStartTime().format(DateTimeFormatter.ISO_LOCAL_TIME),
        entry.getName(),
        String.format("%s-%s", locations.get(0).getName(), locations.get(locations.size() - 1).getName()),
        i18nChineseMap.get(entry.getState().toString())};
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
    JScrollPane scroPanel = new JScrollPane(generateJTable());
    frame.getContentPane().add(scroPanel, BorderLayout.CENTER);
    frame.pack();
    frame.setVisible(true);
  }

}

class BoardTable extends JTable {
  private static final long serialVersionUID = -1353488525757791573L;

  private final Object[][] data1, data2;
  private final String[] columnNames;
  private static final Object[][] data = {{"抵达车次"}, {null}, {"出发车次"}, {null}};
  private static final DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
  static {
    tcr.setHorizontalAlignment(SwingConstants.CENTER);
  }

  public BoardTable(Object[][] data1, Object[][] data2, Location location) {
    super();
    this.data1 = data1;
    this.data2 = data2;
    System.err.println("The Board KeyLocation:" + location);
    columnNames = new String[] {String.format("%s (当前时间)，%s火车站",
      LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME).replace('T', ' ').substring(0, 16),
      location.getName())};
    getTableHeader().setDefaultRenderer(tcr);
    setModel(new DefaultTableModel(data, columnNames));
    setRowHeight(1, super.getRowHeight() * (data1.length + 1));
    setRowHeight(3, super.getRowHeight() * (data2.length + 1));
  }

  @Override
  public TableCellRenderer getCellRenderer(int row, int column) {
    switch (row) {
      case 1:
        return new TableCellRenderer() {
          private String[] columnNames = {"Time", "Plane", "Location", "State"};
          private JTable subTable = new JTable(new DefaultTableModel(data1, columnNames));

          @Override
          public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {
            return subTable;
          }
        };
      case 3:
        return new TableCellRenderer() {
          private String[] columnNames = {"Time", "Plane", "Location", "State"};
          private JTable subTable = new JTable(new DefaultTableModel(data2, columnNames));

          @Override
          public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {
            return subTable;
          }
        };
      default:
        return tcr;
    }
  }

  @Override
  public boolean isCellEditable(int row, int column) {
    return false;
  }
}
