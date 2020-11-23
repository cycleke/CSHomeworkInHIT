package flight;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import base.CommonLocation;
import base.IllegalStateTransitionException;
import base.Location;
import base.MyRegExp;
import base.PlanningEntryAPIs;
import base.PlanningEntryAPIsBruteForceStrategy;
import base.Timeslot;
import flight.Board.IteratorType;

/**
 * This class {@link FlightScheduleApp} is a GUI client for Flight schedule.
 *
 * @author cycleke
 */
public class FlightScheduleApp extends JFrame {
  private static final long serialVersionUID = -7909494900613802756L;
  private final static PlanningEntryAPIs<Plane> entryAPIs = new PlanningEntryAPIsBruteForceStrategy<>();

  private JMenuBar menuBar;
  private JPanel keyLocationPanel;
  private JScrollPane tablePanel;
  private JLabel keyLocationLabel;
  private JComboBox<Location> keyLocationComboBox;
  private JButton refreshButton;

  private Board board;
  private List<Plane> planes;
  private List<Location> locations;

  public FlightScheduleApp() {
    board = new Board();
    planes = new ArrayList<>();
    locations = new ArrayList<>();

    menuBar = new JMenuBar();
    setJMenuBar(menuBar);

    menuBar.add(createFileMenu());
    menuBar.add(createResourceMenu());
    menuBar.add(createLocationMenu());
    menuBar.add(createPlanningEntryMenu());

    setLayout(new BorderLayout());
    keyLocationPanel = new JPanel(new FlowLayout());
    tablePanel = new JScrollPane(board.generateJTable());
    add(keyLocationPanel, BorderLayout.NORTH);
    getContentPane().add(tablePanel, BorderLayout.CENTER);

    keyLocationLabel = new JLabel("当强位置");
    keyLocationComboBox = new JComboBox<>();
    keyLocationComboBox.addItem(CommonLocation.of("...", FlightPlanningEntry.LOCATION_SHAREABLE));
    refreshButton = new JButton("刷新");
    keyLocationPanel.add(keyLocationLabel);
    keyLocationPanel.add(keyLocationComboBox);
    keyLocationPanel.add(refreshButton);
    keyLocationPanel.setPreferredSize(new Dimension(500, 50));

    refreshButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        refreshTable();
      }
    });

    setTitle("航班管理");
    pack();
    setLocationRelativeTo(null);
  }

  private JMenu createFileMenu() {
    JMenu fileMenu = new JMenu("文件");
    fileMenu.setMnemonic('f');

    JMenuItem loadItem = new JMenuItem("加载文件", 'l');
    fileMenu.add(loadItem);

    JFrame frame = this;
    loadItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
          File selectedFile = fileChooser.getSelectedFile();
          System.out.println("Selected file: " + selectedFile.getAbsolutePath());
          try {
            board = new Board(new ArrayList<>(FlightEntry.parse(selectedFile)));
          } catch (FileParseException exception) {
            JOptionPane.showMessageDialog(frame, "文件解析失败，请选择其他文件", "错误", JOptionPane.ERROR_MESSAGE);
          } catch (FileNotFoundException exception) {
            JOptionPane.showMessageDialog(frame, "无法打开文件", "错误", JOptionPane.ERROR_MESSAGE);
          } finally {
            Set<Plane> planeSet = new HashSet<>();
            Set<Location> locationSet = new HashSet<>();
            board.getEntries().forEach((entry) -> {
              planeSet.add(entry.getResource());
              locationSet.addAll(entry.getLocations());
            });
            planes = planeSet.stream().collect(Collectors.toList());
            locations = locationSet.stream().collect(Collectors.toList());
            refreshTable();
          }
        }
      }
    });
    return fileMenu;
  }

  private JMenu createResourceMenu() {
    JMenu resourceMenu = new JMenu("资源");
    resourceMenu.setMnemonic('r');

    JMenuItem addItem = new JMenuItem("添加资源", 'a');
    JMenuItem removeItem = new JMenuItem("删除资源", 'r');
    JMenuItem queryItem = new JMenuItem("查询资源的所有计划项");
    resourceMenu.add(addItem);
    resourceMenu.add(removeItem);
    resourceMenu.add(queryItem);

    addItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        showAddResourceDialog();
      }
    });
    removeItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        showRemoveResourceDialog();
      }
    });
    queryItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        showQueryResourceDialog();
      }
    });

    return resourceMenu;
  }

  protected void showQueryResourceDialog() {
    if (planes.isEmpty()) {
      JOptionPane.showMessageDialog(this, "资源资源为空", "错误", JOptionPane.ERROR_MESSAGE);
      return;
    }
    Object[] array = planes.toArray();
    Plane plane =
      (Plane)JOptionPane.showInputDialog(this, "选择", "查询资源", JOptionPane.INFORMATION_MESSAGE, null, array, array[0]);
    if (plane == null)
      return;
    List<FlightPlanningEntry> entries = new ArrayList<>();
    for (FlightPlanningEntry entry : board.getEntries()) {
      if (entry.getResource() == null)
        continue;
      if (entry.getResource().equals(plane))
        entries.add(entry);
    }
    int count = 0;
    Object[][] data = new Object[entries.size()][1];
    for (Object object : entries) {
      data[count][0] = object;
      ++count;
    }
    JDialog dialog = new JDialog(this);
    dialog.setLayout(new BorderLayout());
    JPanel queryPanel = new JPanel(new FlowLayout());
    JScrollPane tablePanel = new JScrollPane(new JTable(new DefaultTableModel(data, new String[] {"航班"})));
    dialog.add(queryPanel, BorderLayout.NORTH);
    dialog.getContentPane().add(tablePanel, BorderLayout.CENTER);

    JButton queryButton = new JButton("查询前项");
    queryPanel.add(queryButton);

    Object[] entryObjects = entries.toArray();
    queryButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (entries.isEmpty())
          return;
        Object object = JOptionPane.showInputDialog(dialog, "选择", "查询前项", JOptionPane.INFORMATION_MESSAGE, null,
          entryObjects, entryObjects[0]);
        if (object == null)
          return;
        FlightPlanningEntry entry = (FlightPlanningEntry)object;
        entry = (FlightPlanningEntry)entryAPIs.findPreEntryPerResource(plane, entry, entries);
        JOptionPane.showMessageDialog(dialog, entry.toString());
      }
    });

    dialog.pack();
    dialog.setVisible(true);
  }

  protected void showAddResourceDialog() {
    JDialog dialog = new JDialog(this);
    dialog.setLocationRelativeTo(null);
    dialog.setTitle("添加资源");
    JPanel panel_up = new JPanel();
    JPanel panel_bottom = new JPanel();

    dialog.setLayout(new BorderLayout());
    dialog.add(panel_up, BorderLayout.CENTER);
    dialog.add(panel_bottom, BorderLayout.SOUTH);

    // 输入部分
    panel_up.setLayout(new GridLayout(4, 2));
    JLabel planeLabel = new JLabel("Plane");
    JPanel planePanel = new JPanel(new FlowLayout());
    JLabel typeLabel = new JLabel("Type");
    JPanel typePanel = new JPanel(new FlowLayout());
    JLabel seatsLabel = new JLabel("Seats");
    JPanel seatsPanel = new JPanel(new FlowLayout());
    JLabel ageLabel = new JLabel("Age");
    JPanel agePanel = new JPanel();
    panel_up.add(planeLabel);
    panel_up.add(planePanel);
    panel_up.add(typeLabel);
    panel_up.add(typePanel);
    panel_up.add(seatsLabel);
    panel_up.add(seatsPanel);
    panel_up.add(ageLabel);
    panel_up.add(agePanel);

    JTextField planeField = new JTextField(10);
    JTextField typeField = new JTextField(10);
    JTextField seatsField = new JTextField(10);
    JTextField ageField = new JTextField(10);
    planeField.setDocument(new MyRegExp("^[NB]\\d{0,4}$", 5));
    typeField.setDocument(new MyRegExp("^[a-zA-Z\\d]*$", 10));
    seatsField.setDocument(new MyRegExp("^[1-9]\\d{0,2}$", 10));
    ageField.setDocument(new MyRegExp("^(\\d{0,2}|\\d{0,2}\\.|\\d{0,2}\\.\\d)$", 4));

    planePanel.add(planeField);
    typePanel.add(typeField);
    seatsPanel.add(seatsField);
    agePanel.add(ageField);

    // 控制部分
    panel_bottom.setLayout(new FlowLayout());
    JButton okButton = new JButton("确定");
    JButton cancelButton = new JButton("取消");
    panel_bottom.add(okButton);
    panel_bottom.add(cancelButton);

    JFrame frame = this;
    okButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          String plane = planeField.getText();
          String type = typeField.getText();
          int seats = Integer.parseInt(seatsField.getText());
          double age = Double.parseDouble(ageField.getText());
          if (plane.length() != 5 || type.isEmpty() || seats < 50 || seats > 600 || age < 0 || age > 30) {
            JOptionPane.showMessageDialog(dialog, "参数错误 无法添加", "错误", JOptionPane.ERROR_MESSAGE);
            return;
          }
          if (planes.add(new Plane(plane, type, seats, age))) {
            JOptionPane.showMessageDialog(frame, "添加成功", "成功", JOptionPane.INFORMATION_MESSAGE);
          }
        } catch (NumberFormatException exception) {
          JOptionPane.showMessageDialog(dialog, "参数错误 无法添加", "错误", JOptionPane.ERROR_MESSAGE);
          return;
        }
        dialog.setVisible(false);
        dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));
      }
    });
    cancelButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        dialog.setVisible(false);
        dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));
      }
    });

    dialog.pack();
    dialog.setVisible(true);
  }

  protected void showRemoveResourceDialog() {
    if (planes.isEmpty()) {
      JOptionPane.showMessageDialog(this, "资源资源为空", "错误", JOptionPane.ERROR_MESSAGE);
      return;
    }
    Object[] array = planes.toArray();
    if (planes.remove(
      JOptionPane.showInputDialog(this, "选择", "删除资源", JOptionPane.INFORMATION_MESSAGE, null, array, array[0]))) {
      JOptionPane.showMessageDialog(this, "删除成功");
    }
  }

  private JMenu createLocationMenu() {
    JMenu locationMenu = new JMenu("位置");
    locationMenu.setMnemonic('l');
    JMenuItem addItem = new JMenuItem("添加位置", 'a');
    JMenuItem removeItem = new JMenuItem("删除位置", 'r');
    locationMenu.add(addItem);
    locationMenu.add(removeItem);

    addItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        showAddLocationDialog();
      }
    });
    removeItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        showRemoveLocationDialog();
      }
    });
    return locationMenu;
  }

  protected void showAddLocationDialog() {
    JDialog dialog = new JDialog(this);
    dialog.setLocationRelativeTo(null);
    dialog.setTitle("添加位置");
    JPanel panel_up = new JPanel();
    JPanel panel_bottom = new JPanel();

    dialog.setLayout(new BorderLayout());
    dialog.add(panel_up, BorderLayout.CENTER);
    dialog.add(panel_bottom, BorderLayout.SOUTH);

    // 输入部分
    panel_up.setLayout(new GridLayout(1, 2));
    JLabel locationLabel = new JLabel("位置");
    JPanel locationPanel = new JPanel(new FlowLayout());
    panel_up.add(locationLabel);
    panel_up.add(locationPanel);

    JTextField locationField = new JTextField(20);
    locationPanel.add(locationField);

    // 控制部分
    panel_bottom.setLayout(new FlowLayout());
    JButton okButton = new JButton("确定");
    JButton cancelButton = new JButton("取消");
    panel_bottom.add(okButton);
    panel_bottom.add(cancelButton);

    JFrame frame = this;
    okButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String location = locationField.getText();
        if (location.isEmpty()) {
          JOptionPane.showMessageDialog(dialog, "参数错误 无法添加", "错误", JOptionPane.ERROR_MESSAGE);
          return;
        }
        if (locations.add(CommonLocation.of(location, FlightPlanningEntry.LOCATION_SHAREABLE))) {
          JOptionPane.showMessageDialog(frame, "添加成功", "成功", JOptionPane.INFORMATION_MESSAGE);
          refreshTable();
        }
        dialog.setVisible(false);
        dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));
      }
    });
    cancelButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        dialog.setVisible(false);
        dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));
      }
    });

    dialog.pack();
    dialog.setVisible(true);
  }

  protected void showRemoveLocationDialog() {
    if (locations.isEmpty()) {
      JOptionPane.showMessageDialog(this, "位置列表为空", "错误", JOptionPane.ERROR_MESSAGE);
      return;
    }
    Object[] array = locations.toArray();
    if (locations.remove(
      JOptionPane.showInputDialog(this, "选择", "删除位置", JOptionPane.INFORMATION_MESSAGE, null, array, array[0]))) {
      JOptionPane.showMessageDialog(this, "删除成功");
    }
  }

  private JMenu createPlanningEntryMenu() {
    JMenu planningEntryMenu = new JMenu("计划项");
    planningEntryMenu.setMnemonic('p');
    JMenuItem addItem = new JMenuItem("添加计划项", 'a');
    JMenuItem removeItem = new JMenuItem("删除计划项", 'r');
    JMenuItem changeStateItem = new JMenuItem("改变计划项状态", 'c');
    JMenuItem checkConflictItem = new JMenuItem("检测冲突", 't');
    planningEntryMenu.add(addItem);
    planningEntryMenu.add(removeItem);
    planningEntryMenu.add(changeStateItem);
    planningEntryMenu.add(checkConflictItem);

    addItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        showAddPlanningEntryDialog();
      }
    });
    removeItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        showRemovePlanningEntryDialog();
      }
    });
    changeStateItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        showChangePlanningEntryStateDialog();
      }
    });
    checkConflictItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        showCheckPlanningEntryConflictDialog();
      }
    });

    return planningEntryMenu;
  }

  protected void showAddPlanningEntryDialog() {
    JDialog dialog = new JDialog(this);
    dialog.setLocationRelativeTo(null);
    dialog.setTitle("添加计划项");
    JPanel panel_up = new JPanel();
    JPanel panel_bottom = new JPanel();

    dialog.setLayout(new BorderLayout());
    dialog.add(panel_up, BorderLayout.CENTER);
    dialog.add(panel_bottom, BorderLayout.SOUTH);

    // 输入部分
    panel_up.setLayout(new GridLayout(5, 2));
    JLabel nameLabel = new JLabel("名称");
    JPanel namePanel = new JPanel(new FlowLayout());
    JLabel startTimeLabel = new JLabel("起飞时间(yyyy-MM-dd HH:mm格式)");
    JPanel startTimePanel = new JPanel(new FlowLayout());
    JLabel endTimeLabel = new JLabel("降落时间(yyyy-MM-dd HH:mm格式)");
    JPanel endTimePanel = new JPanel(new FlowLayout());
    JLabel startLocationLabel = new JLabel("起点");
    JPanel startLocationPanel = new JPanel();
    JLabel targetLocationLabel = new JLabel("终点");
    JPanel targetLocationPanel = new JPanel();
    panel_up.add(nameLabel);
    panel_up.add(namePanel);
    panel_up.add(startTimeLabel);
    panel_up.add(startTimePanel);
    panel_up.add(endTimeLabel);
    panel_up.add(endTimePanel);
    panel_up.add(startLocationLabel);
    panel_up.add(startLocationPanel);
    panel_up.add(targetLocationLabel);
    panel_up.add(targetLocationPanel);

    JTextField nameField = new JTextField(20);
    JTextField startTimeField = new JTextField(16);
    JTextField endTimeField = new JTextField(16);
    JComboBox<Location> startLocationComboBox = new JComboBox<Location>();
    JComboBox<Location> targetLocationComboBox = new JComboBox<Location>();
    for (Location location : locations) {
      startLocationComboBox.addItem(location);
      targetLocationComboBox.addItem(location);
    }
    namePanel.add(nameField);
    startTimePanel.add(startTimeField);
    endTimePanel.add(endTimeField);
    startLocationPanel.add(startLocationComboBox);
    targetLocationPanel.add(targetLocationComboBox);

    // 控制部分
    panel_bottom.setLayout(new FlowLayout());
    JButton okButton = new JButton("确定");
    JButton cancelButton = new JButton("取消");
    panel_bottom.add(okButton);
    panel_bottom.add(cancelButton);

    JFrame frame = this;
    okButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          String plane = nameField.getText();
          Timeslot timeslot = Timeslot.parse(startTimeField.getText(), endTimeField.getText());
          Location startLocation = (Location)startLocationComboBox.getSelectedItem();
          Location targetLocation = (Location)targetLocationComboBox.getSelectedItem();
          if (!board.addEntry(new FlightEntry(plane, timeslot, startLocation, targetLocation))) {
            JOptionPane.showMessageDialog(dialog, "产生冲突 无法添加", "错误", JOptionPane.ERROR_MESSAGE);
          }
        } catch (Exception exception) {
          JOptionPane.showMessageDialog(dialog, "参数错误 无法添加", "错误", JOptionPane.ERROR_MESSAGE);
          return;
        }
        JOptionPane.showMessageDialog(frame, "添加成功", "成功", JOptionPane.INFORMATION_MESSAGE);
        refreshTable();
        dialog.setVisible(false);
        dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));
      }
    });
    cancelButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        dialog.setVisible(false);
        dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));
      }
    });

    dialog.pack();
    dialog.setVisible(true);
  }

  protected void showRemovePlanningEntryDialog() {
    Object[] array = board.getEntries().toArray();
    if (array.length == 0) {
      JOptionPane.showMessageDialog(this, "计划项列表为空", "错误", JOptionPane.ERROR_MESSAGE);
      return;
    }
    if (board.removeEntry((FlightPlanningEntry)JOptionPane.showInputDialog(this, "选择", "删除计划项",
      JOptionPane.INFORMATION_MESSAGE, null, array, array[0]))) {
      JOptionPane.showMessageDialog(this, "删除成功");
      refreshTable();
    }
  }

  protected void showChangePlanningEntryStateDialog() {
    JDialog dialog = new JDialog(this);
    dialog.setLocationRelativeTo(null);
    dialog.setTitle("改变计划项状态");

    JPanel panel = new JPanel(new GridLayout(3, 1));
    dialog.add(panel);

    JComboBox<FlightPlanningEntry> entryComboBox = new JComboBox<>();
    for (FlightPlanningEntry entry : board.getEntries()) {
      entryComboBox.addItem(entry);
    }
    JComboBox<String> stateComboBox = new JComboBox<>(new String[] {"分配资源", "起飞", "降落", "取消"});
    JButton execButton = new JButton("运行");
    panel.add(entryComboBox);
    panel.add(stateComboBox);
    panel.add(execButton);

    execButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        FlightPlanningEntry entry = (FlightPlanningEntry)entryComboBox.getSelectedItem();
        try {
          switch (stateComboBox.getSelectedIndex()) {
            case 0:
              if (!(entry.getState() instanceof WaitingState) && !(entry.getState() instanceof AllocatedState))
                throw new IllegalStateTransitionException("The entry is unable to allocate.");
              if (planes.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "资源列表为空", "错误", JOptionPane.ERROR_MESSAGE);
                return;
              }
              Object[] array = planes.toArray();
              Plane plane = (Plane)JOptionPane.showInputDialog(dialog, "选择", "选择资源", JOptionPane.INFORMATION_MESSAGE,
                null, array, array[0]);
              entry.setResource(plane);
              entry.allocated();
              break;
            case 1:
              entry.run();
              break;
            case 2:
              entry.end();
              break;
            case 3:
              entry.cancel();
            default:
              assert false;
          }
        } catch (IllegalStateTransitionException exception) {
          exception.printStackTrace();
          JOptionPane.showMessageDialog(dialog, "无法转移状态", "错误", JOptionPane.ERROR_MESSAGE);
        }
        refreshTable();
      }
    });

    dialog.pack();
    dialog.setVisible(true);
  }

  protected void showCheckPlanningEntryConflictDialog() {
    JDialog dialog = new JDialog(this);
    dialog.setLocationRelativeTo(null);
    dialog.setTitle("检测冲突");

    JPanel panel = new JPanel(new GridLayout(2, 1));
    dialog.add(panel);

    JButton checkLocationButton = new JButton("检测位置冲突");
    JButton checkResourcesButton = new JButton("检测资源冲突");
    panel.add(checkLocationButton);
    panel.add(checkResourcesButton);
    checkLocationButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (entryAPIs.checkLocationConflict(board.getEntries())) {
          JOptionPane.showMessageDialog(dialog, "有冲突", "错误", JOptionPane.ERROR_MESSAGE);
        } else {
          JOptionPane.showMessageDialog(dialog, "无冲突", "消息", JOptionPane.INFORMATION_MESSAGE);
        }
      }
    });
    checkResourcesButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (entryAPIs.checkResourceExclusiveConflict(board.getEntries())) {
          JOptionPane.showMessageDialog(dialog, "有冲突", "错误", JOptionPane.ERROR_MESSAGE);
        } else {
          JOptionPane.showMessageDialog(dialog, "无冲突", "消息", JOptionPane.INFORMATION_MESSAGE);
        }
      }
    });
    dialog.pack();
    dialog.setVisible(true);
  }

  protected void refreshTable() {
    keyLocationPanel.removeAll();

    Location keyLocation = (Location)keyLocationComboBox.getSelectedItem();
    keyLocationComboBox = new JComboBox<>();
    keyLocationComboBox.addItem(CommonLocation.of("...", FlightPlanningEntry.LOCATION_SHAREABLE));

    for (Location location : locations) {
      keyLocationComboBox.addItem(location);
    }
    keyLocationComboBox.setSelectedItem(keyLocation);

    keyLocationPanel.add(keyLocationLabel);
    keyLocationPanel.add(keyLocationComboBox);
    keyLocationPanel.add(refreshButton);
    keyLocationPanel.validate();
    keyLocationPanel.repaint();

    board.setSortKeyLocation(keyLocation);
    board.setIteratorType(IteratorType.DEPARTURE);
    board.setIteratorType(IteratorType.ARRIVAL);

    remove(tablePanel);
    tablePanel = new JScrollPane(board.generateJTable());
    tablePanel.validate();
    tablePanel.repaint();

    setLayout(new BorderLayout());
    add(keyLocationPanel, BorderLayout.NORTH);
    getContentPane().add(tablePanel, BorderLayout.CENTER);
    validate();
    repaint();
    pack();
  }

  public static void main(String[] args) {
    try {
      FlightScheduleApp app = new FlightScheduleApp();
      app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      app.pack();
      app.setVisible(true);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
