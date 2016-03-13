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
public class PageFormatterTest extends TestCase {

  public PageFormatterTest(String testName) {
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
   * Test of format method, of class PageFormatter.
   */
  public void testFormat() {
    System.out.println("format");
    PageFormatter instance = new PageFormatter(new StrIterator(""));
    String expResult = "";
    String result = instance.format();
    assertEquals(expResult, result);
  }

  /**
   * Test of formatLine method, of class PageFormatter.
   */
  public void testFormatLine() {
    System.out.println("formatLine");
    char[] buffer = null;
    int offset = 0;
    int length = 0;
    PageFormatter instance = null;
    boolean expResult = false;
    boolean result = instance.formatLine(buffer, offset, length);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getNestedFormatter method, of class PageFormatter.
   */
  public void testGetNestedFormatter() {
    System.out.println("getNestedFormatter");
    PageFormatter instance = new PageFormatter(new StrIterator(""));
    AbstractFormatter result = instance.getNestedFormatter();
    assertNull(result);
  }

}
