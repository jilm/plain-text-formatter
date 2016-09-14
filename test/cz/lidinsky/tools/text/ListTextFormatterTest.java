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

import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author jilm
 */
public class ListTextFormatterTest {

  ListTextFormatter list1;

  public ListTextFormatterTest() {
  }

  @BeforeClass
  public static void setUpClass() {
  }

  @AfterClass
  public static void tearDownClass() {
  }

  @Before
  public void setUp() {
    list1 = new ListTextFormatter(1, false);
    ItemTextFormatter item = new ItemTextFormatter();
    ParagraphTextFormatter par = new ParagraphTextFormatter(false, 0);
    par.add(new TextFormatter("This article provides insufficient context for those unfamiliar with the subject. (June 2015)", 0, 0));
    item.add(par);
    list1.add(item);
    par = new ParagraphTextFormatter(false, 0);
    par.add(new TextFormatter("This article does not cite any sources. (June 2015)", 0, 0));
    item = new ItemTextFormatter();
    item.add(par);
    list1.add(item);
  }

  @After
  public void tearDown() {
  }

  /**
   * Test of formatLine method, of class ListTextFormatter.
   */
  @Test
  public void testFormatLine() {
    System.out.println("formatLine");
    char[] buffer = new char[80];
    Arrays.fill(buffer, ' ');
    int offset = 2;
    int length = 70;
    Arrays.fill(buffer, offset, length + offset, '-');
    System.out.println(new String(buffer));
    int[] expResults = new int[] {62, 70, 70, 67, 68, 62, 68};
    for (int i = 0; i<expResults.length; i++) {
      Arrays.fill(buffer, ' ');
      int result = list1.formatLine(buffer, offset, length);
      System.out.println(new String(buffer));
      //assertEquals(expResults[i], result);
    }
  }

  /**
   * Test of getPrefix method, of class ListTextFormatter.
   */
  @Test
  public void testGetPrefix() {
    System.out.println("getPrefix");
    char[] buffer = null;
    int offset = 0;
    int length = 0;
    ListTextFormatter instance = null;
    int expResult = 0;
    int result = instance.getPrefix(buffer, offset, length);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getBullet method, of class ListTextFormatter.
   */
  @Test
  public void testGetBullet() {
    System.out.println("getBullet");
    ListTextFormatter instance = null;
    char expResult = ' ';
    char result = instance.getBullet();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getNumber method, of class ListTextFormatter.
   */
  @Test
  public void testGetNumber() {
    System.out.println("getNumber");
    char[] buffer = null;
    int offset = 0;
    int length = 0;
    ListTextFormatter instance = null;
    int expResult = 0;
    int result = instance.getNumber(buffer, offset, length);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getChild method, of class ListTextFormatter.
   */
  @Test
  public void testGetChild() {
    System.out.println("getChild");
    ListTextFormatter instance = null;
    AbstractTextFormatter expResult = null;
    AbstractTextFormatter result = instance.getChild();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of isEmpty method, of class ListTextFormatter.
   */
  @Test
  public void testIsEmpty() {
    System.out.println("isEmpty");
    ListTextFormatter instance = null;
    boolean expResult = false;
    boolean result = instance.isEmpty();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getLength method, of class ListTextFormatter.
   */
  @Test
  public void testGetLength() {
    System.out.println("getLength");
    int length = 0;
    ListTextFormatter instance = null;
    int expResult = 0;
    int result = instance.getLength(length);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getWords method, of class ListTextFormatter.
   */
  @Test
  public void testGetWords() {
    System.out.println("getWords");
    int length = 0;
    ListTextFormatter instance = null;
    int expResult = 0;
    int result = instance.getWords(length);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

}
