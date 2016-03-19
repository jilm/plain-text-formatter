/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lidinsky.tools.text;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;

/**
 *
 * @author jilm
 */
public class StrBufferTest extends TestCase {

  public StrBufferTest(String testName) {
    super(testName);
  }

  @BeforeClass
  public static void setUpClass() throws Exception {
  }

  @AfterClass
  public static void tearDownClass() throws Exception {
  }

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  /**
   * Test of appendHead method, of class StrBuffer.
   */
  public void testAppendHead_String() {
    System.out.println("appendHead");
    String head = "Head";
    StrBuffer instance = new StrBuffer();
    String expResult = new String(new char[] {
      StrCode.HEAD0.getCode(), '0', '4', 'H', 'e', 'a', 'd'});
    instance.appendHead(head);
    instance.writePendingCodes();
    String result = instance.toString();
    assertEquals(expResult, result);
  }

  /**
   * Test of appendSubHead method, of class StrBuffer.
   */
  public void testAppendSubHead() {
    System.out.println("appendSubHead");
    String head = "Head";
    StrBuffer instance = new StrBuffer();
    String expResult = new String(new char[] {
      StrCode.HEAD1.getCode(), '0', '4', 'H', 'e', 'a', 'd'});
    instance.appendSubHead(head);
    instance.writePendingCodes();
    String result = instance.toString();
    assertEquals(expResult, result);
  }

  /**
   * Test of startUnorderedList method, of class StrBuffer.
   */
  @Ignore
  public void testStartUnorderedList() {
    System.out.println("startUnorderedList");
    StrBuffer instance = new StrBuffer();
    String result = instance.startUnorderedList()
            .append("abcd")
            .toString();
    String expected = new String(new char[] {
      StrCode.LIST_UNORDERED.getCode(), '0', '0',
      StrCode.ITEM.getCode(), '0', '0',
      StrCode.PARAGRAPH.getCode(), '0', '0',
      StrCode.TEXT.getCode(), '0', '4',
      'a', 'b', 'c', 'd'});
    assertEquals(expected, result);
  }

  /**
   * Test of closeParagraph method, of class StrBuffer.
   */
  public void testCloseParagraph() {
    System.out.println("closeParagraph");
    StrBuffer instance = new StrBuffer();
    instance.closeParagraph();
    String result = instance.toString();
    assertEquals("", result);
  }

  /**
   * Test of closeParagraph method, of class StrBuffer.
   */
  public void testCloseParagraph2() {
    System.out.println("closeParagraph2");
    StrBuffer instance = new StrBuffer();
    instance.append(StrCode.ITEM);
    instance.closeParagraph();
    String result = instance.toString();
    String expected = new String(new char[] {StrCode.ITEM.getCode(), '0', '0'});
    assertEquals(expected, result);
  }

  /**
   * Test of isParagraphOpen method, of class StrBuffer.
   */
  public void testIsParagraphOpen() {
    System.out.println("isParagraphOpen");
    StrBuffer instance = new StrBuffer();
    assertFalse(instance.isParagraphOpen());
    instance.startParagraph();
    assertTrue(instance.isParagraphOpen());
    instance.append("abcd");
    assertTrue(instance.isParagraphOpen());
    instance.startParagraph();
    assertTrue(instance.isParagraphOpen());
  }

  /**
   * Test of isParagraphPending method, of class StrBuffer.
   */
  public void testIsParagraphPending() {
    System.out.println("isParagraphPending");
    StrBuffer instance = new StrBuffer();
    assertFalse(instance.isParagraphPending());
    instance.startParagraph();
    assertTrue(instance.isParagraphPending());
    instance.append("abcd");
    assertFalse(instance.isParagraphPending());
    instance.startParagraph();
    assertTrue(instance.isParagraphPending());
  }

  /**
   * Test of isListOpen method, of class StrBuffer.
   */
  public void testIsListOpen() {
    StrBuffer instance = new StrBuffer();
    assertFalse("Test failed for an empty buffer!", instance.isListOpen());
    instance.startOrderedList();
    assertTrue("Test failed for ordered list added!", instance.isListOpen());
    instance.append("abcd");
    assertTrue("Test failed for a list that is no longer pending!",
            instance.isListOpen());
  }

  /**
   * Test of isListPending method, of class StrBuffer.
   */
  public void testIsListPending() {
    System.out.println("isListPending");
    StrBuffer instance = new StrBuffer();
    assertFalse("Test failed for an empty buffer!", instance.isListPending());
    instance.startOrderedList();
    assertTrue("Test failed for ordered list added!", instance.isListPending());
    instance.append("abcd");
    assertFalse("Test failed for a list that is no longer pending!",
            instance.isListPending());

  }

  /**
   * Test of closeLastCode method, of class StrBuffer.
   */
  public void testCloseLastCode() {
    System.out.println("closeLastCode");
    StrBuffer instance = new StrBuffer();
    instance.closeLastCode();
    assertFalse(instance.isOpened());
    instance.startOrderedList();
    assertEquals(StrCode.ITEM, instance.getLastOpenedCode());
    instance.closeLastCode();
    assertEquals(StrCode.LIST_ORDERED, instance.getLastOpenedCode());
    instance.closeLastCode();
    assertFalse(instance.isOpened());
  }

  /**
   * Test of getLastOpenedCode method, of class StrBuffer.
   */
  public void testGetLastOpenedCode() {
    System.out.println("getLastOpenedCode");
    StrBuffer instance = new StrBuffer();
    assertNull(instance.getLastOpenedCode());
    instance.startParagraph();
    assertEquals(StrCode.PARAGRAPH, instance.getLastOpenedCode());
    instance.append("abcd");
    assertEquals(StrCode.PARAGRAPH, instance.getLastOpenedCode());
    instance.closeParagraph();
    assertNull(instance.getLastOpenedCode());
  }


  /**
   * Test of closeList method, of class StrBuffer.
   */
  public void testCloseList() {
    System.out.println("closeList");
    StrBuffer instance = new StrBuffer();
    instance.closeList();
    String result = instance.toString();
    assertEquals("", result);
  }

  /**
   * Test of closeList method, of class StrBuffer.
   */
  public void testCloseList2() {
    System.out.println("closeList");
    StrBuffer instance = new StrBuffer();
    instance.startOrderedList();
    instance.closeList();
    String result = instance.toString();
    assertEquals("", result);
  }

  /**
   * Test of closeList method, of class StrBuffer.
   */
  public void testCloseList3() {
    System.out.println("closeList");
    StrBuffer instance = new StrBuffer();
    instance.startOrderedList();
    instance.append("a");
    instance.closeList();
    String result = instance.toString();
    String expected = new String(new char[] {
      StrCode.LIST_ORDERED.getCode(), '0', '0',
      StrCode.ITEM.getCode(), '0', '0',
      StrCode.PARAGRAPH.getCode(), '0', '0',
      StrCode.TEXT.getCode(), '0', '1', 'a',
      StrCode.LIST_END.getCode(), '0', '0'
    });
    assertEquals(expected, result);
  }

  /**
   * Test of closeList method, of class StrBuffer.
   */
  public void testCloseList4() {
    System.out.println("closeList");
    StrBuffer instance = new StrBuffer();
    instance.startOrderedList();
    instance.append("a");
    instance.startItem();
    instance.closeList();
    String result = instance.toString();
    String expected = new String(new char[] {
      StrCode.LIST_ORDERED.getCode(), '0', '0',
      StrCode.ITEM.getCode(), '0', '0',
      StrCode.PARAGRAPH.getCode(), '0', '0',
      StrCode.TEXT.getCode(), '0', '1', 'a',
      StrCode.LIST_END.getCode(), '0', '0'
    });
    assertEquals(expected, result);
  }

  /**
   * Test of mark method, of class StrBuffer.
   */
  public void testMark() {
  }


}
