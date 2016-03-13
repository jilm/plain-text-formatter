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
public class TextBuilderTest extends TestCase {

  public TextBuilderTest(String testName) {
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
   * Test of toString method, of class TextBuilder.
   */
  public void testToString() {
    System.out.println("toString");
    TextBuilder instance = new TextBuilder();
    String expResult = "";
    String result = instance.toString();
    assertEquals(expResult, result);
  }

  /**
   * Test of append method, of class TextBuilder.
   */
  public void testAppend() {
    System.out.println("append");
    String text = "line1\nline2";
    String expResult = "line1\nline2";
    TextBuilder instance = new TextBuilder();
    instance.append(text);
    String result = instance.toString();
    assertEquals(expResult, result);
    assertEquals(5, instance.getCharCount());
    instance.append("\n");
    assertEquals("line1\nline2\n", instance.toString());
    assertEquals(0, instance.getCharCount());
    instance.append("\nline3\n");
    assertEquals("line1\nline2\n\nline3\n", instance.toString());
    assertEquals(0, instance.getCharCount());
  }

  /**
   * Test of appendLine method, of class TextBuilder.
   */
  public void testAppendLine() {
    System.out.println("appendLine");
    String text = "text";
    String expResult = "text";
    TextBuilder instance = new TextBuilder();
    assertEquals("", instance.toString());
    instance.appendLine(text);
    String result = instance.toString();
    assertEquals(expResult, result);
    assertEquals(4, instance.getCharCount());
    instance.appendLine(" aaa ");
    assertEquals("text aaa ", instance.toString());
    assertEquals(9, instance.getCharCount());
  }

  /**
   * Test of newLine method, of class TextBuilder.
   */
  public void testNewLine() {
    System.out.println("newLine");
    TextBuilder instance = new TextBuilder();
    assertEquals("", instance.toString());
    instance.newLine();
    assertEquals("\n", instance.toString());
  }

  /**
   * Test of getCharCount method, of class TextBuilder.
   */
  public void testGetCharCount() {
    System.out.println("getCharCount");
    TextBuilder instance = new TextBuilder();
    int expResult = 0;
    int result = instance.getCharCount();
    assertEquals(expResult, result);
  }

  /**
   * Test of isEmpty method, of class TextBuilder.
   */
  public void testIsEmpty() {
    System.out.println("isEmpty");
    String text = "";
    boolean expResult = true;
    boolean result = TextBuilder.isEmpty(text);
    assertEquals(expResult, result);

    text = null;
    expResult = true;
    result = TextBuilder.isEmpty(text);
    assertEquals(expResult, result);

    text = " ";
    expResult = false;
    result = TextBuilder.isEmpty(text);
    assertEquals(expResult, result);
  }

}
