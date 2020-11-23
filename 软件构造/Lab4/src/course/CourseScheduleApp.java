package course;

import base.*;
import org.apache.log4j.Logger;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * This class {@link CourseScheduleApp} is a GUI client for Course schedule.
 *
 * @author cycleke
 */
public class CourseScheduleApp extends JFrame {
  private static final long serialVersionUID = -6609310539981693817L;

  private final static PlanningEntryApis<Teacher> ENTRY_APIS = new PlanningEntryApisBruteForceStrategy<>();
  private final static Logger logger = Logger.getLogger(CourseScheduleApp.class);
  private final static String[] OPERATIONS = {"添加资源", "删除资源", "添加位置", "删除位置", "添加计划项", "取消计划项", "分配资源", "启动计划项",
    "更换教室", "结束计划项", "检测冲突", "查询计划项", "更换信息板"};
  private final static String[] METHODS = {"addResourceAction", "showRemoveResourceDialog", "addLocationAction",
    "showRemoveLocationDialog", "addPlanningEntryAction", "cancelEntryAction", "allocateResourceAction",
    "runEntryAction", "showChangePlanningEntryLocationDialog", "endEntryAction", "checkConflictAction",
    "showQueryResourceDialog", "refreshTable"};

  private final JPanel keyLocationPanel;
  private final JLabel keyLocationLabel;
  private final JButton refreshButton;
  private final Board board;
  private final List<Teacher> teachers;
  private final List<Location> locations;
  private JScrollPane tablePanel;
  private JComboBox<Location> keyLocationComboBox;

  // Abstraction function:
  // AF(e) = {entries, locations, resources}
  // Rep invariant:
  // All fields are not null.
  // Safety from rep exposure:
  // the field are all private.

  public CourseScheduleApp() {
    board = new Board();
    teachers = new ArrayList<>();
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
    keyLocationComboBox.addItem(CommonLocation.of("...", CoursePlanningEntry.LOCATION_SHAREABLE));
    refreshButton = new JButton("刷新");
    keyLocationPanel.add(keyLocationLabel);
    keyLocationPanel.add(keyLocationComboBox);
    keyLocationPanel.add(refreshButton);
    keyLocationPanel.setPreferredSize(new Dimension(500, 50));

    refreshButton.addActionListener(e -> refreshTable());

    setTitle("课程管理");
    pack();
    setLocationRelativeTo(null);
    checkRep();
    logger.info("Start running Course ScheduleApp.");
  }

  public static void main(String[] args) {
    try {
      CourseScheduleApp app = new CourseScheduleApp();
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
    assert teachers != null;
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
        JOptionPane.showMessageDialog(this, "有尚未结束的计划项正在占用该资源\n" + exception.getMessage(), "错误",
          JOptionPane.ERROR_MESSAGE);
      }
    });
    queryItem.addActionListener(e -> showQueryResourceDialog());

    return resourceMenu;
  }

  protected void showQueryResourceDialog() {
    if (teachers.isEmpty()) {
      logger.error("The teachers list is empty.");
      JOptionPane.showMessageDialog(this, "资源资源为空", "错误", JOptionPane.ERROR_MESSAGE);
      return;
    }
    Object[] array = teachers.toArray();
    Teacher teacher = (Teacher) JOptionPane.showInputDialog(this, "选择", "查询资源", JOptionPane.INFORMATION_MESSAGE, null,
      array, array[0]);
    if (teacher == null) {
      return;
    }
    logger.info("Query resource:" + teacher);
    List<CoursePlanningEntry> entries = new ArrayList<>();
    for (CoursePlanningEntry entry : board.getEntries()) {
      if (entry.getResource() != null && entry.getResource().equals(teacher)) {
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
    JScrollPane tablePanel = new JScrollPane(new JTable(new DefaultTableModel(data, new String[]{"课程"})));
    dialog.add(queryPanel, BorderLayout.NORTH);
    dialog.getContentPane().add(tablePanel, BorderLayout.CENTER);

    JButton queryButton = new JButton("查询前项");
    queryPanel.add(queryButton);

    Object[] entryObjects = entries.toArray();
    queryButton.addActionListener(e -> {
      if (!entries.isEmpty()) {
        Object object = JOptionPane.showInputDialog(dialog, "选择", "查询前项", JOptionPane.INFORMATION_MESSAGE, null,
          entryObjects, entryObjects[0]);
        if (object == null) {
          return;
        }
        CoursePlanningEntry entry = (CoursePlanningEntry) object;
        entry = (CoursePlanningEntry) ENTRY_APIS.findPreEntryPerResource(teacher, entry, entries);
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
    JPanel panelUp = new JPanel();
    JPanel panelBottom = new JPanel();

    dialog.setLayout(new BorderLayout());
    dialog.add(panelUp, BorderLayout.CENTER);
    dialog.add(panelBottom, BorderLayout.SOUTH);

    // 输入部分
    panelUp.setLayout(new GridLayout(4, 2));
    JLabel idNumberLabel = new JLabel("身份证号");
    JPanel idNumberPanel = new JPanel(new FlowLayout());
    JLabel nameLabel = new JLabel("名称");
    JPanel namePanel = new JPanel(new FlowLayout());
    JLabel sexLabel = new JLabel("性别");
    JPanel sexPanel = new JPanel(new FlowLayout());
    JLabel titleLabel = new JLabel("职称");
    JPanel titlePanel = new JPanel();
    panelUp.add(idNumberLabel);
    panelUp.add(idNumberPanel);
    panelUp.add(nameLabel);
    panelUp.add(namePanel);
    panelUp.add(sexLabel);
    panelUp.add(sexPanel);
    panelUp.add(titleLabel);
    panelUp.add(titlePanel);

    JTextField idNumberField = new JTextField(20);
    JTextField nameField = new JTextField(20);
    JTextField sexField = new JTextField(20);
    JTextField titleField = new JTextField(20);
    idNumberField.setDocument(new MyRegExp("^(\\d{0,18}|\\d{17}[0-9Xx]|\\d{14}[0-9Xx])$", 18));

    idNumberPanel.add(idNumberField);
    namePanel.add(nameField);
    sexPanel.add(sexField);
    titlePanel.add(titleField);

    // 控制部分
    panelBottom.setLayout(new FlowLayout());
    JButton okButton = new JButton("确定");
    JButton cancelButton = new JButton("取消");
    panelBottom.add(okButton);
    panelBottom.add(cancelButton);

    okButton.addActionListener(e -> addResourceAction(dialog, idNumberField, nameField, sexField, titleField));
    cancelButton.addActionListener(e -> {
      dialog.setVisible(false);
      dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));
    });

    dialog.pack();
    dialog.setVisible(true);
  }

  private void addResourceAction(JDialog dialog, JTextField idNumberField, JTextField nameField, JTextField sexField,
                                 JTextField titleField) {
    try {
      String idNumber = idNumberField.getText();
      String name = nameField.getText();
      String sex = sexField.getText();
      String title = titleField.getText();
      boolean idNumberLengthValid = idNumber.length() == 15 || idNumber.length() == 18;
      if (!idNumberLengthValid || name.isEmpty() || sex.isEmpty() || title.isEmpty()) {
        logger.error("Fail to parse param when construct a teacher.");
        if (logger.isDebugEnabled()) {
          logger.debug("IDNumber: " + idNumber + ", name: " + name + ", sex: " + sex + ", title: " + title);
        }
        JOptionPane.showMessageDialog(dialog, "参数错误 无法添加", "错误", JOptionPane.ERROR_MESSAGE);
        return;
      }
      Teacher teacher = new Teacher(idNumber, name, sex, title);
      teachers.add(teacher);
      logger.info("Add a teacher: " + teacher);
      JOptionPane.showMessageDialog(this, "添加成功", "成功", JOptionPane.INFORMATION_MESSAGE);
    } finally {
      dialog.setVisible(false);
      dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));
    }
  }

  protected void showRemoveResourceDialog() throws UnfinishedEntryUsingTheResourceException {
    if (teachers.isEmpty()) {
      logger.error("The teachers list is empty.");
      JOptionPane.showMessageDialog(this, "资源资源为空", "错误", JOptionPane.ERROR_MESSAGE);
      return;
    }
    Object[] array = teachers.toArray();
    Teacher teacher = (Teacher) JOptionPane.showInputDialog(this, "选择", "删除资源", JOptionPane.INFORMATION_MESSAGE, null,
      array, array[0]);
    if (teacher == null) {
      return;
    }
    for (CoursePlanningEntry entry : board.getEntries()) {
      String state = entry.getState().toString();
      if (state.contains("ENDED") || state.contains("CANCELLED")) {
        continue;
      }
      if (teacher.equals(entry.getResource())) {
        logger.error("Fail to remove resource: " + teacher + ", a unfinished entry:" + entry + " is using it");
        throw new UnfinishedEntryUsingTheResourceException(entry, teacher);
      }
    }
    if (teachers.remove(teacher)) {
      logger.info("Removed teacher: " + teacher);
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
        JOptionPane.showMessageDialog(this, "有尚未结束的计划项可能到达该位置\n" + exception.getMessage(), "错误",
          JOptionPane.ERROR_MESSAGE);
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
      JOptionPane.showMessageDialog(dialog, "参数错误 无法添加", "错误", JOptionPane.ERROR_MESSAGE);
      return;
    }
    CommonLocation commonLocation = CommonLocation.of(location, CoursePlanningEntry.LOCATION_SHAREABLE);
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
    Object[] array = locations.toArray();
    Location location = (Location) JOptionPane.showInputDialog(this, "选择", "删除位置", JOptionPane.INFORMATION_MESSAGE,
      null, array, array[0]);
    if (location == null) {
      return;
    }
    for (CoursePlanningEntry entry : board.getEntries()) {
      String state = entry.getState().toString();
      if (!state.contains("ENDED") && !state.contains("CANCELLED") && entry.getLocation().equals(location)) {
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
    JMenuItem changeLocationItem = new JMenuItem("更换教室", 'l');
    planningEntryMenu.add(addItem);
    planningEntryMenu.add(removeItem);
    planningEntryMenu.add(changeStateItem);
    planningEntryMenu.add(checkConflictItem);
    planningEntryMenu.add(changeLocationItem);

    addItem.addActionListener(e -> showAddPlanningEntryDialog());
    removeItem.addActionListener(e -> showRemovePlanningEntryDialog());
    changeStateItem.addActionListener(e -> showChangePlanningEntryStateDialog());
    checkConflictItem.addActionListener(e -> showCheckPlanningEntryConflictDialog());
    changeLocationItem.addActionListener(e -> showChangePlanningEntryLocationDialog());

    return planningEntryMenu;
  }

  private void showChangePlanningEntryLocationDialog() {
    Object[] array = board.getEntries().toArray();
    if (array.length == 0) {
      logger.error("There is no entry in App");
      JOptionPane.showMessageDialog(this, "计划项列表为空", "错误", JOptionPane.ERROR_MESSAGE);
      return;
    }
    CoursePlanningEntry entry = (CoursePlanningEntry) JOptionPane.showInputDialog(this, "选择", "选择计划项",
      JOptionPane.INFORMATION_MESSAGE, null, array, array[0]);
    if (entry == null) {
      return;
    }
    Location previousLocation = entry.getCurrentLocation();
    Location[] locations = this.locations.toArray(new Location[0]);
    Location location = (Location) JOptionPane.showInputDialog(this, "选择", "选择新的教室", JOptionPane.INFORMATION_MESSAGE,
      null, locations, locations[0]);
    if (location == null) {
      return;
    }
    try {
      entry.setLocation(location);
      for (CoursePlanningEntry anotherEntry : board.getEntries()) {
        if (entry.conflictLocationWith(anotherEntry)) {
          logger.error("Location conflict with entry: " + entry);
          throw new LocationConflictException(entry, anotherEntry, location);
        }
      }
    } catch (LocationConflictException e) {
      entry.setLocation(previousLocation);
      JOptionPane.showMessageDialog(this, "资源冲突\n" + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
    }
    logger.info("Changed entry: " + entry + " from " + previousLocation + " to " + location);
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
    JLabel startTimeLabel = new JLabel("上课时间(yyyy-MM-dd HH:mm格式)");
    JPanel startTimePanel = new JPanel(new FlowLayout());
    JLabel endTimeLabel = new JLabel("下课时间(yyyy-MM-dd HH:mm格式)");
    JPanel endTimePanel = new JPanel(new FlowLayout());
    JLabel classRoomLocationLabel = new JLabel("教室");
    JPanel classRoomLocationPanel = new JPanel();
    panelUp.add(nameLabel);
    panelUp.add(namePanel);
    panelUp.add(startTimeLabel);
    panelUp.add(startTimePanel);
    panelUp.add(endTimeLabel);
    panelUp.add(endTimePanel);
    panelUp.add(classRoomLocationLabel);
    panelUp.add(classRoomLocationPanel);

    JTextField nameField = new JTextField(20);
    JTextField startTimeField = new JTextField(16);
    JTextField endTimeField = new JTextField(16);
    JComboBox<Location> classRoomLocationComboBox = new JComboBox<>();
    for (Location location : locations) {
      classRoomLocationComboBox.addItem(location);
    }
    namePanel.add(nameField);
    startTimePanel.add(startTimeField);
    endTimePanel.add(endTimeField);
    classRoomLocationPanel.add(classRoomLocationComboBox);

    // 控制部分
    panelBottom.setLayout(new FlowLayout());
    JButton okButton = new JButton("确定");
    JButton cancelButton = new JButton("取消");
    panelBottom.add(okButton);
    panelBottom.add(cancelButton);

    okButton.addActionListener(
      e -> addPlanningEntryAction(dialog, nameField, startTimeField, endTimeField, classRoomLocationComboBox));
    cancelButton.addActionListener(e -> {
      dialog.setVisible(false);
      dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));
    });

    dialog.pack();
    dialog.setVisible(true);
  }

  private void addPlanningEntryAction(JDialog dialog, JTextField nameField, JTextField startTimeField,
                                      JTextField endTimeField, JComboBox<Location> classRoomLocationComboBox) {
    String name = null;
    Timeslot timeslot;
    Location location = null;
    CourseEntry entry;
    try {
      name = nameField.getText();
      timeslot = Timeslot.parse(startTimeField.getText(), endTimeField.getText());
      location = (Location) classRoomLocationComboBox.getSelectedItem();
      entry = new CourseEntry(name, timeslot, location);
      if (board.addEntry(entry)) {
        logger.info("Add a planning entry: " + entry);
        JOptionPane.showMessageDialog(this, "添加成功", "成功", JOptionPane.INFORMATION_MESSAGE);
        refreshTable();
      } else {
        logger.error("Fail to add entry " + entry + ", conflicting with others");
        JOptionPane.showMessageDialog(dialog, "产生冲突 无法添加", "错误", JOptionPane.ERROR_MESSAGE);
      }
    } catch (Exception exception) {
      logger.error("Fail to parse param when construct a course entry.");
      if (logger.isDebugEnabled()) {
        logger.debug("name: " + name + ", startTime: " + startTimeField.getText() + ", endTime: "
          + endTimeField.getText() + ", location:" + location);
      }
      JOptionPane.showMessageDialog(dialog, "参数错误 无法添加", "错误", JOptionPane.ERROR_MESSAGE);
    } finally {
      dialog.setVisible(false);
      dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));
    }
  }

  protected void showRemovePlanningEntryDialog() {
    Object[] array = board.getEntries().toArray();
    if (array.length == 0) {
      logger.error("The entries list is empty.");
      JOptionPane.showMessageDialog(this, "计划项列表为空", "错误", JOptionPane.ERROR_MESSAGE);
      return;
    }
    CoursePlanningEntry entry = (CoursePlanningEntry) JOptionPane.showInputDialog(this, "选择", "删除计划项",
      JOptionPane.INFORMATION_MESSAGE, null, array, array[0]);
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

    JComboBox<CoursePlanningEntry> entryComboBox = new JComboBox<>();
    for (CoursePlanningEntry entry : board.getEntries()) {
      entryComboBox.addItem(entry);
    }
    JComboBox<String> stateComboBox = new JComboBox<>(new String[]{"分配资源", "上课", "下课", "取消"});
    JButton execButton = new JButton("运行");
    panel.add(entryComboBox);
    panel.add(stateComboBox);
    panel.add(execButton);

    execButton.addActionListener(e -> {
      CoursePlanningEntry entry = (CoursePlanningEntry) entryComboBox.getSelectedItem();
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
        JOptionPane.showMessageDialog(dialog, "无法转移状态", "错误", JOptionPane.ERROR_MESSAGE);
      } catch (ResourceConflictException exception) {
        entry.setResource(null);
        JOptionPane.showMessageDialog(dialog, "资源冲突\n" + exception.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
      }
      refreshTable();
    });

    dialog.pack();
    dialog.setVisible(true);
  }

  private void cancelEntryAction(CoursePlanningEntry entry) throws IllegalStateTransitionException {
    entry.cancel();
    logger.info("Cancel entry " + entry);
  }

  private void endEntryAction(CoursePlanningEntry entry) throws IllegalStateTransitionException {
    entry.end();
    logger.info("End " + entry);
  }

  private void runEntryAction(CoursePlanningEntry entry) throws IllegalStateTransitionException {
    entry.run();
    logger.info("Start running " + entry);
  }

  private boolean allocateResourceAction(JDialog dialog, CoursePlanningEntry entry)
    throws IllegalStateTransitionException, ResourceConflictException {
    if (!entry.getState().toString().contains("WAITING") && !entry.getState().toString().contains("ALLOCATED")) {
      logger.error("Fail to convert the entry " + entry + " from " + entry.getState() + " to ALLOCATED STATE");
      throw new IllegalStateTransitionException("The entry is unable to allocate.");
    }
    if (teachers.isEmpty()) {
      logger.error("The teachers list is empty.");
      JOptionPane.showMessageDialog(dialog, "资源列表为空", "错误", JOptionPane.ERROR_MESSAGE);
      return true;
    }
    Object[] array = teachers.toArray();
    Teacher teacher = (Teacher) JOptionPane.showInputDialog(dialog, "选择", "选择资源", JOptionPane.INFORMATION_MESSAGE, null,
      array, array[0]);
    if (teacher == null) {
      return true;
    }
    entry.setResource(teacher);
    for (CoursePlanningEntry anotherEntry : board.getEntries()) {
      if (entry == anotherEntry) {
        continue;
      }
      if (anotherEntry.conflictResourceWith(entry)) {
        logger.error("The entry: " + entry + "and entry: " + anotherEntry + "conflict on teacher: " + teacher);
        throw new ResourceConflictException(entry, anotherEntry, teacher);
      }
    }
    entry.allocated();
    logger.info("Allocate entry " + entry + " with resource " + teacher);
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
      JOptionPane.showMessageDialog(dialog, "有冲突", "错误", JOptionPane.ERROR_MESSAGE);
    } else {
      JOptionPane.showMessageDialog(dialog, "无冲突", "消息", JOptionPane.INFORMATION_MESSAGE);
    }
  }

  protected void refreshTable() {
    keyLocationPanel.removeAll();
    Location keyLocation = (Location) keyLocationComboBox.getSelectedItem();
    logger.info("Refresh board with location: " + keyLocation);
    keyLocationComboBox = new JComboBox<>();
    keyLocationComboBox.addItem(CommonLocation.of("...", CoursePlanningEntry.LOCATION_SHAREABLE));

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
