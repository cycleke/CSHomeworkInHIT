package train;

import base.*;
import org.apache.log4j.Logger;
import train.Board.IteratorType;
import train.TrainCabin.TrainCabinType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class {@link TrainScheduleApp} is a GUI client for Train schedule.
 *
 * @author cycleke
 */
public class TrainScheduleApp extends JFrame {
  private static final long serialVersionUID = 8832753377771452006L;

  private final static PlanningEntryApis<TrainCabin> ENTRY_APIS = new PlanningEntryApisBruteForceStrategy<>();
  private final static Logger logger = Logger.getLogger(TrainScheduleApp.class);
  private final static String[] OPERATIONS = {"添加资源", "删除资源", "添加位置", "删除位置", "添加计划项", "取消计划项", "分配资源", "启动计划项",
    "阻塞计划项", "结束计划项", "检测冲突", "查询计划项", "更换信息板"};
  private final static String[] METHODS = {"addResourceAction", "showRemoveResourceDialog", "addLocationAction",
    "showRemoveLocationDialog", "addPlanningEntryAction", "cancelEntryAction", "allocateResourceAction",
    "runEntryAction", "blockEntryAction", "endEntryAction", "checkConflictAction", "showQueryResourceDialog",
    "refreshTable"};

  private final JPanel keyLocationPanel;
  private final JLabel keyLocationLabel;
  private final JButton refreshButton;
  private final Board board;
  private final List<TrainCabin> trainCabins;
  private final List<Location> locations;
  private JScrollPane tablePanel;
  private JComboBox<Location> keyLocationComboBox;

  // Abstraction function:
  // AF(e) = {entries, locations, resources}
  // Rep invariant:
  // All fields are not null.
  // Safety from rep exposure:
  // the field are all private.

  public TrainScheduleApp() {
    board = new Board();
    trainCabins = new ArrayList<>();
    locations = new ArrayList<>();

    JMenuBar menuBar = new JMenuBar();
    setJMenuBar(menuBar);

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
    keyLocationComboBox.addItem(CommonLocation.of("...", TrainPlanningEntry.LOCATION_SHAREABLE));
    refreshButton = new JButton("刷新");
    keyLocationPanel.add(keyLocationLabel);
    keyLocationPanel.add(keyLocationComboBox);
    keyLocationPanel.add(refreshButton);
    keyLocationPanel.setPreferredSize(new Dimension(500, 50));

    refreshButton.addActionListener(e -> refreshTable());

    setTitle("高铁管理");
    pack();
    setLocationRelativeTo(null);
    checkRep();
    logger.info("Start running Train ScheduleApp.");
  }

  public static void main(final String[] args) {
    try {
      final TrainScheduleApp app = new TrainScheduleApp();
      app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      app.pack();
      app.setVisible(true);
    } catch (final Exception e) {
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
    assert trainCabins != null;
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

  private JMenu createResourceMenu() {
    final JMenu resourceMenu = new JMenu("资源");
    resourceMenu.setMnemonic('r');

    final JMenuItem addItem = new JMenuItem("添加资源", 'a');
    final JMenuItem removeItem = new JMenuItem("删除资源", 'r');
    final JMenuItem queryItem = new JMenuItem("查询资源的所有计划项");
    resourceMenu.add(addItem);
    resourceMenu.add(removeItem);
    resourceMenu.add(queryItem);

    addItem.addActionListener(e -> showAddResourceDialog());
    removeItem.addActionListener(e -> {
      try {
        showRemoveResourceDialog();
      } catch (UnfinishedEntryUsingTheResourceException exception) {
        JOptionPane.showMessageDialog(this, "有尚未结束的计划项正在占用该资源\n" + exception.getMessage(), "错误",
          JOptionPane.ERROR_MESSAGE);
      }
    });
    queryItem.addActionListener(e -> showQueryResourceDialog());

    return resourceMenu;
  }

  protected void showQueryResourceDialog() {
    if (trainCabins.isEmpty()) {
      logger.error("The train cabins list is empty.");
      JOptionPane.showMessageDialog(this, "资源资源为空", "错误", JOptionPane.ERROR_MESSAGE);
      return;
    }
    final Object[] array = trainCabins.toArray();
    TrainCabin trainCabin = (TrainCabin) JOptionPane.showInputDialog(this, "选择", "查询资源",
      JOptionPane.INFORMATION_MESSAGE, null, array, array[0]);
    if (trainCabin == null) {
      return;
    }
    logger.info("Query resource:" + trainCabin);
    final List<TrainPlanningEntry> entries = new ArrayList<>();
    for (final TrainPlanningEntry entry : board.getEntries()) {
      if (entry.getResources().contains(trainCabin)) {
        entries.add(entry);
      }
    }

    int count = 0;
    final Object[][] data = new Object[entries.size()][1];
    for (final Object object : entries) {
      data[count++][0] = object;
    }
    final JDialog dialog = new JDialog(this);
    dialog.setLayout(new BorderLayout());
    final JPanel queryPanel = new JPanel(new FlowLayout());
    final JScrollPane tablePanel = new JScrollPane(new JTable(new DefaultTableModel(data, new String[]{"班次"})));
    dialog.add(queryPanel, BorderLayout.NORTH);
    dialog.getContentPane().add(tablePanel, BorderLayout.CENTER);

    final JButton queryButton = new JButton("查询前项");
    queryPanel.add(queryButton);

    final Object[] entryObjects = entries.toArray();
    queryButton.addActionListener(e -> {
      if (!entries.isEmpty()) {
        final Object object = JOptionPane.showInputDialog(dialog, "选择", "查询前项", JOptionPane.INFORMATION_MESSAGE, null,
          entryObjects, entryObjects[0]);
        if (object == null) {
          return;
        }
        TrainPlanningEntry entry = (TrainPlanningEntry) object;
        entry = (TrainPlanningEntry) ENTRY_APIS.findPreEntryPerResource(trainCabin, entry, entries);
        JOptionPane.showMessageDialog(dialog, entry.toString());
      }
    });

    dialog.pack();
    dialog.setVisible(true);
  }

  protected void showAddResourceDialog() {
    final JDialog dialog = new JDialog(this);
    dialog.setLocationRelativeTo(null);
    dialog.setTitle("添加资源");
    final JPanel panelUp = new JPanel();
    final JPanel panelBottom = new JPanel();

    dialog.setLayout(new BorderLayout());
    dialog.add(panelUp, BorderLayout.CENTER);
    dialog.add(panelBottom, BorderLayout.SOUTH);

    // 输入部分
    panelUp.setLayout(new GridLayout(4, 2));
    final JLabel idLabel = new JLabel("编号");
    final JPanel idPanel = new JPanel(new FlowLayout());
    final JLabel typeLabel = new JLabel("类型");
    final JPanel typePanel = new JPanel(new FlowLayout());
    final JLabel seatsLabel = new JLabel("座位数");
    final JPanel seatsPanel = new JPanel(new FlowLayout());
    final JLabel yearLabel = new JLabel("出厂年份");
    final JPanel yearPanel = new JPanel();
    panelUp.add(idLabel);
    panelUp.add(idPanel);
    panelUp.add(typeLabel);
    panelUp.add(typePanel);
    panelUp.add(seatsLabel);
    panelUp.add(seatsPanel);
    panelUp.add(yearLabel);
    panelUp.add(yearPanel);

    final TrainCabinType[] types = {TrainCabinType.BUSINESS, TrainCabinType.FIRST_CLASS, TrainCabinType.SECOND_CLASS,
      TrainCabinType.SOFT_SLEEPER, TrainCabinType.HARD_SLEEPER, TrainCabinType.HARD_SEAT, TrainCabinType.LUGGAGE_CAR,
      TrainCabinType.DINING_CAR};

    final JTextField idField = new JTextField(10);
    final JComboBox<TrainCabinType> typeComboBox = new JComboBox<>(types);
    final JTextField seatsField = new JTextField(10);
    final JTextField yearField = new JTextField(10);
    idField.setDocument(new MyRegExp("^\\d*$", 3));
    seatsField.setDocument(new MyRegExp("^[1-9]\\d*$", 9));
    yearField.setDocument(new MyRegExp("^\\d{0,4}$", 4));

    idPanel.add(idField);
    typePanel.add(typeComboBox);
    seatsPanel.add(seatsField);
    yearPanel.add(yearField);

    // 控制部分
    panelBottom.setLayout(new FlowLayout());
    final JButton okButton = new JButton("确定");
    final JButton cancelButton = new JButton("取消");
    panelBottom.add(okButton);
    panelBottom.add(cancelButton);

    okButton.addActionListener(e -> addResourceAction(dialog, idField, typeComboBox, seatsField, yearField));
    cancelButton.addActionListener(e -> {
      dialog.setVisible(false);
      dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));
    });

    dialog.pack();
    dialog.setVisible(true);
  }

  private void addResourceAction(JDialog dialog, JTextField idField, JComboBox<TrainCabinType> typeComboBox,
                                 JTextField seatsField, JTextField yearField) {
    int id = 0, seats = 0, year = 0;
    TrainCabinType type = null;
    TrainCabin trainCabin = null;

    try {
      id = Integer.parseInt(idField.getText());
      type = (TrainCabinType) typeComboBox.getSelectedItem();
      seats = Integer.parseInt(seatsField.getText());
      year = Integer.parseInt(yearField.getText());
      trainCabin = new TrainCabin(id, type, seats, year);
      trainCabins.add(trainCabin);
      logger.info("Add a train cabin: " + trainCabin);
      JOptionPane.showMessageDialog(this, "添加成功", "成功", JOptionPane.INFORMATION_MESSAGE);
    } catch (final NumberFormatException exception) {
      logger.error("Fail to parse param when construct a train cabin.");
      if (logger.isDebugEnabled()) {
        logger.debug(
          "ID: " + id + ", type: " + type + ", seats: " + seats + ", year: " + year + ", trainCabin: " + trainCabin);
      }
      JOptionPane.showMessageDialog(dialog, "参数错误 无法添加", "错误", JOptionPane.ERROR_MESSAGE);
    } finally {
      dialog.setVisible(false);
      dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));
    }
  }

  protected void showRemoveResourceDialog() throws UnfinishedEntryUsingTheResourceException {
    if (trainCabins.isEmpty()) {
      logger.error("The train cabins list is empty.");
      JOptionPane.showMessageDialog(this, "资源资源为空", "错误", JOptionPane.ERROR_MESSAGE);
      return;
    }
    final Object[] array = trainCabins.toArray();
    TrainCabin trainCabin = (TrainCabin) JOptionPane.showInputDialog(this, "选择", "删除资源",
      JOptionPane.INFORMATION_MESSAGE, null, array, array[0]);
    if (trainCabin == null) {
      return;
    }
    for (TrainPlanningEntry entry : board.getEntries()) {
      String state = entry.getState().toString();
      if (state.contains("ENDED") || state.contains("CANCELLED")) {
        continue;
      }
      if (entry.getResources().contains(trainCabin)) {
        logger.error("Fail to remove resource: " + trainCabin + ", a unfinished entry:" + entry + " is using it");
        throw new UnfinishedEntryUsingTheResourceException(entry, trainCabin);
      }
    }
    if (trainCabins.remove(trainCabin)) {
      logger.info("Removed trainCabin: " + trainCabin);
      JOptionPane.showMessageDialog(this, "删除成功");
    }
  }

  private JMenu createLocationMenu() {
    final JMenu locationMenu = new JMenu("位置");
    locationMenu.setMnemonic('l');
    final JMenuItem addItem = new JMenuItem("添加位置", 'a');
    final JMenuItem removeItem = new JMenuItem("删除位置", 'r');
    locationMenu.add(addItem);
    locationMenu.add(removeItem);

    addItem.addActionListener(e -> showAddLocationDialog());
    removeItem.addActionListener(e -> {
      try {
        showRemoveLocationDialog();
      } catch (UnfinishedEntryMayReachThisLocationException exception) {
        JOptionPane.showMessageDialog(this, "有尚未结束的计划项可能到达该位置\n" + exception.getMessage(), "错误",
          JOptionPane.ERROR_MESSAGE);
      }
    });
    return locationMenu;
  }

  protected void showAddLocationDialog() {
    final JDialog dialog = new JDialog(this);
    dialog.setLocationRelativeTo(null);
    dialog.setTitle("添加位置");
    final JPanel panelUp = new JPanel();
    final JPanel panelBottom = new JPanel();

    dialog.setLayout(new BorderLayout());
    dialog.add(panelUp, BorderLayout.CENTER);
    dialog.add(panelBottom, BorderLayout.SOUTH);

    // 输入部分
    panelUp.setLayout(new GridLayout(1, 2));
    final JLabel locationLabel = new JLabel("位置");
    final JPanel locationPanel = new JPanel(new FlowLayout());
    panelUp.add(locationLabel);
    panelUp.add(locationPanel);

    final JTextField locationField = new JTextField(20);
    locationPanel.add(locationField);

    // 控制部分
    panelBottom.setLayout(new FlowLayout());
    final JButton okButton = new JButton("确定");
    final JButton cancelButton = new JButton("取消");
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
    final String location = locationField.getText();
    if (location.isEmpty()) {
      logger.error("The location param is empty.");
      JOptionPane.showMessageDialog(dialog, "参数错误 无法添加", "错误", JOptionPane.ERROR_MESSAGE);
      return;
    }
    CommonLocation commonLocation = CommonLocation.of(location, TrainPlanningEntry.LOCATION_SHAREABLE);
    locations.add(commonLocation);
    logger.info("Add a location: " + commonLocation);
    JOptionPane.showMessageDialog(this, "添加成功", "成功", JOptionPane.INFORMATION_MESSAGE);
    refreshTable();
    dialog.setVisible(false);
    dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));
  }

  protected void showRemoveLocationDialog() throws UnfinishedEntryMayReachThisLocationException {
    if (locations.isEmpty()) {
      logger.error("The locations list is empty.");
      JOptionPane.showMessageDialog(this, "位置列表为空", "错误", JOptionPane.ERROR_MESSAGE);
      return;
    }
    final Object[] array = locations.toArray();
    Location location = (Location) JOptionPane.showInputDialog(this, "选择", "删除位置", JOptionPane.INFORMATION_MESSAGE,
      null, array, array[0]);
    if (location == null) {
      return;
    }
    for (TrainPlanningEntry entry : board.getEntries()) {
      String state = entry.getState().toString();
      if (state.contains("ENDED") || state.contains("CANCELLED")) {
        continue;
      }
      List<Location> locations = entry.getLocations();
      int currentIndex = locations.indexOf(entry.getCurrentLocation());
      int indexOfLocation = locations.indexOf(location);
      if (currentIndex <= indexOfLocation) {
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
    final JMenu planningEntryMenu = new JMenu("计划项");
    planningEntryMenu.setMnemonic('p');
    final JMenuItem addItem = new JMenuItem("添加计划项", 'a');
    final JMenuItem removeItem = new JMenuItem("删除计划项", 'r');
    final JMenuItem changeStateItem = new JMenuItem("改变计划项状态", 'c');
    final JMenuItem checkConflictItem = new JMenuItem("检测冲突", 't');
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
    final JDialog dialog = new JDialog(this);
    dialog.setLocationRelativeTo(null);
    dialog.setTitle("添加计划项");
    final JPanel panelUp = new JPanel();
    final JPanel panelBottom = new JPanel();

    dialog.setLayout(new BorderLayout());
    dialog.add(panelUp, BorderLayout.CENTER);
    dialog.add(panelBottom, BorderLayout.SOUTH);

    // 输入部分
    panelUp.setLayout(new GridLayout(5, 2));
    final JLabel nameLabel = new JLabel("名称");
    final JPanel namePanel = new JPanel(new FlowLayout());
    final JLabel startTimeLabel = new JLabel("出发时间(yyyy-MM-dd HH:mm格式)");
    final JPanel startTimePanel = new JPanel(new FlowLayout());
    final JLabel endTimeLabel = new JLabel("到达时间(yyyy-MM-dd HH:mm格式)");
    final JPanel endTimePanel = new JPanel(new FlowLayout());
    final JLabel startLocationLabel = new JLabel("起点");
    final JPanel startLocationPanel = new JPanel();
    final JLabel targetLocationLabel = new JLabel("终点");
    final JPanel targetLocationPanel = new JPanel();
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

    final JTextField nameField = new JTextField(20);
    final JTextField startTimeField = new JTextField(16);
    final JTextField endTimeField = new JTextField(16);
    final JComboBox<Location> startLocationComboBox = new JComboBox<>();
    final JComboBox<Location> targetLocationComboBox = new JComboBox<>();
    locations.forEach(location -> {
      startLocationComboBox.addItem(location);
      targetLocationComboBox.addItem(location);
    });
    namePanel.add(nameField);
    startTimePanel.add(startTimeField);
    endTimePanel.add(endTimeField);
    startLocationPanel.add(startLocationComboBox);
    targetLocationPanel.add(targetLocationComboBox);

    // 控制部分
    panelBottom.setLayout(new FlowLayout());
    final JButton okButton = new JButton("确定");
    final JButton addButton = new JButton("添加一对起止位置/时间");
    final JButton cancelButton = new JButton("取消");
    panelBottom.add(okButton);
    panelBottom.add(addButton);
    panelBottom.add(cancelButton);

    final List<Location> locations = new ArrayList<>();
    final List<Timeslot> timeslots = new ArrayList<>();

    okButton.addActionListener(e -> addPlanningEntryAction(dialog, nameField, locations, timeslots));

    addButton.addActionListener(e -> addSegmentAction(dialog, startTimeField, endTimeField, startLocationComboBox,
      targetLocationComboBox, locations, timeslots));

    cancelButton.addActionListener(e -> {
      dialog.setVisible(false);
      dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));
    });

    dialog.pack();
    dialog.setVisible(true);
  }

  private void addSegmentAction(JDialog dialog, JTextField startTimeField, JTextField endTimeField,
                                JComboBox<Location> startLocationComboBox, JComboBox<Location> targetLocationComboBox, List<Location> locations,
                                List<Timeslot> timeslots) {
    try {
      final Timeslot timeslot = Timeslot.parse(startTimeField.getText(), endTimeField.getText());
      final Location startLocation = (Location) startLocationComboBox.getSelectedItem();
      final Location targetLocation = (Location) targetLocationComboBox.getSelectedItem();
      if (!locations.isEmpty()) {
        if (startLocation != locations.get(locations.size() - 1)) {
          logger.error("The Location changed between to segment.");
          throw new RuntimeException("The Location changed between to segment.");
        } else {
          if (timeslot.getStartTime().compareTo(timeslots.get(timeslots.size() - 1).getEndTime()) <= 0) {
            logger.error("The timeslots don't increase between two segments.");
            throw new RuntimeException("The timeslots must increase.");
          }
        }
      } else {
        locations.add(startLocation);
        logger.info("Add the start station: " + startLocation);
      }
      timeslots.add(timeslot);
      logger.info("Add a timeslot: " + timeslot);
      locations.add(targetLocation);
      logger.info("Add a stop: " + targetLocation);
      startLocationComboBox.setSelectedItem(targetLocation);
    } catch (final Exception exception) {
      exception.printStackTrace();
      logger.error("Fail to add the segment.");
      JOptionPane.showMessageDialog(dialog, "参数错误 无法添加", "错误", JOptionPane.ERROR_MESSAGE);
    } finally {
      logger.info("Add a segment successfully.");
      JOptionPane.showMessageDialog(dialog, "添加成功", "成功", JOptionPane.INFORMATION_MESSAGE);
    }
  }

  private void addPlanningEntryAction(JDialog dialog, JTextField nameField, List<Location> locations,
                                      List<Timeslot> timeslots) {
    String name = null;
    try {
      name = nameField.getText();
      if (!timeslots.isEmpty() && timeslots.size() + 1 == locations.size()) {
        TrainEntry entry = new TrainEntry(name, timeslots, locations);
        if (board.addEntry(entry)) {
          logger.info("Add a planning entry: " + entry);
          JOptionPane.showMessageDialog(this, "添加成功", "成功", JOptionPane.INFORMATION_MESSAGE);
          refreshTable();
        } else {
          logger.error("Fail to add entry " + entry + ", conflicting with others");
          JOptionPane.showMessageDialog(dialog, "产生冲突 无法添加", "错误", JOptionPane.ERROR_MESSAGE);
        }
      } else {
        throw new IllegalArgumentException();
      }
    } catch (final Exception exception) {
      logger.error("Fail to parse param when construct a course entry.");
      if (logger.isDebugEnabled()) {
        logger.debug("name: " + name + ", timeslots: " + timeslots + ", locations: " + locations);
      }
      JOptionPane.showMessageDialog(dialog, "参数错误 无法添加", "错误", JOptionPane.ERROR_MESSAGE);
    } finally {
      dialog.setVisible(false);
      dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));
    }
  }

  protected void showRemovePlanningEntryDialog() {
    final Object[] array = board.getEntries().toArray();
    if (array.length == 0) {
      logger.error("The entries list is empty.");
      JOptionPane.showMessageDialog(this, "计划项列表为空", "错误", JOptionPane.ERROR_MESSAGE);
      return;
    }
    TrainPlanningEntry entry = (TrainPlanningEntry) JOptionPane.showInputDialog(this, "选择", "删除计划项",
      JOptionPane.INFORMATION_MESSAGE, null, array, array[0]);
    if (board.removeEntry(entry)) {
      logger.info("Removed entry: " + entry);
      JOptionPane.showMessageDialog(this, "删除成功");
      refreshTable();
    }
  }

  protected void showChangePlanningEntryStateDialog() {
    final JDialog dialog = new JDialog(this);
    dialog.setLocationRelativeTo(null);
    dialog.setTitle("改变计划项状态");

    final JPanel panel = new JPanel(new GridLayout(3, 1));
    dialog.add(panel);

    final JComboBox<TrainPlanningEntry> entryComboBox = new JComboBox<>();
    for (final TrainPlanningEntry entry : board.getEntries()) {
      entryComboBox.addItem(entry);
    }
    final JComboBox<String> stateComboBox = new JComboBox<>(new String[]{"分配资源", "出发", "中停", "到达终点", "取消"});
    final JButton execButton = new JButton("运行");
    panel.add(entryComboBox);
    panel.add(stateComboBox);
    panel.add(execButton);

    execButton.addActionListener(e -> {
      final TrainPlanningEntry entry = (TrainPlanningEntry) entryComboBox.getSelectedItem();
      assert entry != null;
      try {
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
            blockEntryAction(entry);
            break;
          case 3:
            endEntryAction(entry);
            break;
          case 4:
            cancelEntryAction(entry);
          default:
            assert false;
        }
      } catch (final IllegalStateTransitionException exception) {
        exception.printStackTrace();
        logger.error(exception.getMessage());
        JOptionPane.showMessageDialog(dialog, "无法转移状态", "错误", JOptionPane.ERROR_MESSAGE);
      } catch (ResourceConflictException exception) {
        entry.setResources(Collections.emptyList());
        JOptionPane.showMessageDialog(dialog, "资源冲突\n" + exception.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
      }
      refreshTable();
    });

    dialog.pack();
    dialog.setVisible(true);
  }

  private void blockEntryAction(TrainPlanningEntry entry) throws IllegalStateTransitionException {
    entry.block();
    logger.info("Block entry " + entry);
  }

  private void cancelEntryAction(TrainPlanningEntry entry) throws IllegalStateTransitionException {
    entry.cancel();
    logger.info("Cancel entry " + entry);

  }

  private void endEntryAction(TrainPlanningEntry entry) throws IllegalStateTransitionException {
    entry.end();
    logger.info("End " + entry);

  }

  private void runEntryAction(TrainPlanningEntry entry) throws IllegalStateTransitionException {
    entry.run();
    logger.info("Start running " + entry);

  }

  private boolean allocateResourceAction(JDialog dialog, TrainPlanningEntry entry)
    throws IllegalStateTransitionException, ResourceConflictException {
    if (!entry.getState().toString().contains("WAITING") && !entry.getState().toString().contains("ALLOCATED")) {
      logger.error("Fail to convert the entry " + entry + " from " + entry.getState() + " to ALLOCATED STATE");
      throw new IllegalStateTransitionException("The entry is unable to allocate.");
    }
    if (trainCabins.isEmpty()) {
      logger.error("The train cabins list is empty.");
      JOptionPane.showMessageDialog(dialog, "资源列表为空", "错误", JOptionPane.ERROR_MESSAGE);
      return true;
    }
    final Object[] array = trainCabins.toArray();
    TrainCabin trainCabin = (TrainCabin) JOptionPane.showInputDialog(dialog, "选择", "选择资源",
      JOptionPane.INFORMATION_MESSAGE, null, array, array[0]);
    if (trainCabin == null) {
      return true;
    }
    final List<TrainCabin> trainCabins = entry.getResources();
    trainCabins.add(trainCabin);
    entry.setResources(trainCabins);
    for (TrainPlanningEntry anotherEntry : board.getEntries()) {
      if (entry == anotherEntry) {
        continue;
      }
      if (anotherEntry.conflictResourceWith(entry)) {
        logger.error("The entry: " + entry + "and entry: " + anotherEntry + "conflict on train cabin: " + trainCabin);
        throw new ResourceConflictException(entry, anotherEntry, trainCabin);
      }
    }
    entry.allocated();
    logger.info("Allocate entry " + entry + " with resource " + trainCabins);
    return false;
  }

  protected void showCheckPlanningEntryConflictDialog() {
    final JDialog dialog = new JDialog(this);
    dialog.setLocationRelativeTo(null);
    dialog.setTitle("检测冲突");

    final JPanel panel = new JPanel(new GridLayout(2, 1));
    dialog.add(panel);

    final JButton checkLocationButton = new JButton("检测位置冲突");
    final JButton checkResourcesButton = new JButton("检测资源冲突");
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
      JOptionPane.showMessageDialog(dialog, "有冲突", "错误", JOptionPane.ERROR_MESSAGE);
    } else {
      JOptionPane.showMessageDialog(dialog, "无冲突", "消息", JOptionPane.INFORMATION_MESSAGE);
    }
  }

  protected void refreshTable() {
    keyLocationPanel.removeAll();

    final Location keyLocation = (Location) keyLocationComboBox.getSelectedItem();
    logger.info("Refresh board with location: " + keyLocation);
    keyLocationComboBox = new JComboBox<>();
    keyLocationComboBox.addItem(CommonLocation.of("...", TrainPlanningEntry.LOCATION_SHAREABLE));

    for (final Location location : locations) {
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
