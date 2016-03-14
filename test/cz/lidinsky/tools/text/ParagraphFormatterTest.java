/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lidinsky.tools.text;

import java.util.Arrays;
import junit.framework.TestCase;

/**
 *
 * @author jilm
 */
public class ParagraphFormatterTest extends TestCase {

  public ParagraphFormatterTest(String testName) {
    super(testName);
  }

  public StrIterator emptyIterator;
  public StrIterator shortIterator;
  public StrIterator longIterator;
  public StrIterator moreParagraphs;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    emptyIterator = new StrIterator("");
    shortIterator = new StrIterator(
            new StrBuffer()
            .append("A word.")
            .toString());
    longIterator = new StrIterator(
            new StrBuffer()
            .append("This is a longer line, just to test the line wrapping.")
            .append("I am only curious wheather this will work or not.")
            .append("I will just see.")
            .toString());
  }

  @Override
  protected void tearDown() throws Exception {
    super.tearDown();
  }

  /**
   * Test of formatLine method, of class ParagraphFormatter.
   */
  public void testFormatLine() {
    System.out.println("formatLine");
    char[] buffer = new char[80];
    int offset = 0;
    int length = 79;
    ParagraphFormatter instance = new ParagraphFormatter(emptyIterator);
    assertFalse(instance.formatLine(buffer, offset, length));
  }

  /**
   * Test of formatLine method, of class ParagraphFormatter.
   */
  public void testFormatLine1() {
    System.out.println("formatLine1");
    char[] buffer = new char[80];
    Arrays.fill(buffer, '.');
    int offset = 0;
    int length = 79;
    ParagraphFormatter instance = new ParagraphFormatter(shortIterator);
    boolean result = instance.formatLine(buffer, offset, length);
    System.out.println(new String(buffer));
    assertTrue(result);
    Arrays.fill(buffer, '.');
    result = instance.formatLine(buffer, offset, length);
    System.out.println(new String(buffer));
    assertFalse(result);
  }

  /**
   * Test of formatLine method, of class ParagraphFormatter.
   */
  public void testFormatLine2() {
    System.out.println("formatLine2");
    char[] buffer = new char[80];
    Arrays.fill(buffer, '.');
    int offset = 0;
    int length = 79;
    ParagraphFormatter instance = new ParagraphFormatter(longIterator);
    boolean result = instance.formatLine(buffer, offset, length);
    System.out.println(new String(buffer));
    assertTrue(result);
    Arrays.fill(buffer, '.');
    result = instance.formatLine(buffer, offset, length);
    System.out.println(new String(buffer));
    assertTrue(result);
    Arrays.fill(buffer, '.');
    result = instance.formatLine(buffer, offset, length);
    System.out.println(new String(buffer));
    assertFalse(result);
  }

  public void testFormatLine3() {
    System.out.println("formatLine2");
    char[] buffer = new char[80];
    Arrays.fill(buffer, '.');
    int offset = 0;
    int length = 79;
    ParagraphFormatter instance = new ParagraphFormatter(moreParagraphs);
    boolean result = instance.formatLine(buffer, offset, length);
    System.out.println(new String(buffer));
    assertTrue(result);
    Arrays.fill(buffer, '.');
    result = instance.formatLine(buffer, offset, length);
    System.out.println(new String(buffer));
    assertTrue(result);
    Arrays.fill(buffer, '.');
    result = instance.formatLine(buffer, offset, length);
    System.out.println(new String(buffer));
    assertTrue(result);
    result = instance.formatLine(buffer, offset, length);
    assertFalse(result);
  }

  /**
   * Test of next method, of class ParagraphFormatter.
   */
  public void testNext() {
  }

  /**
   * Test of leftAlign method, of class ParagraphFormatter.
   */
  public void testLeftAlign() {
  }

}
