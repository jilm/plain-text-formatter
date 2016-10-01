/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lidinsky.tools.text;

import junit.framework.TestCase;
import org.junit.Test;

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
    String result = new Formatter().format(buffer);
    System.out.println(result);
  }

  /**
   * Test of dfs method, of class Object2String.
   */
  @Test
  public void testDfs() {
    System.out.println("dfs");
    Object2String instance = new Object2String();
    instance.dfs();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of isDiscovered method, of class Object2String.
   */
  @Test
  public void testIsDiscovered() {
    System.out.println("isDiscovered");
    Object object = null;
    Object2String instance = new Object2String();
    boolean expResult = false;
    boolean result = instance.isDiscovered(object);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getBasicStatement method, of class Object2String.
   */
  @Test
  public void testGetBasicStatement() {
    System.out.println("getBasicStatement");
    Object object = null;
    String expResult = "";
    String result = Object2String.getBasicStatement(object);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of isPrimitive method, of class Object2String.
   */
  @Test
  public void testIsPrimitive() {
    System.out.println("isPrimitive");
    Object object = null;
    boolean expResult = false;
    boolean result = Object2String.isPrimitive(object);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getPrimitiveRepresentation method, of class Object2String.
   */
  @Test
  public void testGetPrimitiveRepresentation() {
    System.out.println("getPrimitiveRepresentation");
    Object object = null;
    String expResult = "";
    String result = Object2String.getPrimitiveRepresentation(object);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getShortStatement method, of class Object2String.
   */
  @Test
  public void testGetShortStatement() {
    System.out.println("getShortStatement");
    Object object = null;
    Object2String instance = new Object2String();
    String expResult = "";
    String result = instance.getShortStatement(object);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }


}
