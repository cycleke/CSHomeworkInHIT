package base;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Copied from
 * https://github.com/xfhy/JournalManageSystem/blob/27c5964746f6f67c6e98b5b375ca8f92dc92223d/src/liang/guo/diary/util/MyRegExp.java
 *
 * @author XFHY
 * @date 2016年12月3日 下午12:44:26
 * @function 用正则表达式限制swing(JTextField等)的输入
 */
public class MyRegExp extends PlainDocument {
  private static final long serialVersionUID = 5549734558374478790L;

  private final Pattern pattern;
  private final int maxLength;

  // Abstraction function:
  // AF(i) = {regex, maxLength}
  // Rep invariant:
  // The pattern is not null and maxLength > 0.
  // Safety from rep exposure:
  // The field are all private.

  /**
   * Constructor of {@link MyRegExp}.
   *
   * @param regex     the regex to limit the input, not null
   * @param maxLength the max length of the input, positive
   */
  public MyRegExp(String regex, int maxLength) {
    super();
    this.pattern = Pattern.compile(regex);
    this.maxLength = maxLength;
    checkRep();
  }

  private void checkRep() {
    assert pattern != null;
    assert maxLength > 0;
  }

  /**
   * 向文档中插入某些内容。插入内容会导致在实际发生改变时存储写锁定，接着会向线程上抓取该写入锁定的观察者发出通知。
   */
  @Override
  public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
    if (str == null) {
      return;
    }
    // concat:将指定字符串连接到此字符串的结尾
    String tmp = getText(0, offset).concat(str);
    Matcher m = pattern.matcher(tmp);

    // 如果符合,才进行插入 getLength():以前的长度 str:现在需要插入的字符串
    if (m.matches() && (getLength() + str.length()) <= maxLength) {
      super.insertString(offset, str, attr);
    }
  }
}
