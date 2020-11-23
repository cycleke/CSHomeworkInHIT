package course;

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
public class Board implements Iterable<CoursePlanningEntry> {

  private List<CoursePlanningEntry> entries;

  private boolean sorted;
  private Location sortKeyLocation;
  private List<CoursePlanningEntry> sortedEntries;
  private final static Map<String, String> i18nChineseMap = new HashMap<>();
  static {
    i18nChineseMap.put("ALLOCATED STATE", "待上课");
    i18nChineseMap.put("CANCELLED STATE", "已取消");
    i18nChineseMap.put("ENDED STATE", "下课");
    i18nChineseMap.put("RUNNING STATE", "上课中");
    i18nChineseMap.put("WAITING STATE", "计划");
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
    entries = new ArrayList<>();

    sorted = false;
    sortKeyLocation = CommonLocation.of("", CoursePlanningEntry.LOCATION_SHAREABLE);
    checkRep();
  }

  /**
   * Constructor of {@link Board}.
   *
   * @param entries
   *          the entries to initialize, not null
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
   * @param sortKeyLocation
   *          the sortKeyLocation to set, not null
   */
  public void setSortKeyLocation(Location sortKeyLocation) {
    if (this.sortKeyLocation.equals(sortKeyLocation))
      return;
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
   * @param entry
   *          the entry to add
   * @return true if there is no collision and added successfully
   */
  public boolean addEntry(CoursePlanningEntry entry) {
    for (CoursePlanningEntry entry2 : entries)
      if (entry.conflictResourceWith(entry2) || entry.conflictLocationWith(entry2))
        return false;
    if (entries.add(entry)) {
      sorted = false;
      return true;
    }
    return false;
  }

  /**
   * Remove an entry from the board.
   *
   * @param entry
   *          the entry to remove
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
      Collections.sort(sortedEntries, (e1, e2) -> {
        return e1.getTimeslot().getStartTime().compareTo(e2.getTimeslot().getStartTime());
      });
    }
  }

  /**
   * Return a {@link JTable} that contains entries from or to sortKeyLocation.
   *
   * @return a {@link JTable} contains entries from or to sortKeyLocation
   */
  public JTable generateJTable() {
    updateSortedEntries();
    int subTableSize = sortedEntries.size();
    Object[][] data = new Object[subTableSize][];
    int count = 0;
    for (CoursePlanningEntry entry : sortedEntries) {
      data[count] = new Object[] {
        String.format("%s-%s", entry.getTimeslot().getStartTime().format(DateTimeFormatter.ISO_LOCAL_TIME),
          entry.getTimeslot().getEndTime().format(DateTimeFormatter.ISO_LOCAL_TIME)),
        entry.getName(), (entry.getResource() == null ? "待分配" : entry.getResource().getName()),
        i18nChineseMap.get(entry.getState().toString())};
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
    JScrollPane scroPanel = new JScrollPane(generateJTable());
    frame.getContentPane().add(scroPanel, BorderLayout.CENTER);
    frame.pack();
    frame.setVisible(true);
  }

}

class BoardTable extends JTable {
  private static final long serialVersionUID = -8758916743386491313L;

  private final Object[][] data1;
  private final String[] columnNames;
  private static final Object[][] data = {{null}};
  private static final DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
  static {
    tcr.setHorizontalAlignment(SwingConstants.CENTER);
  }

  public BoardTable(Object[][] data1, Location location) {
    super();
    this.data1 = data1;
    System.err.println("The Board KeyLocation:" + location);
    columnNames = new String[] {String.format("%s (当前时间)，%s教室",
      LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME).replace('T', ' ').substring(0, 16),
      location.getName())};
    getTableHeader().setDefaultRenderer(tcr);
    setModel(new DefaultTableModel(data, columnNames));
    setRowHeight(0, super.getRowHeight() * (data1.length + 1));
  }

  @Override
  public TableCellRenderer getCellRenderer(int row, int column) {
    switch (row) {
      case 0:
        return new TableCellRenderer() {
          private String[] columnNames = {"Time", "Name", "Teacher", "State"};
          private JTable subTable = new JTable(new DefaultTableModel(data1, columnNames));

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
