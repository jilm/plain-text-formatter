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
public class ItemTextFormatterTest {

  ItemTextFormatter formatter1;

  public ItemTextFormatterTest() {
  }

  @BeforeClass
  public static void setUpClass() {
  }

  @AfterClass
  public static void tearDownClass() {
  }

  @Before
  public void setUp() {
    formatter1 = new ItemTextFormatter();
    ParagraphTextFormatter par = new ParagraphTextFormatter(true, 0);
    par.add(new TextFormatter("There are 84 kilometres of railway in Sierra Leone, all of which is private and of a narrow gauge, 1,067 mm (3 ft 6 in). Sierra Leone no longer has any common carrier railroads, as the 762 mm (2 ft 6 in) gauge Sierra Leone Government Railway from Freetown through Bo to Kenema and Daru with a branch to Makeni closed in 1974. The country does not share rail links with adjacent countries, Guinea and Liberia.", 0, 0));
    formatter1.add(par);
    par = new ParagraphTextFormatter(true, 0);
    par.add(new TextFormatter("The existing railway between the port of Pepel and the Marampa iron ore mine is being refurbished by African Minerals plc. This is a common carrier railway, but will be used predominantly for transporting iron ore. African Minerals is also constructing a new standard gauge railway from the Tonkolili iron ore mine to a new port at Tagrin Point.", 0, 0));
    formatter1.add(par);

  }

  @After
  public void tearDown() {
  }

  /**
   * Test of formatLine method, of class ItemTextFormatter.
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
    int[] expResults = new int[] {62, 70, 70, 67, 68, 62, 68, 10, 10, 10, 10, 10};
    for (int i = 0; i<expResults.length; i++) {
      Arrays.fill(buffer, ' ');
      int result = formatter1.formatLine(buffer, offset, length);
      System.out.println(new String(buffer));
      //assertEquals(expResults[i], result);
    }
  }

  /**
   * Test of getPrefix method, of class ItemTextFormatter.
   */
  @Test
  public void testGetPrefix() {
    System.out.println("getPrefix");
    char[] buffer = null;
    int offset = 0;
    int length = 0;
    ItemTextFormatter instance = new ItemTextFormatter();
    int expResult = 0;
    int result = instance.getPrefix(buffer, offset, length);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getChild method, of class ItemTextFormatter.
   */
  @Test
  public void testGetChild() {
    System.out.println("getChild");
    ItemTextFormatter instance = new ItemTextFormatter();
    AbstractTextFormatter expResult = null;
    AbstractTextFormatter result = instance.getChild();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of isEmpty method, of class ItemTextFormatter.
   */
  @Test
  public void testIsEmpty() {
    System.out.println("isEmpty");
    ItemTextFormatter instance = new ItemTextFormatter();
    boolean expResult = false;
    boolean result = instance.isEmpty();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getLength method, of class ItemTextFormatter.
   */
  @Test
  public void testGetLength() {
    System.out.println("getLength");
    int length = 0;
    ItemTextFormatter instance = new ItemTextFormatter();
    int expResult = 0;
    int result = instance.getLength(length);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getWords method, of class ItemTextFormatter.
   */
  @Test
  public void testGetWords() {
    System.out.println("getWords");
    int length = 0;
    ItemTextFormatter instance = new ItemTextFormatter();
    int expResult = 0;
    int result = instance.getWords(length);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of add method, of class ItemTextFormatter.
   */
  @Test
  public void testAdd() {
    System.out.println("add");
    AbstractTextFormatter text = null;
    ItemTextFormatter instance = new ItemTextFormatter();
    instance.add(text);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

}
