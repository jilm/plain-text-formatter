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

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author jilm
 */
public class ListFormatterTest {

  public ListFormatterTest() {
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
   * Test of formatLine method, of class ListFormatter.
   */
  @Test
  public void testFormatLine() {
    System.out.println("formatLine");
    char[] buffer = null;
    int offset = 0;
    int length = 0;
    ListFormatter instance = null;
    boolean expResult = false;
    boolean result = instance.formatLine(buffer, offset, length);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getBullet method, of class ListFormatter.
   */
  @Test
  public void testGetBullet_int() {
    System.out.println("getBullet");
    int level = 0;
    char expResult = ' ';
    char result = ListFormatter.getBullet(level);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getBullet method, of class ListFormatter.
   */
  @Test
  public void testGetBullet_4args() {
    System.out.println("getBullet");
    int level = 0;
    char[] buffer = null;
    int offset = 0;
    int length = 0;
    ListFormatter.getBullet(level, buffer, offset, length);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getOrderNumber method, of class ListFormatter.
   */
  @Test
  public void testGetOrderNumber_int_int() {
    System.out.println("getOrderNumber");
    int level = 0;
    int order = 0;
    String expResult = "";
    String result = ListFormatter.getOrderNumber(level, order);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getOrderNumber method, of class ListFormatter.
   */
  @Test
  public void testGetOrderNumber_5args() {
    System.out.println("getOrderNumber");
    int level = 0;
    int order = 0;
    char[] buffer = null;
    int offset = 0;
    int length = 0;
    ListFormatter.getOrderNumber(level, order, buffer, offset, length);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getNumericOrderNumber method, of class ListFormatter.
   */
  @Test
  public void testGetNumericOrderNumber() {
    System.out.println("getNumericOrderNumber");
    int order = 0;
    String expResult = "";
    String result = ListFormatter.getNumericOrderNumber(order);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of intToLowercase method, of class ListFormatter.
   */
  @Test
  public void testIntToLowercase() {
    System.out.println("intToLowercase");
    assertEquals("a", ListFormatter.intToLowercase(0));
    assertEquals("b", ListFormatter.intToLowercase(1));
    assertEquals("z", ListFormatter.intToLowercase(25));
    assertEquals("ba", ListFormatter.intToLowercase(26));

  }

}
