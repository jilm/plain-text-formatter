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
public class BufferTest extends TestCase {

  public BufferTest(String testName) {
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

  /**
   * Test of append method, of class Buffer.
   */
  public void testAppend_StrCode() {
    System.out.println("append");
    StrCode code = StrCode.HORIZONTAL_RULE;
    Buffer instance = new Buffer();
    instance.append(code);
    String result = instance.toString();
    String expected = new String(new char[] {StrCode.HORIZONTAL_RULE.getCode(), '0', '0'});
    assertEquals(expected, result);
  }

  /**
   * Test of append method, of class Buffer.
   */
  public void testAppend_StrCode_String() {
    System.out.println("append");
    StrCode code = StrCode.HEAD0;
    String text = "Head";
    Buffer instance = new Buffer();
    instance.append(code, text);
    String result = instance.toString();
    String expected = new String(new char[]
    {code.getCode(), '0', '4', 'H', 'e', 'a', 'd'});
    assertEquals(expected, result);
  }

  /**
   * Test of isEmpty method, of class Buffer.
   */
  public void testIsEmpty() {
    System.out.println("isEmpty");
    String text = "";
    Buffer instance = new Buffer();
    boolean expResult = true;
    boolean result = instance.isEmpty(text);
    assertEquals(expResult, result);
  }

  /**
   * Test of toString method, of class Buffer.
   */
  public void testToString() {
    System.out.println("toString");
    Buffer instance = new Buffer();
    String expResult = "";
    String result = instance.toString();
    assertEquals(expResult, result);
  }

}
