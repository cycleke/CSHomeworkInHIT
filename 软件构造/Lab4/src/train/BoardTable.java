package train;

import base.Location;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static javax.swing.SwingConstants.CENTER;

class BoardTable extends JTable {
  private static final long serialVersionUID = -1353488525757791573L;
  private static final Object[][] DATA = {{"抵达车次"}, {null}, {"出发车次"}, {null}};
  private static final DefaultTableCellRenderer TCR_CENTER = new DefaultTableCellRenderer();

  static {
    TCR_CENTER.setHorizontalAlignment(CENTER);
  }

  private final Object[][] data1, data2;

  // Abstraction function:
  // AF(i) = {data1, data2}
  // Rep invariant:
  // The fields are not null.
  // Safety from rep exposure:
  // The field are all private.

  public BoardTable(Object[][] data1, Object[][] data2, Location location) {
    super();
    this.data1 = data1;
    this.data2 = data2;
    String[] columnNames = new String[]{String.format("%s (当前时间)，%s火车站",
      LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME).replace('T', ' ').substring(0, 16),
      location.getName())};
    getTableHeader().setDefaultRenderer(TCR_CENTER);
    setModel(new DefaultTableModel(DATA, columnNames));
    setRowHeight(1, super.getRowHeight() * (data1.length + 1));
    setRowHeight(3, super.getRowHeight() * (data2.length + 1));
    checkRep();
  }

  private void checkRep() {
    assert data1 != null;
    assert data2 != null;
  }

  @Override
  public TableCellRenderer getCellRenderer(int row, int column) {
    switch (row) {
      case 1:
        return new TableCellRenderer() {
          private final String[] columnNames = {"Time", "Plane", "Location", "State"};
          private final JTable subTable = new JTable(new DefaultTableModel(data1, columnNames));

          @Override
          public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                         boolean hasFocus, int row, int column) {
            return subTable;
          }
        };
      case 3:
        return new TableCellRenderer() {
          private final String[] columnNames = {"Time", "Plane", "Location", "State"};
          private final JTable subTable = new JTable(new DefaultTableModel(data2, columnNames));

          @Override
          public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                         boolean hasFocus, int row, int column) {
            return subTable;
          }
        };
      default:
        return TCR_CENTER;
    }
  }

  @Override
  public boolean isCellEditable(int row, int column) {
    return false;
  }
}
