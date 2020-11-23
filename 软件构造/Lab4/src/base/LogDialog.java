package base;


import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Objects;

/**
 * This class {@link LogDialog} is used to display the logs.
 *
 * @author cycleke
 */
public class LogDialog extends JDialog {
  private static final long serialVersionUID = -3521009929522917965L;

  private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("(yyyy-MM-dd HH:mm:ss");
  private final static String[] COLUMN_NAMES = {"TIME", "LEVEL", "CALL PATH", "MESSAGE"};
  private final static String ALL = "全部";
  private final static LogManager LOG_MANAGER = LogManager.LOG_MANAGER;

  /**
   * Constructor of {@link LogDialog}.
   *
   * @param operations the operations
   * @param methods    the methods related to the operations, the size must equal
   *                   the operations'
   * @param entries    the entries stored
   */
  public LogDialog(JFrame frame, String[] operations, String[] methods, String[] entries) {
    super(frame);
    assert operations.length == methods.length;

    String[] operationsClone = Arrays.copyOf(operations, operations.length + 1);
    String[] methodsClone = Arrays.copyOf(methods, methods.length + 1);
    String[] entriesClone = Arrays.copyOf(entries, entries.length + 1);
    operationsClone[operations.length] = ALL;
    methodsClone[methods.length] = null;
    entriesClone[entries.length] = ALL;

    setLocationRelativeTo(null);
    setTitle("显示日志");
    JPanel inputPanel = new JPanel(), buttonPanel = new JPanel();
    setLayout(new BorderLayout());
    add(inputPanel, BorderLayout.NORTH);
    add(buttonPanel, BorderLayout.CENTER);

    JLabel startTimeLabel = new JLabel("起始时间(yyyy-MM-dd HH:mm:ss格式，若无限制不填)");
    JTextField startTimeField = new JTextField(20);
    JLabel endTimeLabel = new JLabel("终止时间(yyyy-MM-dd HH:mm:ss格式，若无限制不填)");
    JTextField endTimeField = new JTextField(20);
    JLabel operationLabel = new JLabel("过滤操作");
    JComboBox<String> operationBox = new JComboBox<>(operationsClone);
    JLabel entryLabel = new JLabel("过滤计划项");
    JComboBox<String> entryBox = new JComboBox<>(entriesClone);
    LocalDateTime now = LocalDateTime.now();
    endTimeField.setText(now.format(FORMATTER));
    startTimeField.setText(now.minusHours(1).format(FORMATTER));
    operationBox.setSelectedIndex(operations.length);
    entryBox.setSelectedIndex(entries.length);

    inputPanel.setLayout(new GridLayout(4, 2));
    inputPanel.add(startTimeLabel);
    inputPanel.add(startTimeField);
    inputPanel.add(endTimeLabel);
    inputPanel.add(endTimeField);
    inputPanel.add(operationLabel);
    inputPanel.add(operationBox);
    inputPanel.add(entryLabel);
    inputPanel.add(entryBox);

    JButton okButton = new JButton("确定");
    JButton cancelButton = new JButton("取消");
    buttonPanel.setLayout(new FlowLayout());
    buttonPanel.add(okButton);
    buttonPanel.add(cancelButton);

    okButton.addActionListener(e -> {
      LocalDateTime startTime, endTime;
      String method, entry;
      try {
        String startTimeText = startTimeField.getText();
        startTime = startTimeText.isEmpty() ? null : LocalDateTime.parse(startTimeText, FORMATTER);
        String endTimeText = endTimeField.getText();
        endTime = endTimeText.isEmpty() ? null : LocalDateTime.parse(endTimeText, FORMATTER);
        method = methodsClone[operationBox.getSelectedIndex()];
        entry = (String) entryBox.getSelectedItem();
        if (Objects.equals(entry, ALL)) {
          entry = null;
        }
      } catch (DateTimeParseException exception) {
        JOptionPane.showMessageDialog(this, "参数错误", "错误", JOptionPane.ERROR_MESSAGE);
        return;
      }
      Object[][] data;
      synchronized (LOG_MANAGER) {
        LOG_MANAGER.setStartTime(startTime);
        LOG_MANAGER.setEndTime(endTime);
        LOG_MANAGER.setEntry(entry);
        LOG_MANAGER.setMethod(method);
        data = LOG_MANAGER.getFilteredLogs();
      }

      JDialog dialog = new JDialog();
      dialog.setTitle("Logs");
      JTable table = new JTable(data, COLUMN_NAMES);
      TableColumnModel module = table.getColumnModel();
      module.getColumn(0).setWidth(20);
      module.getColumn(1).setWidth(10);
      module.getColumn(2).setWidth(50);
      module.getColumn(3).setMinWidth(80);
      JScrollPane tablePane = new JScrollPane(table);
      dialog.setLayout(new BorderLayout());
      dialog.getContentPane().add(tablePane, BorderLayout.CENTER);
      setVisible(false);
      dialog.setVisible(true);
      dialog.pack();
    });
    cancelButton.addActionListener(e -> {
      setVisible(false);
      dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    });

    pack();
    setVisible(true);
  }
}
