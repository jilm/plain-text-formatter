/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lidinsky.tools.text;

import junit.framework.TestCase;

/**
 *
 * @author jilm
 */
public class Object2StringTest extends TestCase {

  public Object2StringTest(String testName) {
    super(testName);
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();
  }

  @Override
  protected void tearDown() throws Exception {
    super.tearDown();
  }

  class TestClass {

    private String field1 = "abcd";

    public Object field2;

    protected int intField = 135;
    private java.util.Date dateField = new java.util.Date();

  }

  /**
   * Test of toStrBuffer method, of class Object2String.
   */
  public void testToStrBuffer() {
    System.out.println("toStrBuffer");
    TestClass object = new TestClass();
    object.field2 = object;
    Object2String instance = new Object2String();
    String expResult = "";
    String buffer = instance.toStrBuffer(object);
    System.out.println(buffer);
    String result = new Formatter().format(new StrIterator(buffer));
    System.out.println(result);
  }


}
