package base;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * Copied from
 * https://github.com/xfhy/JournalManageSystem/blob/27c5964746f6f67c6e98b5b375ca8f92dc92223d/src/liang/guo/diary/util/MyRegExp.java
 *
 * @author XFHY
 * @date 2016年12月3日 下午12:44:26
 * @function 用正则表达式限制swing(JTextField等)的输入
 */
public class MyRegExp extends PlainDocument {

  private static final long serialVersionUID = 2851695051373575598L;
  private Pattern pattern;
  private Matcher m;
  private int maxLength;

  public MyRegExp(String pat, int maxLength) {
    super();
    this.pattern = Pattern.compile(pat);
    this.maxLength = maxLength;
  }

  /**
   * 向文档中插入某些内容。插入内容会导致在实际发生改变时存储写锁定，接着会向线程上抓取该写入锁定的观察者发出通知。
   */
  @Override
  public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
    if (str == null) {
      return;
    }
    String tmp = getText(0, offset).concat(str); // concat:将指定字符串连接到此字符串的结尾
    m = pattern.matcher(tmp);

    // 如果符合,才进行插入 getLength():以前的长度 str:现在需要插入的字符串
    if (m.matches() && (getLength() + str.length()) <= maxLength)
      super.insertString(offset, str, attr);
  }
}
