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
public class StrIteratorTest extends TestCase {

  public StrIteratorTest(String testName) {
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
   * Test of next method, of class StrIterator.
   */
  public void testNext() {
    System.out.println("next - for an empty buffer");
    StrIterator instance = new StrIterator("");
    boolean expResult = false;
    boolean result = instance.next();
    assertEquals(expResult, result);
  }

  /**
   * Test of next method, of class StrIterator.
   */
  public void testNext1() {
    System.out.println("next");
    StrIterator instance = new StrIterator("a00");
    assertTrue(instance.next());
    assertFalse(instance.next());
  }

  /**
   * Test of getCode method, of class StrIterator.
   */
  public void testGetCode() {
    try {
    System.out.println("getCode");
    StrIterator instance = new StrIterator("");
    instance.getCode();
    fail();
    } catch (Exception e) {

    }
  }

  /**
   * Test of getText method, of class StrIterator.
   */
  public void testGetText() {
    try {
    System.out.println("getText");
    StrIterator instance = new StrIterator("");
    instance.getText();
    fail();
    } catch (Exception e) {}
  }

  /**
   * Test of back method, of class StrIterator.
   */
  public void testBack() {
    System.out.println("back");
    StrIterator instance = new StrIterator("");
    instance.back();
  }

  /**
   * Test of back method, of class StrIterator.
   */
  public void testBack1() {
    System.out.println("back");
    StrIterator instance = new StrIterator("a00");
    assertTrue(instance.next());
    instance.back();
    assertTrue(instance.next());
  }

  /**
   * Test of isAtTheEnd method, of class StrIterator.
   */
  public void testIsAtTheEnd() {
    System.out.println("isAtTheEnd");
    StrIterator instance = new StrIterator("");
    assertTrue(instance.isAtTheEnd());
  }

  public void testIsAtTheEnd1() {
    System.out.println("isAtTheEnd");
    StrIterator instance = new StrIterator("a00");
    assertFalse(instance.isAtTheEnd());
    assertTrue(instance.next());
    assertTrue(instance.isAtTheEnd());
  }

}
