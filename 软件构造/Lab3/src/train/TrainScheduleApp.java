package train;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
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
import train.Board.IteratorType;
import train.TrainCabin.TrainCabinType;

/**
 * This class {@link TrainScheduleApp} is a GUI client for Train schedule.
 *
 * @author cycleke
 */
public class TrainScheduleApp extends JFrame {
  private static final long serialVersionUID = -7909494900613802756L;
  private final static PlanningEntryAPIs<TrainCabin> entryAPIs = new PlanningEntryAPIsBruteForceStrategy<>();

  private final JMenuBar menuBar;
  private final JPanel keyLocationPanel;
  private JScrollPane tablePanel;
  private final JLabel keyLocationLabel;
  private JComboBox<Location> keyLocationComboBox;
  private final JButton refreshButton;

  private final Board board;
  private final List<TrainCabin> trainCabins;
  private final List<Location> locations;

  public TrainScheduleApp() {
    board = new Board();
    trainCabins = new ArrayList<>();
    locations = new ArrayList<>();

    menuBar = new JMenuBar();
    setJMenuBar(menuBar);

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
    keyLocationComboBox.addItem(CommonLocation.of("...", TrainPlanningEntry.LOCATION_SHAREABLE));
    refreshButton = new JButton("刷新");
    keyLocationPanel.add(keyLocationLabel);
    keyLocationPanel.add(keyLocationComboBox);
    keyLocationPanel.add(refreshButton);
    keyLocationPanel.setPreferredSize(new Dimension(500, 50));

    refreshButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        refreshTable();
      }
    });

    setTitle("高铁管理");
    pack();
    setLocationRelativeTo(null);
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

    addItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        showAddResourceDialog();
      }
    });
    removeItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        showRemoveResourceDialog();
      }
    });
    queryItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        showQueryResourceDialog();
      }
    });

    return resourceMenu;
  }

  protected void showQueryResourceDialog() {
    if (trainCabins.isEmpty()) {
      JOptionPane.showMessageDialog(this, "资源资源为空", "错误", JOptionPane.ERROR_MESSAGE);
      return;
    }
    final Object[] array = trainCabins.toArray();
    final TrainCabin trainCabin = (TrainCabin)JOptionPane.showInputDialog(this, "选择", "查询资源",
      JOptionPane.INFORMATION_MESSAGE, null, array, array[0]);
    if (trainCabin == null)
      return;
    final List<TrainPlanningEntry> entries = new ArrayList<>();
    for (final TrainPlanningEntry entry : board.getEntries()) {
      if (entry.getResources().contains(trainCabin))
        entries.add(entry);
    }
    int count = 0;
    final Object[][] data = new Object[entries.size()][1];
    for (final Object object : entries) {
      data[count][0] = object;
      ++count;
    }
    final JDialog dialog = new JDialog(this);
    dialog.setLayout(new BorderLayout());
    final JPanel queryPanel = new JPanel(new FlowLayout());
    final JScrollPane tablePanel = new JScrollPane(new JTable(new DefaultTableModel(data, new String[] {"班次"})));
    dialog.add(queryPanel, BorderLayout.NORTH);
    dialog.getContentPane().add(tablePanel, BorderLayout.CENTER);

    final JButton queryButton = new JButton("查询前项");
    queryPanel.add(queryButton);

    final Object[] entryObjects = entries.toArray();
    queryButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        if (entries.isEmpty())
          return;
        final Object object = JOptionPane.showInputDialog(dialog, "选择", "查询前项", JOptionPane.INFORMATION_MESSAGE, null,
          entryObjects, entryObjects[0]);
        if (object == null)
          return;
        TrainPlanningEntry entry = (TrainPlanningEntry)object;
        entry = (TrainPlanningEntry)entryAPIs.findPreEntryPerResource(trainCabin, entry, entries);
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
    final JPanel panel_up = new JPanel();
    final JPanel panel_bottom = new JPanel();

    dialog.setLayout(new BorderLayout());
    dialog.add(panel_up, BorderLayout.CENTER);
    dialog.add(panel_bottom, BorderLayout.SOUTH);

    // 输入部分
    panel_up.setLayout(new GridLayout(4, 2));
    final JLabel idLabel = new JLabel("编号");
    final JPanel idPanel = new JPanel(new FlowLayout());
    final JLabel typeLabel = new JLabel("类型");
    final JPanel typePanel = new JPanel(new FlowLayout());
    final JLabel seatsLabel = new JLabel("座位数");
    final JPanel seatsPanel = new JPanel(new FlowLayout());
    final JLabel yearLabel = new JLabel("出厂年份");
    final JPanel yearPanel = new JPanel();
    panel_up.add(idLabel);
    panel_up.add(idPanel);
    panel_up.add(typeLabel);
    panel_up.add(typePanel);
    panel_up.add(seatsLabel);
    panel_up.add(seatsPanel);
    panel_up.add(yearLabel);
    panel_up.add(yearPanel);

    final TrainCabinType[] types =
      {TrainCabinType.BUSINESS, TrainCabinType.FIRST_CLASS, TrainCabinType.SECOND_CLASS, TrainCabinType.SOFT_SLEEPER,
        TrainCabinType.HARD_SLEEPER, TrainCabinType.HARD_SEAT, TrainCabinType.LUGGAGE_CAR, TrainCabinType.DINING_CAR};

    final JTextField idField = new JTextField(10);
    final JComboBox<TrainCabinType> typeComboBox = new JComboBox<TrainCabinType>(types);
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
    panel_bottom.setLayout(new FlowLayout());
    final JButton okButton = new JButton("确定");
    final JButton cancelButton = new JButton("取消");
    panel_bottom.add(okButton);
    panel_bottom.add(cancelButton);

    final JFrame frame = this;
    okButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        try {
          final int id = Integer.parseInt(idField.getText());
          final TrainCabinType type = (TrainCabinType)typeComboBox.getSelectedItem();
          final int seats = Integer.parseInt(seatsField.getText());
          final int year = Integer.parseInt(yearField.getText());
          if (trainCabins.add(new TrainCabin(id, type, seats, year))) {
            JOptionPane.showMessageDialog(frame, "添加成功", "成功", JOptionPane.INFORMATION_MESSAGE);
          }
        } catch (final NumberFormatException exception) {
          JOptionPane.showMessageDialog(dialog, "参数错误 无法添加", "错误", JOptionPane.ERROR_MESSAGE);
          return;
        }
        dialog.setVisible(false);
        dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));
      }
    });
    cancelButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        dialog.setVisible(false);
        dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));
      }
    });

    dialog.pack();
    dialog.setVisible(true);
  }

  protected void showRemoveResourceDialog() {
    if (trainCabins.isEmpty()) {
      JOptionPane.showMessageDialog(this, "资源资源为空", "错误", JOptionPane.ERROR_MESSAGE);
      return;
    }
    final Object[] array = trainCabins.toArray();
    if (trainCabins.remove(
      JOptionPane.showInputDialog(this, "选择", "删除资源", JOptionPane.INFORMATION_MESSAGE, null, array, array[0]))) {
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

    addItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        showAddLocationDialog();
      }
    });
    removeItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        showRemoveLocationDialog();
      }
    });
    return locationMenu;
  }

  protected void showAddLocationDialog() {
    final JDialog dialog = new JDialog(this);
    dialog.setLocationRelativeTo(null);
    dialog.setTitle("添加位置");
    final JPanel panel_up = new JPanel();
    final JPanel panel_bottom = new JPanel();

    dialog.setLayout(new BorderLayout());
    dialog.add(panel_up, BorderLayout.CENTER);
    dialog.add(panel_bottom, BorderLayout.SOUTH);

    // 输入部分
    panel_up.setLayout(new GridLayout(1, 2));
    final JLabel locationLabel = new JLabel("位置");
    final JPanel locationPanel = new JPanel(new FlowLayout());
    panel_up.add(locationLabel);
    panel_up.add(locationPanel);

    final JTextField locationField = new JTextField(20);
    locationPanel.add(locationField);

    // 控制部分
    panel_bottom.setLayout(new FlowLayout());
    final JButton okButton = new JButton("确定");
    final JButton cancelButton = new JButton("取消");
    panel_bottom.add(okButton);
    panel_bottom.add(cancelButton);

    final JFrame frame = this;
    okButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        final String location = locationField.getText();
        if (location.isEmpty()) {
          JOptionPane.showMessageDialog(dialog, "参数错误 无法添加", "错误", JOptionPane.ERROR_MESSAGE);
          return;
        }
        if (locations.add(CommonLocation.of(location, TrainPlanningEntry.LOCATION_SHAREABLE))) {
          JOptionPane.showMessageDialog(frame, "添加成功", "成功", JOptionPane.INFORMATION_MESSAGE);
          refreshTable();
        }
        dialog.setVisible(false);
        dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));
      }
    });
    cancelButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
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
    final Object[] array = locations.toArray();
    if (locations.remove(
      JOptionPane.showInputDialog(this, "选择", "删除位置", JOptionPane.INFORMATION_MESSAGE, null, array, array[0]))) {
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

    addItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        showAddPlanningEntryDialog();
      }
    });
    removeItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        showRemovePlanningEntryDialog();
      }
    });
    changeStateItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        showChangePlanningEntryStateDialog();
      }
    });
    checkConflictItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        showCheckPlanningEntryConflictDialog();
      }
    });

    return planningEntryMenu;
  }

  protected void showAddPlanningEntryDialog() {
    final JDialog dialog = new JDialog(this);
    dialog.setLocationRelativeTo(null);
    dialog.setTitle("添加计划项");
    final JPanel panel_up = new JPanel();
    final JPanel panel_bottom = new JPanel();

    dialog.setLayout(new BorderLayout());
    dialog.add(panel_up, BorderLayout.CENTER);
    dialog.add(panel_bottom, BorderLayout.SOUTH);

    // 输入部分
    panel_up.setLayout(new GridLayout(5, 2));
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

    final JTextField nameField = new JTextField(20);
    final JTextField startTimeField = new JTextField(16);
    final JTextField endTimeField = new JTextField(16);
    final JComboBox<Location> startLocationComboBox = new JComboBox<Location>();
    final JComboBox<Location> targetLocationComboBox = new JComboBox<Location>();
    for (final Location location : locations) {
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
    final JButton okButton = new JButton("确定");
    final JButton addButton = new JButton("添加一对起止位置/时间");
    final JButton cancelButton = new JButton("取消");
    panel_bottom.add(okButton);
    panel_bottom.add(addButton);
    panel_bottom.add(cancelButton);

    final JFrame frame = this;
    final List<Location> locations = new ArrayList<>();
    final List<Timeslot> timeslots = new ArrayList<>();

    okButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        try {
          final String name = nameField.getText();
          if (!board.addEntry(new TrainEntry(name, timeslots, locations))) {
            JOptionPane.showMessageDialog(dialog, "产生冲突 无法添加", "错误", JOptionPane.ERROR_MESSAGE);
          }
        } catch (final Exception exception) {
          JOptionPane.showMessageDialog(dialog, "参数错误 无法添加", "错误", JOptionPane.ERROR_MESSAGE);
          return;
        }
        JOptionPane.showMessageDialog(frame, "添加成功", "成功", JOptionPane.INFORMATION_MESSAGE);
        refreshTable();
        dialog.setVisible(false);
        dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));
      }
    });

    addButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        try {
          final Timeslot timeslot = Timeslot.parse(startTimeField.getText(), endTimeField.getText());
          final Location startLocation = (Location)startLocationComboBox.getSelectedItem();
          final Location targetLocation = (Location)targetLocationComboBox.getSelectedItem();
          if (!locations.isEmpty()) {
            if (startLocation != locations.get(locations.size() - 1))
              throw new RuntimeException("The Location changed between to segment.");
            if (timeslot.getStartTime().compareTo(timeslots.get(timeslots.size() - 1).getEndTime()) <= 0)
              throw new RuntimeException("The timeslots must increase.");
          } else {
            locations.add(startLocation);
          }
          timeslots.add(timeslot);
          locations.add(targetLocation);
          startLocationComboBox.setSelectedItem(targetLocation);
        } catch (final Exception exception) {
          exception.printStackTrace();
          JOptionPane.showMessageDialog(dialog, "参数错误 无法添加", "错误", JOptionPane.ERROR_MESSAGE);
          return;
        } finally {
          JOptionPane.showMessageDialog(dialog, "添加成功", "成功", JOptionPane.INFORMATION_MESSAGE);
        }
      }
    });

    cancelButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        dialog.setVisible(false);
        dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));
      }
    });

    dialog.pack();
    dialog.setVisible(true);
  }

  protected void showRemovePlanningEntryDialog() {
    final Object[] array = board.getEntries().toArray();
    if (array.length == 0) {
      JOptionPane.showMessageDialog(this, "计划项列表为空", "错误", JOptionPane.ERROR_MESSAGE);
      return;
    }
    if (board.removeEntry((TrainPlanningEntry)JOptionPane.showInputDialog(this, "选择", "删除计划项",
      JOptionPane.INFORMATION_MESSAGE, null, array, array[0]))) {
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
    final JComboBox<String> stateComboBox = new JComboBox<>(new String[] {"分配资源", "出发", "中停", "到达终点", "取消"});
    final JButton execButton = new JButton("运行");
    panel.add(entryComboBox);
    panel.add(stateComboBox);
    panel.add(execButton);

    execButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        final TrainPlanningEntry entry = (TrainPlanningEntry)entryComboBox.getSelectedItem();
        try {
          switch (stateComboBox.getSelectedIndex()) {
            case 0:
              if (!(entry.getState() instanceof WaitingState) && !(entry.getState() instanceof AllocatedState))
                throw new IllegalStateTransitionException("The entry is unable to allocate.");
              if (trainCabins.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "资源列表为空", "错误", JOptionPane.ERROR_MESSAGE);
                return;
              }
              final Object[] array = trainCabins.toArray();
              final TrainCabin trainCabin = (TrainCabin)JOptionPane.showInputDialog(dialog, "选择", "选择资源",
                JOptionPane.INFORMATION_MESSAGE, null, array, array[0]);
              final List<TrainCabin> trainCabins = entry.getResources();
              trainCabins.add(trainCabin);
              entry.setResources(trainCabins);
              entry.allocated();
              break;
            case 1:
              entry.run();
              break;
            case 2:
              entry.block();
              break;
            case 3:
              entry.end();
              break;
            case 4:
              entry.cancel();
            default:
              assert false;
          }
        } catch (final IllegalStateTransitionException exception) {
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
    final JDialog dialog = new JDialog(this);
    dialog.setLocationRelativeTo(null);
    dialog.setTitle("检测冲突");

    final JPanel panel = new JPanel(new GridLayout(2, 1));
    dialog.add(panel);

    final JButton checkLocationButton = new JButton("检测位置冲突");
    final JButton checkResourcesButton = new JButton("检测资源冲突");
    panel.add(checkLocationButton);
    panel.add(checkResourcesButton);
    checkLocationButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        if (entryAPIs.checkLocationConflict(board.getEntries())) {
          JOptionPane.showMessageDialog(dialog, "有冲突", "错误", JOptionPane.ERROR_MESSAGE);
        } else {
          JOptionPane.showMessageDialog(dialog, "无冲突", "消息", JOptionPane.INFORMATION_MESSAGE);
        }
      }
    });
    checkResourcesButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
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

    final Location keyLocation = (Location)keyLocationComboBox.getSelectedItem();
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
}
