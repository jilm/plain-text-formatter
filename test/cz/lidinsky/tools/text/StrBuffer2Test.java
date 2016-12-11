/*
 * Copyright (C) 2016 jilm
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package cz.lidinsky.tools.text;

import java.util.Collection;
import java.util.Iterator;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author jilm
 */
public class StrBuffer2Test {

  StrBuffer2 tableBuffer = new StrBuffer2(StrCode.TABLE);
  StrBuffer2 emptyTextBuffer = new StrBuffer2(StrCode.TEXT);
  StrBuffer2 textBuffer = new StrBuffer2(StrCode.TEXT, "abcd");
  StrBuffer2 nestedBuffer = tableBuffer.appendChild(textBuffer);

  public StrBuffer2Test() {
  }

  @BeforeClass
  public static void setUpClass() {
  }

  @AfterClass
  public static void tearDownClass() {
  }

  @Before
  public void setUp() {
  }

  @After
  public void tearDown() {
  }

  /**
   * Test of getCode method, of class StrBuffer2.
   */
  @Test
  public void testGetCode() {
    System.out.println("getCode");
    StrBuffer2 instance = new StrBuffer2(StrCode.TABLE);
    StrCode expResult = StrCode.TABLE;
    StrCode result = instance.getCode();
    assertEquals(expResult, result);
    instance = new StrBuffer2(StrCode.TEXT);
    expResult = StrCode.TEXT;
    result = instance.getCode();
    assertEquals(expResult, result);
  }

  /**
   * Test of getText method, of class StrBuffer2.
   */
  @Test
  public void testGetText() {
    System.out.println("getText");
//    StrBuffer2 instance = null;
//    String expResult = "";
//    String result = instance.getText();
    assertEquals("abcd", textBuffer.getText());
  }

  /**
   * Test of getRoot method, of class StrBuffer2.
   */
  @Test
  public void testGetRoot() {
    System.out.println("getRoot");
    StrBuffer2 instance = null;
    StrBuffer2 expResult = null;
    StrBuffer2 result = instance.getRoot();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getParent method, of class StrBuffer2.
   */
  @Test
  public void testGetParent() {
    System.out.println("getParent");
    StrBuffer2 instance = null;
    StrBuffer2 expResult = null;
    StrBuffer2 result = instance.getParent();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of isLeaf method, of class StrBuffer2.
   */
  @Test
  public void testIsLeaf() {
    System.out.println("isLeaf");
//    StrBuffer2 instance = null;
//    boolean expResult = false;
//    boolean result = instance.isLeaf();
    assertEquals(true, textBuffer.isLeaf());
    assertEquals(true, tableBuffer.isLeaf());
    assertEquals(false, nestedBuffer.isLeaf());
  }

  /**
   * Test of iterator method, of class StrBuffer2.
   */
  @Test
  public void testIterator() {
    System.out.println("iterator");
    StrBuffer2 instance = null;
    Iterator<StrBuffer2> expResult = null;
    Iterator<StrBuffer2> result = instance.iterator();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getChildren method, of class StrBuffer2.
   */
  @Test
  public void testGetChildren() {
    System.out.println("getChildren");
    Collection<StrBuffer2> result = tableBuffer.getChildren();
    assertEquals(0, result.size());
    result = nestedBuffer.getChildren();
    assertEquals(1, result.size());
  }

  /**
   * Test of appendChild method, of class StrBuffer2.
   */
  @Test
  public void testAppendChild_StrBuffer2() {
    System.out.println("appendChild");
    StrBuffer2 buffer = tableBuffer.appendChild(textBuffer);
    StringBuilder expResult = new StringBuilder();
    expResult.append(StrCode.TABLE.getCode())
            .append(textBuffer.getBuffer())
            .append(StrCode.END.getCode());
    assertEquals(expResult.toString(), buffer.getBuffer());
  }

  /**
   * Test of appendChild method, of class StrBuffer2.
   */
  @Test
  public void testAppendChild_StrCode() {
    System.out.println("appendChild");
    StrCode code = null;
    StrBuffer2 instance = null;
    StrBuffer2 expResult = null;
    StrBuffer2 result = instance.appendChild(code);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of appendChild method, of class StrBuffer2.
   */
  @Test
  public void testAppendChild_StrCode_String() {
    System.out.println("appendChild");
    StrCode code = null;
    String text = "";
    StrBuffer2 instance = null;
    StrBuffer2 expResult = null;
    StrBuffer2 result = instance.appendChild(code, text);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of equals method, of class StrBuffer2.
   */
  @Test
  public void testEquals() {
    System.out.println("equals");
    Object object1 = new StrBuffer2(StrCode.TEXT, "asdf");
    Object object2 = new StrBuffer2(StrCode.TEXT, "asdf");
    Object object3 = new StrBuffer2(StrCode.TEXT, "ab");
    Object object4 = new StrBuffer2(StrCode.TABLE);
    Object object5 = new StrBuffer2(StrCode.TITLE, "asdf");
    assertTrue(object1.equals(object2));
    assertFalse(object1.equals(object3));
    assertFalse(object1.equals(object4));
    assertFalse(object1.equals(object5));
  }

  /**
   * Test of hashCode method, of class StrBuffer2.
   */
  @Test
  @Ignore
  public void testHashCode() {
    System.out.println("hashCode");
    StrBuffer2 instance = null;
    int expResult = 0;
    int result = instance.hashCode();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of next method, of class StrBuffer2.
   */
  @Test
  public void testNext() {
    System.out.println("next");
    int pointer = 0;
    StrBuffer2 instance = null;
    int expResult = 0;
    int result = instance.next(pointer);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

}
