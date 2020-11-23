package base;

import org.junit.Test;

import javax.swing.*;

/**
 * Tests the class {@link LogDialog}.
 *
 * @author cycleke
 */
public class LogDialogTest {
  @Test
  public void testLauncher() throws Exception {
    JDialog dialog = new LogDialog(null, new String[]{"Oper1", "Oper2"}, new String[]{"Method1", "Method2"},
      new String[]{});
    dialog.setVisible(true);
  }
}
