package base;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class MyRegExpTest {

  @Test
  public void testContructor() {
    MyRegExp exp = new MyRegExp(".*", 10);
    assertNotNull(exp);
  }

}
