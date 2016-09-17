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
import java.util.Collection;
import java.util.Map;
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
public class TableTextFormatterTest {

  TableTextFormatter table1;

  public TableTextFormatterTest() {
  }

  @BeforeClass
  public static void setUpClass() {
  }

  @AfterClass
  public static void tearDownClass() {
  }

  @Before
  public void setUp() {
    table1 = new TableTextFormatter(0, 0);
    table1.putValue("date", "6.5");
    table1.putValue("team 1", "The Czech Rep.");
    table1.putValue("team 2", "Russia");
    table1.putValue("result", "3:0");
    table1.putValue("arena", "Moskva");
    table1.newRow();
    table1.putValue("date", "7.5");
    table1.putValue("team 1", "Lotys");
    table1.putValue("team 2", "The Czech Rep.");
    table1.putValue("result", "3:4");
    table1.putValue("arena", "Moskva");
    table1.newRow();
    table1.putValue("date", "9.5");
    table1.putValue("team 1", "Sweden");
    table1.putValue("team 2", "The Czech Rep.");
    table1.putValue("result", "2:4");
    table1.putValue("arena", "Moskva");


  }

  @After
  public void tearDown() {
  }

  /**
   * Test of formatLine method, of class TableTextFormatter.
   */
  @Test
  public void testFormatLine() {
    System.out.println("formatLine");
    char[] buffer = new char[80];
    int offset = 2;
    int length = 70;
    int[] expResults = new int[] {62, 70, 70, 67, 68, 62, 68};
    for (int i = 0; i<expResults.length; i++) {
      Arrays.fill(buffer, ' ');
      int result = table1.formatLine(buffer, offset, length);
      System.out.println(new String(buffer));
      //assertEquals(expResults[i], result);
    }
  }

  /**
   * Test of formatRow method, of class TableTextFormatter.
   */
  @Test
  public void testFormatRow() {
    System.out.println("formatRow");
    char[] buffer = null;
    int offset = 0;
    int length = 0;
    Collection<String> values = null;
    int[] widths = null;
    TableTextFormatter instance = null;
    int expResult = 0;
    int result = instance.formatRow(buffer, offset, length, values, widths);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getRowValues method, of class TableTextFormatter.
   */
  @Test
  public void testGetRowValues() {
    System.out.println("getRowValues");
    TableTextFormatter instance = null;
    Collection<String> expResult = null;
    Collection<String> result = instance.getRowValues();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of isEmpty method, of class TableTextFormatter.
   */
  @Test
  public void testIsEmpty() {
    System.out.println("isEmpty");
    TableTextFormatter instance = null;
    boolean expResult = false;
    boolean result = instance.isEmpty();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getLength method, of class TableTextFormatter.
   */
  @Test
  public void testGetLength() {
    System.out.println("getLength");
    int length = 0;
    TableTextFormatter instance = null;
    int expResult = 0;
    int result = instance.getLength(length);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getWords method, of class TableTextFormatter.
   */
  @Test
  public void testGetWords() {
    System.out.println("getWords");
    int length = 0;
    TableTextFormatter instance = null;
    int expResult = 0;
    int result = instance.getWords(length);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of add method, of class TableTextFormatter.
   */
  @Test
  public void testAdd() {
    System.out.println("add");
    Map<String, String> row = null;
    TableTextFormatter instance = null;
    instance.add(row);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of putValue method, of class TableTextFormatter.
   */
  @Test
  public void testPutValue() {
    System.out.println("putValue");
    String key = "";
    String value = "";
    TableTextFormatter instance = null;
    instance.putValue(key, value);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of newRow method, of class TableTextFormatter.
   */
  @Test
  public void testNewRow() {
    System.out.println("newRow");
    TableTextFormatter instance = null;
    instance.newRow();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  public class TableTextFormatterImpl extends TableTextFormatter {

    public TableTextFormatterImpl() {
      super(0, 0);
    }
  }

}
