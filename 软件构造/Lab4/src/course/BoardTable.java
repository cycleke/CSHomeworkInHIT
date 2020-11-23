package course;

import base.Location;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.time.LocalDateTime;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;
import static javax.swing.SwingConstants.CENTER;

class BoardTable extends JTable {
  private static final long serialVersionUID = -8758916743386491313L;
  private static final Object[][] DATA = {{null}};
  private static final DefaultTableCellRenderer TCR_CENTER = new DefaultTableCellRenderer();

  static {
    TCR_CENTER.setHorizontalAlignment(CENTER);
  }

  private final Object[][] data;

  // Abstraction function:
  // AF(i) = {data}
  // Rep invariant:
  // The fields are not null.
  // Safety from rep exposure:
  // The fields are all private.

  public BoardTable(Object[][] data, Location location) {
    super();
    this.data = data;
    String[] columnNames = new String[]{String.format("%s (当前时间)，%s教室",
      LocalDateTime.now().format(ISO_LOCAL_DATE_TIME).replace('T', ' ').substring(0, 16), location.getName())};
    getTableHeader().setDefaultRenderer(TCR_CENTER);
    setModel(new DefaultTableModel(DATA, columnNames));
    setRowHeight(0, super.getRowHeight() * (data.length + 1));
    checkRep();
  }

  private void checkRep() {
    assert data != null;
  }

  @Override
  public TableCellRenderer getCellRenderer(int row, int column) {
    if (row == 0) {
      return new TableCellRenderer() {
        private final String[] columnNames = {"Time", "Name", "Teacher", "State"};
        private final JTable subTable = new JTable(new DefaultTableModel(data, columnNames));

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
          return subTable;
        }
      };
    }
    return TCR_CENTER;
  }

  @Override
  public boolean isCellEditable(int row, int column) {
    return false;
  }
}
