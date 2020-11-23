package flight;

import base.*;
import flight.Board.IteratorType;
import org.apache.log4j.Logger;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;

/**
 * This class {@link FlightScheduleApp} is a GUI client for Flight schedule.
 *
 * @author cycleke
 */
public class FlightScheduleApp extends JFrame {
  private static final long serialVersionUID = 4797982366087709883L;

  private final static PlanningEntryApis<Plane> ENTRY_APIS = new PlanningEntryApisBruteForceStrategy<>();
  private final static Logger logger = Logger.getLogger(FlightScheduleApp.class);
  private final static String[] OPERATIONS = {"添加资源", "删除资源", "添加位置", "删除位置", "添加计划项", "取消计划项", "分配资源", "启动计划项",
    "结束计划项", "检测冲突", "查询计划项", "更换信息板"};
  private final static String[] METHODS = {"addResourceAction", "showRemoveResourceDialog", "addLocationAction",
    "showRemoveLocationDialog", "addPlanningEntryAction", "cancelEntryAction", "allocateResourceAction",
    "runEntryAction", "endEntryAction", "checkConflictAction", "showQueryResourceDialog", "refreshTable"};

  private final JPanel keyLocationPanel;
  private final JLabel keyLocationLabel;
  private final JButton refreshButton;
  private JScrollPane tablePanel;
  private JComboBox<Location> keyLocationComboBox;
  private Board board;
  private List<Plane> planes;
  private List<Location> locations;

  // Abstraction function:
  // AF(e) = {entries, locations, resources}
  // Rep invariant:
  // All fields are not null.
  // Safety from rep exposure:
  // the field are all private.

  public FlightScheduleApp() {
    board = new Board();
    planes = new ArrayList<>();
    locations = new ArrayList<>();

    JMenuBar menuBar = new JMenuBar();
    setJMenuBar(menuBar);

    menuBar.add(createFileMenu());
    menuBar.add(createResourceMenu());
    menuBar.add(createLocationMenu());
    menuBar.add(createPlanningEntryMenu());
    menuBar.add(createLogMenu());

    setLayout(new BorderLayout());
    keyLocationPanel = new JPanel(new FlowLayout());
    tablePanel = new JScrollPane(board.generateTable());
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

    refreshButton.addActionListener(e -> refreshTable());

    setTitle("航班管理");
    pack();
    setLocationRelativeTo(null);
    checkRep();
    logger.info("Start running Flight ScheduleApp.");
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

  private void checkRep() {
    assert keyLocationPanel != null;
    assert keyLocationLabel != null;
    assert refreshButton != null;
    assert tablePanel != null;
    assert keyLocationComboBox != null;
    assert board != null;
    assert planes != null;
    assert locations != null;
  }

  private JMenu createLogMenu() {
    JMenu logMenu = new JMenu("日志");

    JMenuItem viewItem = new JMenuItem("查询日志");
    logMenu.add(viewItem);

    viewItem.addActionListener(e -> showViewLogDialog());
    return logMenu;
  }

  private void showViewLogDialog() {
    logger.info("View logs");
    String[] entries = board.getEntries().stream().map(Object::toString).toArray(String[]::new);
    try {
      JDialog dialog = new LogDialog(this, OPERATIONS, METHODS, entries);
      dialog.setVisible(true);
    } catch (Exception e) {
      logger.error("Exception: " + e.getMessage());
    }
  }

  private JMenu createFileMenu() {
    JMenu fileMenu = new JMenu("文件");
    fileMenu.setMnemonic('f');

    JMenuItem loadItem = new JMenuItem("加载文件", 'l');
    fileMenu.add(loadItem);

    loadItem.addActionListener(e -> {
      JFileChooser fileChooser = new JFileChooser();
      fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
      int result = fileChooser.showOpenDialog(this);
      if (result == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fileChooser.getSelectedFile();
        logger.info("Try to parse " + selectedFile.getAbsolutePath());
        try {
          board = new Board(new ArrayList<>(FlightEntry.parse(selectedFile)));
        } catch (FileParseException exception) {
          logger.error("Fail to parse " + selectedFile.getAbsolutePath());
          JOptionPane.showMessageDialog(this, "文件解析失败，请选择其他文件\n" + exception.getMessage(), "错误", ERROR_MESSAGE);
        } catch (FileNotFoundException exception) {
          logger.fatal("Fail to open " + selectedFile.getAbsolutePath());
          JOptionPane.showMessageDialog(this, "无法打开文件", "错误", ERROR_MESSAGE);
        } finally {
          Set<Plane> planeSet = new HashSet<>();
          Set<Location> locationSet = new HashSet<>();
          board.getEntries().forEach((entry) -> {
            planeSet.add(entry.getResource());
            locationSet.addAll(entry.getLocations());
          });
          planes = new ArrayList<>(planeSet);
          locations = new ArrayList<>(locationSet);
          logger.info("Parsed " + selectedFile.getAbsolutePath() + " successfully");
          refreshTable();
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

    addItem.addActionListener(e -> showAddResourceDialog());
    removeItem.addActionListener(e -> {
      try {
        showRemoveResourceDialog();
      } catch (UnfinishedEntryUsingTheResourceException exception) {
        JOptionPane.showMessageDialog(this, "有尚未结束的计划项正在占用该资源\n" + exception.getMessage(), "错误", ERROR_MESSAGE);
      }
    });
    queryItem.addActionListener(e -> showQueryResourceDialog());

    return resourceMenu;
  }

  protected void showQueryResourceDialog() {
    if (planes.isEmpty()) {
      logger.error("The planes list is empty.");
      JOptionPane.showMessageDialog(this, "资源资源为空", "错误", ERROR_MESSAGE);
      return;
    }
    Object[] array = planes.toArray();
    Plane plane = (Plane) JOptionPane.showInputDialog(this, "选择", "查询资源", INFORMATION_MESSAGE, null, array, array[0]);
    if (plane == null) {
      return;
    }
    logger.info("Query resource:" + plane);
    List<FlightPlanningEntry> entries = new ArrayList<>();
    for (FlightPlanningEntry entry : board.getEntries()) {
      if (entry.getResource() == null) {
        continue;
      }
      if (entry.getResource().equals(plane)) {
        entries.add(entry);
      }
    }

    int count = 0;
    Object[][] data = new Object[entries.size()][1];
    for (Object object : entries) {
      data[count++][0] = object;
    }
    JDialog dialog = new JDialog(this);
    dialog.setLayout(new BorderLayout());
    JPanel queryPanel = new JPanel(new FlowLayout());
    JScrollPane tablePanel = new JScrollPane(new JTable(new DefaultTableModel(data, new String[]{"航班"})));
    dialog.add(queryPanel, BorderLayout.NORTH);
    dialog.getContentPane().add(tablePanel, BorderLayout.CENTER);

    JButton queryButton = new JButton("查询前项");
    queryPanel.add(queryButton);

    Object[] entryObjects = entries.toArray();
    queryButton.addActionListener(e -> {
      if (entries.isEmpty()) {
        return;
      }
      Object object = JOptionPane.showInputDialog(dialog, "选择", "查询前项", INFORMATION_MESSAGE, null, entryObjects,
        entryObjects[0]);
      if (object == null) {
        return;
      }
      FlightPlanningEntry entry = (FlightPlanningEntry) object;
      entry = (FlightPlanningEntry) ENTRY_APIS.findPreEntryPerResource(plane, entry, entries);
      JOptionPane.showMessageDialog(dialog, entry.toString());
    });

    dialog.pack();
    dialog.setVisible(true);
  }

  protected void showAddResourceDialog() {
    JDialog dialog = new JDialog(this);
    dialog.setLocationRelativeTo(null);
    dialog.setTitle("添加资源");
    JPanel panelUp = new JPanel();
    JPanel panelBottom = new JPanel();

    dialog.setLayout(new BorderLayout());
    dialog.add(panelUp, BorderLayout.CENTER);
    dialog.add(panelBottom, BorderLayout.SOUTH);

    // 输入部分
    panelUp.setLayout(new GridLayout(4, 2));
    JLabel planeLabel = new JLabel("Plane");
    JPanel planePanel = new JPanel(new FlowLayout());
    JLabel typeLabel = new JLabel("Type");
    JPanel typePanel = new JPanel(new FlowLayout());
    JLabel seatsLabel = new JLabel("Seats");
    JPanel seatsPanel = new JPanel(new FlowLayout());
    JLabel ageLabel = new JLabel("Age");
    JPanel agePanel = new JPanel();
    generateUpPanel(panelUp, planeLabel, planePanel, typeLabel, typePanel, seatsLabel, seatsPanel, ageLabel, agePanel);

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
    panelBottom.setLayout(new FlowLayout());
    JButton okButton = new JButton("确定");
    JButton cancelButton = new JButton("取消");
    panelBottom.add(okButton);
    panelBottom.add(cancelButton);

    okButton.addActionListener(e -> addResourceAction(dialog, planeField, typeField, seatsField, ageField));
    cancelButton.addActionListener(e -> {
      dialog.setVisible(false);
      dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));
    });

    dialog.pack();
    dialog.setVisible(true);
  }

  private void addResourceAction(JDialog dialog, JTextField planeField, JTextField typeField, JTextField seatsField,
                                 JTextField ageField) {
    try {
      String plane = planeField.getText();
      String type = typeField.getText();
      int seats = Integer.parseInt(seatsField.getText());
      double age = Double.parseDouble(ageField.getText());
      if (plane.length() != 5 || type.isEmpty() || seats < 50 || seats > 600 || age < 0 || age > 30) {
        logger.error("Fail to parse param when construct a plane.");
        if (logger.isDebugEnabled()) {
          logger.debug("Plane: " + plane + ", type: " + type + ", seats: " + seats + ", age: " + age);
        }
        JOptionPane.showMessageDialog(dialog, "参数错误 无法添加", "错误", ERROR_MESSAGE);
        return;
      }
      Plane plane1 = new Plane(plane, type, seats, age);
      planes.add(plane1);
      logger.info("Add a plane: " + plane1);
      JOptionPane.showMessageDialog(this, "添加成功", "成功", INFORMATION_MESSAGE);
    } catch (NumberFormatException exception) {
      logger.error("Fail to parse param when construct a plane.");
      JOptionPane.showMessageDialog(dialog, "参数错误 无法添加", "错误", ERROR_MESSAGE);
    } finally {
      dialog.setVisible(false);
      dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));
    }
  }

  private void generateUpPanel(JPanel panelUp, JLabel planeLabel, JPanel planePanel, JLabel typeLabel, JPanel typePanel,
                               JLabel seatsLabel, JPanel seatsPanel, JLabel ageLabel, JPanel agePanel2) {
    panelUp.add(planeLabel);
    panelUp.add(planePanel);
    panelUp.add(typeLabel);
    panelUp.add(typePanel);
    panelUp.add(seatsLabel);
    panelUp.add(seatsPanel);
    panelUp.add(ageLabel);
    panelUp.add(agePanel2);
  }

  protected void showRemoveResourceDialog() throws UnfinishedEntryUsingTheResourceException {
    if (planes.isEmpty()) {
      logger.error("The planes list is empty.");
      JOptionPane.showMessageDialog(this, "资源资源为空", "错误", ERROR_MESSAGE);
      return;
    }
    Object[] array = planes.toArray();
    Plane plane = (Plane) JOptionPane.showInputDialog(this, "选择", "删除资源", INFORMATION_MESSAGE, null, array, array[0]);
    if (plane == null) {
      return;
    }
    for (FlightPlanningEntry entry : board.getEntries()) {
      String state = entry.getState().toString();
      if (state.contains("ENDED") || state.contains("CANCELLED")) {
        continue;
      }
      if (plane.equals(entry.getResource())) {
        logger.error("Fail to remove resource: " + plane + ", a unfinished entry:" + entry + " is using it");
        throw new UnfinishedEntryUsingTheResourceException(entry, plane);
      }
    }
    if (planes.remove(plane)) {
      logger.info("Removed plane: " + plane);
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

    addItem.addActionListener(e -> showAddLocationDialog());
    removeItem.addActionListener(e -> {
      try {
        showRemoveLocationDialog();
      } catch (UnfinishedEntryMayReachThisLocationException exception) {
        JOptionPane.showMessageDialog(this, "有尚未结束的计划项可能到达该位置\n" + exception.getMessage(), "错误", ERROR_MESSAGE);
      }
    });
    return locationMenu;
  }

  protected void showAddLocationDialog() {
    JDialog dialog = new JDialog(this);
    dialog.setLocationRelativeTo(null);
    dialog.setTitle("添加位置");
    JPanel panelUp = new JPanel();
    JPanel panelBottom = new JPanel();

    dialog.setLayout(new BorderLayout());
    dialog.add(panelUp, BorderLayout.CENTER);
    dialog.add(panelBottom, BorderLayout.SOUTH);

    // 输入部分
    panelUp.setLayout(new GridLayout(1, 2));
    JLabel locationLabel = new JLabel("位置");
    JPanel locationPanel = new JPanel(new FlowLayout());
    panelUp.add(locationLabel);
    panelUp.add(locationPanel);

    JTextField locationField = new JTextField(20);
    locationPanel.add(locationField);

    // 控制部分
    panelBottom.setLayout(new FlowLayout());
    JButton okButton = new JButton("确定");
    JButton cancelButton = new JButton("取消");
    panelBottom.add(okButton);
    panelBottom.add(cancelButton);

    okButton.addActionListener(e -> addLocationAction(dialog, locationField));
    cancelButton.addActionListener(e -> {
      dialog.setVisible(false);
      dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));
    });

    dialog.pack();
    dialog.setVisible(true);
  }

  private void addLocationAction(JDialog dialog, JTextField locationField) {
    String location = locationField.getText();
    if (location.isEmpty()) {
      logger.error("The location param is empty.");
      JOptionPane.showMessageDialog(dialog, "参数错误 无法添加", "错误", ERROR_MESSAGE);
      return;
    }
    CommonLocation commonLocation = CommonLocation.of(location, FlightPlanningEntry.LOCATION_SHAREABLE);
    locations.add(commonLocation);
    JOptionPane.showMessageDialog(this, "添加成功", "成功", INFORMATION_MESSAGE);
    refreshTable();
    dialog.setVisible(false);
    logger.info("Added a location: " + commonLocation);
    dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));
  }

  protected void showRemoveLocationDialog() throws UnfinishedEntryMayReachThisLocationException {
    if (locations.isEmpty()) {
      logger.error("The locations list is empty.");
      JOptionPane.showMessageDialog(this, "位置列表为空", "错误", ERROR_MESSAGE);
      return;
    }
    Object[] array = locations.toArray();
    Location location = (Location) JOptionPane.showInputDialog(this, "选择", "删除位置", INFORMATION_MESSAGE, null, array,
      array[0]);
    if (location == null) {
      return;
    }
    for (FlightPlanningEntry entry : board.getEntries()) {
      String state = entry.getState().toString();
      if (state.contains("ENDED") || state.contains("CANCELLED")) {
        continue;
      }
      if (!state.contains("RUNNING") && location.equals(entry.getStartLocation())) {
        logger.error("Fail to remove location: " + location + ", a unfinished entry:" + entry + " may reach it");
        throw new UnfinishedEntryMayReachThisLocationException(entry, location);
      }
      if (location.equals(entry.getTargetLocation())) {
        logger.error("Fail to remove location: " + location + ", a unfinished entry:" + entry + " may reach it");
        throw new UnfinishedEntryMayReachThisLocationException(entry, location);
      }
    }
    if (locations.remove(location)) {
      logger.info("Removed location: " + location);
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

    addItem.addActionListener(e -> showAddPlanningEntryDialog());
    removeItem.addActionListener(e -> showRemovePlanningEntryDialog());
    changeStateItem.addActionListener(e -> showChangePlanningEntryStateDialog());
    checkConflictItem.addActionListener(e -> showCheckPlanningEntryConflictDialog());

    return planningEntryMenu;
  }

  protected void showAddPlanningEntryDialog() {
    JDialog dialog = new JDialog(this);
    dialog.setLocationRelativeTo(null);
    dialog.setTitle("添加计划项");
    JPanel panelUp = new JPanel();
    JPanel panelBottom = new JPanel();

    dialog.setLayout(new BorderLayout());
    dialog.add(panelUp, BorderLayout.CENTER);
    dialog.add(panelBottom, BorderLayout.SOUTH);

    // 输入部分
    panelUp.setLayout(new GridLayout(5, 2));
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
    panelUp.add(nameLabel);
    panelUp.add(namePanel);
    panelUp.add(startTimeLabel);
    panelUp.add(startTimePanel);
    panelUp.add(endTimeLabel);
    panelUp.add(endTimePanel);
    panelUp.add(startLocationLabel);
    panelUp.add(startLocationPanel);
    panelUp.add(targetLocationLabel);
    panelUp.add(targetLocationPanel);

    JTextField nameField = new JTextField(20);
    JTextField startTimeField = new JTextField(16);
    JTextField endTimeField = new JTextField(16);
    JComboBox<Location> startLocationComboBox = new JComboBox<>();
    JComboBox<Location> targetLocationComboBox = new JComboBox<>();
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
    panelBottom.setLayout(new FlowLayout());
    JButton okButton = new JButton("确定");
    JButton cancelButton = new JButton("取消");
    panelBottom.add(okButton);
    panelBottom.add(cancelButton);

    okButton.addActionListener(e -> addPlanningEntryAction(dialog, nameField, startTimeField, endTimeField,
      startLocationComboBox, targetLocationComboBox));
    cancelButton.addActionListener(e -> {
      dialog.setVisible(false);
      dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));
    });

    dialog.pack();
    dialog.setVisible(true);
  }

  private void addPlanningEntryAction(JDialog dialog, JTextField nameField, JTextField startTimeField,
                                      JTextField endTimeField, JComboBox<Location> startLocationComboBox, JComboBox<Location> targetLocationComboBox) {
    String plane = null;
    Timeslot timeslot;
    Location startLocation = null, targetLocation = null;
    FlightEntry entry;

    try {
      plane = nameField.getText();
      timeslot = Timeslot.parse(startTimeField.getText(), endTimeField.getText());
      startLocation = (Location) startLocationComboBox.getSelectedItem();
      targetLocation = (Location) targetLocationComboBox.getSelectedItem();
      entry = new FlightEntry(plane, timeslot, startLocation, targetLocation);
      if (board.addEntry(entry)) {
        logger.info("Add a planning entry: " + entry);
        JOptionPane.showMessageDialog(this, "添加成功", "成功", INFORMATION_MESSAGE);
        refreshTable();
      } else {
        logger.error("Fail to add entry " + entry + ", conflicting with others");
        JOptionPane.showMessageDialog(dialog, "产生冲突 无法添加", "错误", ERROR_MESSAGE);
      }
    } catch (Exception exception) {
      logger.error("Fail to parse param when construct a flight entry.");
      if (logger.isDebugEnabled()) {
        logger.debug("plane: " + plane + ", startTime: " + startTimeField.getText() + ", endTime: "
          + endTimeField.getText() + ", startLocation:" + startLocation + ", targetLocation:" + targetLocation);
      }
      JOptionPane.showMessageDialog(dialog, "参数错误 无法添加", "错误", ERROR_MESSAGE);
    } finally {
      dialog.setVisible(false);
      dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));
    }
  }

  protected void showRemovePlanningEntryDialog() {
    Object[] array = board.getEntries().toArray();
    if (array.length == 0) {
      logger.error("The entries list is empty.");
      JOptionPane.showMessageDialog(this, "计划项列表为空", "错误", ERROR_MESSAGE);
      return;
    }
    FlightPlanningEntry entry = (FlightPlanningEntry) JOptionPane.showInputDialog(this, "选择", "删除计划项",
      INFORMATION_MESSAGE, null, array, array[0]);
    if (board.removeEntry(entry)) {
      logger.info("Removed entry: " + entry);
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
    JComboBox<String> stateComboBox = new JComboBox<>(new String[]{"分配资源", "起飞", "降落", "取消"});
    JButton execButton = new JButton("运行");
    panel.add(entryComboBox);
    panel.add(stateComboBox);
    panel.add(execButton);

    execButton.addActionListener(e -> {
      FlightPlanningEntry entry = (FlightPlanningEntry) entryComboBox.getSelectedItem();
      try {
        assert entry != null;
        switch (stateComboBox.getSelectedIndex()) {
          case 0:
            if (allocateResourceAction(dialog, entry)) {
              return;
            }
            break;
          case 1:
            runEntryAction(entry);
            break;
          case 2:
            endEntryAction(entry);
            break;
          case 3:
            cancelEntryAction(entry);
            break;
          default:
            assert false;
        }
      } catch (IllegalStateTransitionException exception) {
        exception.printStackTrace();
        logger.error(exception.getMessage());
        JOptionPane.showMessageDialog(dialog, "无法转移状态", "错误", ERROR_MESSAGE);
      } catch (ResourceConflictException exception) {
        entry.setResource(null);
        JOptionPane.showMessageDialog(dialog, "资源冲突\n" + exception.getMessage(), "错误", ERROR_MESSAGE);
      }
      refreshTable();
    });

    dialog.pack();
    dialog.setVisible(true);
  }

  private void cancelEntryAction(FlightPlanningEntry entry) throws IllegalStateTransitionException {
    entry.cancel();
    logger.info("Cancel entry " + entry);
  }

  private void endEntryAction(FlightPlanningEntry entry) throws IllegalStateTransitionException {
    entry.end();
    logger.info("End " + entry);
  }

  private void runEntryAction(FlightPlanningEntry entry) throws IllegalStateTransitionException {
    entry.run();
    logger.info("Start running " + entry);
  }

  private boolean allocateResourceAction(JDialog dialog, FlightPlanningEntry entry)
    throws IllegalStateTransitionException, ResourceConflictException {
    if (!(entry.getState().toString().contains("WAITING")) && !(entry.getState().toString().contains("ALLOCATED"))) {
      logger.error("Fail to convert the entry " + entry + " from " + entry.getState() + " to ALLOCATED STATE");
      throw new IllegalStateTransitionException("The entry is unable to allocate.");
    }
    if (planes.isEmpty()) {
      logger.error("The planes list is empty.");
      JOptionPane.showMessageDialog(dialog, "资源列表为空", "错误", ERROR_MESSAGE);
      return true;
    }
    Object[] array = planes.toArray();
    Plane plane = (Plane) JOptionPane.showInputDialog(dialog, "选择", "选择资源", INFORMATION_MESSAGE, null, array, array[0]);
    if (plane == null) {
      return true;
    }
    entry.setResource(plane);
    for (FlightPlanningEntry anotherEntry : board.getEntries()) {
      if (entry == anotherEntry) {
        continue;
      }
      if (anotherEntry.conflictResourceWith(entry)) {
        logger.error("The entry: " + entry + "and entry: " + anotherEntry + "conflict on plane: " + plane);
        throw new ResourceConflictException(entry, anotherEntry, plane);
      }
    }
    entry.allocated();
    logger.info("Allocate entry " + entry + " with resource " + plane);
    return false;
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
    checkLocationButton
      .addActionListener(e -> checkConflictAction(dialog, ENTRY_APIS.checkLocationConflict(board.getEntries())));
    checkResourcesButton.addActionListener(
      e -> checkConflictAction(dialog, ENTRY_APIS.checkResourceExclusiveConflict(board.getEntries())));
    dialog.pack();
    dialog.setVisible(true);
  }

  private void checkConflictAction(JDialog dialog, boolean b) {
    logger.info("Checking conflict.");
    if (b) {
      JOptionPane.showMessageDialog(dialog, "有冲突", "错误", ERROR_MESSAGE);
    } else {
      JOptionPane.showMessageDialog(dialog, "无冲突", "消息", INFORMATION_MESSAGE);
    }
  }

  protected void refreshTable() {
    keyLocationPanel.removeAll();

    Location keyLocation = (Location) keyLocationComboBox.getSelectedItem();
    logger.info("Refresh board with location: " + keyLocation);
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
    tablePanel = new JScrollPane(board.generateTable());
    tablePanel.validate();
    tablePanel.repaint();

    setLayout(new BorderLayout());
    add(keyLocationPanel, BorderLayout.NORTH);
    getContentPane().add(tablePanel, BorderLayout.CENTER);
    validate();
    repaint();
    pack();
  }
}
