package course;

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

/**
 * This class {@link CourseScheduleApp} is a GUI client for Course schedule.
 *
 * @author cycleke
 */
public class CourseScheduleApp extends JFrame {
  private static final long serialVersionUID = -7909494900613802756L;
  private final static PlanningEntryAPIs<Teacher> entryAPIs = new PlanningEntryAPIsBruteForceStrategy<>();

  private JMenuBar menuBar;
  private JPanel keyLocationPanel;
  private JScrollPane tablePanel;
  private JLabel keyLocationLabel;
  private JComboBox<Location> keyLocationComboBox;
  private JButton refreshButton;

  private Board board;
  private List<Teacher> teachers;
  private List<Location> locations;

  public CourseScheduleApp() {
    board = new Board();
    teachers = new ArrayList<>();
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
    keyLocationComboBox.addItem(CommonLocation.of("...", CoursePlanningEntry.LOCATION_SHAREABLE));
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

    setTitle("课程管理");
    pack();
    setLocationRelativeTo(null);
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
    if (teachers.isEmpty()) {
      JOptionPane.showMessageDialog(this, "资源资源为空", "错误", JOptionPane.ERROR_MESSAGE);
      return;
    }
    Object[] array = teachers.toArray();
    Teacher teacher =
      (Teacher)JOptionPane.showInputDialog(this, "选择", "查询资源", JOptionPane.INFORMATION_MESSAGE, null, array, array[0]);
    if (teacher == null)
      return;
    List<CoursePlanningEntry> entries = new ArrayList<>();
    for (CoursePlanningEntry entry : board.getEntries()) {
      if (entry.getResource() == null)
        continue;
      if (entry.getResource().equals(teacher))
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
    JScrollPane tablePanel = new JScrollPane(new JTable(new DefaultTableModel(data, new String[] {"课程"})));
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
        CoursePlanningEntry entry = (CoursePlanningEntry)object;
        entry = (CoursePlanningEntry)entryAPIs.findPreEntryPerResource(teacher, entry, entries);
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
    JLabel idNumberLabel = new JLabel("身份证号");
    JPanel idNumberPanel = new JPanel(new FlowLayout());
    JLabel nameLabel = new JLabel("名称");
    JPanel namePanel = new JPanel(new FlowLayout());
    JLabel sexLabel = new JLabel("性别");
    JPanel sexPanel = new JPanel(new FlowLayout());
    JLabel titleLabel = new JLabel("职称");
    JPanel titlePanel = new JPanel();
    panel_up.add(idNumberLabel);
    panel_up.add(idNumberPanel);
    panel_up.add(nameLabel);
    panel_up.add(namePanel);
    panel_up.add(sexLabel);
    panel_up.add(sexPanel);
    panel_up.add(titleLabel);
    panel_up.add(titlePanel);

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
    panel_bottom.setLayout(new FlowLayout());
    JButton okButton = new JButton("确定");
    JButton cancelButton = new JButton("取消");
    panel_bottom.add(okButton);
    panel_bottom.add(cancelButton);

    JFrame frame = this;
    okButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String idNumber = idNumberField.getText();
        String name = nameField.getText();
        String sex = sexField.getText();
        String title = titleField.getText();
        if ((idNumber.length() != 15 && idNumber.length() != 18) || name.isEmpty() || sex.isEmpty()
          || title.isEmpty()) {
          JOptionPane.showMessageDialog(dialog, "参数错误 无法添加", "错误", JOptionPane.ERROR_MESSAGE);
          return;
        }
        if (teachers.add(new Teacher(idNumber, name, sex, title))) {
          JOptionPane.showMessageDialog(frame, "添加成功", "成功", JOptionPane.INFORMATION_MESSAGE);
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
    if (teachers.isEmpty()) {
      JOptionPane.showMessageDialog(this, "资源资源为空", "错误", JOptionPane.ERROR_MESSAGE);
      return;
    }
    Object[] array = teachers.toArray();
    if (teachers.remove(
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
        if (locations.add(CommonLocation.of(location, CoursePlanningEntry.LOCATION_SHAREABLE))) {
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
    JLabel startTimeLabel = new JLabel("上课时间(yyyy-MM-dd HH:mm格式)");
    JPanel startTimePanel = new JPanel(new FlowLayout());
    JLabel endTimeLabel = new JLabel("下课时间(yyyy-MM-dd HH:mm格式)");
    JPanel endTimePanel = new JPanel(new FlowLayout());
    JLabel classRoomLocationLabel = new JLabel("教室");
    JPanel classRoomLocationPanel = new JPanel();
    panel_up.add(nameLabel);
    panel_up.add(namePanel);
    panel_up.add(startTimeLabel);
    panel_up.add(startTimePanel);
    panel_up.add(endTimeLabel);
    panel_up.add(endTimePanel);
    panel_up.add(classRoomLocationLabel);
    panel_up.add(classRoomLocationPanel);

    JTextField nameField = new JTextField(20);
    JTextField startTimeField = new JTextField(16);
    JTextField endTimeField = new JTextField(16);
    JComboBox<Location> classRoomLocationComboBox = new JComboBox<Location>();
    for (Location location : locations) {
      classRoomLocationComboBox.addItem(location);
    }
    namePanel.add(nameField);
    startTimePanel.add(startTimeField);
    endTimePanel.add(endTimeField);
    classRoomLocationPanel.add(classRoomLocationComboBox);

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
          String name = nameField.getText();
          Timeslot timeslot = Timeslot.parse(startTimeField.getText(), endTimeField.getText());
          Location location = (Location)classRoomLocationComboBox.getSelectedItem();
          if (!board.addEntry(new CourseEntry(name, timeslot, location))) {
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
    if (board.removeEntry((CoursePlanningEntry)JOptionPane.showInputDialog(this, "选择", "删除计划项",
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

    JComboBox<CoursePlanningEntry> entryComboBox = new JComboBox<>();
    for (CoursePlanningEntry entry : board.getEntries()) {
      entryComboBox.addItem(entry);
    }
    JComboBox<String> stateComboBox = new JComboBox<>(new String[] {"分配资源", "上课", "下课", "取消"});
    JButton execButton = new JButton("运行");
    panel.add(entryComboBox);
    panel.add(stateComboBox);
    panel.add(execButton);

    execButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        CoursePlanningEntry entry = (CoursePlanningEntry)entryComboBox.getSelectedItem();
        try {
          switch (stateComboBox.getSelectedIndex()) {
            case 0:
              if (!(entry.getState() instanceof WaitingState) && !(entry.getState() instanceof AllocatedState))
                throw new IllegalStateTransitionException("The entry is unable to allocate.");
              if (teachers.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "资源列表为空", "错误", JOptionPane.ERROR_MESSAGE);
                return;
              }
              Object[] array = teachers.toArray();
              Teacher teacher = (Teacher)JOptionPane.showInputDialog(dialog, "选择", "选择资源",
                JOptionPane.INFORMATION_MESSAGE, null, array, array[0]);
              entry.setResource(teacher);
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
      CourseScheduleApp app = new CourseScheduleApp();
      app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      app.pack();
      app.setVisible(true);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
