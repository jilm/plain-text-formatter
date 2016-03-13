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
import org.junit.Test;

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
   * Test of appendUpperHead method, of class StrBuffer.
   */
  public void testAppendUpperHead() {
    System.out.println("appendUpperHead");
    String head = "";
    StrBuffer instance = new StrBuffer();
    StrBuffer expResult = null;
    StrBuffer result = instance.appendUpperHead(head);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of appendHead method, of class StrBuffer.
   */
  public void testAppendHead_int_String() {
    System.out.println("appendHead");
    int level = 0;
    String head = "";
    StrBuffer instance = new StrBuffer();
    StrBuffer expResult = null;
    StrBuffer result = instance.appendHead(level, head);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getHeadLevel method, of class StrBuffer.
   */
  public void testGetHeadLevel() {
    System.out.println("getHeadLevel");
    StrBuffer instance = new StrBuffer();
    int expResult = 0;
    int result = instance.getHeadLevel();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of append method, of class StrBuffer.
   */
  public void testAppend_String() {
    System.out.println("append");
    String text = "";
    StrBuffer instance = new StrBuffer();
    StrBuffer expResult = null;
    StrBuffer result = instance.append(text);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of startParagraph method, of class StrBuffer.
   */
  public void testStartParagraph_0args() {
    System.out.println("startParagraph");
    StrBuffer instance = new StrBuffer();
    StrBuffer expResult = null;
    StrBuffer result = instance.startParagraph();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of startParagraph method, of class StrBuffer.
   */
  public void testStartParagraph_String() {
    System.out.println("startParagraph");
    String text = "";
    StrBuffer instance = new StrBuffer();
    StrBuffer expResult = null;
    StrBuffer result = instance.startParagraph(text);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of startOrderedList method, of class StrBuffer.
   */
  public void testStartOrderedList() {
    System.out.println("startOrderedList");
    StrBuffer instance = new StrBuffer();
    StrBuffer expResult = null;
    StrBuffer result = instance.startOrderedList();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of startUnorderedList method, of class StrBuffer.
   */
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
   * Test of startItem method, of class StrBuffer.
   */
  public void testStartItem_0args() {
    System.out.println("startItem");
    StrBuffer instance = new StrBuffer();
    StrBuffer expResult = null;
    StrBuffer result = instance.startItem();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of startItem method, of class StrBuffer.
   */
  public void testStartItem_String() {
    System.out.println("startItem");
    String key = "";
    StrBuffer instance = new StrBuffer();
    StrBuffer expResult = null;
    StrBuffer result = instance.startItem(key);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of toString method, of class StrBuffer.
   */
  public void testToString() {
    System.out.println("toString");
    StrBuffer instance = new StrBuffer();
    String expResult = "";
    String result = instance.toString();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of append method, of class StrBuffer.
   */
  public void testAppend_StrCode() {
    System.out.println("append");
    StrCode code = null;
    StrBuffer instance = new StrBuffer();
    instance.append(code);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of append method, of class StrBuffer.
   */
  public void testAppend_StrCode_String() {
    System.out.println("append");
    StrCode code = null;
    String text = "";
    StrBuffer instance = new StrBuffer();
    instance.append(code, text);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of appendAsAppendix method, of class StrBuffer.
   */
  public void testAppendAsAppendix() {
    System.out.println("appendAsAppendix");
    StrIterator iterator = null;
    StrBuffer instance = new StrBuffer();
    StrBuffer expResult = null;
    StrBuffer result = instance.appendAsAppendix(iterator);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of appendInBrackets method, of class StrBuffer.
   */
  public void testAppendInBrackets() {
    System.out.println("appendInBrackets");
    String text = "";
    StrBuffer instance = new StrBuffer();
    StrBuffer expResult = null;
    StrBuffer result = instance.appendInBrackets(text);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
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
   * Test of writePendingCodes method, of class StrBuffer.
   */
  public void testWritePendingCodes() {
    System.out.println("writePendingCodes");
    StrBuffer instance = new StrBuffer();
    assertEquals("", instance.toString());
    instance.startParagraph();
    assertEquals("", instance.toString());
    instance.writePendingCodes();
    String expected = new String(new char[] {StrCode.PARAGRAPH.getCode(), '0', '0'});
    assertEquals(expected, instance.toString());
  }

  /**
   * Test of endList method, of class StrBuffer.
   */
  public void testEndList() {
    System.out.println("endList");
    StrBuffer instance = new StrBuffer();
    StrBuffer expResult = null;
    StrBuffer result = instance.closeList();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of appendAtTheEnd method, of class StrBuffer.
   */
  public void testAppendAtTheEnd_StrCode() {
    System.out.println("appendAtTheEnd");
    StrCode code = null;
    StrBuffer instance = new StrBuffer();
    instance.appendAtTheEnd(code);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of appendAtTheEnd method, of class StrBuffer.
   */
  public void testAppendAtTheEnd_StrCode_String() {
    System.out.println("appendAtTheEnd");
    StrCode code = null;
    String text = "";
    StrBuffer instance = new StrBuffer();
    instance.appendAtTheEnd(code, text);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
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
   * Test of closeItem method, of class StrBuffer.
   */
  public void testCloseItem() {
    System.out.println("closeItem");
    StrBuffer instance = new StrBuffer();
    instance.closeItem();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
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
   * Test of close method, of class StrBuffer.
   */
  public void testClose() {
    System.out.println("close");
    int level = 0;
    StrBuffer instance = new StrBuffer();
    instance.close(level);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
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
   * Test of isOpened method, of class StrBuffer.
   */
  public void testIsOpened() {
    System.out.println("isOpened");
    StrBuffer instance = new StrBuffer();
    boolean expResult = false;
    boolean result = instance.isOpened();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of closeLast method, of class StrBuffer.
   */
  public void testCloseLast() {
    System.out.println("closeLast");
    StrBuffer instance = new StrBuffer();
    instance.closeLast();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
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
   * Test of reserveMark method, of class StrBuffer.
   */
  public void testReserveMark() {
    System.out.println("reserveMark");
    StrBuffer instance = new StrBuffer();
    int expResult = 0;
    int result = instance.reserveMark();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of mark method, of class StrBuffer.
   */
  public void testMark() {
  }

  /**
   * Test of reference method, of class StrBuffer.
   */
  public void testReference() {
    System.out.println("reference");
    int mark = 0;
    StrBuffer instance = new StrBuffer();
    StrBuffer expResult = null;
    StrBuffer result = instance.reference(mark);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of closeHead method, of class StrBuffer.
   */
  @Test
  public void testCloseHead() {
    System.out.println("closeHead");
    StrBuffer instance = new StrBuffer();
    StrBuffer expResult = null;
    StrBuffer result = instance.closeHead();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

}
